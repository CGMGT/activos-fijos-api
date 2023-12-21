package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;

@Entity
@Table(name = "TEST", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_TEST", sequenceName = "SEQ_TEST", initialValue=1, allocationSize=1)
public class TestEntity {
    private long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEST")
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
