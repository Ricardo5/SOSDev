
package pRuebasEcodexTimbrado;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AdvertenciasValidacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AdvertenciasValidacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AdvertenciaValidacion" type="{http://Ecodex.WS.Model/2011/CFDI}AdvertenciaValidacion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdvertenciasValidacion", propOrder = {
    "advertenciaValidacion"
})
public class AdvertenciasValidacion {

    @XmlElement(name = "AdvertenciaValidacion", nillable = true)
    protected List<AdvertenciaValidacion> advertenciaValidacion;

    /**
     * Gets the value of the advertenciaValidacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the advertenciaValidacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdvertenciaValidacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdvertenciaValidacion }
     * 
     * 
     */
    public List<AdvertenciaValidacion> getAdvertenciaValidacion() {
        if (advertenciaValidacion == null) {
            advertenciaValidacion = new ArrayList<AdvertenciaValidacion>();
        }
        return this.advertenciaValidacion;
    }

}
