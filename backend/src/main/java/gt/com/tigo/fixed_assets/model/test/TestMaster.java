package gt.com.tigo.fixed_assets.model.test;

import javax.persistence.*;

@Entity
@Table(name = "TEST_MASTER")
public class TestMaster {
    @Id
    @Column(name = "ID_MASTER")
    private Long idMaster;

    @Column(name = "VALUE")
    private String value;


    public Long getIdMaster() {
        return this.idMaster;
    }

    public void setIdMaster(Long idMaster) {
        this.idMaster = idMaster;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
