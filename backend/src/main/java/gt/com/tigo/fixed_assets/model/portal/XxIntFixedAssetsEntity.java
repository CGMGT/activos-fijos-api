package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "XX_INT_FIXED_ASSETS", schema = "", catalog = "")
public class XxIntFixedAssetsEntity {
    private String assetNumber;
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
    private String employeeNumber;
    private String employeeName;
    private long locationId;

    @Basic
    @Id
    @Column(name = "ASSET_NUMBER", nullable = false, length = 15)
    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 80)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "TAG_NUMBER", nullable = true, length = 15)
    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    @Basic
    @Column(name = "LEGACYCODE", nullable = true, length = 150)
    public String getLegacycode() {
        return legacycode;
    }

    public void setLegacycode(String legacycode) {
        this.legacycode = legacycode;
    }

    @Basic
    @Column(name = "MODEL_NUMBER", nullable = true, length = 40)
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
    @Column(name = "SERIAL_NUMBER", nullable = true, length = 35)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Basic
    @Column(name = "ASSET_TYPE", nullable = false, length = 11)
    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    @Basic
    @Column(name = "INVENTORIAL", nullable = true, length = 3)
    public String getInventorial() {
        return inventorial;
    }

    public void setInventorial(String inventorial) {
        this.inventorial = inventorial;
    }

    @Basic
    @Column(name = "CATEGORY_CODE", nullable = true, length = 61)
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Basic
    @Column(name = "BOOK_TYPE_CODE", nullable = false, length = 15)
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
    @Column(name = "COMBINATION", nullable = true, length = 185)
    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    @Basic
    @Column(name = "EXPENSE_ACCOUNT", nullable = true, length = 337)
    public String getExpenseAccount() {
        return expenseAccount;
    }

    public void setExpenseAccount(String expenseAccount) {
        this.expenseAccount = expenseAccount;
    }

    @Basic
    @Column(name = "ASSET_ACCOUNT", nullable = false, length = 25)
    public String getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccount = assetAccount;
    }

    @Basic
    @Column(name = "EMPLOYEE_NUMBER", nullable = true, length = 30)
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Basic
    @Column(name = "EMPLOYEE_NAME", nullable = true, length = 301)
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Basic
    @Column(name = "LOCATION_ID", nullable = false, precision = 0)
    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XxIntFixedAssetsEntity that = (XxIntFixedAssetsEntity) o;
        return locationId == that.locationId &&
                Objects.equals(assetNumber, that.assetNumber) &&
                Objects.equals(description, that.description) &&
                Objects.equals(tagNumber, that.tagNumber) &&
                Objects.equals(legacycode, that.legacycode) &&
                Objects.equals(modelNumber, that.modelNumber) &&
                Objects.equals(parentAssetId, that.parentAssetId) &&
                Objects.equals(assetKeyCcid, that.assetKeyCcid) &&
                Objects.equals(serialNumber, that.serialNumber) &&
                Objects.equals(assetType, that.assetType) &&
                Objects.equals(inventorial, that.inventorial) &&
                Objects.equals(categoryCode, that.categoryCode) &&
                Objects.equals(bookTypeCode, that.bookTypeCode) &&
                Objects.equals(dateIn, that.dateIn) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(combination, that.combination) &&
                Objects.equals(expenseAccount, that.expenseAccount) &&
                Objects.equals(assetAccount, that.assetAccount) &&
                Objects.equals(employeeNumber, that.employeeNumber) &&
                Objects.equals(employeeName, that.employeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assetNumber, description, tagNumber, legacycode, modelNumber, parentAssetId, assetKeyCcid, serialNumber, assetType, inventorial, categoryCode, bookTypeCode, dateIn, cost, combination, expenseAccount, assetAccount, employeeNumber, employeeName, locationId);
    }
}
