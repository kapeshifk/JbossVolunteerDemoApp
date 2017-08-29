
package co.za.fnb.volunteers.ws;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.za.fnb.volunteers.ws package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.za.fnb.volunteers.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDropOffPoints }
     * 
     */
    public GetDropOffPoints createGetDropOffPoints() {
        return new GetDropOffPoints();
    }

    /**
     * Create an instance of {@link DropOffPointsRequest }
     * 
     */
    public DropOffPointsRequest createDropOffPointsRequest() {
        return new DropOffPointsRequest();
    }

    /**
     * Create an instance of {@link DropOffPointsResponseType }
     * 
     */
    public DropOffPointsResponseType createDropOffPointsResponseType() {
        return new DropOffPointsResponseType();
    }

    /**
     * Create an instance of {@link DropOffPointsRequestType }
     * 
     */
    public DropOffPointsRequestType createDropOffPointsRequestType() {
        return new DropOffPointsRequestType();
    }

    /**
     * Create an instance of {@link GetDropOffPointsResponse }
     * 
     */
    public GetDropOffPointsResponse createGetDropOffPointsResponse() {
        return new GetDropOffPointsResponse();
    }

}
