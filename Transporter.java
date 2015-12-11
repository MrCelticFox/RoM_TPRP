import java.util.ArrayList;
import java.util.HashMap;


/**
 * A Transporter object which stores the location (town) of a given transporter,
 * their exact coordinates (to find them easier ingame), a list of transporters
 * they can send you to and how much it costs to be sent there.
 */
public class Transporter {
    // these values dictate the attribute names in the JSON source file
    public static final String TAG_LOCATION = "location";
    public static final String TAG_COORDS = "coordinates";
    public static final String TAG_GOINGTO = "transports-to";
    public static final String TAG_PLACE = "to-location";
    public static final String TAG_PRICE = "price";
    
    private String location;
    private String coords;
    private ArrayList<HashMap<String, String>> transports_to;
    
    
    
    /**
     * Returns a new Transporter object.
     * 
     * @param location the name of the town the transporter is in
     * @param coords their exact coordinates in a string of the shape (x;y) 
     * @param transports_to a list of other transporters they can send you to
     */
    public Transporter(String location, String coords, ArrayList<HashMap<String, String>> transports_to) {
	this.location = location;
	this.coords = coords;
	this.transports_to = transports_to;
    }
    
    
    
    /**
     * Returns a String representing the Transporter
     */
    public String toString() {
	return location + "\n" + coords + "\n" + transports_to;
    }

}
