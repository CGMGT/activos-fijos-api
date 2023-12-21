package gt.com.tigo.fixed_assets.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gt.com.tigo.fixed_assets.dao.portal.UserRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.AdmUsuarioEntity;
import gt.com.tigo.fixed_assets.util.Utils;
import gt.com.tigo.fixed_assets.util.exception.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

import static gt.com.tigo.fixed_assets.util.Utils.*;

@Service
public class UserService implements ICatalog<AdmUsuarioEntity> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public static final String NO_SE_HA_PODIDO_OBTENER_UNA_RESPUESTA_DEL_SERVIDOR = "No se ha podido obtener una respuesta del servidor.";
    public static final String SE_HA_OBTENIDO_UNA_RESPUESTA_INVALIDA_DEL_SERVIDOR = "Se ha obtenido una respuesta inválida del servidor.";
    public static final String USUARIO = "usuario";
    public static final String NOMBRES = "nombres";
    public static final String APELLIDOS = "apellidos";
    public static final String CORREO_ELECTRONICO = "correoElectronico";
    public static final String PUESTO = "puesto";
    public static final String NOMBRE_MOSTRADO = "nombreMostrado";
    public static final String DISPLAY_NAME = "displayName";
    public static final String BUSINESS_PHONES = "businessPhones";
    public static final String OFFICE_LOCATION = "officeLocation";
    public static final String PREFERRED_LANGUAGE = "preferredLanguage";
    public static final String JOB_TITLE = "jobTitle";
    public static final String MOBILE_PHONE = "mobilePhone";
    public static final String SURNAME = "surname";
    public static final String MAIL = "mail";
    public static final String GIVEN_NAME = "givenName";
    public static final String ID = "id";

    @Autowired
    private UserRepository userRepo;

    /**
     * Returns all resources' data.
     * @param token Azure AD token to access the Azure AD services.
     * @return a map containing all resources' data.
     * @throws Exception when an error occurs.
     */
    public Map<String, Object> getInfo(String token) throws SearchIdentityException {
        logger.debug(String.format("@%s::getInfo(%s)", this.getClass().getName(), token));

        HttpClient client = HttpClients.custom().build();

        HttpUriRequest request = RequestBuilder.get()
                .setUri("https://graph.microsoft.com/v1.0/me")
                .setHeader(HttpHeaders.AUTHORIZATION, token)
                .build();

        HttpResponse response;

        try {
            response = client.execute(request);
        } catch (IOException ex) {
            logger.error(ex.getMessage());

            throw new SearchIdentityException(NO_SE_HA_PODIDO_OBTENER_UNA_RESPUESTA_DEL_SERVIDOR);
        }

        if (response.getStatusLine().getStatusCode() == 200) {
            String body;

            try {
                body = EntityUtils.toString(response.getEntity());
            } catch (IOException | ParseException ex) {
                logger.error(ex.getMessage());

                throw new SearchIdentityException(SE_HA_OBTENIDO_UNA_RESPUESTA_INVALIDA_DEL_SERVIDOR);
            }

            ObjectMapper mapper = new ObjectMapper();

            try {
                return mapper.readValue(body, Map.class);
            } catch (IOException ex) {
                logger.error(ex.getMessage());

                throw new SearchIdentityException("No se han podido leer los datos del usuario.");
            }
        } else {
            throw new SearchIdentityException(NO_SE_HA_PODIDO_OBTENER_UNA_RESPUESTA_DEL_SERVIDOR);
        }
    }

    /**
     * Returns a string containing the image bytes.
     * @param token Azure AD token to access the Azure AD services.
     * @return a string containing the image bytes in code base 64.
     * @throws Exception when an error occurs.
     */
    public String getPhoto(String token) throws SearchIdentityException {
        logger.debug(String.format("@%s::getPhoto(%s)", this.getClass().getName(), token));

        HttpClient client = HttpClients.custom().build();

        HttpUriRequest request = RequestBuilder.get()
                .setUri("https://graph.microsoft.com/v1.0/me/photo/$value")
                .setHeader(HttpHeaders.AUTHORIZATION, token)
                .build();

        HttpResponse response;

        try {
            response = client.execute(request);
        } catch (IOException ex) {
            logger.error(ex.getMessage());

            throw new SearchIdentityException(NO_SE_HA_PODIDO_OBTENER_UNA_RESPUESTA_DEL_SERVIDOR);
        }

        if (response.getStatusLine().getStatusCode() == 404) {
            throw new SearchIdentityException("El usuario no posee foto.");
        } else {
            String encodedString;

            try {
                InputStream is = response.getEntity().getContent();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                int b;

                while ((b = is.read()) != -1) {
                    bos.write(b);
                }

                is.close();

                bos.flush();

                encodedString = DatatypeConverter.printBase64Binary(bos.toByteArray());

                bos.close();
            } catch (IOException ex) {
                logger.error(ex.getMessage());

                throw new SearchIdentityException("No se han podido obtener los bytes de la foto del usuario.");
            }

            return encodedString;
        }
    }

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<AdmUsuarioEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.userRepo.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }



    /**
     * Returns a list of all resources in this catalog by page and page size.
     * @param dataTableRequestDto page, sort and filter data.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public PaginatedDataDto<AdmUsuarioEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%s)", this.getClass().getName(), dataTableRequestDto.toString()));

        try {
            Map<String, FilterInfoDto> filtersInfo = new HashMap<>();

            // get column names to filter
            if (dataTableRequestDto.getFiltered() != null) {
                for (FilterInfoDto filterInfo : dataTableRequestDto.getFiltered()) {
                    filtersInfo.put(filterInfo.getId(), filterInfo);
                }
            }

            // build filter specifications
            Specification userSpec = Utils.<AdmUsuarioEntity>getLikeSpecification(USUARIO, getValueAsString(filtersInfo, USUARIO));
            Specification firstNameSpec = Utils.<AdmUsuarioEntity>getLikeSpecification(NOMBRES, getValueAsString(filtersInfo, NOMBRES));
            Specification lastNameSpec = Utils.<AdmUsuarioEntity>getLikeSpecification(APELLIDOS, getValueAsString(filtersInfo, APELLIDOS));
            Specification emailSpec = Utils.<AdmUsuarioEntity>getLikeSpecification(CORREO_ELECTRONICO, getValueAsString(filtersInfo, CORREO_ELECTRONICO));
            Specification positionSpec = Utils.<AdmUsuarioEntity>getLikeSpecification(PUESTO, getValueAsString(filtersInfo, PUESTO));
            Specification displayNameSpec = Utils.<AdmUsuarioEntity>getLikeSpecification(NOMBRE_MOSTRADO, getValueAsString(filtersInfo, NOMBRE_MOSTRADO));

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(userSpec)
                    .and(firstNameSpec)
                    .and(lastNameSpec)
                    .and(emailSpec)
                    .and(positionSpec)
                    .and(displayNameSpec);

            Page<AdmUsuarioEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.userRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.ASC, NOMBRES));
            } else { // else use the requested order
                pageData = this.userRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    /**
     * Returns a resource by id.
     * @param id The id of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    @Override
    public AdmUsuarioEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        AdmUsuarioEntity user = this.userRepo.findOne(id);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    /**
     * Returns a resource by uid.
     * @param token Azure AD token to access the Azure AD services.
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public AdmUsuarioEntity findByUid(String token) throws ResourceNotFoundException, ResourceCreateException, ResourceUpdateException {
        logger.debug(String.format("@%s::findByUid(********)", this.getClass().getName()));

        Map<String, Object> userInfo = null;
        try {
            userInfo = this.getInfo(token);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }

        if (userInfo == null || userInfo.size() <= 0 || !userInfo.containsKey(ID)) {
            throw new ResourceNotFoundException();
        }

        String foto = null;

        try {
            foto = this.getPhoto(token);
        } catch (Exception ex) {
            logger.error("Couldn't get user photo.");
        }

        AdmUsuarioEntity user = this.userRepo.findByCorreoElectronico(userInfo.get(MAIL).toString().toLowerCase());
        // if user does not exist then create
        if (user == null) {
            throw new ResourceNotFoundException();
        } else {
            try {
                user.setUuid(userInfo.containsKey(ID) ? userInfo.get(ID).toString() : null);
                user.setUsuario(userInfo.containsKey(MAIL) ? userInfo.get(MAIL) == null ? null : userInfo.get(MAIL).toString() : null);
                user.setNombres(userInfo.containsKey(GIVEN_NAME) ? userInfo.get(GIVEN_NAME) == null ? null : userInfo.get(GIVEN_NAME).toString() : null);
                user.setApellidos(userInfo.containsKey(SURNAME) ? userInfo.get(SURNAME) == null ? null : userInfo.get(SURNAME).toString() : null);
                user.setCorreoElectronico(userInfo.containsKey(MAIL) ? userInfo.get(MAIL) == null ? null : userInfo.get(MAIL).toString().toLowerCase() : null);
                user.setNombreMostrado(userInfo.containsKey(DISPLAY_NAME) ? userInfo.get(DISPLAY_NAME) == null ? null : userInfo.get(DISPLAY_NAME).toString() : null);
                user.setPuesto(userInfo.containsKey(JOB_TITLE) ? userInfo.get(JOB_TITLE) == null ? null : userInfo.get(JOB_TITLE).toString() : null);
                user.setTelefonoCelular(userInfo.containsKey(MOBILE_PHONE) ? userInfo.get(MOBILE_PHONE) == null ? null : userInfo.get(MOBILE_PHONE).toString() : null);
                user.setTelefonoOficina(userInfo.containsKey(BUSINESS_PHONES) ? userInfo.get(BUSINESS_PHONES) == null ? null : userInfo.get(BUSINESS_PHONES).toString() : null);
                user.setUbicacion(userInfo.containsKey(OFFICE_LOCATION) ? userInfo.get(OFFICE_LOCATION) == null ? null : userInfo.get(OFFICE_LOCATION).toString() : null);
                user.setIdiomaPreferido(userInfo.containsKey(PREFERRED_LANGUAGE) ? userInfo.get(PREFERRED_LANGUAGE) == null ? null : userInfo.get(PREFERRED_LANGUAGE).toString() : null);
                user.setFoto(foto);
                user.setFechaModificacion(new Timestamp(new Date().getTime()));
                user.setUsuarioModificacion("PROCESO");
                user.setToken(createJWT("Tigo", "FixedAssets", "JWT Token", 86400)); // 24 hours

                user = this.userRepo.save(user);
            } catch (Exception ex) {
                throw new ResourceUpdateException();
            }
        }

        return user;
    }

    private String createJWT(String id, String issuer, String subject, long ttlSec) {

        Long now = System.currentTimeMillis();

        String token = Jwts.builder()
                .setSubject(subject)
                // Convert to list of strings.
                // This is important because it affects the way we get them back in the Gateway.
                .setIssuer(issuer)
                .setId(id)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ttlSec * 1000)) // in milliseconds
                .signWith(SignatureAlgorithm.HS512, "7!G0".getBytes())
                .compact();

        return "Bearer " + token;
    }

    /**
     * Returns a resource by email.
     * @param email The email of the requested resource
     * @return an object containing the found resource.
     * @throws ResourceNotFoundException when no record is found.
     */
    public AdmUsuarioEntity findByEmail(String email) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findByEmail(%s)", this.getClass().getName(), email));

        AdmUsuarioEntity user = this.userRepo.findByCorreoElectronico(email);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        return user;
    }

    /**
     * Saves a new resource.
     * @param entity an object containing the new resource's data
     * @param requesterId the requester user id.
     * @return an object containing the new resource.
     * @throws RequesterNotFoundException when the requester is not found.
     * @throws ResourceCreateException when the resource could not be saved.
     */
    @Override
    public AdmUsuarioEntity create(AdmUsuarioEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::create(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioCreacion(requester.getUsuario());
        entity.setFechaCreacion(new Timestamp(new Date().getTime()));

        entity.setUsuario(entity.getCorreoElectronico().toLowerCase().split("@")[0]);
        entity.setCorreoElectronico(entity.getCorreoElectronico().toLowerCase());
        entity.setNombreMostrado(String.format("%s %s", entity.getNombres(), entity.getApellidos()));
        entity.setPuesto("-");
        entity.setEstado("I");

        try {
            return this.userRepo.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceCreateException();
        }
    }

    /**
     * Updates an existing resource.
     * @param entity an object containing the updated resource's data.
     * @param requesterId the requester user id.
     * @return an object containing the updated resource.
     * @throws RequesterNotFoundException when requester is not found.
     * @throws ResourceUpdateException when the resource could not be updated.
     */
    @Override
    public AdmUsuarioEntity update(AdmUsuarioEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::update(%s, %d)", this.getClass().getName(), entity, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        entity.setUsuarioModificacion(requester.getUsuario());
        entity.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.userRepo.save(entity);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

    /**
     * Deletes an existing resource.
     * @param entityId the id of the resource to delete
     * @param requesterId the requester user id.
     * @return an object containing the deleted resrouce.
     * @throws RequesterNotFoundException when requester is not found.
     * @throws ResourceNotFoundException when no record is found.
     * @throws ResourceUpdateException when the resource could not be updated.
     */
    @Override
    public AdmUsuarioEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        logger.debug(String.format("@%s::delete(%d, %d)", this.getClass().getName(), entityId, requesterId));

        AdmUsuarioEntity requester = this.userRepo.findOne(requesterId);

        if (requester == null) {
            throw new RequesterNotFoundException();
        }

        AdmUsuarioEntity user = this.userRepo.findOne(entityId);

        if (user == null) {
            throw new ResourceNotFoundException();
        }

        user.setEstado("I");
        user.setUsuarioModificacion(requester.getUsuario());
        user.setFechaModificacion(new Timestamp(new Date().getTime()));

        try {
            return this.userRepo.save(user);
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourceUpdateException();
        }
    }

}
