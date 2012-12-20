import java.util.ArrayList;


public class CSVService {
	public static ArrayList<String> getFields(String csv){
		
		ArrayList<String> fields = new ArrayList<String>();
		boolean fieldStarted = false;
		StringBuffer tmpBuffer = new StringBuffer();
		for (int i = 0; i < csv.length(); i++) {
		    char ch = csv.charAt(i);
		    if (ch == '\"'){
		        fieldStarted = !fieldStarted;
		    } else if ((ch == ',' && !fieldStarted)) {
		        fields.add((tmpBuffer.toString()).replaceAll("\"",""));
		        tmpBuffer = new StringBuffer();
		    } else {
		        tmpBuffer.append(ch);
		    }
		}
		fields.add((tmpBuffer.toString().replaceAll("\"","")));
		return fields;
	}
}
