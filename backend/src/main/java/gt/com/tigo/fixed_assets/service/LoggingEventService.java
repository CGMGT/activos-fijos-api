package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dao.portal.LoggingEventRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.FilterInfoDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.portal.LoggingEventEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static gt.com.tigo.fixed_assets.util.Utils.getDefaultSpecification;
import static gt.com.tigo.fixed_assets.util.Utils.getLikeSpecification;

public class LoggingEventService implements ICatalog<LoggingEventEntity> {

    private static final Logger logger = LoggerFactory.getLogger(LoggingEventService.class);
    public static final String FORMATTED_MESSAGE = "formattedMessage";
    public static final String LEVEL_STRING = "levelString";
    public static final String CALLER_FILENAME = "callerFilename";

    @Autowired
    private LoggingEventRepository eventRepo;

    /**
     * Returns a list of all resources in this catalog.
     * @return a list of objects containing all resources' data.
     * @throws ResourcesNotFoundException when an error occurs.
     */
    @Override
    public List<LoggingEventEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.eventRepo.findAll();
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
    public PaginatedDataDto<LoggingEventEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
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
            Specification formattedMessageSpec = getLikeSpecification(FORMATTED_MESSAGE, filtersInfo.get(FORMATTED_MESSAGE) == null ? null : filtersInfo.get(FORMATTED_MESSAGE).getValue());
            Specification levelStringSpec = getLikeSpecification(LEVEL_STRING, filtersInfo.get(LEVEL_STRING) == null ? null : filtersInfo.get(LEVEL_STRING).getValue());
            Specification callerFilenameSpec = getLikeSpecification(CALLER_FILENAME, filtersInfo.get(CALLER_FILENAME) == null ? null : filtersInfo.get(CALLER_FILENAME).getValue());

            // put filter specifications together
            Specification filterSpec = Specifications
                    .where(getDefaultSpecification())
                    .and(formattedMessageSpec)
                    .and(levelStringSpec)
                    .and(callerFilenameSpec);

            Page<LoggingEventEntity> pageData;

            // if sort was not requested then use the default order
            if (dataTableRequestDto.getSorted() == null || dataTableRequestDto.getSorted().isEmpty()) {
                pageData = this.eventRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Sort.Direction.DESC,"eventId"));
            } else { // else use the requested order
                pageData = this.eventRepo.findAll(filterSpec, new PageRequest(dataTableRequestDto.getPage(), dataTableRequestDto.getPageSize(), Boolean.TRUE.equals(dataTableRequestDto.getSorted().get(0).getDesc()) ? Sort.Direction.DESC : Sort.Direction.ASC, dataTableRequestDto.getSorted().get(0).getId()));
            }

            // return a transfer object containing the rows, total rows and total pages
            return new PaginatedDataDto<>(pageData.getContent(), pageData.getTotalElements(), pageData.getTotalPages());
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public LoggingEventEntity findById(Long id) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public LoggingEventEntity create(LoggingEventEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public LoggingEventEntity update(LoggingEventEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public LoggingEventEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
