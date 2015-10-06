
package princip;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the princip package. 
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

    private final static QName _Revsis_QNAME = new QName("http://princip/", "revsis");
    private final static QName _Avis_QNAME = new QName("http://princip/", "avis");
    private final static QName _Pruebcon_QNAME = new QName("http://princip/", "pruebcon");
    private final static QName _Serkey_QNAME = new QName("http://princip/", "serkey");
    private final static QName _Regmsj_QNAME = new QName("http://princip/", "regmsj");
    private final static QName _Givmac_QNAME = new QName("http://princip/", "givmac");
    private final static QName _MsjmacResponse_QNAME = new QName("http://princip/", "msjmacResponse");
    private final static QName _RevsisResponse_QNAME = new QName("http://princip/", "revsisResponse");
    private final static QName _GivmacResponse_QNAME = new QName("http://princip/", "givmacResponse");
    private final static QName _RegmsjResponse_QNAME = new QName("http://princip/", "regmsjResponse");
    private final static QName _Msjmac_QNAME = new QName("http://princip/", "msjmac");
    private final static QName _AvisResponse_QNAME = new QName("http://princip/", "avisResponse");
    private final static QName _SerkeyResponse_QNAME = new QName("http://princip/", "serkeyResponse");
    private final static QName _PruebconResponse_QNAME = new QName("http://princip/", "pruebconResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: princip
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GivmacResponse }
     * 
     */
    public GivmacResponse createGivmacResponse() {
        return new GivmacResponse();
    }

    /**
     * Create an instance of {@link RegmsjResponse }
     * 
     */
    public RegmsjResponse createRegmsjResponse() {
        return new RegmsjResponse();
    }

    /**
     * Create an instance of {@link AvisResponse }
     * 
     */
    public AvisResponse createAvisResponse() {
        return new AvisResponse();
    }

    /**
     * Create an instance of {@link SerkeyResponse }
     * 
     */
    public SerkeyResponse createSerkeyResponse() {
        return new SerkeyResponse();
    }

    /**
     * Create an instance of {@link PruebconResponse }
     * 
     */
    public PruebconResponse createPruebconResponse() {
        return new PruebconResponse();
    }

    /**
     * Create an instance of {@link Msjmac }
     * 
     */
    public Msjmac createMsjmac() {
        return new Msjmac();
    }

    /**
     * Create an instance of {@link Avis }
     * 
     */
    public Avis createAvis() {
        return new Avis();
    }

    /**
     * Create an instance of {@link Revsis }
     * 
     */
    public Revsis createRevsis() {
        return new Revsis();
    }

    /**
     * Create an instance of {@link Regmsj }
     * 
     */
    public Regmsj createRegmsj() {
        return new Regmsj();
    }

    /**
     * Create an instance of {@link Givmac }
     * 
     */
    public Givmac createGivmac() {
        return new Givmac();
    }

    /**
     * Create an instance of {@link MsjmacResponse }
     * 
     */
    public MsjmacResponse createMsjmacResponse() {
        return new MsjmacResponse();
    }

    /**
     * Create an instance of {@link RevsisResponse }
     * 
     */
    public RevsisResponse createRevsisResponse() {
        return new RevsisResponse();
    }

    /**
     * Create an instance of {@link Pruebcon }
     * 
     */
    public Pruebcon createPruebcon() {
        return new Pruebcon();
    }

    /**
     * Create an instance of {@link Serkey }
     * 
     */
    public Serkey createSerkey() {
        return new Serkey();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Revsis }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "revsis")
    public JAXBElement<Revsis> createRevsis(Revsis value) {
        return new JAXBElement<Revsis>(_Revsis_QNAME, Revsis.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Avis }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "avis")
    public JAXBElement<Avis> createAvis(Avis value) {
        return new JAXBElement<Avis>(_Avis_QNAME, Avis.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pruebcon }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "pruebcon")
    public JAXBElement<Pruebcon> createPruebcon(Pruebcon value) {
        return new JAXBElement<Pruebcon>(_Pruebcon_QNAME, Pruebcon.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Serkey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "serkey")
    public JAXBElement<Serkey> createSerkey(Serkey value) {
        return new JAXBElement<Serkey>(_Serkey_QNAME, Serkey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Regmsj }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "regmsj")
    public JAXBElement<Regmsj> createRegmsj(Regmsj value) {
        return new JAXBElement<Regmsj>(_Regmsj_QNAME, Regmsj.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Givmac }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "givmac")
    public JAXBElement<Givmac> createGivmac(Givmac value) {
        return new JAXBElement<Givmac>(_Givmac_QNAME, Givmac.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MsjmacResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "msjmacResponse")
    public JAXBElement<MsjmacResponse> createMsjmacResponse(MsjmacResponse value) {
        return new JAXBElement<MsjmacResponse>(_MsjmacResponse_QNAME, MsjmacResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RevsisResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "revsisResponse")
    public JAXBElement<RevsisResponse> createRevsisResponse(RevsisResponse value) {
        return new JAXBElement<RevsisResponse>(_RevsisResponse_QNAME, RevsisResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GivmacResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "givmacResponse")
    public JAXBElement<GivmacResponse> createGivmacResponse(GivmacResponse value) {
        return new JAXBElement<GivmacResponse>(_GivmacResponse_QNAME, GivmacResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegmsjResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "regmsjResponse")
    public JAXBElement<RegmsjResponse> createRegmsjResponse(RegmsjResponse value) {
        return new JAXBElement<RegmsjResponse>(_RegmsjResponse_QNAME, RegmsjResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Msjmac }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "msjmac")
    public JAXBElement<Msjmac> createMsjmac(Msjmac value) {
        return new JAXBElement<Msjmac>(_Msjmac_QNAME, Msjmac.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AvisResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "avisResponse")
    public JAXBElement<AvisResponse> createAvisResponse(AvisResponse value) {
        return new JAXBElement<AvisResponse>(_AvisResponse_QNAME, AvisResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SerkeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "serkeyResponse")
    public JAXBElement<SerkeyResponse> createSerkeyResponse(SerkeyResponse value) {
        return new JAXBElement<SerkeyResponse>(_SerkeyResponse_QNAME, SerkeyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PruebconResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://princip/", name = "pruebconResponse")
    public JAXBElement<PruebconResponse> createPruebconResponse(PruebconResponse value) {
        return new JAXBElement<PruebconResponse>(_PruebconResponse_QNAME, PruebconResponse.class, null, value);
    }

}
