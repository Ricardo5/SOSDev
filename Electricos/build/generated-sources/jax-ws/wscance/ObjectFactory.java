
package wscance;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wscance package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _UnsignedLong_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedLong");
    private final static QName _ResultadoCancelacion_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "ResultadoCancelacion");
    private final static QName _UnsignedByte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedByte");
    private final static QName _UnsignedShort_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedShort");
    private final static QName _FallaValidacion_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "FallaValidacion");
    private final static QName _Duration_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "duration");
    private final static QName _Long_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "long");
    private final static QName _Float_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "float");
    private final static QName _DateTime_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "dateTime");
    private final static QName _AnyType_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyType");
    private final static QName _String_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "string");
    private final static QName _UnsignedInt_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "unsignedInt");
    private final static QName _Char_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "char");
    private final static QName _Short_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "short");
    private final static QName _Guid_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "guid");
    private final static QName _FallaServicio_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "FallaServicio");
    private final static QName _Decimal_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "decimal");
    private final static QName _Boolean_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "boolean");
    private final static QName _ListaResultadoCancelacion_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "ListaResultadoCancelacion");
    private final static QName _Base64Binary_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "base64Binary");
    private final static QName _FallaSesion_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "FallaSesion");
    private final static QName _Int_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "int");
    private final static QName _AnyURI_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "anyURI");
    private final static QName _ListaCancelar_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "ListaCancelar");
    private final static QName _Byte_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "byte");
    private final static QName _Double_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "double");
    private final static QName _QName_QNAME = new QName("http://schemas.microsoft.com/2003/10/Serialization/", "QName");
    private final static QName _RespuestaCancelaOtrosResultado_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Resultado");
    private final static QName _RespuestaCancelaMultipleTransaccionID_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "TransaccionID");
    private final static QName _SolicitudAcuseToken_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Token");
    private final static QName _SolicitudAcuseRFC_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "RFC");
    private final static QName _FallaValidacionSugerencia_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Sugerencia");
    private final static QName _FallaValidacionAntecedente_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Antecedente");
    private final static QName _FallaValidacionNodo_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Nodo");
    private final static QName _FallaValidacionEvento_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Evento");
    private final static QName _FallaValidacionDescripcion_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Descripcion");
    private final static QName _ResultadoCancelacionEstatus_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "Estatus");
    private final static QName _SolicitudCancelaOtrosRFCReceptor_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "RFCReceptor");
    private final static QName _SolicitudCancelaOtrosRFCEmisor_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "RFCEmisor");
    private final static QName _RespuestaRecuperarAcuseAcuseXML_QNAME = new QName("http://Ecodex.WS.Model/2011/CFDI", "AcuseXML");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wscance
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ResultadoCancelacion }
     * 
     */
    public ResultadoCancelacion createResultadoCancelacion() {
        return new ResultadoCancelacion();
    }

    /**
     * Create an instance of {@link FallaSesion }
     * 
     */
    public FallaSesion createFallaSesion() {
        return new FallaSesion();
    }

    /**
     * Create an instance of {@link SolicitudCancelaMultiple }
     * 
     */
    public SolicitudCancelaMultiple createSolicitudCancelaMultiple() {
        return new SolicitudCancelaMultiple();
    }

    /**
     * Create an instance of {@link ListaCancelar }
     * 
     */
    public ListaCancelar createListaCancelar() {
        return new ListaCancelar();
    }

    /**
     * Create an instance of {@link FallaServicio }
     * 
     */
    public FallaServicio createFallaServicio() {
        return new FallaServicio();
    }

    /**
     * Create an instance of {@link RespuestaCancelaMultiple }
     * 
     */
    public RespuestaCancelaMultiple createRespuestaCancelaMultiple() {
        return new RespuestaCancelaMultiple();
    }

    /**
     * Create an instance of {@link ListaResultadoCancelacion }
     * 
     */
    public ListaResultadoCancelacion createListaResultadoCancelacion() {
        return new ListaResultadoCancelacion();
    }

    /**
     * Create an instance of {@link RespuestaRecuperarAcuse }
     * 
     */
    public RespuestaRecuperarAcuse createRespuestaRecuperarAcuse() {
        return new RespuestaRecuperarAcuse();
    }

    /**
     * Create an instance of {@link RespuestaCancelaOtros }
     * 
     */
    public RespuestaCancelaOtros createRespuestaCancelaOtros() {
        return new RespuestaCancelaOtros();
    }

    /**
     * Create an instance of {@link SolicitudAcuse }
     * 
     */
    public SolicitudAcuse createSolicitudAcuse() {
        return new SolicitudAcuse();
    }

    /**
     * Create an instance of {@link FallaValidacion }
     * 
     */
    public FallaValidacion createFallaValidacion() {
        return new FallaValidacion();
    }

    /**
     * Create an instance of {@link SolicitudCancelaOtros }
     * 
     */
    public SolicitudCancelaOtros createSolicitudCancelaOtros() {
        return new SolicitudCancelaOtros();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedLong")
    public JAXBElement<BigInteger> createUnsignedLong(BigInteger value) {
        return new JAXBElement<BigInteger>(_UnsignedLong_QNAME, BigInteger.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultadoCancelacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "ResultadoCancelacion")
    public JAXBElement<ResultadoCancelacion> createResultadoCancelacion(ResultadoCancelacion value) {
        return new JAXBElement<ResultadoCancelacion>(_ResultadoCancelacion_QNAME, ResultadoCancelacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedByte")
    public JAXBElement<Short> createUnsignedByte(Short value) {
        return new JAXBElement<Short>(_UnsignedByte_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedShort")
    public JAXBElement<Integer> createUnsignedShort(Integer value) {
        return new JAXBElement<Integer>(_UnsignedShort_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FallaValidacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "FallaValidacion")
    public JAXBElement<FallaValidacion> createFallaValidacion(FallaValidacion value) {
        return new JAXBElement<FallaValidacion>(_FallaValidacion_QNAME, FallaValidacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Duration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "duration")
    public JAXBElement<Duration> createDuration(Duration value) {
        return new JAXBElement<Duration>(_Duration_QNAME, Duration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "long")
    public JAXBElement<Long> createLong(Long value) {
        return new JAXBElement<Long>(_Long_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Float }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "float")
    public JAXBElement<Float> createFloat(Float value) {
        return new JAXBElement<Float>(_Float_QNAME, Float.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "dateTime")
    public JAXBElement<XMLGregorianCalendar> createDateTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_DateTime_QNAME, XMLGregorianCalendar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyType")
    public JAXBElement<Object> createAnyType(Object value) {
        return new JAXBElement<Object>(_AnyType_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "unsignedInt")
    public JAXBElement<Long> createUnsignedInt(Long value) {
        return new JAXBElement<Long>(_UnsignedInt_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "char")
    public JAXBElement<Integer> createChar(Integer value) {
        return new JAXBElement<Integer>(_Char_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Short }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "short")
    public JAXBElement<Short> createShort(Short value) {
        return new JAXBElement<Short>(_Short_QNAME, Short.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "guid")
    public JAXBElement<String> createGuid(String value) {
        return new JAXBElement<String>(_Guid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FallaServicio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "FallaServicio")
    public JAXBElement<FallaServicio> createFallaServicio(FallaServicio value) {
        return new JAXBElement<FallaServicio>(_FallaServicio_QNAME, FallaServicio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "decimal")
    public JAXBElement<BigDecimal> createDecimal(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_Decimal_QNAME, BigDecimal.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaResultadoCancelacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "ListaResultadoCancelacion")
    public JAXBElement<ListaResultadoCancelacion> createListaResultadoCancelacion(ListaResultadoCancelacion value) {
        return new JAXBElement<ListaResultadoCancelacion>(_ListaResultadoCancelacion_QNAME, ListaResultadoCancelacion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "base64Binary")
    public JAXBElement<byte[]> createBase64Binary(byte[] value) {
        return new JAXBElement<byte[]>(_Base64Binary_QNAME, byte[].class, null, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FallaSesion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "FallaSesion")
    public JAXBElement<FallaSesion> createFallaSesion(FallaSesion value) {
        return new JAXBElement<FallaSesion>(_FallaSesion_QNAME, FallaSesion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "int")
    public JAXBElement<Integer> createInt(Integer value) {
        return new JAXBElement<Integer>(_Int_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "anyURI")
    public JAXBElement<String> createAnyURI(String value) {
        return new JAXBElement<String>(_AnyURI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaCancelar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "ListaCancelar")
    public JAXBElement<ListaCancelar> createListaCancelar(ListaCancelar value) {
        return new JAXBElement<ListaCancelar>(_ListaCancelar_QNAME, ListaCancelar.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Byte }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "byte")
    public JAXBElement<Byte> createByte(Byte value) {
        return new JAXBElement<Byte>(_Byte_QNAME, Byte.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Double }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "double")
    public JAXBElement<Double> createDouble(Double value) {
        return new JAXBElement<Double>(_Double_QNAME, Double.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.microsoft.com/2003/10/Serialization/", name = "QName")
    public JAXBElement<QName> createQName(QName value) {
        return new JAXBElement<QName>(_QName_QNAME, QName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaResultadoCancelacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Resultado", scope = RespuestaCancelaOtros.class)
    public JAXBElement<ListaResultadoCancelacion> createRespuestaCancelaOtrosResultado(ListaResultadoCancelacion value) {
        return new JAXBElement<ListaResultadoCancelacion>(_RespuestaCancelaOtrosResultado_QNAME, ListaResultadoCancelacion.class, RespuestaCancelaOtros.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaResultadoCancelacion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Resultado", scope = RespuestaCancelaMultiple.class)
    public JAXBElement<ListaResultadoCancelacion> createRespuestaCancelaMultipleResultado(ListaResultadoCancelacion value) {
        return new JAXBElement<ListaResultadoCancelacion>(_RespuestaCancelaOtrosResultado_QNAME, ListaResultadoCancelacion.class, RespuestaCancelaMultiple.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "TransaccionID", scope = RespuestaCancelaMultiple.class)
    public JAXBElement<String> createRespuestaCancelaMultipleTransaccionID(String value) {
        return new JAXBElement<String>(_RespuestaCancelaMultipleTransaccionID_QNAME, String.class, RespuestaCancelaMultiple.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Token", scope = SolicitudAcuse.class)
    public JAXBElement<String> createSolicitudAcuseToken(String value) {
        return new JAXBElement<String>(_SolicitudAcuseToken_QNAME, String.class, SolicitudAcuse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "RFC", scope = SolicitudAcuse.class)
    public JAXBElement<String> createSolicitudAcuseRFC(String value) {
        return new JAXBElement<String>(_SolicitudAcuseRFC_QNAME, String.class, SolicitudAcuse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Sugerencia", scope = FallaValidacion.class)
    public JAXBElement<String> createFallaValidacionSugerencia(String value) {
        return new JAXBElement<String>(_FallaValidacionSugerencia_QNAME, String.class, FallaValidacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Antecedente", scope = FallaValidacion.class)
    public JAXBElement<String> createFallaValidacionAntecedente(String value) {
        return new JAXBElement<String>(_FallaValidacionAntecedente_QNAME, String.class, FallaValidacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Nodo", scope = FallaValidacion.class)
    public JAXBElement<String> createFallaValidacionNodo(String value) {
        return new JAXBElement<String>(_FallaValidacionNodo_QNAME, String.class, FallaValidacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Evento", scope = FallaValidacion.class)
    public JAXBElement<String> createFallaValidacionEvento(String value) {
        return new JAXBElement<String>(_FallaValidacionEvento_QNAME, String.class, FallaValidacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Descripcion", scope = FallaValidacion.class)
    public JAXBElement<String> createFallaValidacionDescripcion(String value) {
        return new JAXBElement<String>(_FallaValidacionDescripcion_QNAME, String.class, FallaValidacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Estatus", scope = ResultadoCancelacion.class)
    public JAXBElement<String> createResultadoCancelacionEstatus(String value) {
        return new JAXBElement<String>(_ResultadoCancelacionEstatus_QNAME, String.class, ResultadoCancelacion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "RFCReceptor", scope = SolicitudCancelaOtros.class)
    public JAXBElement<String> createSolicitudCancelaOtrosRFCReceptor(String value) {
        return new JAXBElement<String>(_SolicitudCancelaOtrosRFCReceptor_QNAME, String.class, SolicitudCancelaOtros.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Token", scope = SolicitudCancelaOtros.class)
    public JAXBElement<String> createSolicitudCancelaOtrosToken(String value) {
        return new JAXBElement<String>(_SolicitudAcuseToken_QNAME, String.class, SolicitudCancelaOtros.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "RFCEmisor", scope = SolicitudCancelaOtros.class)
    public JAXBElement<String> createSolicitudCancelaOtrosRFCEmisor(String value) {
        return new JAXBElement<String>(_SolicitudCancelaOtrosRFCEmisor_QNAME, String.class, SolicitudCancelaOtros.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Descripcion", scope = FallaSesion.class)
    public JAXBElement<String> createFallaSesionDescripcion(String value) {
        return new JAXBElement<String>(_FallaValidacionDescripcion_QNAME, String.class, FallaSesion.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListaCancelar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "ListaCancelar", scope = SolicitudCancelaMultiple.class)
    public JAXBElement<ListaCancelar> createSolicitudCancelaMultipleListaCancelar(ListaCancelar value) {
        return new JAXBElement<ListaCancelar>(_ListaCancelar_QNAME, ListaCancelar.class, SolicitudCancelaMultiple.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Token", scope = SolicitudCancelaMultiple.class)
    public JAXBElement<String> createSolicitudCancelaMultipleToken(String value) {
        return new JAXBElement<String>(_SolicitudAcuseToken_QNAME, String.class, SolicitudCancelaMultiple.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "RFC", scope = SolicitudCancelaMultiple.class)
    public JAXBElement<String> createSolicitudCancelaMultipleRFC(String value) {
        return new JAXBElement<String>(_SolicitudAcuseRFC_QNAME, String.class, SolicitudCancelaMultiple.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Evento", scope = FallaServicio.class)
    public JAXBElement<String> createFallaServicioEvento(String value) {
        return new JAXBElement<String>(_FallaValidacionEvento_QNAME, String.class, FallaServicio.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Descripcion", scope = FallaServicio.class)
    public JAXBElement<String> createFallaServicioDescripcion(String value) {
        return new JAXBElement<String>(_FallaValidacionDescripcion_QNAME, String.class, FallaServicio.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "AcuseXML", scope = RespuestaRecuperarAcuse.class)
    public JAXBElement<String> createRespuestaRecuperarAcuseAcuseXML(String value) {
        return new JAXBElement<String>(_RespuestaRecuperarAcuseAcuseXML_QNAME, String.class, RespuestaRecuperarAcuse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Ecodex.WS.Model/2011/CFDI", name = "Estatus", scope = RespuestaRecuperarAcuse.class)
    public JAXBElement<String> createRespuestaRecuperarAcuseEstatus(String value) {
        return new JAXBElement<String>(_ResultadoCancelacionEstatus_QNAME, String.class, RespuestaRecuperarAcuse.class, value);
    }

}
