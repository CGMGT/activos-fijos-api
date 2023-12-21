package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;

@Entity
@Table(name = "TEST_DETAIL", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_TEST_DETAIL", sequenceName = "SEQ_TEST_DETAIL", initialValue=1, allocationSize=1)
public class TestDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEST_DETAIL")
    @Column(name = "ID_DETAIL", nullable = false, precision = 0)
    private long idDetail;

    @Basic
    @Column(name = "VALUE", nullable = false, length = 50)
    private String value;

    public long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(long idDetail) {
        this.idDetail = idDetail;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
