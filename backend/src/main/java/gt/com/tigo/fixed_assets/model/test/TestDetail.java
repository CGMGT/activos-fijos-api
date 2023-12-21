package gt.com.tigo.fixed_assets.model.test;

import javax.persistence.*;

@Entity
@Table(name = "TEST_DETAIL")
public class TestDetail {
    @Id
    @Column(name = "ID_DETAIL")
    private Long idDetail;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "MASTER")
    private Long master;


    public Long getIdDetail() {
        return this.idDetail;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getMaster() {
        return this.master;
    }

    public void setMaster(Long master) {
        this.master = master;
    }
}
