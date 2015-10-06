
package pRuebasEcodexTimbrado;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AdvertenciaValidacion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AdvertenciaValidacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Nodo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Descripcion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Antecedente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sugerencia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AdvertenciaValidacion", propOrder = {
    "nodo",
    "descripcion",
    "antecedente",
    "sugerencia"
})
public class AdvertenciaValidacion {

    @XmlElement(name = "Nodo", required = true, nillable = true)
    protected String nodo;
    @XmlElement(name = "Descripcion", required = true, nillable = true)
    protected String descripcion;
    @XmlElementRef(name = "Antecedente", namespace = "http://Ecodex.WS.Model/2011/CFDI", type = JAXBElement.class, required = false)
    protected JAXBElement<String> antecedente;
    @XmlElementRef(name = "Sugerencia", namespace = "http://Ecodex.WS.Model/2011/CFDI", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sugerencia;

    /**
     * Obtiene el valor de la propiedad nodo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNodo() {
        return nodo;
    }

    /**
     * Define el valor de la propiedad nodo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNodo(String value) {
        this.nodo = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad antecedente.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAntecedente() {
        return antecedente;
    }

    /**
     * Define el valor de la propiedad antecedente.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAntecedente(JAXBElement<String> value) {
        this.antecedente = value;
    }

    /**
     * Obtiene el valor de la propiedad sugerencia.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSugerencia() {
        return sugerencia;
    }

    /**
     * Define el valor de la propiedad sugerencia.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSugerencia(JAXBElement<String> value) {
        this.sugerencia = value;
    }

}
