package au.com.redbackconsulting.skillsurvey.api;

import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.redbackconsulting.skillsurvey.api.bean.FunctionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.LevelBean;
import au.com.redbackconsulting.skillsurvey.api.bean.OccupationBean;
import au.com.redbackconsulting.skillsurvey.api.bean.RoleBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.GenderBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.OverviewListSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.PathwayBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileDetailsBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileListItemBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfilePwdChangeBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileRoleBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.QuestionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ReportProfileInfoBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ReportSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SessionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyDataBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyGapReport;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyMergedReport;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyReportSkillRecognitionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.CourseBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.FunctionalReport;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.FunctionalReportDetailRepItemBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.Count;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.ReportDetailBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.ReportHigherEducationPathwayBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview.SQLVIEWBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview.SQLViewCollBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview.SqlViewDropDownBean;
import au.com.redbackconsulting.skillsurvey.persistence.FunctionDAO;
import au.com.redbackconsulting.skillsurvey.persistence.IndividualDAO;
import au.com.redbackconsulting.skillsurvey.persistence.NeedDAO;
import au.com.redbackconsulting.skillsurvey.persistence.OccupationDAO;
import au.com.redbackconsulting.skillsurvey.persistence.model.Function;
import au.com.redbackconsulting.skillsurvey.persistence.model.Level;
import au.com.redbackconsulting.skillsurvey.persistence.model.Need;
import au.com.redbackconsulting.skillsurvey.persistence.model.Occupation;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_COURSES_BY_GENDER_LEVEL;
import au.com.redbackconsulting.skillsurvey.service.UserLock;

