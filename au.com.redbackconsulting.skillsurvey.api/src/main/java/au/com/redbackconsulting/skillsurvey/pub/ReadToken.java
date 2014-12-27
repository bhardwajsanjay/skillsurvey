package au.com.redbackconsulting.skillsurvey.pub;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import au.com.redbackconsulting.skillsurvey.api.SurveyDataService;

public class ReadToken {
	
	
	private static HashMap<String, String> tokenMap =null;
	public static String  usertokenName ="usertoken";
	
	public static String readTokenProperties(String keyName,
			HttpServletRequest request) {

		if (ReadToken.tokenMap == null) {
			ReadToken.tokenMap= new HashMap<String, String>();

			try {
				Properties properties = new Properties();
				final InputStream is = request.getServletContext()
						.getResourceAsStream("/WEB-INF/token.properties");
				try {
					properties.load(is);
					Set  collection = properties.entrySet();
				 	
					for (Iterator iterator = collection.iterator(); iterator
							.hasNext();) {
						Map.Entry entry= (Map.Entry) iterator.next();
						String key = (String) entry.getKey();
						String value =(String) entry.getValue();
						
						ReadToken.tokenMap.put(key, value);
					}
			 
				} finally {
					is.close();
				}

				
			 
			} catch (Exception asd) {
				return null;
			}
		}
		
		if(ReadToken.tokenMap!=null){
		String value =ReadToken.tokenMap.get(keyName);
		return value;
		}
		return null;

	}
	 
	

}
