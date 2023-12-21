package gt.com.tigo.fixed_assets.service;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import gt.com.tigo.fixed_assets.dto.PaginatedDataDto;
import gt.com.tigo.fixed_assets.util.exception.*;

import java.util.List;

public interface ICatalog<T> {

    List<T> findAll() throws ResourcesNotFoundException;

    PaginatedDataDto<T> findAllByPage(DataTableRequestDto dataTableRequestDto) throws ResourcesNotFoundException;

    T findById(Long id) throws ResourceNotFoundException;

    T create(T entity, Long requesterId) throws RequesterNotFoundException, ResourceCreateException;

    T update(T entity, Long requesterId) throws RequesterNotFoundException, ResourceUpdateException;

    T delete(Long entityId, Long requesterId) throws RequesterNotFoundException, ResourceNotFoundException, ResourceUpdateException;

}
