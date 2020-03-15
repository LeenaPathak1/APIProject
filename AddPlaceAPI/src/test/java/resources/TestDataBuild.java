package resources;

import java.util.ArrayList;
import java.util.List;

import Payload.AddPlace;
import Payload.Location;

public class TestDataBuild {
	AddPlace ap = new AddPlace();
	
	public AddPlace createPayload(String name,String language,String address){
		
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://216.10.245.166");
		ap.setName(name);
		
		List<String> mytypes = new ArrayList<String>();
		mytypes.add("shoe park");
		mytypes.add("shop");
		ap.setTypes(mytypes);
		
		Location l = new Location();
		
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		return ap;
		
	}
	
	public String deletePlacePayload(String place_id){
		return "{\r\n    \"place_id\":\""+place_id+"\"          \r\n}";
		
	}

}
