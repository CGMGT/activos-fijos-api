package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TEST_MASTER", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_TEST_MASTER", sequenceName = "SEQ_TEST_MASTER", initialValue=1, allocationSize=1)
public class TestMasterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEST_MASTER")
    @Column(name = "ID_MASTER", nullable = false, precision = 0)
    private long idMaster;

    @Basic
    @Column(name = "VALUE", nullable = false, length = 50)
    private String value;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MASTER", referencedColumnName = "ID_MASTER")
    private List<TestDetailEntity> details;

    @JoinColumn(name = "TYPE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private TestTypeEntity type;

    public long getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(long idMaster) {
        this.idMaster = idMaster;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<TestDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<TestDetailEntity> details) {
        this.details = details;
    }

    public TestTypeEntity getType() {
        return type;
    }

    public void setType(TestTypeEntity type) {
        this.type = type;
    }
}
