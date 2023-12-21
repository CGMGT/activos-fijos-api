package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CP_LOCATIONS", schema = "", catalog = "")
public class CpLocationsEntity {
    private long id;
    private long idLocacion;
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
    private String estado;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private AdmUsuarioEntity encargado1;
    private AdmUsuarioEntity encargado2;
    private AdmUsuarioEntity encargado3;
    private AdmUsuarioEntity encargado4;
    private AdmUsuarioEntity encargado5;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_LOCACION", nullable = false, precision = 0)
    public long getIdLocacion() {
        return idLocacion;
    }

    public void setIdLocacion(long idLocacion) {
        this.idLocacion = idLocacion;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = true, length = 500)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "ORIG_SYSTEM_REFERENCE", nullable = false, length = 500)
    public String getOrigSystemReference() {
        return origSystemReference;
    }

    public void setOrigSystemReference(String origSystemReference) {
        this.origSystemReference = origSystemReference;
    }

    @Basic
    @Column(name = "COUNTRY", nullable = false, length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "ADDRESS1", nullable = false, length = 500)
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Basic
    @Column(name = "ADDRESS2", nullable = true, length = 500)
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Basic
    @Column(name = "ADDRESS3", nullable = true, length = 500)
    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    @Basic
    @Column(name = "ADDRESS4", nullable = true, length = 500)
    public String getAddress4() {
        return address4;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    @Basic
    @Column(name = "CITY", nullable = true, length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "POSTAL_CODE", nullable = true, length = 100)
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 100)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "PROVINCE", nullable = true, length = 100)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "COUNTY", nullable = true, length = 100)
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Basic
    @Column(name = "ADDRESS_KEY", nullable = true, length = 1000)
    public String getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(String addressKey) {
        this.addressKey = addressKey;
    }

    @Basic
    @Column(name = "ESTADO", nullable = false, length = 1)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "FECHA_CREACION", nullable = false)
    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Basic
    @Column(name = "USUARIO_CREACION", nullable = false, length = 150)
    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @Basic
    @Column(name = "FECHA_MODIFICACION", nullable = true)
    public Timestamp getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Timestamp fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Basic
    @Column(name = "USUARIO_MODIFICACION", nullable = true, length = 150)
    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @Basic
    @Column(name = "ATTRIBUTE1", nullable = true, length = 250)
    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @Basic
    @Column(name = "ATTRIBUTE2", nullable = true, length = 250)
    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    @Basic
    @Column(name = "ATTRIBUTE3", nullable = true, length = 250)
    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    @Basic
    @Column(name = "ATTRIBUTE4", nullable = true, length = 250)
    public String getAttribute4() {
        return attribute4;
    }

    public void setAttribute4(String attribute4) {
        this.attribute4 = attribute4;
    }

    @Basic
    @Column(name = "ATTRIBUTE5", nullable = true, length = 250)
    public String getAttribute5() {
        return attribute5;
    }

    public void setAttribute5(String attribute5) {
        this.attribute5 = attribute5;
    }

    @Basic
    @Column(name = "ATTRIBUTE6", nullable = true, length = 250)
    public String getAttribute6() {
        return attribute6;
    }

    public void setAttribute6(String attribute6) {
        this.attribute6 = attribute6;
    }

    @Basic
    @Column(name = "ATTRIBUTE7", nullable = true, length = 250)
    public String getAttribute7() {
        return attribute7;
    }

    public void setAttribute7(String attribute7) {
        this.attribute7 = attribute7;
    }

    @ManyToOne
    @JoinColumn(name = "ENCARGADO1", referencedColumnName = "ID")
    public AdmUsuarioEntity getEncargado1() {
        return encargado1;
    }

    public void setEncargado1(AdmUsuarioEntity encargado1) {
        this.encargado1 = encargado1;
    }

    @ManyToOne
    @JoinColumn(name = "ENCARGADO2", referencedColumnName = "ID")
    public AdmUsuarioEntity getEncargado2() {
        return encargado2;
    }

    public void setEncargado2(AdmUsuarioEntity encargado2) {
        this.encargado2 = encargado2;
    }

    @ManyToOne
    @JoinColumn(name = "ENCARGADO3", referencedColumnName = "ID")
    public AdmUsuarioEntity getEncargado3() {
        return encargado3;
    }

    public void setEncargado3(AdmUsuarioEntity encargado3) {
        this.encargado3 = encargado3;
    }

    @ManyToOne
    @JoinColumn(name = "ENCARGADO4", referencedColumnName = "ID")
    public AdmUsuarioEntity getEncargado4() {
        return encargado4;
    }

    public void setEncargado4(AdmUsuarioEntity encargado4) {
        this.encargado4 = encargado4;
    }

    @ManyToOne
    @JoinColumn(name = "ENCARGADO5", referencedColumnName = "ID")
    public AdmUsuarioEntity getEncargado5() {
        return encargado5;
    }

    public void setEncargado5(AdmUsuarioEntity encargado5) {
        this.encargado5 = encargado5;
    }

}
