package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "XX_INT_LOCATIONS", schema = "", catalog = "")
public class XxIntLocationsEntity {
    private long locationId;
    private String nombre;
    private String origSystemReference;
    private String country;
    private String address1;
    private String address2;
    private String address3;
    private String address4;
    private String city;
    private String postalCode;
    private String state;
    private String province;
    private String county;
    private String addressKey;
    private String faLocationCode;

    @Basic
    @Id
    @Column(name = "LOCATION_ID", nullable = false, precision = 0)
    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 240)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ORIG_SYSTEM_REFERENCE", nullable = false, length = 240)
    public String getOrigSystemReference() {
        return origSystemReference;
    }

    public void setOrigSystemReference(String origSystemReference) {
        this.origSystemReference = origSystemReference;
    }

    @Basic
    @Column(name = "COUNTRY", nullable = false, length = 60)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "ADDRESS1", nullable = false, length = 240)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "ADDRESS2", nullable = true, length = 240)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "ADDRESS3", nullable = true, length = 240)
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "ADDRESS4", nullable = true, length = 240)
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @Basic
    @Column(name = "CITY", nullable = true, length = 60)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "POSTAL_CODE", nullable = true, length = 60)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 60)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PROVINCE", nullable = true, length = 60)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "COUNTY", nullable = true, length = 60)
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Basic
    @Column(name = "ADDRESS_KEY", nullable = true, length = 500)
    public String getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(String addressKey) {
        this.addressKey = addressKey;
    }

    @Basic
    @Column(name = "FA_LOCATION_CODE", nullable = true, length = 250)
    public String getFaLocationCode() {
        return faLocationCode;
    }

    public void setFaLocationCode(String faLocationCode) {
        this.faLocationCode = faLocationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XxIntLocationsEntity that = (XxIntLocationsEntity) o;
        return locationId == that.locationId &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(origSystemReference, that.origSystemReference) &&
                Objects.equals(country, that.country) &&
                Objects.equals(address1, that.address1) &&
                Objects.equals(address2, that.address2) &&
                Objects.equals(address3, that.address3) &&
                Objects.equals(address4, that.address4) &&
                Objects.equals(city, that.city) &&
                Objects.equals(postalCode, that.postalCode) &&
                Objects.equals(state, that.state) &&
                Objects.equals(province, that.province) &&
                Objects.equals(county, that.county) &&
                Objects.equals(addressKey, that.addressKey) &&
                Objects.equals(faLocationCode, that.faLocationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, nombre, origSystemReference, country, address1, address2, address3, address4, city, postalCode, state, province, county, addressKey, faLocationCode);
    }
}
