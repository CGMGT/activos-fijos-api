package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "NOTIFICACIONES", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_NOTIFICACIONES", sequenceName = "SEQ_NOTIFICACIONES", initialValue=1, allocationSize=1)
public class NotificacionesEntity {
    private long id;
    private Long jobId;
    private String command;
    private Timestamp initialDate;
    private Timestamp endDate;
    private String periodo;
    private String unidadNegocio;
    private String areaFuncional;
    private String unidadAdministrativa;
    private String estado;
    private String comentarios;
    private Long recurrente;
    private Long diasRecordatorio;
    private Long diasEscalamiento;
    private Timestamp fechaCreacion;
    private String usuarioCreacion;
    private Timestamp fechaModificacion;
    private String usuarioModificacion;
    private String attribute1;
    private String attribute2;
    private String attribute3;


    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTIFICACIONES")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JOB_ID", nullable = true, precision = 0)
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "COMMAND", nullable = true)
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Basic
    @Column(name = "INITIAL_DATE", nullable = false)
    public Timestamp getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Timestamp nextDate) {
        this.initialDate = nextDate;
    }

    @Basic
    @Column(name = "END_DATE", nullable = true)
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "PERIODO", nullable = true, length = 50)
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @Basic
    @Column(name = "UNIDAD_NEGOCIO", nullable = false, length = 250)
    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    @Basic
    @Column(name = "AREA_FUNCIONAL", nullable = true, length = 250)
    public String getAreaFuncional() {
        return areaFuncional;
    }

    public void setAreaFuncional(String areaFuncional) {
        this.areaFuncional = areaFuncional;
    }

    @Basic
    @Column(name = "UNIDAD_ADMINISTRATIVA", nullable = true, length = 250)
    public String getUnidadAdministrativa() {
        return unidadAdministrativa;
    }

    public void setUnidadAdministrativa(String unidadAdministrativa) {
        this.unidadAdministrativa = unidadAdministrativa;
    }

    @Basic
    @Column(name = "ESTADO", nullable = true, length = 50)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "COMENTARIOS", nullable = true, length = 500)
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Basic
    @Column(name = "RECURRENTE", nullable = true, precision = 0)
    public Long getRecurrente() {
        return recurrente;
    }

    public void setRecurrente(Long recurrente) {
        this.recurrente = recurrente;
    }

    @Basic
    @Column(name = "DIAS_RECORDATORIO", nullable = true, precision = 0)
    public Long getDiasRecordatorio() {
        return diasRecordatorio;
    }

    public void setDiasRecordatorio(Long diasRecordatorio) {
        this.diasRecordatorio = diasRecordatorio;
    }


    @Basic
    @Column(name = "DIAS_ESCALAMIENTO", nullable = true, precision = 0)
    public Long getDiasEscalamiento() {
        return diasEscalamiento;
    }

    public void setDiasEscalamiento(Long diasEscalamiento) {
        this.diasEscalamiento = diasEscalamiento;
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

}
