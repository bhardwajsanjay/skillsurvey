package au.com.redbackconsulting.skillsurvey.api.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import au.com.redbackconsulting.skillsurvey.api.SurveyDataService;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview.SqlViewDropDownBean;

public class SQLViewConfigReader {
	Properties properties = new Properties();

	public SQLViewConfigReader(HttpServletRequest request) {
		try {

			final InputStream is = request.getServletContext()
					.getResourceAsStream("/WEB-INF/config.properties");
			try {
				properties.load(is);

			} finally {
				is.close();
			}

		} catch (Exception asd) {

		}

	}

	public String getQuery(String keyValue) {

		try {

			Set<Object >  keySet = properties.keySet();
			Iterator<Object> it =keySet.iterator();
			String myKey =null;
			while (it.hasNext()){
				String keyname  = (String) it.next();
				
				String value = (String) properties.get(keyname);
				
				if(value!=null){
					if(value.equalsIgnoreCase(keyValue)){
						myKey= keyname;
						break;
						
					}
					
				}
				
			}
			
			if (myKey!=null){
				
				String[] parts = myKey.split("_");
				
				
				int len = parts.length;
				if(parts.length==3){
				String part3 = parts[len-1];
				String part1 = parts[0];
				String queryKey = part1+"_"+ "QUERY"+ "_"+part3;
				String queryvalue =(String) properties.get(queryKey);
				return queryvalue;
				}
			}
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;

	}
	
	public List<SqlViewDropDownBean> getLabels( ){
		
		List<SqlViewDropDownBean> result = new ArrayList<SqlViewDropDownBean>();
		
		try {
			List<String> labelKeys = new ArrayList<String>();
			Set<Object >  keySet = properties.keySet();
			Iterator<Object> it =keySet.iterator();
			while (it.hasNext()) {
				String keyName = (String) it.next();
				String[] parts = keyName.split("_");
			 
				if(parts.length==3){
					if(parts[0].equalsIgnoreCase("sqlview") && parts[1].equalsIgnoreCase("label") ){
						labelKeys.add(keyName);
						
					}
				}
				
				
			}
			
			
			for (Iterator iterator = labelKeys.iterator(); iterator.hasNext();) {
				String labelKey = (String) iterator.next();
				String[] parts = labelKey.split("_");
				String keyNo = parts[2];
				Set<Object> propKeyset = properties.keySet();
				for (Iterator iterator2 = propKeyset.iterator(); iterator2
						.hasNext();) {
					String propKey = (String) iterator2.next();
					String[] propParts = propKey.split("_");
					if(propParts.length==3){
						String part1 =propParts[0];
						String part2 = propParts[1];
						String part3 = propParts[2];
						
					if(part1.equalsIgnoreCase("SQLVIEW") && part2.equalsIgnoreCase("KEY") && part3.equalsIgnoreCase(keyNo)){
						
						String labelValue =(String) properties.get(labelKey);
						String keyValue =(String) properties.get(propKey);
						SqlViewDropDownBean bean = new SqlViewDropDownBean(keyValue	, labelValue);
						result.add(bean);
					}
					}
					
				}
				
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}

}
