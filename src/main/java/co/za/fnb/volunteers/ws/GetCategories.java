
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
 *         &lt;element name="CategoriesRequest" type="{http://www.example.org/Categories/}CategoriesRequestType"/>
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
    "categoriesRequest"
})
@XmlRootElement(name = "getCategories")
public class GetCategories {

    @XmlElement(name = "CategoriesRequest", required = true)
    protected CategoriesRequestType categoriesRequest;

    /**
     * Gets the value of the categoriesRequest property.
     * 
     * @return
     *     possible object is
     *     {@link CategoriesRequestType }
     *     
     */
    public CategoriesRequestType getCategoriesRequest() {
        return categoriesRequest;
    }

    /**
     * Sets the value of the categoriesRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriesRequestType }
     *     
     */
    public void setCategoriesRequest(CategoriesRequestType value) {
        this.categoriesRequest = value;
    }

}
