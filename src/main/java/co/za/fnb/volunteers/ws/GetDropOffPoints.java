
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
 *         &lt;element name="DropOffPointsRequest" type="{http://www.example.org/DropOffPoints/}DropOffPointsRequestType"/>
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
    "dropOffPointsRequest"
})
@XmlRootElement(name = "getDropOffPoints")
public class GetDropOffPoints {

    @XmlElement(name = "DropOffPointsRequest", required = true)
    protected DropOffPointsRequestType dropOffPointsRequest;

    /**
     * Gets the value of the dropOffPointsRequest property.
     * 
     * @return
     *     possible object is
     *     {@link DropOffPointsRequestType }
     *     
     */
    public DropOffPointsRequestType getDropOffPointsRequest() {
        return dropOffPointsRequest;
    }

    /**
     * Sets the value of the dropOffPointsRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link DropOffPointsRequestType }
     *     
     */
    public void setDropOffPointsRequest(DropOffPointsRequestType value) {
        this.dropOffPointsRequest = value;
    }

}
