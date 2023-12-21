package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmailTemplateRepository extends JpaRepository<EmailTemplateEntity, Long>, JpaSpecificationExecutor<EmailTemplateEntity> {
}
