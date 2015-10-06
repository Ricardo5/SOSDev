
package pRuebasEcodexCancelacionTimbre;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ListaResultadoCancelacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ListaResultadoCancelacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultadoCancelacion" type="{http://Ecodex.WS.Model/2011/CFDI}ResultadoCancelacion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListaResultadoCancelacion", propOrder = {
    "resultadoCancelacion"
})
public class ListaResultadoCancelacion {

    @XmlElement(name = "ResultadoCancelacion", nillable = true)
    protected List<ResultadoCancelacion> resultadoCancelacion;

    /**
     * Gets the value of the resultadoCancelacion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultadoCancelacion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultadoCancelacion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResultadoCancelacion }
     * 
     * 
     */
    public List<ResultadoCancelacion> getResultadoCancelacion() {
        if (resultadoCancelacion == null) {
            resultadoCancelacion = new ArrayList<ResultadoCancelacion>();
        }
        return this.resultadoCancelacion;
    }

}
