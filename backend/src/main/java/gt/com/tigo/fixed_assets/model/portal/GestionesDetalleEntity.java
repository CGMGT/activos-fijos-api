package gt.com.tigo.fixed_assets.model.portal;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "GESTIONES_DETALLE", schema = "", catalog = "")
@SequenceGenerator(name="SEQ_GESTIONES_DETALLE", sequenceName = "SEQ_GESTIONES_DETALLE", initialValue=1, allocationSize=1)
public class GestionesDetalleEntity {
    private Long id;
    private Long idGestion;
    private Long assetNumber;
    private String serie;
    private String modelo;
    private String legacyCode;
    private String etiqueta;
    private String descripcion;
    private String motivo;
    private String tipoMotivo;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String attribute4;
    private String attribute5;
    private String attribute6;
    private String attribute7;
    private Collection<DocumentosEntity> documentos;
    private CpActivosFijosEntity activoFijo;
    private CpLocationsEntity locacion;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GESTIONES_DETALLE")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ID_GESTION", nullable = false, precision = 0)
    public Long getIdGestion() {
        return idGestion;
    }

    public void setIdGestion(Long idGestion) {
        this.idGestion = idGestion;
    }

    @Basic
    @Column(name = "ASSET_NUMBER", nullable = false, precision = 0)
    public Long getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(Long assetNumber) {
        this.assetNumber = assetNumber;
    }

    @Basic
    @Column(name = "SERIE", nullable = true, length = 250)
    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    @Basic
    @Column(name = "MODELO", nullable = true, length = 250)
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Basic
    @Column(name = "LEGACY_CODE", nullable = true, length = 250)
    public String getLegacyCode() {
        return legacyCode;
    }

    public void setLegacyCode(String legacyCode) {
        this.legacyCode = legacyCode;
    }

    @Basic
    @Column(name = "ETIQUETA", nullable = true, length = 250)
    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 500)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "MOTIVO", nullable = true, length = 1000)
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Basic
    @Column(name = "TIPO_MOTIVO", nullable = true, length = 250)
    public String getTipoMotivo() {
        return tipoMotivo;
    }

    public void setTipoMotivo(String tipoMotivo) {
        this.tipoMotivo = tipoMotivo;
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


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_GESTION_DET", referencedColumnName = "ID", insertable = false, updatable = false)
    public Collection<DocumentosEntity> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Collection<DocumentosEntity> documentos) {
        this.documentos = documentos;
    }


    @ManyToOne
    @JoinColumn(name = "ID_ACTIVO_FIJO", referencedColumnName = "ID")
    public CpActivosFijosEntity getActivoFijo() {
        return activoFijo;
    }

    public void setActivoFijo(CpActivosFijosEntity activoFijo) {
        this.activoFijo = activoFijo;
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
