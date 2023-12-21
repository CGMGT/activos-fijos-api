package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CP_ACTIVOS_FIJOS", schema = "", catalog = "")
public class CpActivosFijosEntity {
    private Long id;
    private Long assetNumber;
    private String description;
    private String tagNumber;
    private String legacycode;
    private String modelNumber;
    private Long parentAssetId;
    private Long assetKeyCcid;
    private String serialNumber;
    private String assetType;
    private String inventorial;
    private String categoryCode;
    private String bookTypeCode;
    private Timestamp dateIn;
    private Long cost;
    private String combination;
    private String expenseAccount;
    private String assetAccount;
    private Long employeeNumber;
    private String employeeName;
    private Integer locked;
    private Integer sincronizado;
    private String estado;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private CpLocationsEntity locacion;


    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ASSET_NUMBER", nullable = true, precision = 0)
    public Long getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(Long assetNumber) {
        this.assetNumber = assetNumber;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 150)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "TAG_NUMBER", nullable = true, length = 50)
    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    @Basic
    @Column(name = "LEGACYCODE", nullable = true, length = 200)
    public String getLegacycode() {
        return legacycode;
    }

    public void setLegacycode(String legacycode) {
        this.legacycode = legacycode;
    }

    @Basic
    @Column(name = "MODEL_NUMBER", nullable = true, length = 100)
    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @Basic
    @Column(name = "PARENT_ASSET_ID", nullable = true, precision = 0)
    public Long getParentAssetId() {
        return parentAssetId;
    }

    public void setParentAssetId(Long parentAssetId) {
        this.parentAssetId = parentAssetId;
    }

    @Basic
    @Column(name = "ASSET_KEY_CCID", nullable = true, precision = 0)
    public Long getAssetKeyCcid() {
        return assetKeyCcid;
    }

    public void setAssetKeyCcid(Long assetKeyCcid) {
        this.assetKeyCcid = assetKeyCcid;
    }

    @Basic
    @Column(name = "SERIAL_NUMBER", nullable = true, length = 50)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "ASSET_TYPE", nullable = false, length = 50)
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @Basic
    @Column(name = "INVENTORIAL", nullable = true, length = 10)
    public String getInventorial() {
        return inventorial;
    }

    public void setInventorial(String inventorial) {
        this.inventorial = inventorial;
    }

    @Basic
    @Column(name = "CATEGORY_CODE", nullable = true, length = 100)
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Basic
    @Column(name = "BOOK_TYPE_CODE", nullable = false, length = 50)
    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    @Basic
    @Column(name = "DATE_IN", nullable = false)
    public Timestamp getDateIn() {
        return dateIn;
    }

    public void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    @Basic
    @Column(name = "COST", nullable = true, precision = 0)
    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "COMBINATION", nullable = true, length = 250)
    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    @Basic
    @Column(name = "EXPENSE_ACCOUNT", nullable = true, length = 450)
    public String getExpenseAccount() {
        return expenseAccount;
    }

    public void setExpenseAccount(String expenseAccount) {
        this.expenseAccount = expenseAccount;
    }

    @Basic
    @Column(name = "ASSET_ACCOUNT", nullable = false, length = 50)
    public String getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccount = assetAccount;
    }

    @Basic
    @Column(name = "EMPLOYEE_NUMBER", nullable = true, precision = 0)
    public Long getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Long employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Basic
    @Column(name = "EMPLOYEE_NAME", nullable = true, length = 400)
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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
    @Column(name = "LOCKED", nullable = true, precision = 0)
    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Basic
    @Column(name = "SINCRONIZADO", nullable = true, precision = 0)
    public Integer getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Integer sincronizado) {
        this.sincronizado = sincronizado;
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

    @ManyToOne
    @JoinColumn(name = "ID_LOCACION", referencedColumnName = "ID")
    public CpLocationsEntity getLocacion() {
        return locacion;
    }

    public void setLocacion(CpLocationsEntity locacion) {
        this.locacion = locacion;
    }

}
