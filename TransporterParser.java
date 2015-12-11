import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * The TransporterParser is for any methods involving the conversion of JSON 
 * data to Transporter objects or vice versa.
 */
public class TransporterParser {
    /**
     * Constructor, currently it does not do anything special
     */
    public TransporterParser() {

    }



    /**
     * Reads in a JSON file, parses it and inputs the data into an ArrayList of
     * Transporter objects.
     * 
     * @param filename the name of the JSON file to read the transporter info from
     * @return an ArrayList of Transporter objects
     */
    public ArrayList<Transporter> parseFromFile(String filename) {
	ArrayList<Transporter> transporters = new ArrayList<Transporter>();
	JSONParser parser = new JSONParser();

	try {
	    Object obj = parser.parse(new FileReader(filename));
	    JSONArray jsonArray = (JSONArray) obj;

	    Iterator<JSONObject> iterator = jsonArray.iterator();
	    while (iterator.hasNext()) {
		JSONObject jobj = iterator.next();;
		String location = (String) jobj.get(Transporter.TAG_LOCATION);
		String coords = (String) jobj.get(Transporter.TAG_COORDS);
		ArrayList<HashMap<String, String>> transports_to = new ArrayList<HashMap<String, String>>();

		JSONArray places = (JSONArray) jobj.get(Transporter.TAG_GOINGTO);
		Iterator<JSONObject> it = places.iterator();
		while (it.hasNext()) {
		    JSONObject j = it.next(); 
		    HashMap<String, String> map = new HashMap<String, String>();
		    
		    map.put(Transporter.TAG_PLACE, (String) j.get(Transporter.TAG_PLACE));
		    map.put(Transporter.TAG_PRICE, (String) j.get(Transporter.TAG_PRICE));
		    transports_to.add(map);
		}

		transporters.add(new Transporter(location, coords, transports_to));
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ParseException e) {
	    e.printStackTrace();
	}

	return transporters;
    }

}
