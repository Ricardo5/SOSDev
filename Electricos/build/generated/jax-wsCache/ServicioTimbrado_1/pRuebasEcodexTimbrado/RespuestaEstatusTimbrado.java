
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
 *         &lt;element name="Estatus" type="{http://Ecodex.WS.Model/2011/CFDI}ComprobanteEstatus" minOccurs="0"/>
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
    "estatus",
    "transaccionID"
})
@XmlRootElement(name = "RespuestaEstatusTimbrado")
public class RespuestaEstatusTimbrado {

    @XmlElementRef(name = "Estatus", namespace = "http://Ecodex.WS.Model/2011/CFDI", type = JAXBElement.class, required = false)
    protected JAXBElement<ComprobanteEstatus> estatus;
    @XmlElement(name = "TransaccionID")
    protected Long transaccionID;

    /**
     * Obtiene el valor de la propiedad estatus.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ComprobanteEstatus }{@code >}
     *     
     */
    public JAXBElement<ComprobanteEstatus> getEstatus() {
        return estatus;
    }

    /**
     * Define el valor de la propiedad estatus.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ComprobanteEstatus }{@code >}
     *     
     */
    public void setEstatus(JAXBElement<ComprobanteEstatus> value) {
        this.estatus = value;
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
