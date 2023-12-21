package gt.com.tigo.fixed_assets.service;


import gt.com.tigo.fixed_assets.dao.ebs.FixedAssetsEbsRepository;

import gt.com.tigo.fixed_assets.dao.portal.XxIntFixedAssetsRepository;
import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.model.ebs.FixedAssetsEbsEntity;
import gt.com.tigo.fixed_assets.model.portal.XxIntFixedAssetsEntity;
import gt.com.tigo.fixed_assets.util.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Service
public class FixedAssetsEbsService implements ICatalog<FixedAssetsEbsEntity>{
    private static final Logger logger = LoggerFactory.getLogger(FixedAssetsEbsService.class);
    @Autowired
    private FixedAssetsEbsRepository faRepo;


    @Autowired
    private XxIntFixedAssetsRepository intFixedAssetsBatchRepository ;





    @Override
    public List<FixedAssetsEbsEntity> findAll() throws ResourcesNotFoundException {
        logger.debug(String.format("@%s::findAll()", this.getClass().getName()));

        try {
            return this.faRepo.findAll();
        } catch (Exception ex) {
            logger.error(ex.getMessage());

            throw new ResourcesNotFoundException();
        }
    }

    @Override
    public PaginatedDataDto<FixedAssetsEbsEntity> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException {
        return null;
    }

    public List<FixedAssetsEbsEntity> findAllByPage(Long offset, Long fetchRows) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findAllByPage(%d,%d)", this.getClass().getName(), offset, fetchRows));

        List<FixedAssetsEbsEntity> entidad = this.faRepo.findAllByPage(offset, fetchRows);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }

    @Override
    public FixedAssetsEbsEntity findById(Long id) throws ResourceNotFoundException {
        logger.debug(String.format("@%s::findById(%d)", this.getClass().getName(), id));

        FixedAssetsEbsEntity entidad = this.faRepo.findById(id);

        if (entidad == null) {
            throw new ResourceNotFoundException();
        }

        return entidad;
    }



    public String sync() throws ResourceNotFoundException, ResourceCreateException {
        logger.debug(String.format("@%s::sync()", this.getClass().getName()));




        long totalRows = this.faRepo.getTotalRows();
        int pageSize = 25000;
        int totalPages = ((int)(totalRows) / pageSize) + 1;
        logger.debug(String.format("@%d::total rows into sync()", totalRows));



        for (int i=0; i < totalPages; i++) {
            List<FixedAssetsEbsEntity> entidad = this.faRepo.findAllByPage((long)i*pageSize,pageSize);

            if (entidad == null) {
                throw new ResourceNotFoundException();
            }
            try{
                int counter = 0;
                int size = entidad.size();
                List<XxIntFixedAssetsEntity> fixedAssets = new ArrayList<>();;
                for (FixedAssetsEbsEntity entity : entidad){
                    XxIntFixedAssetsEntity fixedAsset = new XxIntFixedAssetsEntity();
                    fixedAsset.setAssetNumber(entity.getAssetNumber());
                    fixedAsset.setDescription(entity.getDescription());
                    fixedAsset.setTagNumber(entity.getTagNumber());
                    fixedAsset.setLegacycode(entity.getLegacycode());
                    fixedAsset.setModelNumber(entity.getModelNumber());
                    fixedAsset.setParentAssetId(entity.getParentAssetId());
                    fixedAsset.setAssetKeyCcid(entity.getAssetKeyCcid());
                    fixedAsset.setSerialNumber(entity.getSerialNumber());
                    fixedAsset.setAssetType(entity.getAssetType());
                    fixedAsset.setInventorial(entity.getInventorial());
                    fixedAsset.setCategoryCode(entity.getCategoryCode());
                    fixedAsset.setBookTypeCode(entity.getBookTypeCode());
                    fixedAsset.setDateIn(entity.getDateIn());
                    fixedAsset.setCost(entity.getCost());
                    fixedAsset.setCombination(entity.getCombination());
                    fixedAsset.setExpenseAccount(entity.getExpenseAccount());
                    fixedAsset.setAssetAccount(entity.getAssetAccount());
                    fixedAsset.setEmployeeNumber(entity.getEmployeeNumber());
                    fixedAsset.setEmployeeName(entity.getEmployeeName());
                    fixedAsset.setLocationId(entity.getLocationId());

                    fixedAssets.add(fixedAsset);

                    if ((counter + 1) % 5000 == 0 || (counter + 1) == size) {
                        intFixedAssetsBatchRepository.save(fixedAssets);
                        fixedAssets.clear();
                        logger.debug(String.format("@%d::saving rows to FIXED ASSETS database()", counter));
                    }

                    counter++;
                }

            } catch (Exception ex) {
                logger.error(ex.getMessage());

                throw new ResourceCreateException();
            }
        }
        return null;
    }

    @Override
    public FixedAssetsEbsEntity create(FixedAssetsEbsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException {
        return null;
    }

    @Override
    public FixedAssetsEbsEntity update(FixedAssetsEbsEntity entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException {
        return null;
    }

    @Override
    public FixedAssetsEbsEntity delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException {
        return null;
    }
}
