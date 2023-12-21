package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "XX_INT_EBS_EMPLOYEES", schema = "", catalog = "")
public class XxIntEbsEmployeesEntity {
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
    private Long partyId;
    private String globalName;
    private String localName;

    @Basic
    @Id
    @Column(name = "PERSON_ID", nullable = false, precision = 0)
    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    @Basic
    @Column(name = "EFFECTIVE_START_DATE", nullable = false)
    public Timestamp getEffectiveStartDate() {
        return effectiveStartDate;
    }

    public void setEffectiveStartDate(Timestamp effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    @Basic
    @Column(name = "EFFECTIVE_END_DATE", nullable = false)
    public Timestamp getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public void setEffectiveEndDate(Timestamp effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    @Basic
    @Column(name = "PERSON_TYPE_ID", nullable = false, precision = 0)
    public long getPersonTypeId() {
        return personTypeId;
    }

    public void setPersonTypeId(long personTypeId) {
        this.personTypeId = personTypeId;
    }

    @Basic
    @Column(name = "LAST_NAME", nullable = false, length = 150)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "FIRST_NAME", nullable = true, length = 150)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "START_DATE", nullable = false)
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "CURRENT_EMP_OR_APL_FLAG", nullable = true, length = 30)
    public String getCurrentEmpOrAplFlag() {
        return currentEmpOrAplFlag;
    }

    public void setCurrentEmpOrAplFlag(String currentEmpOrAplFlag) {
        this.currentEmpOrAplFlag = currentEmpOrAplFlag;
    }

    @Basic
    @Column(name = "CURRENT_EMPLOYEE_FLAG", nullable = true, length = 30)
    public String getCurrentEmployeeFlag() {
        return currentEmployeeFlag;
    }

    public void setCurrentEmployeeFlag(String currentEmployeeFlag) {
        this.currentEmployeeFlag = currentEmployeeFlag;
    }

    @Basic
    @Column(name = "EMAIL_ADDRESS", nullable = true, length = 240)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
    @Column(name = "FULL_NAME", nullable = true, length = 240)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "SEX", nullable = true, length = 30)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "ATTRIBUTE1", nullable = true, length = 150)
    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    @Basic
    @Column(name = "ORIGINAL_DATE_OF_HIRE", nullable = true)
    public Timestamp getOriginalDateOfHire() {
        return originalDateOfHire;
    }

    public void setOriginalDateOfHire(Timestamp originalDateOfHire) {
        this.originalDateOfHire = originalDateOfHire;
    }

    @Basic
    @Column(name = "PARTY_ID", nullable = true, precision = 0)
    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    @Basic
    @Column(name = "GLOBAL_NAME", nullable = true, length = 240)
    public String getGlobalName() {
        return globalName;
    }

    public void setGlobalName(String globalName) {
        this.globalName = globalName;
    }

    @Basic
    @Column(name = "LOCAL_NAME", nullable = true, length = 240)
    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XxIntEbsEmployeesEntity that = (XxIntEbsEmployeesEntity) o;
        return personId == that.personId &&
                personTypeId == that.personTypeId &&
                Objects.equals(effectiveStartDate, that.effectiveStartDate) &&
                Objects.equals(effectiveEndDate, that.effectiveEndDate) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(currentEmpOrAplFlag, that.currentEmpOrAplFlag) &&
                Objects.equals(currentEmployeeFlag, that.currentEmployeeFlag) &&
                Objects.equals(emailAddress, that.emailAddress) &&
                Objects.equals(employeeNumber, that.employeeNumber) &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(attribute1, that.attribute1) &&
                Objects.equals(originalDateOfHire, that.originalDateOfHire) &&
                Objects.equals(partyId, that.partyId) &&
                Objects.equals(globalName, that.globalName) &&
                Objects.equals(localName, that.localName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, effectiveStartDate, effectiveEndDate, personTypeId, lastName, firstName, startDate, currentEmpOrAplFlag, currentEmployeeFlag, emailAddress, employeeNumber, fullName, sex, attribute1, originalDateOfHire, partyId, globalName, localName);
    }
}
