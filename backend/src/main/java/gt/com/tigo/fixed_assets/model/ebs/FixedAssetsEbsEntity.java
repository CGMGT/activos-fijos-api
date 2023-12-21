package gt.com.tigo.fixed_assets.model.ebs;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class FixedAssetsEbsEntity {

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

    @Id
    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(String tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getLegacycode() {
        return legacycode;
    }

    public void setLegacycode(String legacycode) {
        this.legacycode = legacycode;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public Long getParentAssetId() {
        return parentAssetId;
    }

    public void setParentAssetId(Long parentAssetId) {
        this.parentAssetId = parentAssetId;
    }

    public Long getAssetKeyCcid() {
        return assetKeyCcid;
    }

    public void setAssetKeyCcid(Long assetKeyCcid) {
        this.assetKeyCcid = assetKeyCcid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getInventorial() {
        return inventorial;
    }

    public void setInventorial(String inventorial) {
        this.inventorial = inventorial;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    public Timestamp getDateIn() {
        return dateIn;
    }

    public void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getCombination() {
        return combination;
    }

    public void setCombination(String combination) {
        this.combination = combination;
    }

    public String getExpenseAccount() {
        return expenseAccount;
    }

    public void setExpenseAccount(String expenseAccount) {
        this.expenseAccount = expenseAccount;
    }

    public String getAssetAccount() {
        return assetAccount;
    }

    public void setAssetAccount(String assetAccount) {
        this.assetAccount = assetAccount;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
}