@Path("/surveydata")
public class SurveyDataService extends BaseService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static HashMap<String, String> configMap =null;
	private static HashMap<String, String> textMap =null;
	private static HashMap<String, String> mailConfigMap =null;
	
	



	public static String md5(String input) {
	    
	    String md5 = null;
	     
	    if(null == input) return null;
	     
	    try {
	         
	    //Create MessageDigest object for MD5
	    MessageDigest digest = MessageDigest.getInstance("MD5");
	     
	    //Update input string in message digest
	    digest.update(input.getBytes(), 0, input.length());

	    //Converts message digest value in base 16 (hex) 
	    md5 = new BigInteger(1, digest.digest()).toString(16);

	    } catch (NoSuchAlgorithmException e) {

	        e.printStackTrace();
	    }
	    return md5;
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/report/view/list")
	public List<SqlViewDropDownBean> getCSVDropDownList(){
		
		try {

			SurveyDataServiceHelper helper= new SurveyDataServiceHelper(request);
			List<SqlViewDropDownBean>  result =helper.getCSVDropDownList();
			return result;
		} catch (Exception e) {
			return  null;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/report/view/{key}")
	public SQLViewCollBean getSurveyResponse(@PathParam("key") String key){
		
		try {

			SurveyDataServiceHelper helper= new SurveyDataServiceHelper(request);
			 SQLViewCollBean  result =helper.getSURVEY_RESPONSE(key);
			return result;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GET
	@Path("/public/register")
	public String registerNewUser(){
		return "Register Ok";
	}
	
	@GET
	@Path("/sendmail/{toemailid}")
	public Response sendMail(@PathParam("toemailid") String emailId){
		
		try {
			SurveyDataServiceHelper helper= new SurveyDataServiceHelper(request);
			String subject = "Test Subject";
			String message ="Test Message";
			boolean status =helper.sendMail(emailId,subject, message );
			if(status)
				return createOkResponse();
			else
				return createBadRequestResponse("Error while sending email");

		} catch (Exception e) {
			return createBadRequestResponse(e.getMessage());
		}
		
		
		 
	}
	
	
	@GET
	@Path("/report/get/functions")
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<FunctionBean> getAllFunctions(){
		logger.info("Test Message");
		logger.debug("Test debugger");
		List<FunctionBean> collection = new ArrayList<FunctionBean>();
		FunctionBean pleaseSelect= new FunctionBean();
		pleaseSelect.setId(-1);
		pleaseSelect.setDescription("Please Select");
		pleaseSelect.setName("Please Select");
		collection.add(pleaseSelect);
		
		try {
			FunctionDAO functionDao =new FunctionDAO();
			List<Function> functions = functionDao.getAll();
			
			
			int len = functions.size();
			if (len ==0)
				return collection;
			Function temp[] = new Function[len];						
			 functions.toArray(temp);
			 
			 
			 Arrays.sort(temp ,Function.functionNameComparator);

			functions = Arrays.asList(temp);
			
			
			
			for (Iterator iterator = functions.iterator(); iterator.hasNext();) {
				Function function = (Function) iterator.next();
				FunctionBean bean =FunctionBean.get(function);
				collection.add(bean);
			}
			

			
			return collection;

		} catch (Exception e) {
return collection;
		}
		
	}
	
	
	
	@GET
	@Path("/report/orgreport")
	@Produces(MediaType.APPLICATION_JSON)
	public FunctionalReportDetailRepItemBean callforSP_FR_BY_GENDER_FUNCTION( ){
	
		try {
		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(request);
		return helper.getFR_BY_GENDER_FUNCTION();
	} catch (Exception e) {
		return new FunctionalReportDetailRepItemBean();
	}	
		
	}
	// List<FunctionalReport>
	@GET
	@Path("/report/functionbylevel")
	@Produces(MediaType.APPLICATION_JSON)
	public FunctionalReportDetailRepItemBean callforSP_FR_BY_GENDER_LEVEL( ){
	
		try {
		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(request);
		return helper.getFR_BY_GENDER_LEVEL();
	} catch (Exception e) {
		return new FunctionalReportDetailRepItemBean();
	}	
		
	}
	
	@GET
	@Path("/report/educationpathway")
	@Produces(MediaType.APPLICATION_JSON)
	public ReportHigherEducationPathwayBean callforEducationPathway( ){
	
		try {
		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(request);
		return helper.getReportEducationPathway();
	} catch (Exception e) {
		return new ReportHigherEducationPathwayBean();
	}	
		
	}
	
	// List<FunctionalReportByLocation>
		@GET
		@Path("/report/functionbylocation")
		@Produces(MediaType.APPLICATION_JSON)
		public FunctionalReportDetailRepItemBean callforSP_FR_BY_GENDER_LOCATION( ){
		
			try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(request);
			FunctionalReportDetailRepItemBean result = helper.getFR_BY_GENDER_LOCATION(); 
		
			return result;
		} catch (Exception e) {
			return new FunctionalReportDetailRepItemBean();
		}	
			
		}
		
		// List<FunctionalReportByLocation>
		@GET
		@Path("/report/functionbycourse")
		@Produces(MediaType.APPLICATION_JSON)
		public FunctionalReportDetailRepItemBean callforSP_FR_BY_GENDER_UOC_COURSE( ){
		
			try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(request);
			return helper.getFR_BY_GENDER_UOC_COURSE();
		} catch (Exception e) {
			return new FunctionalReportDetailRepItemBean();
		}	
			
		}
			


	@GET
	@Path("/session")
	@Produces(MediaType.APPLICATION_JSON)
	public SessionBean getSessionDetail() {

		try {
			HttpSession session = request.getSession(true);
			if (session!=null){
		int maxInactiveIntervnal =	session.getMaxInactiveInterval();
		String sessionId = session.getId();	
		
		SessionBean bean = new SessionBean();
	//	bean.setSessionId(sessionId);
		bean.setTimeInterval(maxInactiveIntervnal);
		bean.setCreationTime(session.getCreationTime());
		bean.setLastAccessedtime(session.getLastAccessedTime());
		return bean;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		// return _saveProfile(profileSurveyBean);
	}
	
	
	@POST
	@Path("/admin/profile/role/edit/save")
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces (MediaType.APPLICATION_JSON)
	public Response adminEditRoleSave(ProfileRoleBean profile) 
	{
		try {

 
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			boolean result = helper.adminEditRoleSave(profile );
			if (result)
				return createOkResponse();
			return createBadRequestResponse("Wrong");
		} catch (Exception e) {
			return createBadRequestResponse("Wrong");
		}
		// return _saveProfile(profileSurveyBean);
	}

	
	@GET
	@Path("/admin/profile/role/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileRoleBean adminGetRolesById(@PathParam("id") String loginId) {
		ProfileRoleBean profileRole = null;

		try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			profileRole = helper.admingetProfileRolesById(loginId);
			return profileRole;
		} catch (Exception e) {
			return new ProfileRoleBean();
		}
		// return _saveProfile(profileSurveyBean);
	}

	@GET
	@Path("/admin/roles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RoleBean> adminGetRoles() {
		List<RoleBean> roles = null;

		try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			roles = helper.getRoles();
			return roles;
		} catch (Exception e) {
			return roles;
		}
		// return _saveProfile(profileSurveyBean);
	}

//	@GET
//	@Path("/admin/unlockuser/{loginid}")
//	public Response adminUnlockUser(@PathParam("loginid") String loginId) {
//		try {
//			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
//					request);
//			boolean result = helper.unlockUser(loginId);
//			if (result)
//				return createOkResponse();
//			return createBadRequestResponse("Wrong");
//		} catch (Exception e) {
//			return createBadRequestResponse("Wrong");
//		}
//		// return _saveProfile(profileSurveyBean);
//	}
//
//	@GET
//	@Path("/admin/lockuser/{loginid}")
//	public Response adminLockUser(@PathParam("loginid") String loginId) {
//		try {
//			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
//					request);
//			boolean result = helper.lockUser(loginId);
//			if (result)
//				return createOkResponse();
//			return createBadRequestResponse("Wrong");
//		} catch (Exception e) {
//			return createBadRequestResponse("Wrong");
//		}
//		 
//	}
//	
 	

	@POST
	@Path("/admin/profile/pwd/save")
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces (MediaType.APPLICATION_JSON)
	public Response adminChangePassword(ProfilePwdChangeBean profilePwd) {
		try {

			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			boolean result = helper.adminChangePassword(profilePwd);
			if (result)
				return createOkResponse();
			return createBadRequestResponse("Wrong");
		} catch (Exception e) {
			return createBadRequestResponse("Wrong");
		}
		// return _saveProfile(profileSurveyBean);
	}

	@GET
	@Path("/logout")
	public Response logout() {

		try {
			String user = getLoggedInUserId();
			UserLock.getInstance().deleteUserLock(user);
			request.getSession().invalidate();
			return createOkResponse();
		} catch (Exception e) {
			return createBadRequestResponse("Something went Wrong");
		}

	}

	@GET
	@Path("/get/{needname}")
	@Produces(MediaType.APPLICATION_JSON)
	public SurveyDataBean getSurvey(@PathParam("needname") String needName) {
		try {
		 	SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			SurveyDataBean bean = helper.getSurvey(needName);
			return bean;
		} catch (Exception e) {
			return new SurveyDataBean();
		}

	}

	@GET
	@Path("/overview")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OverviewListSurveyBean> getOverview() {
		List<OverviewListSurveyBean> report = new ArrayList<OverviewListSurveyBean>();
		try {
	 		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			report = helper.getOverview();
			return report;

		} catch (Exception e) {
			return report;
		}
	}

	@GET
	@Path("/profile/username")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserName() {

		String name = getLoggedInUserId();
		return name;
	}

	@GET
	@Path("/profile/rolename")
	@Produces(MediaType.TEXT_PLAIN)
	public String getRoleName() {

		try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			return helper.getRoleName();

		} catch (Exception e) {
			return null;
		}
		// boolean status = false;
		// status = request.isUserInRole(adminType);
		// if (status)
		// return adminType;
		// status = request.isUserInRole(individualType);
		// if (status)
		// return individualType;
		// status = request.isUserInRole(reportingType);
		// if (status)
		// return reportingType;
		// return "NoRole";

	}

	@GET
	@Path("/admin/profile/blank")
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileListItemBean getProfileModel() {

		try {
	 		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			return helper.getBlankUserProfile();
		} catch (Exception e) {
			return new ProfileListItemBean();
		}

	}

	@GET
	@Path("/admin/profile/list")
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileDetailsBean adminGetProfileList() {
		try {
	 		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			return helper.getProfiles();
		} catch (Exception e) {
			return new ProfileDetailsBean();
		}

	}

	@GET
	@Path("/profile/status")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean getProfilestatus() {

		try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			return helper.getProfileStatus();
		} catch (Exception e) {
			return false;
		}
	 	}

	@GET
	@Path("/report/gap")
	@Produces(MediaType.APPLICATION_JSON)
	public SurveyGapReport getGap() {

		try {
 			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			SurveyGapReport report = helper.getGapReport();
			return report;
		} catch (Exception e) {
			return new SurveyGapReport();
		}

	}

	@GET
	@Path("/report/merged")
	@Produces(MediaType.APPLICATION_JSON)
	public SurveyMergedReport getMergedReport() {
		try {
	 		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			SurveyMergedReport report = helper.getMergedReport1();
			return report;
		} catch (Exception e) {
			return new SurveyMergedReport();
		}
	}

	@GET
	@Path("/report/recognition")
	@Produces(MediaType.APPLICATION_JSON)
	public SurveyReportSkillRecognitionBean getRecognition() {
		try {
	 		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			SurveyReportSkillRecognitionBean report = helper
					.getRecognitionReport();
			return report;
		} catch (Exception e) {
			return new SurveyReportSkillRecognitionBean();
		}
	}

	private static List<GenderBean> genders;

 

	@GET
	@Path("/report/{need}")
	@Produces(MediaType.APPLICATION_JSON)
	public ReportSurveyBean getReport(@PathParam("need") String needName) {
 		try {

			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			ReportSurveyBean report = helper.getNeedReport(needName);
			return report;
		} catch (Exception e) {
			return new ReportSurveyBean();
		}
	}

	@GET
	@Path("/menu")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMenu1() {
		try {
			// String loginId = getLoggedInUserId();

			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			String result = helper.getMenu1();
			return result;

		} catch (Exception e) {
			return null;
		}
	}

	@POST
	@Path("/profile/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	// @Produces (MediaType.APPLICATION_JSON)
	public Response editProfile(ProfileSurveyBean profileSurveyBean) {
		try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			boolean result = helper.saveProfile(profileSurveyBean);
			if (result)
				return createOkResponse();
			return createBadRequestResponse("Wrong");
		} catch (Exception e) {
			return createBadRequestResponse("Wrong");
		}
		// return _saveProfile(profileSurveyBean);
	}

	 

	public static HashMap<String, String> menus = new HashMap<String, String>();

	public static String readMenuJSON(String fileName,
			HttpServletRequest request) {
		HashMap<String, String> menus = SurveyDataService.menus;
		String menu = menus.get(fileName);
		if (menu == null) {

			try {

				final InputStream is = request.getServletContext()
						.getResourceAsStream("/WEB-INF/" + fileName);
				try {
					StringBuilder out = new StringBuilder();
					byte[] bytes = null;// lineNo;

					int data = 0;
					while ((data = is.read()) != -1) {
						char c = (char) data;
						out.append(c);
					}
					menu = out.toString();
					menus.put(fileName, menu);
				} finally {
					is.close();

				}

			} catch (Exception asd) {

			}

		}

		return menu;
	}

	private static List<PathwayBean> pathways;

 

	private static HashMap<String, QuestionBean> questions;

	public static  QuestionBean  readQuestionProperties(String questionNo,
			HttpServletRequest request) {

		if (SurveyDataService.questions == null) {
			SurveyDataService.questions = new HashMap<String, QuestionBean>();

			try {
				Properties properties = new Properties();
				final InputStream is = request.getServletContext()
						.getResourceAsStream("/WEB-INF/questions.properties");
				try {
					properties.load(is);
				} finally {
					is.close();
				}
				Set  collection = properties.entrySet();
			
				for (Iterator iterator = collection.iterator(); iterator
						.hasNext();) {
					Map.Entry entry= (Map.Entry) iterator.next();
					String key = (String) entry.getKey();
					String value =(String) entry.getValue();
					QuestionBean bean = new QuestionBean();
					bean.setId(Long.parseLong(key));
					bean.setQuestion(value);
					questions.put(key, bean);
					

				}
				
				
				SurveyDataService.questions = questions;
			} catch (Exception asd) {
				return null;
			}
		}
		
		QuestionBean bean = SurveyDataService.questions.get(questionNo);
		return bean ;

	}


	public static String readTextProperties(String keyName,
			HttpServletRequest request) {

		if (SurveyDataService.textMap == null) {
			SurveyDataService.textMap = new HashMap<String, String>();

			try {
				Properties properties = new Properties();
				final InputStream is = request.getServletContext()
						.getResourceAsStream("/WEB-INF/reporttexts.properties");
				try {
					properties.load(is);
					Set  collection = properties.entrySet();
				 	
					for (Iterator iterator = collection.iterator(); iterator
							.hasNext();) {
						Map.Entry entry= (Map.Entry) iterator.next();
						String key = (String) entry.getKey();
						String value =(String) entry.getValue();
						
						SurveyDataService.textMap.put(key, value);
					}
			 
				} finally {
					is.close();
				}

				
			 
			} catch (Exception asd) {
				return null;
			}
		}
		
		if(SurveyDataService.textMap!=null){
		String value =SurveyDataService.textMap.get(keyName);
		return value;
		}
		return null;

	}
	 
	
	public static String readMailConfigProperties(String keyName,
			HttpServletRequest request) {

		if (SurveyDataService.mailConfigMap == null) {
			SurveyDataService.mailConfigMap = new HashMap<String, String>();

			try {
				Properties properties = new Properties();
				final InputStream is = request.getServletContext()
						.getResourceAsStream("/WEB-INF/mailconfig.properties");
				try {
					properties.load(is);
					Set  collection = properties.entrySet();
				 	
					for (Iterator iterator = collection.iterator(); iterator
							.hasNext();) {
						Map.Entry entry= (Map.Entry) iterator.next();
						String key = (String) entry.getKey();
						String value =(String) entry.getValue();
						
						SurveyDataService.mailConfigMap.put(key, value);
					}
			 
				} finally {
					is.close();
				}

				
			 
			} catch (Exception asd) {
				return null;
			}
		}
		
		if(SurveyDataService.mailConfigMap!=null){
		String value =SurveyDataService.mailConfigMap.get(keyName);
		if(value!=null){
			if(value.length()==0)
				return null;
		}
			
		return value;
		}
		return null;

	}
	 
	

 
	public static String readConfigProperties(String keyName,
			HttpServletRequest request) {

		if (SurveyDataService.configMap == null) {
			SurveyDataService.configMap = new HashMap<String, String>();

			try {
				Properties properties = new Properties();
				final InputStream is = request.getServletContext()
						.getResourceAsStream("/WEB-INF/config.properties");
				try {
					properties.load(is);
					Set  collection = properties.entrySet();
				 	
					for (Iterator iterator = collection.iterator(); iterator
							.hasNext();) {
						Map.Entry entry= (Map.Entry) iterator.next();
						String key = (String) entry.getKey();
						String value =(String) entry.getValue();
						
						SurveyDataService.configMap.put(key, value);
					}
			 
				} finally {
					is.close();
				}

				
			 
			} catch (Exception asd) {
				return null;
			}
		}
		
		if(SurveyDataService.configMap!=null){
		String value =SurveyDataService.configMap.get(keyName);
		return value;
		}
		return null;

	}
	 

	@GET
	@Path("/admin/profile/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileListItemBean getUserProfile(
			@PathParam("userid") String selectedUserlogindId) {
		try {

			String loginId = getLoggedInUserId();
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);

			return helper.getSelectedUserProfile(selectedUserlogindId);

		} catch (Exception e) {
			return new ProfileListItemBean();
		}
	}

	// to get Profile data
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileSurveyBean getProfile() {
		try {
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			ProfileSurveyBean profileBean = helper.getProfile();// _getProfile();
			return profileBean;
		} catch (Exception e) {
			 
		}
		// ProfileSurveyBean profileBean = _getProfile();

		return new ProfileSurveyBean();

	}

	@GET
	@Path("/profile/get/levels/{occupationid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LevelBean> getLevels(
			@PathParam("occupationid") String occupationId) {
		List<LevelBean>  levelBeans = new ArrayList<LevelBean>() ;
		LevelBean bean = new LevelBean();
		bean.setId(-1);
		bean.setDescription("Please Select");
		bean.setCode("-1");
		levelBeans.add(bean);
		try {
			OccupationDAO occupationDao = new OccupationDAO();
			Occupation occupation = occupationDao.getById(Long
					.parseLong(occupationId));
			List<Level> levels = occupation.getLevels();
		 
			Collections.sort(levels, Level.LevelNameComparator);
			
//			int len = levels.size();
//			Level temp[] = new Level[len];						
//			 levels.toArray(temp);
//			 
//			 
//			 Arrays.sort(temp ,Level.LevelNameComparator);
//
//			levels = Arrays.asList(temp);
//			
			 
			
			for (Iterator iterator = levels.iterator(); iterator.hasNext();) {
				Level level = (Level) iterator.next();
				LevelBean lbean = new LevelBean();
				lbean.setCode(level.getCode());
				lbean.setDescription(level.getDescription());
				lbean.setId(level.getIdlevel());
				levelBeans.add(lbean);
			}

 
	return levelBeans;

		} catch (Exception e) {
			return levelBeans;
		}
	}

	@GET
	@Path("/profile/get/jobtitles/{functionid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OccupationBean> getJobTiles(
			@PathParam("functionid") String functionId) {
		List<OccupationBean> occupationBeans = new ArrayList<OccupationBean>();
		OccupationBean bean = new OccupationBean();
		bean.setId(-1);
		bean.setName("Please Select");
		bean.setDescription("Please Select");
		occupationBeans.add(bean);

		FunctionDAO functionDao = new FunctionDAO();
		Function function = functionDao.getById(Long.parseLong(functionId));
		if (function != null) {
			List<Occupation> occupations = function.getOccupations();
			
			Collections.sort(occupations, Occupation.occupationNameComparator);
			

			if (occupations != null) {
				for (Iterator iterator = occupations.iterator(); iterator
						.hasNext();) {
					Occupation occupation = (Occupation) iterator.next();
					OccupationBean occBean = new OccupationBean();
					occBean.setId(occupation.getIdoccupation());
					occBean.setName(occupation.getName());
					occBean.setDescription(occupation.getDescription());
					occupationBeans.add(occBean);
				}
			}
		}
		return occupationBeans;
	}

	 

	@GET
	@Path("/admin/profile/get/edit/{loginid}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProfileListItemBean adminGetUserByAdmin(
			@PathParam("loginid") String userId) {
		try {
			String loginId = getLoggedInUserId();
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			return helper.getEditUserByAdmin(userId);
		} catch (Exception e) {
			return new ProfileListItemBean();
		}

	}

	@POST
	@Path("/admin/profile/edit/save")
	@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Response adminSaveEditUser(ProfileListItemBean bean) {
		try {
			String loginId = getLoggedInUserId();
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			helper.editUserByAdmin(bean);
			return createOkResponse();
		} catch (Exception e) {
			return createBadRequestResponse(e.getMessage());
		}

	}

	@POST
	@Path("/admin/profile/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response adminCreateNewUser(ProfileListItemBean bean) {
		try {
			String loginId = getLoggedInUserId();
			SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			helper.createNewUserByAdmin(bean);
			return createOkResponse();
		} catch (Exception e) {
return createBadRequestResponse(e.getMessage());
		}
		 
	}

	@POST
	@Path("/survey/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createNewSurvey(SurveyDataBean surveyDataBean) {
		try {
	 		SurveyDataServiceHelper helper = new SurveyDataServiceHelper(
					request);
			helper.saveSurvey(surveyDataBean);
			return createOkResponse();
		} catch (Exception e) {
			return createBadRequestResponse(e.getMessage());
		}

	}

	 

}
