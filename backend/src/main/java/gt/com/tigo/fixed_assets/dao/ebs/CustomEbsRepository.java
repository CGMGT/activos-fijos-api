package gt.com.tigo.fixed_assets.dao.ebs;

import gt.com.tigo.fixed_assets.model.ebs.FixedAssetsEbsEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;

import java.util.List;

@Repository
public class CustomEbsRepository {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long getTotalFaRows(String query){
        return jdbcTemplate.queryForObject(query, long.class);
    }

    public List<FixedAssetsEbsEntity> findFaByPage(String query, long offset, long fetchRows) {

        return jdbcTemplate.query(
                query,
                new Object[]{offset, fetchRows},
                new BeanPropertyRowMapper(FixedAssetsEbsEntity.class));

    }
}
