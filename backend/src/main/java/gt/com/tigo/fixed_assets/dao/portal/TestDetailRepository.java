package gt.com.tigo.fixed_assets.dao.portal;

import gt.com.tigo.fixed_assets.model.portal.TestDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestDetailRepository extends JpaRepository<TestDetailEntity, Long> {
}
