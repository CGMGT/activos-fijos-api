package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.EmailBlacklistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmailBlacklistRepository extends JpaRepository<EmailBlacklistEntity, Long>, JpaSpecificationExecutor<EmailBlacklistEntity> {
}
