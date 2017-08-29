
package co.za.fnb.volunteers.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CategoriesResponse" type="{http://www.example.org/Categories/}CategoriesResponseType"/>
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
    "categoriesResponse"
})
@XmlRootElement(name = "getCategoriesResponse")
public class GetCategoriesResponse {

    @XmlElement(name = "CategoriesResponse", required = true)
    protected CategoriesResponseType categoriesResponse;

    /**
     * Gets the value of the categoriesResponse property.
     * 
     * @return
     *     possible object is
     *     {@link CategoriesResponseType }
     *     
     */
    public CategoriesResponseType getCategoriesResponse() {
        return categoriesResponse;
    }

    /**
     * Sets the value of the categoriesResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriesResponseType }
     *     
     */
    public void setCategoriesResponse(CategoriesResponseType value) {
        this.categoriesResponse = value;
    }

}
