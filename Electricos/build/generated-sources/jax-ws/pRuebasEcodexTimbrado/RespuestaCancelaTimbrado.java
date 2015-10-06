
package pRuebasEcodexTimbrado;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="Cancelada" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "cancelada",
    "transaccionID"
})
@XmlRootElement(name = "RespuestaCancelaTimbrado")
public class RespuestaCancelaTimbrado {

    @XmlElement(name = "Cancelada")
    protected Boolean cancelada;
    @XmlElement(name = "TransaccionID")
    protected Long transaccionID;

    /**
     * Obtiene el valor de la propiedad cancelada.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCancelada() {
        return cancelada;
    }

    /**
     * Define el valor de la propiedad cancelada.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCancelada(Boolean value) {
        this.cancelada = value;
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
