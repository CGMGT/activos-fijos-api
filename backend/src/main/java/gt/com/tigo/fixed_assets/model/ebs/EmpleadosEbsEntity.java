package gt.com.tigo.fixed_assets.model.ebs;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class EmpleadosEbsEntity {
    private long personId;
    private Timestamp effectiveStartDate;
    private Timestamp effectiveEndDate;
    private long personTypeId;
    private String lastName;
    private String firstName;
    private Timestamp startDate;
    private String currentEmpOrAplFlag;
    private String currentEmployeeFlag;
    private String emailAddress;
    private String employeeNumber;
    private String fullName;
    private String sex;
    private String attribute1;
    private Timestamp originalDateOfHire;
    private long partyId;
    private String globalName;
    private String localName;

    @Id
    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public Timestamp getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Timestamp effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public Timestamp getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Timestamp effectiveEndtDate) {
        this.effectiveEndDate = effectiveEndtDate;
    }

    public long getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(long personTypeId) {
        this.personTypeId = personTypeId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getCurrentEmpOrAplFlag() {
        return currentEmpOrAplFlag;
    }

    public void setCurrentEmpOrAplFlag(String currentEmpOrAplFlag) {
        this.currentEmpOrAplFlag = currentEmpOrAplFlag;
    }

    public String getCurrentEmployeeFlag() {
        return currentEmployeeFlag;
    }

    public void setCurrentEmployeeFlag(String currentEmployeeFlag) {
        this.currentEmployeeFlag = currentEmployeeFlag;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public Timestamp getOriginalDateOfHire() {
        return originalDateOfHire;
    }

    public void setOriginalDateOfHire(Timestamp originalDateOfHire) {
        this.originalDateOfHire = originalDateOfHire;
    }

    public long getPartyId() {
        return partyId;
    }

    public void setPartyId(long partyId) {
        this.partyId = partyId;
    }

    public String getGlobalName() {
        return globalName;
    }

    public void setGlobalName(String globalName) {
        this.globalName = globalName;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
}
