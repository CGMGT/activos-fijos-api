package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CP_EMPLEADOS_EBS", schema = "", catalog = "")
public class CpEmpleadosEbsEntity {
    private long id;
    private int personId;
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
    private String estado;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private String attribute2;
    private String attribute3;
    private String attribute4;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PERSON_ID", nullable = false, precision = 0)
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
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
}
