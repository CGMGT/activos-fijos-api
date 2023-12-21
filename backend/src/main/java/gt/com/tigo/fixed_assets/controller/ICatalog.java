package gt.com.tigo.fixed_assets.controller;

import gt.com.tigo.fixed_assets.dto.DataTableRequestDto;
import org.springframework.http.ResponseEntity;

public interface ICatalog<T> {

    ResponseEntity findAll();

    ResponseEntity findAllByPage(DataTableRequestDto dataTableRequestDto);

    ResponseEntity findById(Long id);

    ResponseEntity create(T entity, Long requesterId);

    ResponseEntity update(T entity, Long requesterId);

    ResponseEntity delete(Long entityId, Long requesterId);

}
