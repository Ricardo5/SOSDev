
package pRuebasEcodexTimbrado;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Advertencias" type="{http://Ecodex.WS.Model/2011/CFDI}AdvertenciasValidacion" minOccurs="0"/>
 *         &lt;element name="ComprobanteXML" type="{http://Ecodex.WS.Model/2011/CFDI}ComprobanteXML" minOccurs="0"/>
 *         &lt;element name="TransaccionID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "advertencias",
    "comprobanteXML",
    "transaccionID"
})
@XmlRootElement(name = "RespuestaTimbraXML")
public class RespuestaTimbraXML {

    @XmlElementRef(name = "Advertencias", namespace = "http://Ecodex.WS.Model/2011/CFDI", type = JAXBElement.class, required = false)
    protected JAXBElement<AdvertenciasValidacion> advertencias;
    @XmlElementRef(name = "ComprobanteXML", namespace = "http://Ecodex.WS.Model/2011/CFDI", type = JAXBElement.class, required = false)
    protected JAXBElement<ComprobanteXML> comprobanteXML;
    @XmlElement(name = "TransaccionID")
    protected Long transaccionID;

    /**
     * Obtiene el valor de la propiedad advertencias.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AdvertenciasValidacion }{@code >}
     *     
     */
    public JAXBElement<AdvertenciasValidacion> getAdvertencias() {
        return advertencias;
    }

    /**
     * Define el valor de la propiedad advertencias.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AdvertenciasValidacion }{@code >}
     *     
     */
    public void setAdvertencias(JAXBElement<AdvertenciasValidacion> value) {
        this.advertencias = value;
    }

    /**
     * Obtiene el valor de la propiedad comprobanteXML.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ComprobanteXML }{@code >}
     *     
     */
    public JAXBElement<ComprobanteXML> getComprobanteXML() {
        return comprobanteXML;
    }

    /**
     * Define el valor de la propiedad comprobanteXML.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ComprobanteXML }{@code >}
     *     
     */
    public void setComprobanteXML(JAXBElement<ComprobanteXML> value) {
        this.comprobanteXML = value;
    }

    /**
     * Obtiene el valor de la propiedad transaccionID.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTransaccionID() {
        return transaccionID;
    }

    /**
     * Define el valor de la propiedad transaccionID.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransaccionID(Long value) {
        this.transaccionID = value;
    }

}
