import java.util.ArrayList;
import java.util.HashMap;


public class Transporter {
    public static final String TAG_PLACE = "place";
    public static final String TAG_PRICE = "price";
    
    private String location;
    private String coords;
    private ArrayList<HashMap<String, String>> transports_to; // = new ArrayList<HashMap<String, String>>();
    
    public Transporter(String location, String coords, ArrayList<HashMap<String, String>> transports_to) {
	this.location = location;
	this.coords = coords;
	this.transports_to = transports_to;
    }
    
 // creating new HashMap
    //HashMap<String, String> map = new HashMap<String, String>();

    // adding each child node to HashMap key => value
    //map.put(TAG_CODE, code);
    //map.put(TAG_NAME, name);
}
