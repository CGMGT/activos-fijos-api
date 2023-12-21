package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.LoggingEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingEventRepository extends JpaRepository<LoggingEventEntity, Integer>, JpaSpecificationExecutor<LoggingEventEntity> {
}
