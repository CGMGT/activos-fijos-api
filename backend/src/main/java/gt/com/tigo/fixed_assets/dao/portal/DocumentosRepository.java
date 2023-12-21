package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.DocumentosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DocumentosRepository extends JpaRepository<DocumentosEntity, Long>, JpaSpecificationExecutor<DocumentosEntity> {
}
