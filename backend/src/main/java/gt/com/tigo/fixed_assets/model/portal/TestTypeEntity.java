package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;

@Entity
@Table(name = "TEST_TYPE", schema = "", catalog = "")
public class TestTypeEntity {

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private long id;

    @Basic
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 1000)
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
