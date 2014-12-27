package au.com.redbackconsulting.skillsurvey.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.google.gson.Gson;

import au.com.redbackconsulting.skillsurvey.api.bean.FunctionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.LevelBean;
import au.com.redbackconsulting.skillsurvey.api.bean.OccupationBean;
import au.com.redbackconsulting.skillsurvey.api.bean.RoleBean;
import au.com.redbackconsulting.skillsurvey.api.bean.UocBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ChoiceBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.GenderBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.LocationBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.MenuBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.MenusBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.OverviewListSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.PathwayBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileDetailsBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileListItemBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfilePwdChangeBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileRoleBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProfileSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProviderBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.QuestionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.QuestionSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ReportMergedSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ReportProfileInfoBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ReportSurveyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.ReportUocBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SkillRecogReportBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyDataBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyGapReport;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyMergedReport;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.SurveyReportSkillRecognitionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.UnitOfCompetencyBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.UocCourseStatusBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.UocCourseStatusRecognitionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.UserTypeBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.CourseBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.FunctionalReport;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.FunctionalReportDetailRepItemBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.Count; 
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.ReportDetailBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.ReportHigherEducationItemBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.ReportHigherEducationPathwayBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.ReportLearningPathwayItemBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview.SQLVIEWBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview.SQLViewCollBean;
import au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview.SqlViewDropDownBean;
import au.com.redbackconsulting.skillsurvey.api.exception.NoAnsweredFoundForExistingSurvey;
import au.com.redbackconsulting.skillsurvey.api.exception.NoNeedMappedToUserType;
import au.com.redbackconsulting.skillsurvey.api.exception.NoSuchDapsscoExistException;
import au.com.redbackconsulting.skillsurvey.api.exception.NoSuchNeedExistException;
import au.com.redbackconsulting.skillsurvey.api.exception.NoSuchSurveyExistException;
import au.com.redbackconsulting.skillsurvey.api.exception.NoSuchUserExistException;
import au.com.redbackconsulting.skillsurvey.api.exception.NoUOCExistException;
import au.com.redbackconsulting.skillsurvey.api.exception.PrimaryQuestionNotExistsException;
import au.com.redbackconsulting.skillsurvey.api.exception.ProfileNotUpdatedException;
import au.com.redbackconsulting.skillsurvey.api.exception.SurveyCreateException;
import au.com.redbackconsulting.skillsurvey.api.exception.SurveyDataHelperException;
import au.com.redbackconsulting.skillsurvey.api.util.SQLViewConfigReader;
import au.com.redbackconsulting.skillsurvey.persistence.DapsscoDAO;
import au.com.redbackconsulting.skillsurvey.persistence.FunctionDAO;
import au.com.redbackconsulting.skillsurvey.persistence.GenderDao;
import au.com.redbackconsulting.skillsurvey.persistence.IndividualDAO;
import au.com.redbackconsulting.skillsurvey.persistence.LevelDAO;
import au.com.redbackconsulting.skillsurvey.persistence.LocationDAO;
import au.com.redbackconsulting.skillsurvey.persistence.NeedDAO;
import au.com.redbackconsulting.skillsurvey.persistence.OccupationDAO;
import au.com.redbackconsulting.skillsurvey.persistence.PathwayDao;
import au.com.redbackconsulting.skillsurvey.persistence.RoleAssignmentDAO;
import au.com.redbackconsulting.skillsurvey.persistence.RoleDAO;
import au.com.redbackconsulting.skillsurvey.persistence.SurveyAnswerDAO;
import au.com.redbackconsulting.skillsurvey.persistence.SurveyDAO;
import au.com.redbackconsulting.skillsurvey.persistence.UocQuestionDAO;
import au.com.redbackconsulting.skillsurvey.persistence.UserTypeDAO;
import au.com.redbackconsulting.skillsurvey.persistence.model.Dapssco;
import au.com.redbackconsulting.skillsurvey.persistence.model.Function;
import au.com.redbackconsulting.skillsurvey.persistence.model.Gender;
import au.com.redbackconsulting.skillsurvey.persistence.model.Individual;
import au.com.redbackconsulting.skillsurvey.persistence.model.Level;
import au.com.redbackconsulting.skillsurvey.persistence.model.Location;
import au.com.redbackconsulting.skillsurvey.persistence.model.Need;
import au.com.redbackconsulting.skillsurvey.persistence.model.Occupation;
import au.com.redbackconsulting.skillsurvey.persistence.model.Pathway;
import au.com.redbackconsulting.skillsurvey.persistence.model.Provider;
import au.com.redbackconsulting.skillsurvey.persistence.model.Role;
import au.com.redbackconsulting.skillsurvey.persistence.model.RoleAssignment;
import au.com.redbackconsulting.skillsurvey.persistence.model.RoleAssignmentPK;
import au.com.redbackconsulting.skillsurvey.persistence.model.SurveryAnswer;
import au.com.redbackconsulting.skillsurvey.persistence.model.Survey;
import au.com.redbackconsulting.skillsurvey.persistence.model.Uoc;
import au.com.redbackconsulting.skillsurvey.persistence.model.UocGroup;
import au.com.redbackconsulting.skillsurvey.persistence.model.UocQuestion;
import au.com.redbackconsulting.skillsurvey.persistence.model.UserType;
import au.com.redbackconsulting.skillsurvey.persistence.model.sqlview.SurveyResponseBySQLView;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_GENDER_FUNCTION;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_GENDER_LOCATION;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_GENDER_UOC_COURSE;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_HIGHER_EDUCATION;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_PATHWAY;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_COURSES_BY_GENDER_LEVEL;

public final class SurveyDataServiceHelper {

	public final static  String salt = "Random$SaltValue";
	private final static short questionTypePrimary = 1;
	private final static short questionTypesecondary = 2;
	private final static short questionTypeQuestion = 3;

	private final static String answerYes = "Yes";
	private final static String answerNo = "No";
	private final static String answerNotAnswered = "No Answer";

	private final static String statusYes = "Yes";
	private final static String statusNo = "No";
	private final static String statusNotApplicable = "N/A";
	private final static String statusBlank = "";

	private final static String pleaseSelect = "Please Select";

	private static String mandatory = "Mandatory";
	private static String supervisor = "Supervisor";
	private static String desirable = "Desirable";
	private static String highlyDesirable = "highlydesirable";

	final static public String adminType = "admin";
	final static private String individualType = "Individual";
	final static public String reportingType = "report";
	final static private String defaultType = "default";
	final static private String supervisorType = "Supervisor";
	private HttpServletRequest request;

	Individual user = null;
	Function function = null;
	Occupation occupation = null;
	Level level = null;
	Dapssco dapssco = null;
	SurveyDAO surveyDao = null;
	SurveyAnswerDAO surveyAnswerDao = null;

	public boolean getProfileStatus() {
		try {

			String loginId = user.getLogin();

			if (loginId.startsWith(SurveyDataServiceHelper.adminType)
					|| loginId
							.startsWith(SurveyDataServiceHelper.reportingType)) {

				return true;

			}
			UserType userType = user.getUsertypefk();
			Gender gender = user.getGender();
			Location location = user.getLocation();

			if (function == null || occupation == null || level == null
					|| dapssco == null || userType == null || gender == null
					|| location == null)
				return false;
			return true;
			// boolean result = individualDao.getProfileUpdateStatus(name);
			// return result;

		} catch (Exception e) {
			return true;
		}
	}

	public SurveyDataServiceHelper(HttpServletRequest request)
			throws NoSuchUserExistException, ProfileNotUpdatedException,
			NoSuchDapsscoExistException {
		this.request = request;
		String loginId = request.getUserPrincipal().getName();
		IndividualDAO dao = new IndividualDAO();
		Individual indivi = dao.getByLogin(loginId);
		if (indivi == null) {
			throw new NoSuchUserExistException("There is no" + loginId
					+ "user Exist");
		}
		user = indivi;
		function = user.getFunction();
		occupation = user.getOccupation();
		level = user.getLevel();

		if (function == null || occupation == null || level == null) {
			// throw new ProfileNotUpdatedException("Profile is not updated");
		}

		DapsscoDAO dapsscoDao = new DapsscoDAO();
		dapssco = dapsscoDao.getByLevelOccuFunc(level, occupation, function);

		if (dapssco == null) {
			// throw new
			// NoSuchDapsscoExistException("There is no dapssco to this individual");
		}

		surveyDao = new SurveyDAO();
		surveyAnswerDao = new SurveyAnswerDAO();

	}

	public SurveyDataBean getSurvey(String needName) {
		SurveyDataBean surveybean = new SurveyDataBean();
		List<QuestionSurveyBean> questionSurveyBeans = new ArrayList<QuestionSurveyBean>();

		try {

			Need need = _getNeed(needName);
			surveybean.setNeedName(need.getName());
			surveybean.setNeedDescription(need.getDescription());
			List<Uoc> uocs = _getUocCollectionByNeedDapsso(need);
			// List<Uoc> uocs =_filterUniqueUocs(tempUocs);
			List<UocQuestion> uocQuestions = _getUocQuestions(uocs);
			questionSurveyBeans = _convertIntQuestionSurveyBeanCollection(uocQuestions);
			surveybean.setQuestions(questionSurveyBeans);
			List<UocBean> uocBeans = _convertIntoUOCBeans(uocs);
			surveybean.setCourseItems(uocBeans);
			Survey survey = _getSurvey(need);
			long surveyId = survey.getIdsurvey();
			List<SurveryAnswer> answers = _getSurveyAnswersByNeed(need, survey);
			questionSurveyBeans = _getupdatedByAnswerBean(questionSurveyBeans,
					answers, surveyId);
			surveybean.setQuestions(questionSurveyBeans);

			return surveybean;

		} catch (NoSuchNeedExistException e) {
			 
		} catch (NoUOCExistException e) {

		} catch (NoSuchSurveyExistException e) {

		} catch (NoAnsweredFoundForExistingSurvey e) {

			 
			e.printStackTrace();
		}

		return surveybean;

	}
	
	private Date _completedStatus(SurveyDataBean surveyDataBean){
		
		try {
			
			String needName = surveyDataBean.getNeedName();
			List<QuestionSurveyBean> allQuestions  = surveyDataBean.getQuestions();
			List<QuestionSurveyBean> allPrimaryQuestions = _filterAllPrimaryQuestions(allQuestions);
			boolean isAllPrimaryAnsweredYes = _getIsAllPrimaryAnsweredYes(allPrimaryQuestions);
			if(isAllPrimaryAnsweredYes)
				return new Date();
			
			boolean isAnyPrimaryNotAnswered=_getIsAnyPrimaryNotAnswered(allPrimaryQuestions);
			if(isAnyPrimaryNotAnswered)
				return null;
			
			// if all primary is not having yes answered
			if(SurveyDataServiceHelper.mandatory.equalsIgnoreCase(needName)|| SurveyDataServiceHelper.supervisor.equalsIgnoreCase(needName)){
				boolean isAllPrimaryAnsweredYesOrNo = _getIsAnsweredYesOrNo(allPrimaryQuestions);
				
				if(isAllPrimaryAnsweredYesOrNo)
					return new Date();
				return null;
				
			}
			
			// for desirable and highly desirable
			List<QuestionSurveyBean> primaryQuestionsAnsweredNo= _filterPrimaryQuestionAnsweredNo(allPrimaryQuestions);
			
			
			List<QuestionSurveyBean> allSecondaryQuestions = _filterAllSecondaryQuestions(allQuestions, primaryQuestionsAnsweredNo);
			boolean isAllSecondaryAnsweredNo = _getIsAnsweredNo(allSecondaryQuestions);
			if(isAllSecondaryAnsweredNo){
				return new Date();
			}
			 
			//TODO 
			////////////////////////////////////
			////////////////////////////////////
			////////////////////////////////////
			
			boolean isAnySecondaryNotAnswered = _filterNotAnsweredSecondaryQuestions(allSecondaryQuestions);
			if(isAnySecondaryNotAnswered)
				return null;
			
			List<QuestionSurveyBean> allYesAnsweredSecondaryQuestion = _filterYesAnsweredSecondaryQuestions(allSecondaryQuestions);
			
			List<QuestionSurveyBean> allThirdLevelQuestions = _getThirdLevelQuestions(allYesAnsweredSecondaryQuestion, allQuestions);
			
			boolean isAnyThirdLevelNotAnswered = _isAnyThirdLevelNotAnswered(allThirdLevelQuestions);
			
			if(isAnyThirdLevelNotAnswered)
				return null;
			return new Date();
			
			
			
		
			
			
			
		} catch (Exception e) {
			return null;
		}
	}

	private boolean _isAnyThirdLevelNotAnswered(
			List<QuestionSurveyBean> allThirdLevelQuestions) {
		try {
			for (Iterator iterator = allThirdLevelQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				String answer = questionSurveyBean.getAnswer();
				if(SurveyDataServiceHelper.answerNotAnswered.equalsIgnoreCase(answer)){
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
			return true;		}
	}

	private List<QuestionSurveyBean> _getThirdLevelQuestions(
			List<QuestionSurveyBean> allYesAnsweredSecondaryQuestion,
			List<QuestionSurveyBean> allQuestions) {
		
		List<QuestionSurveyBean> coll = new ArrayList<QuestionSurveyBean>();
		try {
			
			for (Iterator iterator = allYesAnsweredSecondaryQuestion.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean yesQuestionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				String yesCourseId= yesQuestionSurveyBean.getCourseId();
				for (Iterator iterator2 = allQuestions.iterator(); iterator2
						.hasNext();) {
					QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator2
							.next();
					String courseId = questionSurveyBean.getCourseId();
					if(yesCourseId.equalsIgnoreCase(courseId) && questionSurveyBean.isPrimary()==SurveyDataServiceHelper.questionTypeQuestion){
						coll.add(questionSurveyBean);
					}
					
				}
			}
			return coll;
		} catch (Exception e) {
			return coll;
		}
	}

	private boolean _filterNotAnsweredSecondaryQuestions(
			List<QuestionSurveyBean> allSecondaryQuestions) {
		try {
			for (Iterator iterator = allSecondaryQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				String answer = questionSurveyBean.getAnswer();
				
				if(SurveyDataServiceHelper.answerNotAnswered.equalsIgnoreCase(answer))
					return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	private List<QuestionSurveyBean> _filterYesAnsweredSecondaryQuestions(
			List<QuestionSurveyBean> allSecondaryQuestions) {
		List<QuestionSurveyBean> coll= new ArrayList<QuestionSurveyBean>();
		try {
			for (Iterator iterator = allSecondaryQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				
				String answer = questionSurveyBean.getAnswer();
				
				if(SurveyDataServiceHelper.answerYes.equalsIgnoreCase(answer)){
					coll.add(questionSurveyBean);
				}
			}
			return coll;
		} catch (Exception e) {
			return coll;
		}
	}

	private boolean _getIsAnsweredNo(
			List<QuestionSurveyBean> allSecondaryQuestions) {
		try {
			for (Iterator iterator = allSecondaryQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				String answer = questionSurveyBean.getAnswer();
				if(SurveyDataServiceHelper.answerYes.equalsIgnoreCase(answer)|| SurveyDataServiceHelper.answerNotAnswered.equalsIgnoreCase(answer)){
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean _getIsAnyPrimaryNotAnswered(
			List<QuestionSurveyBean> allPrimaryQuestions) {
		try {
			for (Iterator iterator = allPrimaryQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				String answer = questionSurveyBean.getAnswer();
				if(SurveyDataServiceHelper.answerNotAnswered.equalsIgnoreCase(answer)){
					return true;
				}
				
			}
			return false;
		} catch (Exception e) {
		return true;
		}
	}

	private List<QuestionSurveyBean> _filterAllSecondaryQuestions(
			List<QuestionSurveyBean> allQuestions,
			List<QuestionSurveyBean> primaryQuestionsAnsweredNo) {
		
		List<QuestionSurveyBean> questions = new ArrayList<QuestionSurveyBean>();
		try {
			for (Iterator iterator = primaryQuestionsAnsweredNo.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				
				String courseId = questionSurveyBean.getCourseId();
				
				for (Iterator iterator2 = allQuestions.iterator(); iterator2
						.hasNext();) {
					QuestionSurveyBean question = (QuestionSurveyBean) iterator2
							.next();
					String courseId2 = question.getCourseId();
					
					if(courseId.equalsIgnoreCase(courseId2) && question.isPrimary()==SurveyDataServiceHelper.questionTypesecondary){
						questions.add(question);
						
					}
					
				}
				
				
				
				
			}
			
			return questions;
			
		} catch (Exception e) {
			return questions;
		}
	}

	private List<QuestionSurveyBean> _filterPrimaryQuestionAnsweredNo(
			List<QuestionSurveyBean> allPrimaryQuestions) {
		
		List<QuestionSurveyBean> questions = new ArrayList<QuestionSurveyBean>();
		
		try {
			for (Iterator iterator = allPrimaryQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				
				String answer = questionSurveyBean.getAnswer();
				if(SurveyDataServiceHelper.answerNo.equalsIgnoreCase(answer)){
					questions.add(questionSurveyBean);
				}
				
				
			}
			return questions;
		} catch (Exception e) {
			return questions;
		}
	}

	private boolean _getIsAnsweredYesOrNo(
			List<QuestionSurveyBean> allPrimaryQuestions) {
 		try {
			
			for (Iterator iterator = allPrimaryQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				String answer = questionSurveyBean.getAnswer();
				if(answer==null)
					return false;
				 
				if( (answer.equalsIgnoreCase(SurveyDataServiceHelper.answerYes))|| ( answer.equalsIgnoreCase(SurveyDataServiceHelper.answerNo))){
					 
				}else{return false;}
				
				
			}
			return true;
		} catch (Exception e) {
		return false;
		
		}
	}

	private boolean _getIsAllPrimaryAnsweredYes(
			List<QuestionSurveyBean> allPrimaryQuestions) {
		
		boolean status = true;
		try {
			for (Iterator iterator = allPrimaryQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
			String answer = questionSurveyBean.getAnswer();
			if(answer==null)
				return false;
			if(answer.equalsIgnoreCase(SurveyDataServiceHelper.answerNo)|| answer.equalsIgnoreCase(SurveyDataServiceHelper.answerNotAnswered))
				return false;
				
			}
			return status;
		} catch (Exception e) {
			return false;
			}
	}

	private List<QuestionSurveyBean> _filterAllPrimaryQuestions(
			List<QuestionSurveyBean> allQuestions) {
		
		List<QuestionSurveyBean> questions = new ArrayList<QuestionSurveyBean>();
		try {
			for (Iterator iterator = allQuestions.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();
				
				int questionLevel = questionSurveyBean.isPrimary();
				if(questionLevel==SurveyDataServiceHelper.questionTypePrimary)
					questions.add(questionSurveyBean);
				
			}
			return questions;
		} catch (Exception e) {
return null;
		}
	}

	public void saveSurvey(SurveyDataBean surveyDataBean)
			throws SurveyCreateException {
		Need need = null;
		List<UocQuestion> allPrimaryQuestion = null;

		Survey survey = null;
		try {
			String needName = surveyDataBean.getNeedName();
			need = _getNeed(needName);

			List<Uoc> tempUocs = _getUocCollectionByNeedDapsso(need);
			List<Uoc> uocs = _filterUniqueUocs(tempUocs);
			List<UocQuestion> uocQuestions = _getUocQuestions(uocs);

			allPrimaryQuestion = _getQuestionByQtnType(uocQuestions,
					questionTypePrimary);

			List<QuestionSurveyBean> questionBeans = surveyDataBean
					.getQuestions();
			List<SurveryAnswer> answers = _convertBeanToEntity(questionBeans);

			List<SurveryAnswer> allPrimaryAnswers = _filterAnswersByQtnType(
					answers, allPrimaryQuestion);

			List<SurveryAnswer> primaryYesAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerYes);
			List<SurveryAnswer> primaryNoAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerNo);
			List<SurveryAnswer> primaryNotAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers,
					SurveyDataServiceHelper.answerNotAnswered);

//			int yesSize = primaryYesAnswered.size();
//			int noSize = primaryNoAnswered.size();
//			int notAnsweredSize = primaryNotAnswered.size();
//
//			int total = yesSize + noSize + notAnsweredSize;
//			int answered = yesSize + noSize;

			survey = _getSurvey(need);

//			if (total == answered) {
//				survey.setCompletedAt(new Date());
//			} else {
//				survey.setCompletedAt(null);
//			}
			Date surveyCompletedDate = _completedStatus(surveyDataBean);
			survey.setCompletedAt(surveyCompletedDate);
			survey = surveyDao.save(survey);
			surveyDao.refresh(survey);

			long surveyId = survey.getIdsurvey();

			for (Iterator iterator = questionBeans.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();

				SurveryAnswer answer = new SurveryAnswer();
				answer.setAnsweredAt(new Date());
				answer.setSurveyId(surveyId);
				answer.setUocQuestionId(questionSurveyBean.getUocQuestionId());
				answer.setValue(questionSurveyBean.getAnswer());
				answer = surveyAnswerDao.save(answer);
				surveyAnswerDao.refresh(answer);

			}

		} catch (NoSuchNeedExistException e) {
			throw new SurveyCreateException("No Need Exist");
		} catch (NoSuchSurveyExistException e) {

			survey = new Survey();

			List<QuestionSurveyBean> questionBeans = surveyDataBean
					.getQuestions();

			List<SurveryAnswer> answers = _convertBeanToEntity(questionBeans);

			List<SurveryAnswer> allPrimaryAnswers = _filterAnswersByQtnType(
					answers, allPrimaryQuestion);

			List<SurveryAnswer> primaryYesAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerYes);
			List<SurveryAnswer> primaryNoAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerNo);
			List<SurveryAnswer> primaryNotAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers,
					SurveyDataServiceHelper.answerNotAnswered);

//			int yesSize = primaryYesAnswered.size();
//			int noSize = primaryNoAnswered.size();
//			int notAnsweredSize = primaryNotAnswered.size();
//
//			int total = yesSize + noSize + notAnsweredSize;
//			int answered = yesSize + noSize;

//			if (total == answered) {
//				survey.setCompletedAt(new Date());
//			}

			Date surveyComplteDate = _completedStatus(surveyDataBean);
			survey.setCompletedAt(surveyComplteDate);
			survey.setDapssco(dapssco);
			survey.setIndividual(user);
			survey.setNee(need);
			survey.setStartedAt(new Date());
			survey = surveyDao.saveNew(survey);
			surveyDao.refresh(survey);
			long surveyId = survey.getIdsurvey();

			for (Iterator iterator = questionBeans.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();

				SurveryAnswer answer = new SurveryAnswer();
				answer.setAnsweredAt(new Date());
				answer.setSurveyId(surveyId);
				answer.setUocQuestionId(questionSurveyBean.getUocQuestionId());
				answer.setValue(questionSurveyBean.getAnswer());
				answer = surveyAnswerDao.saveNew(answer);
				surveyAnswerDao.refresh(answer);

			}

		} catch (NoUOCExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	
	
	
	
	private List<SurveryAnswer> _convertBeanToEntity(
			List<QuestionSurveyBean> questionBeans) {

		List<SurveryAnswer> collection = new ArrayList<SurveryAnswer>();
		try {
			for (Iterator iterator = questionBeans.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean questionSurveyBean = (QuestionSurveyBean) iterator
						.next();

				SurveryAnswer answer = new SurveryAnswer();
				answer.setUocQuestionId(questionSurveyBean.getUocQuestionId());
				answer.setValue(questionSurveyBean.getAnswer());
				answer.setSurveyId(questionSurveyBean.getsurveyId());
				// answer.setAnsweredAt(questionSurveyBean.get);

				collection.add(answer);

			}
			return collection;
		} catch (Exception e) {

			return collection;
		}
	}

	private boolean _isAllPrimaryAnswered(List<QuestionSurveyBean> questionBeans) {
		try {
			for (Iterator iterator = questionBeans.iterator(); iterator
					.hasNext();) {
				QuestionSurveyBean answer = (QuestionSurveyBean) iterator
						.next();
				if (!answer.getAnswer().equalsIgnoreCase(
						SurveyDataServiceHelper.answerYes)
						&& !answer.getAnswer().equalsIgnoreCase(
								SurveyDataServiceHelper.answerNo))
					return false;

			}
			return true;

		} catch (Exception e) {
			return true;

		}
	}

 	private ReportMergedSurveyBean mergedReport(String needName) {
		ReportMergedSurveyBean report = new ReportMergedSurveyBean();
		List<UocCourseStatusRecognitionBean> courseStatusCollection = new ArrayList<UocCourseStatusRecognitionBean>();
		report.setItems(courseStatusCollection);
	
		List<Uoc> uocs = null;

		try {

			UocQuestionDAO uocQuestionDao = new UocQuestionDAO();
			Need need = _getNeed(needName);
		report.setNeedDescription(need.getDescription());
		report.setNeedName(need.getName());
		report.setUocCourses(courseStatusCollection);
		report.setProfileInfo(_getReportProfileInfo());
		report.setTitle(need.getDescription()+" Training");
		
			uocs = _getUocCollectionByNeedDapsso(need);
		
			Survey survey = _getSurvey(need);
			long surveyId = survey.getIdsurvey();

			SurveyAnswerDAO surveyAnswerDao = new SurveyAnswerDAO();

			for (Iterator iterator = uocs.iterator(); iterator.hasNext();) {
				Uoc uoc = (Uoc) iterator.next();
				
				List<UocQuestion> allQuestionofUOC = uoc.getUocQuestions();
				UocQuestion primaryQuestion = _getPrimaryQuestion(allQuestionofUOC);
						

				long primaryQuestionId = primaryQuestion.getIduocquestion();
				
				
			
				UocCourseStatusRecognitionBean coursestatusRecognition = new UocCourseStatusRecognitionBean();
				courseStatusCollection.add(coursestatusRecognition);
				coursestatusRecognition.setCourseName(uoc.getName());
				coursestatusRecognition.setFututeLearning(SurveyDataServiceHelper.statusBlank);
				coursestatusRecognition.setRecognition(SurveyDataServiceHelper.statusBlank);
				coursestatusRecognition.setCompleted(SurveyDataServiceHelper.statusBlank);
				
				
				SurveryAnswer primaryAnswer = surveyAnswerDao.getByPk(
						primaryQuestionId, surveyId);
			
					
				if (need.getName().equalsIgnoreCase(
						SurveyDataServiceHelper.mandatory)
						|| need.getName().equalsIgnoreCase(
								SurveyDataServiceHelper.supervisor))
				{
					String primaryAnswerValue = primaryAnswer.getValue();
					if (primaryAnswerValue
							.equalsIgnoreCase(SurveyDataServiceHelper.answerYes)) {
						coursestatusRecognition
								.setCompleted(SurveyDataServiceHelper.statusYes);
						coursestatusRecognition
						.setFututeLearning(SurveyDataServiceHelper.statusNo);
						coursestatusRecognition
						.setRecognition(SurveyDataServiceHelper.statusNotApplicable);
				
					}

					if (primaryAnswerValue
							.equalsIgnoreCase(SurveyDataServiceHelper.answerNo)) {
						coursestatusRecognition
								.setCompleted(SurveyDataServiceHelper.statusNo);

						coursestatusRecognition.setFututeLearning(SurveyDataServiceHelper.statusYes);
						coursestatusRecognition
						.setRecognition(SurveyDataServiceHelper.statusNotApplicable);
					}
					if (primaryAnswerValue
							.equalsIgnoreCase(SurveyDataServiceHelper.answerNotAnswered)) {
						coursestatusRecognition
								.setCompleted(SurveyDataServiceHelper.statusBlank);
						coursestatusRecognition
						.setFututeLearning(SurveyDataServiceHelper.statusBlank);
						coursestatusRecognition
						.setRecognition(SurveyDataServiceHelper.statusBlank);


					}
					
			}
				
				/// for highly desirable  and desirbale

				if (need.getName().equalsIgnoreCase(
						SurveyDataServiceHelper.desirable)
						|| need.getName().equalsIgnoreCase(
								SurveyDataServiceHelper.highlyDesirable)) {
							String primaryAnswerValue = primaryAnswer.getValue();
							
							
					if(primaryAnswerValue.equalsIgnoreCase(SurveyDataServiceHelper.answerYes)){
						coursestatusRecognition.setCompleted(SurveyDataServiceHelper.statusYes);
						coursestatusRecognition.setFututeLearning(SurveyDataServiceHelper.statusNotApplicable);
						coursestatusRecognition.setRecognition(SurveyDataServiceHelper.statusNotApplicable);
						
					} else if (primaryAnswerValue.equalsIgnoreCase(SurveyDataServiceHelper.answerNo)){
					
						
						UocQuestion secondaryQuestion =_getSecondaryQuestion(allQuestionofUOC);
						long secondaryQuestionId = secondaryQuestion
								.getIduocquestion();

						SurveryAnswer secondaryQtnAnswer = surveyAnswerDao.getByPk(
								secondaryQuestionId, surveyId);
						String secondaryAnswervalue = secondaryQtnAnswer.getValue();
				
					
					if(secondaryAnswervalue.equalsIgnoreCase(SurveyDataServiceHelper.answerNo)){
						
						coursestatusRecognition.setCompleted(SurveyDataServiceHelper.statusNo);
						coursestatusRecognition.setFututeLearning(SurveyDataServiceHelper.statusNo);
						coursestatusRecognition.setRecognition(SurveyDataServiceHelper.statusNotApplicable);
												
						
					} else 	if(secondaryAnswervalue.equalsIgnoreCase(SurveyDataServiceHelper.answerYes)){
						coursestatusRecognition.setCompleted(SurveyDataServiceHelper.statusNo);

						//List<UocQuestion> allQuestions  =_getUocQuestions(uocs);
						List<UocQuestion> thirdLevelQuestions = _getQuestionByQtnType(allQuestionofUOC, SurveyDataServiceHelper.questionTypeQuestion);
						List<SurveryAnswer> allAnsweres =_getSurveyAnswersByNeed(need, survey);
						List<SurveryAnswer> thirdLevelAnswers = _filterAnswersByQtnType(allAnsweres, thirdLevelQuestions);
						boolean isAnyThirdLevelAnsweredasYes =_isAnyYesAnsweredThirdLevelAnswer (thirdLevelAnswers);
						if(!isAnyThirdLevelAnsweredasYes){
							coursestatusRecognition.setCompleted(SurveyDataServiceHelper.statusNo);
							coursestatusRecognition.setFututeLearning(SurveyDataServiceHelper.statusYes);
							coursestatusRecognition.setRecognition(SurveyDataServiceHelper.statusNo);
							
							
						} else 
						{
							coursestatusRecognition.setCompleted(SurveyDataServiceHelper.statusNo);
							coursestatusRecognition.setFututeLearning(SurveyDataServiceHelper.statusYes);
							coursestatusRecognition.setRecognition(SurveyDataServiceHelper.statusYes);
					
							
							
						}
								
					}
					
					
					} // if primary no
					
				} // if need getName

			}

		} catch (NoSuchSurveyExistException e) {
			for (Iterator iterator = uocs.iterator(); iterator.hasNext();) {
				Uoc uoc = (Uoc) iterator.next();
				UocCourseStatusRecognitionBean bean = new UocCourseStatusRecognitionBean();
				bean.setCourseName(uoc.getName());
				bean.setCompleted(SurveyDataServiceHelper.statusBlank);
				bean.setFututeLearning(SurveyDataServiceHelper.statusBlank);
				bean.setRecognition(SurveyDataServiceHelper.statusBlank);
				courseStatusCollection.add(bean);
			}
		} catch (NoSuchNeedExistException e1) {
			// TODO: handle exception
		} catch (NoUOCExistException e2) {

		} catch (NoAnsweredFoundForExistingSurvey e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception em){
			
		}
return report;
	}

	private UocQuestion _getSecondaryQuestion(List<UocQuestion> allQuestionofUOC) {
		try {
			for (Iterator iterator = allQuestionofUOC.iterator(); iterator
					.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				if(uocQuestion.isIsprimay()==2)
					return uocQuestion;
			}
		} catch (Exception e) {
			 
		}
		return null;
	}

	private UocQuestion _getPrimaryQuestion(List<UocQuestion> allQuestionofUOC) {
		try {
			for (Iterator iterator = allQuestionofUOC.iterator(); iterator
					.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				if(uocQuestion.isIsprimay()==1)
					return uocQuestion;
			}
		} catch (Exception e) {
			 
		}
		return null;
	}

	private boolean _isAnyYesAnsweredThirdLevelAnswer(
			List<SurveryAnswer> thirdLevelAnswers) {
		try {
			for (Iterator iterator = thirdLevelAnswers.iterator(); iterator
					.hasNext();) {
				SurveryAnswer surveryAnswer = (SurveryAnswer) iterator.next();
				String answerValue = surveryAnswer.getValue();
				if (answerValue.equalsIgnoreCase(SurveyDataServiceHelper.answerYes)){
					return true;
					
				}
			}
			return false;
		} catch (Exception e) {
return false;	
		}
		}
		

	private List<UocBean> _convertIntoUOCBeans(List<Uoc> uocs) {
		List<UocBean> uocBeans = new ArrayList<UocBean>();
		try {
			for (Iterator iterator = uocs.iterator(); iterator.hasNext();) {
				Uoc uoc = (Uoc) iterator.next();
				UocBean uocbean = UocBean.get(uoc);
				uocbean.setProviderVisibility(false);
				ProviderBean providerBean = new ProviderBean();
				providerBean.setProviderName("");
				providerBean.setCourseURL("");
				providerBean.setDescription("");
				providerBean.setId(0);
				uocbean.setProvider(providerBean);
				
				Provider provider = uoc.getProvider();
				if (provider != null) { 
					String url = provider.getCourseURL();
					if(url!=null ){
						if(url.length()>0){
						String courseURL = provider.getCourseURL() + "?Code="
								+ uoc.getUid();
						 
						providerBean.setCourseURL(courseURL);
						uocbean.setProvider(providerBean);
						
						
						uocbean.setProviderVisibility(true);
						
						} 
					}
					
					 

				}  
				 
				uocBeans.add(uocbean);

			}

			return uocBeans;
		} catch (Exception e) {
			return uocBeans;
		}
	}

	private List<QuestionSurveyBean> _getupdatedByAnswerBean(
			List<QuestionSurveyBean> questionSurveyBeans,
			List<SurveryAnswer> answers, long surveyId) {
		List<QuestionSurveyBean> collection = new ArrayList<QuestionSurveyBean>();
		try {
			for (Iterator iterator = answers.iterator(); iterator.hasNext();) {
				SurveryAnswer surveryAnswer = (SurveryAnswer) iterator.next();
				for (Iterator iterator2 = questionSurveyBeans.iterator(); iterator2
						.hasNext();) {
					QuestionSurveyBean quBean = (QuestionSurveyBean) iterator2
							.next();
					if (surveryAnswer.getUocQuestionId()== 
							quBean.getUocQuestionId()) {
						quBean.setAnswer(surveryAnswer.getValue());
						quBean.setSurveyId(surveyId);

						collection.add(quBean);
					}

				}

			}
			return collection;
		} catch (Exception e) {
			return collection;
		}
	}

	private List<QuestionSurveyBean> _convertIntQuestionSurveyBeanCollection(
			List<UocQuestion> uocQuestions) {

		List<QuestionSurveyBean> collection = new ArrayList<QuestionSurveyBean>();
		try {
			for (Iterator iterator = uocQuestions.iterator(); iterator
					.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				QuestionSurveyBean bean = new QuestionSurveyBean();
				bean.setCourseId(uocQuestion.getUoc().getIdsuoc());
				bean.setPrimary(uocQuestion.isIsprimay());
				bean.setQuestion(uocQuestion.getText());
				bean.setStyle(uocQuestion.getStyle());
				bean.setUocQuestionId(uocQuestion.getIduocquestion());
				bean.setUocName(uocQuestion.getUoc().getName());
				collection.add(bean);

			}
			return collection;
		} catch (Exception e) {
			return collection;
		}
	}

	private OverviewListSurveyBean _getOverviewByNeed(String needName) {
		OverviewListSurveyBean overview = new OverviewListSurveyBean();
		List<Uoc> uocs = new ArrayList<Uoc>();
		Survey survey = null;
		try {

			Need need = _getNeed(needName);
			overview.setNeedName(need.getName());
			overview.setNeedDesciption(need.getDescription());
			List<Uoc> tempUocs = _getUocCollectionByNeedDapsso(need);
			uocs = _filterUniqueUocs(tempUocs);
			survey = _getSurvey(need);
			List<SurveryAnswer> answers = _getSurveyAnswersByNeed(need, survey);
			
			int incomplteCourses =_getIncompltedCoursesCount(need, survey, uocs, answers);
			
			int totalCourses = uocs.size();
 
			int completed = totalCourses-incomplteCourses;
			
			int percentage = (completed *100) / totalCourses; 
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date startDt = survey.getStartedAt();
			String startDtStr = "";

			Date completedDt = survey.getCompletedAt();
			String completedDtStr = "";
			if (startDt != null) {
				startDtStr = df.format(startDt);

			}
			if (completedDt != null) {
				completedDtStr = df.format(completedDt);

			}

			overview.setStartDate(startDtStr);
			overview.setFinishDate(completedDtStr);
			if (percentage == 100) {
				overview.setStatus("Completed");
			} else  {
				overview.setStatus("In Process");
			}

			overview.setPercentage(percentage);

		} catch (NoSuchNeedExistException e) {
			overview.setNeedName(needName);

			overview.setStatus("No course for the need :"
					+ needName.toUpperCase());
			overview.setPercentage(0);
		} catch (NoUOCExistException e) {
			overview.setStatus("No Course Assigned");
			overview.setPercentage(0);

		} catch (NoSuchSurveyExistException e) {
			overview.setStatus("Not Started");
			overview.setPercentage(0);

		} catch (NoAnsweredFoundForExistingSurvey e) {

			overview.setStatus("No Answered Found");
			overview.setPercentage(0);

		} 
		return overview;
	}

	private int _getIncompltedCoursesCount(Need need, Survey survey,
			List<Uoc> uocs, List<SurveryAnswer> answers) {
		
	try {
		int totalCourses = uocs.size();
		List<UocQuestion> uocQuestions = _getUocQuestions(uocs);
		List<UocQuestion> allPrimaryQuestion = _getQuestionByQtnType(
				uocQuestions, questionTypePrimary);
		List<SurveryAnswer> allPrimaryAnswers = _filterAnswersByQtnType(
				answers, allPrimaryQuestion);

		List<SurveryAnswer> primaryYesAnswered = _filterAnswersByAnsType(
				allPrimaryAnswers, SurveyDataServiceHelper.answerYes);
		List<SurveryAnswer> primaryNoAnswered = _filterAnswersByAnsType(
				allPrimaryAnswers, SurveyDataServiceHelper.answerNo);
		List<SurveryAnswer> primaryNotAnswered = _filterAnswersByAnsType(
				allPrimaryAnswers,
				SurveyDataServiceHelper.answerNotAnswered);

		int yesSize = primaryYesAnswered.size();
		int noSize = primaryNoAnswered.size();
		int notAnsweredSize = primaryNotAnswered.size();
		int totalSize = yesSize+ noSize+ notAnsweredSize;
		
		
		if((SurveyDataServiceHelper.mandatory.equalsIgnoreCase(need.getName()) || SurveyDataServiceHelper.supervisor.equalsIgnoreCase(need.getName())) && notAnsweredSize==0){
			return 0;
		}
		
		if((SurveyDataServiceHelper.mandatory.equalsIgnoreCase(need.getName()) || SurveyDataServiceHelper.supervisor.equalsIgnoreCase(need.getName())) && notAnsweredSize>0){
			return notAnsweredSize;
			
		}
		
		if(SurveyDataServiceHelper.desirable.equalsIgnoreCase(need.getName()) || SurveyDataServiceHelper.highlyDesirable.equalsIgnoreCase(need.getName())){
			
			
			
			if(yesSize ==totalSize)
				return 0;
			 
			int count =0;
			count= count+notAnsweredSize;
			for (Iterator iterator = primaryNoAnswered.iterator(); iterator
					.hasNext();) {
				SurveryAnswer surveryAnswer = (SurveryAnswer) iterator.next();
				long pUocQuestionId = surveryAnswer.getUocQuestionId();
				Uoc uoc = _getUoc(pUocQuestionId, uocQuestions);
				if(uoc==null){
				count = count+1;
					continue;
				}
				UocQuestion secondaryQuestion = _getSecondaryQuestion(uoc);
				if (secondaryQuestion==null){
					count=count+1;
					continue;
				}
				
				long  sUocQuestionId = secondaryQuestion.getIduocquestion();
				SurveryAnswer sqSurveyAnswer = _getAnswerByQuesionId(answers, sUocQuestionId);
				String sqAnsValue = sqSurveyAnswer.getValue();
				
				if(SurveyDataServiceHelper.answerYes.equalsIgnoreCase(sqAnsValue)){
					boolean status = _isAnyQtnInThirdLevelNotAnswered(uoc, answers);
					if(status)
						count= count+1;
					continue;
					
				} 
				
				if(sqAnsValue.equalsIgnoreCase(SurveyDataServiceHelper.answerNotAnswered))
					count= count+1;
				
				
				
				
			}
			return count ;
			
			
			
		}
		
		
		
		return 0;
		
		
	

		
	} catch (Exception e) {
		return 0;
	}
	}

	private boolean _isAnyQtnInThirdLevelNotAnswered(Uoc uoc,
			List<SurveryAnswer> answers) {
		try {
			List<UocQuestion> questions =uoc.getUocQuestions();
			List<UocQuestion> thirdLevelQuestions = _filterThirdLevelQuestions(questions);
			if(thirdLevelQuestions.size()==0)
				return false;
			
			List<SurveryAnswer> thirdLevelAnswers =_filterThirdLevelAnswers(thirdLevelQuestions, answers);
			for (Iterator iterator = thirdLevelAnswers.iterator(); iterator
					.hasNext();) {
				SurveryAnswer surveryAnswer = (SurveryAnswer) iterator.next();
				if(SurveyDataServiceHelper.answerNotAnswered.equalsIgnoreCase(surveryAnswer.getValue())){
					
					return true;
				}
			}
			
			return false;
			
			
			
		} catch (Exception e) {
		return false;
		}
	}

	private List<SurveryAnswer> _filterThirdLevelAnswers(
			List<UocQuestion> thirdLevelQuestions, List<SurveryAnswer> answers) {
		
		List<SurveryAnswer> thirdLevelAnswers = new ArrayList<SurveryAnswer>();
		try {
			for (Iterator iterator = thirdLevelQuestions.iterator(); iterator.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				
				for (Iterator iterator2 = answers.iterator(); iterator2
						.hasNext();) {
					SurveryAnswer surveryAnswer = (SurveryAnswer) iterator2
							.next();
					if(uocQuestion.getIduocquestion()==surveryAnswer.getUocQuestionId()){
						thirdLevelAnswers.add(surveryAnswer);
					}
				}
				
			}
			return thirdLevelAnswers;
		} catch (Exception e) {
return thirdLevelAnswers;
		}
	}

	private List<UocQuestion> _filterThirdLevelQuestions(
			List<UocQuestion> questions) {
		
		List<UocQuestion> tlUocQuestionColl= new ArrayList<UocQuestion>();
		try {
			for (Iterator iterator = questions.iterator(); iterator.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				if(uocQuestion.isIsprimay()==SurveyDataServiceHelper.questionTypeQuestion){
					tlUocQuestionColl.add(uocQuestion);
				}
			}
			return  tlUocQuestionColl;
		} catch (Exception e) {
			return tlUocQuestionColl;
		}
	}

	private SurveryAnswer _getAnswerByQuesionId(List<SurveryAnswer> answers,
			long sUocQuestionId) {
		try {
			
			for (Iterator iterator = answers.iterator(); iterator.hasNext();) {
				SurveryAnswer surveryAnswer = (SurveryAnswer) iterator.next();
				if(surveryAnswer.getUocQuestionId()==sUocQuestionId)
					return surveryAnswer;
			}
			return null;
		} catch (Exception e) {
	return null;
		}
	}

	private UocQuestion _getSecondaryQuestion(Uoc uoc) {
		try {
		List<UocQuestion>  questions =	uoc.getUocQuestions();
		for (Iterator iterator = questions.iterator(); iterator.hasNext();) {
			UocQuestion uocQuestion = (UocQuestion) iterator.next();
			if(uocQuestion.isIsprimay()==SurveyDataServiceHelper.questionTypesecondary){
				return uocQuestion;
				
			}
		}
		return null;
		} catch (Exception e) {
	return null;
		}
	}

	private Uoc _getUoc(long pUocQuestionId, List<UocQuestion> uocQuestions) {
		try {
			for (Iterator iterator = uocQuestions.iterator(); iterator
					.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				if(uocQuestion.getIduocquestion()==pUocQuestionId)
					return uocQuestion.getUoc();
			}
			return  null;
		} catch (Exception e) {
			return null;
		}
	}

	public List<OverviewListSurveyBean> getOverview() {
		List<OverviewListSurveyBean> reports = new ArrayList<OverviewListSurveyBean>();
		try {
			List<Need> userTypeNeed = _getUserTypeNeed();

			for (Iterator iterator = userTypeNeed.iterator(); iterator
					.hasNext();) {
				Need need = (Need) iterator.next();
				OverviewListSurveyBean bean = _getOverviewByNeed(need.getName());
				reports.add(bean);
				

			}

			return reports;

		} catch (NoNeedMappedToUserType e) {
			return reports;
		}

	}

	private List<Need> _getUserTypeNeed() throws NoNeedMappedToUserType {
		try {
			UserType usertype = user.getUsertypefk();
			List<Need> needs = usertype.getNeeds();
			if (needs == null || needs.size() == 0)
				throw new NoNeedMappedToUserType("No NeedMapped to UserType");
			return needs;
		} catch (Exception e) {
			throw new NoNeedMappedToUserType("No NeedMapped to UserType");

		}
	}

	private MenusBean _getMenu() {
		MenusBean result = new MenusBean();
		try {

			String loginId = user.getLogin();

			if (loginId.toLowerCase().startsWith(adminType.toLowerCase())) {
				return _getAdminMenu(SurveyDataServiceHelper.adminType);
			}

			if (loginId.toLowerCase().startsWith(reportingType.toLowerCase())) {
				return _getReporting(SurveyDataServiceHelper.reportingType);
			}

			UserType userType = user.getUsertypefk();
			String userName = userType.getUserType();
			if (SurveyDataServiceHelper.individualType
					.equalsIgnoreCase(userName)) {

				result = _getIndividualMenus(SurveyDataServiceHelper.individualType);
				return result;
			} else if (SurveyDataServiceHelper.supervisorType
					.equalsIgnoreCase(userName)) {

				result = _getSupervisorMenus(SurveyDataServiceHelper.supervisorType);
				return result;
			} else {

				// result =_getDefault(SupervisonsServ);
			}

		} catch (Exception e) {
			 
		}
		return result;

	}

	private MenusBean _getReporting(String reportingtype2) {
		MenusBean menusBean = new MenusBean();
		menusBean.setUserType(reportingtype2);
		try {
			MenuBean home = new MenuBean();
			menusBean.add(home);
			home.setDefault(false);
			home.setKey("profileHome");
			home.setLabel("Home");

			MenuBean homeSubmenu = new MenuBean();
			home.addSubMenu(homeSubmenu);
			homeSubmenu.setDefault(true);
			homeSubmenu.setKey("Home-Master");
			homeSubmenu.setLabel("Home");

			MenuBean userManagementMainMenu = new MenuBean();
			userManagementMainMenu.setKey("usermanagement");
			userManagementMainMenu.setLabel("User Management");
			userManagementMainMenu.setDefault(false);

			menusBean.add(userManagementMainMenu);

			MenuBean userManagementSubMenu = new MenuBean();
			userManagementMainMenu.addSubMenu(userManagementSubMenu);
			userManagementSubMenu.setDefault(true);
			userManagementSubMenu.setKey("usermanagement-UserManagement");
			userManagementSubMenu.setLabel("User Management");

			// /////////

			MenuBean manageSurvey = new MenuBean();
			menusBean.add(manageSurvey);
			manageSurvey.setKey("usermanagement-UserManagement");
			manageSurvey.setLabel("Manage Survey");
			manageSurvey.setDefault(false);

			MenuBean survey = new MenuBean();
			manageSurvey.addSubMenu(survey);
			survey.setDefault(false);
			survey.setKey("managesurvey-ManageSurvey");
			survey.setLabel("Survey");

			MenuBean report = new MenuBean();
			manageSurvey.addSubMenu(report);
			report.setDefault(false);
			report.setKey("managesurvey-ManageReport");
			report.setLabel("Report");

			// /////////

			MenuBean manageSite = new MenuBean();
			menusBean.add(manageSite);
			manageSite.setKey("managesite");
			manageSite.setLabel("Manage Site");
			manageSite.setDefault(false);

			MenuBean manageSiteContent = new MenuBean();
			manageSite.addSubMenu(manageSiteContent);
			manageSiteContent.setDefault(false);
			manageSiteContent.setKey("managesite-ManageSite");
			manageSiteContent.setLabel("Manage Site Content");

			return menusBean;

		} catch (NullPointerException e) {

			return null;
		}

	}

	private MenusBean _getAdminMenu(String admintype2) {
		try {
			MenusBean menusBean = new MenusBean();
			menusBean.setUserType(admintype2);
			try {
				MenuBean home = new MenuBean();
				menusBean.add(home);
				home.setDefault(false);
				home.setKey("profileHome");
				home.setLabel("Home");

				MenuBean homeSubmenu = new MenuBean();
				home.addSubMenu(homeSubmenu);
				homeSubmenu.setDefault(true);
				homeSubmenu.setKey("Home-Master");
				homeSubmenu.setLabel("Home");

				MenuBean userManagementMainMenu = new MenuBean();
				userManagementMainMenu.setKey("usermanagement");
				userManagementMainMenu.setLabel("User Management");
				userManagementMainMenu.setDefault(false);

				menusBean.add(userManagementMainMenu);

				MenuBean userManagementSubMenu = new MenuBean();
				userManagementMainMenu.addSubMenu(userManagementSubMenu);
				userManagementSubMenu.setDefault(true);
				userManagementSubMenu.setKey("usermanagement-UserManagement");
				userManagementSubMenu.setLabel("User Management");

				// /////////

				MenuBean manageSurvey = new MenuBean();
				menusBean.add(manageSurvey);
				manageSurvey.setKey("usermanagement-UserManagement");
				manageSurvey.setLabel("Manage Survey");
				manageSurvey.setDefault(false);

				MenuBean survey = new MenuBean();
				manageSurvey.addSubMenu(survey);
				survey.setDefault(false);
				survey.setKey("managesurvey-ManageSurvey");
				survey.setLabel("Survey");

				MenuBean report = new MenuBean();
				manageSurvey.addSubMenu(report);
				report.setDefault(false);
				report.setKey("managesurvey-ManageReport");
				report.setLabel("Report");

				// /////////

				MenuBean manageSite = new MenuBean();
				menusBean.add(manageSite);
				manageSite.setKey("managesite");
				manageSite.setLabel("Manage Site");
				manageSite.setDefault(false);

				MenuBean manageSiteContent = new MenuBean();
				manageSite.addSubMenu(manageSiteContent);
				manageSiteContent.setDefault(false);
				manageSiteContent.setKey("managesite-ManageSite");
				manageSiteContent.setLabel("Manage Site Content");

				return menusBean;

			} catch (NullPointerException e) {

				return null;
			}

		} catch (NullPointerException e) {
			return null;
		}

	}

	private MenusBean _getIndividualMenus(String individualtype) {
		MenusBean menusBean = new MenusBean();
		menusBean.setUserType(individualtype);
		try {
			MenuBean home = new MenuBean();
			menusBean.add(home);
			home.setDefault(false);
			home.setKey("profileHome");
			home.setLabel("Home");

			MenuBean homeSubmenu = new MenuBean();
			home.addSubMenu(homeSubmenu);
			homeSubmenu.setDefault(true);
			homeSubmenu.setKey("Home-Master");
			homeSubmenu.setLabel("Home");

			MenuBean mySurvey = new MenuBean();
			mySurvey.setKey("mysurvey");
			mySurvey.setLabel("My Surveys");
			mySurvey.setDefault(false);

			menusBean.add(mySurvey);

			MenuBean overview = new MenuBean();
			mySurvey.addSubMenu(overview);
			overview.setDefault(true);
			overview.setKey("mysurvey-SurveyOverview");
			overview.setLabel("Overview");

			MenuBean mandatory = new MenuBean();
			mySurvey.addSubMenu(mandatory);
			mandatory.setDefault(false);
			mandatory.setKey("mysurvey-Mandatory");
			mandatory.setLabel("Mandatory");

			MenuBean desirable = new MenuBean();

			mySurvey.addSubMenu(desirable);
			desirable.setDefault(false);
			desirable.setKey("mysurvey-Desirable");
			desirable.setLabel("Desirable");

			MenuBean highlydesirable = new MenuBean();

			mySurvey.addSubMenu(highlydesirable);
			highlydesirable.setDefault(false);
			highlydesirable.setKey("mysurvey-HighlyDesirable");
			highlydesirable.setLabel("Highly Desirable");

			// /////////

			MenuBean myReport = new MenuBean();
			menusBean.add(myReport);
			myReport.setKey("myreport");
			myReport.setLabel("My Reports");
			myReport.setDefault(false);

			MenuBean mandatoryReport = new MenuBean();
			myReport.addSubMenu(mandatoryReport);
			mandatoryReport.setDefault(false);
			mandatoryReport.setKey("myreport-MandatoryReport");
			mandatoryReport.setLabel("Mandatory");

			MenuBean desirableReport = new MenuBean();
			myReport.addSubMenu(desirableReport);
			desirableReport.setDefault(false);
			desirableReport.setKey("myreport-DesirableReport");
			desirableReport.setLabel("Desirable");

			MenuBean highlydesirableReport = new MenuBean();
			myReport.addSubMenu(highlydesirableReport);
			highlydesirableReport.setDefault(false);
			highlydesirableReport.setKey("myreport-HighlyDesirableReport");
			highlydesirableReport.setLabel("Highly Desirable");

			MenuBean gapReport = new MenuBean();
			myReport.addSubMenu(gapReport);
			gapReport.setDefault(false);
			gapReport.setKey("myreport-SkillsCompentencyGapReport");
			gapReport.setLabel("Skills Compentency Gap");

			MenuBean recognitionReport = new MenuBean();
			myReport.addSubMenu(recognitionReport);
			recognitionReport.setDefault(false);
			recognitionReport.setKey("myreport-SkillsRecognitionReport");
			recognitionReport.setLabel("Skills Recognition");

			return menusBean;

		} catch (Exception e) {
			 
		}
		return null;
	}

	private MenusBean _getSupervisorMenus(String individualtype) {
		MenusBean menusBean = new MenusBean();
		menusBean.setUserType(individualtype);
		try {
			MenuBean home = new MenuBean();
			menusBean.add(home);
			home.setDefault(false);
			home.setKey("profileHome");
			home.setLabel("Home");

			MenuBean homeSubmenu = new MenuBean();
			home.addSubMenu(homeSubmenu);
			homeSubmenu.setDefault(true);
			homeSubmenu.setKey("Home-Master");
			homeSubmenu.setLabel("Home");

			MenuBean mySurvey = new MenuBean();
			mySurvey.setKey("mysurvey");
			mySurvey.setLabel("My Surveys");
			mySurvey.setDefault(false);

			menusBean.add(mySurvey);

			MenuBean overview = new MenuBean();
			mySurvey.addSubMenu(overview);
			overview.setDefault(true);
			overview.setKey("mysurvey-SurveyOverview");
			overview.setLabel("Overview");

			MenuBean mandatory = new MenuBean();
			mandatory.setDefault(false);
			mandatory.setKey("mysurvey-Mandatory");
			mandatory.setLabel("Mandatory");

			MenuBean supervisor = new MenuBean();
			supervisor.setDefault(false);
			supervisor.setKey("mysurvey-Supervisor");
			supervisor.setLabel("Supervisor");

			MenuBean highlydesirable = new MenuBean();
			highlydesirable.setDefault(false);
			highlydesirable.setKey("mysurvey-HighlyDesirable");
			highlydesirable.setLabel("Highly Desirable");

			MenuBean desirable = new MenuBean();
			desirable.setDefault(false);
			desirable.setKey("mysurvey-Desirable");
			desirable.setLabel("Desirable");

			// /////////

			MenuBean myReport = new MenuBean();
			menusBean.add(myReport);
			myReport.setKey("myreport");
			myReport.setLabel("My Reports");
			myReport.setDefault(false);

			MenuBean mandatoryReport = new MenuBean();
			mandatoryReport.setDefault(false);
			mandatoryReport.setKey("myreport-MandatoryReport");
			mandatoryReport.setLabel("Mandatory");

			MenuBean highlydesirableReport = new MenuBean();
			highlydesirableReport.setDefault(false);
			highlydesirableReport.setKey("myreport-HighlyDesirableReport");
			highlydesirableReport.setLabel("Highly Desirable");

			MenuBean desirableReport = new MenuBean();
			desirableReport.setDefault(false);
			desirableReport.setKey("myreport-DesirableReport");
			desirableReport.setLabel("Desirable");

			MenuBean gapReport = new MenuBean();
			gapReport.setDefault(false);
			gapReport.setKey("myreport-SkillsCompentencyGapReport");
			gapReport.setLabel("Skills Compentency Gap");

			MenuBean recognitionReport = new MenuBean();
			recognitionReport.setDefault(false);
			recognitionReport.setKey("myreport-SkillsRecognitionReport");
			recognitionReport.setLabel("Skills Recognition");

			MenuBean supervisorReport = new MenuBean();
			supervisorReport.setDefault(false);
			supervisorReport.setKey("myreport-SupervisorReport");
			supervisorReport.setLabel("Supervisor");

			mySurvey.addSubMenu(mandatory);
			mySurvey.addSubMenu(desirable);
			mySurvey.addSubMenu(highlydesirable);
			mySurvey.addSubMenu(supervisor);

			myReport.addSubMenu(mandatoryReport);
			myReport.addSubMenu(desirableReport);
			myReport.addSubMenu(highlydesirableReport);

			myReport.addSubMenu(supervisorReport);
			myReport.addSubMenu(gapReport);
			myReport.addSubMenu(recognitionReport);

			return menusBean;

		} catch (Exception e) {
			 
		}
		return null;
	}

	private String _getMenu1() {
		String loginId = user.getLogin();
		String fileName = null;
		if (loginId.startsWith(SurveyDataServiceHelper.adminType)) {
			fileName = "admin.json";
		} else if (loginId.startsWith(SurveyDataServiceHelper.reportingType)) {
			fileName = "report.json";
		} else {
			UserType userType = user.getUsertypefk();
			String userTypeName = userType.getUserType();
			if (userTypeName
					.equalsIgnoreCase(SurveyDataServiceHelper.individualType))
				fileName = "individual.json";
			else if (userTypeName
					.equalsIgnoreCase(SurveyDataServiceHelper.supervisorType))
				fileName = "supervisor.json";

		}

		String menu = SurveyDataService.readMenuJSON(fileName, request);
		return menu;

	}

	public String getMenu1() {
		try {

			return _getMenu1();
		} catch (Exception e) {
			return null;
		}
	}

	public MenusBean getMenu() {
		try {

			return _getMenu();
		} catch (Exception e) {
			return null;
		}
	}

	public String getRoleName() {

		boolean status = false;
		status = request.isUserInRole(adminType);
		if (status)
			return adminType;
		status = request.isUserInRole(individualType);
		if (status)
			return individualType;
		status = request.isUserInRole(reportingType);
		if (status)
			return reportingType;
		return "NoRole";

	}

	public ReportSurveyBean getNeedReport(String needName) {

		ReportSurveyBean report = new ReportSurveyBean();
		List<Uoc> uocs = new ArrayList<Uoc>();
		Need need = null;
		try {

			need = _getNeed(needName);

			report.setNeedName(need.getName());
			report.setNeedDescription(need.getDescription());
			report.setTitle(need.getDescription() + " Training");

			uocs = _getUocCollectionByNeedDapsso(need);

			List<UocQuestion> uocQuestions = _getUocQuestions(uocs);
			Survey survey = _getSurvey(need);
			List<SurveryAnswer> answers = _getSurveyAnswersByNeed(need, survey);

			List<UocQuestion> allPrimaryQuestion = _getQuestionByQtnType(
					uocQuestions, questionTypePrimary);
			List<SurveryAnswer> allPrimaryAnswers = _filterAnswersByQtnType(
					answers, allPrimaryQuestion);

			List<SurveryAnswer> primaryYesAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerYes);
			List<SurveryAnswer> primaryNoAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerNo);
			List<SurveryAnswer> primaryNotAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers,
					SurveyDataServiceHelper.answerNotAnswered);

			List<Uoc> uocsYesAnswered = _getUocByAnswered(primaryYesAnswered,
					allPrimaryQuestion);
			List<Uoc> uocsNoAnswered = _getUocByAnswered(primaryNoAnswered,
					allPrimaryQuestion);
			List<Uoc> uocsNotAnswered = _getUocByAnswered(primaryNotAnswered,
					allPrimaryQuestion);

			List<UocCourseStatusBean> yesUOCCourseStatusBeans = _convertIntoUOCCourstatusBeans(
					uocsYesAnswered, SurveyDataServiceHelper.answerYes);
			List<UocCourseStatusBean> noUOCCourseStatusBeans = _convertIntoUOCCourstatusBeans(
					uocsNoAnswered, SurveyDataServiceHelper.answerNo);
			List<UocCourseStatusBean> notAnsweredUOCCourseStatusBeans = _convertIntoUOCCourstatusBeans(
					uocsNotAnswered, SurveyDataServiceHelper.answerNotAnswered);

			report.addAll(yesUOCCourseStatusBeans);
			report.addAll(noUOCCourseStatusBeans);
			report.addAll(notAnsweredUOCCourseStatusBeans);

			return report;

		} catch (NoSuchNeedExistException e) {
			report.setNeedName(needName);

		} catch (NoUOCExistException e) {
			report.setNeedName(need.getName());

		} catch (NoSuchSurveyExistException e) {
			report.setNeedName(need.getName());
			List<UocCourseStatusBean> notAnsweredUOCCourseStatusBeans = _convertIntoUOCCourstatusBeans(
					uocs, SurveyDataServiceHelper.answerNotAnswered);
			report.setUocCourses(notAnsweredUOCCourseStatusBeans);

		} catch (NoAnsweredFoundForExistingSurvey e) {
			report.setNeedName(need.getName());
			List<UocCourseStatusBean> notAnsweredUOCCourseStatusBeans = _convertIntoUOCCourstatusBeans(
					uocs, SurveyDataServiceHelper.answerNotAnswered);
			report.setUocCourses(notAnsweredUOCCourseStatusBeans);

		}  
		return report;

	}

	private List<Uoc> _filterUniqueUocs(List<Uoc> tempUocs) {
		List<Uoc> filteredUocs = new ArrayList<Uoc>();
		try {
			for (Iterator iterator = tempUocs.iterator(); iterator.hasNext();) {
				Uoc uoc = (Uoc) iterator.next();
				boolean isContain = filteredUocs.contains(uoc);
				if (!isContain)
					filteredUocs.add(uoc);

			}
			return filteredUocs;
		} catch (Exception e) {
			return filteredUocs;
		}
	}

	private List<UocCourseStatusBean> _convertIntoUOCCourstatusBeans(
			List<Uoc> uocsYesAnswered, String completed) {

		List<UocCourseStatusBean> beans = new ArrayList<UocCourseStatusBean>();
		try {

			for (Iterator iterator = uocsYesAnswered.iterator(); iterator
					.hasNext();) {
				Uoc uoc = (Uoc) iterator.next();
				UocCourseStatusBean bean = new UocCourseStatusBean();
				bean.setCourseName(uoc.getName());
				if ((completed
						.equalsIgnoreCase(SurveyDataServiceHelper.answerYes))
						|| (completed
								.equalsIgnoreCase(SurveyDataServiceHelper.answerNo))) {
					bean.setCompleted("Yes");
					bean.setFututeLearning("");
				} else {
					bean.setCompleted("");
					bean.setFututeLearning("Yes");

				}

				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			return beans;
		}
	}

	private List<Uoc> _getUocByAnswered(List<SurveryAnswer> primaryYesAnswered,
			List<UocQuestion> allPrimaryQuestion) {
		List<Uoc> uocs = new ArrayList<Uoc>();
		try {
			for (Iterator iterator = primaryYesAnswered.iterator(); iterator
					.hasNext();) {
				SurveryAnswer answer = (SurveryAnswer) iterator.next();
				for (Iterator iterator2 = allPrimaryQuestion.iterator(); iterator2
						.hasNext();) {
					UocQuestion uocQuestion = (UocQuestion) iterator2.next();
					if (uocQuestion.getIduocquestion()==
							answer.getUocQuestionId()) {
						uocs.add(uocQuestion.getUoc());
						break;
					}

				}

			}
			return uocs;
		} catch (Exception e) {
			return uocs;
		}
	}

	private List<SurveryAnswer> _filterAnswersByAnsType(
			List<SurveryAnswer> allPrimaryAnswers, String answerValue) {

		List<SurveryAnswer> answers = new ArrayList<SurveryAnswer>();
		try {
			for (Iterator iterator = allPrimaryAnswers.iterator(); iterator
					.hasNext();) {
				SurveryAnswer surveryAnswer = (SurveryAnswer) iterator.next();
				if (surveryAnswer.getValue().equalsIgnoreCase(answerValue)) {
					answers.add(surveryAnswer);
				}
			}
			return answers;
		} catch (Exception e) {
			return answers;
		}
	}

	private List<SurveryAnswer> _filterAnswersByQtnType(
			List<SurveryAnswer> answers, List<UocQuestion> allPrimaryQuestion) {

		List<SurveryAnswer> resultAnswers = new ArrayList<SurveryAnswer>();
		try {
			for (Iterator iterator = answers.iterator(); iterator.hasNext();) {
				SurveryAnswer answer = (SurveryAnswer) iterator.next();
				for (Iterator iterator2 = allPrimaryQuestion.iterator(); iterator2
						.hasNext();) {
					UocQuestion question = (UocQuestion) iterator2.next();
					if (answer.getUocQuestionId()==
							question.getIduocquestion()) {
						resultAnswers.add(answer);
						break;
					}

				}

			}
			return resultAnswers;
		} catch (Exception e) {
			return resultAnswers;
		}
	}

	private List<UocQuestion> _getQuestionByQtnType(
			List<UocQuestion> uocQuestions, short questiontype)
			  {
		List<UocQuestion> questions = new ArrayList<UocQuestion>();
		try {
			for (Iterator iterator = uocQuestions.iterator(); iterator
					.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				if (uocQuestion.isIsprimay().shortValue() == questiontype)
					questions.add(uocQuestion);

			}

			if (questions.size() == 0)
				throw new PrimaryQuestionNotExistsException("No Question Found");

			return questions;

		} catch (Exception e) {
			return null;
			//throw new PrimaryQuestionNotExistsException("No Question Found");
		}
	}

	public ProfileListItemBean getBlankUserProfile() {
		ProfileListItemBean bean = new ProfileListItemBean();
		try {

			List<UserTypeBean> userTypes = _getUserTypes();
			List<FunctionBean> functions = _getFunctions();
			List<OccupationBean> occupations = _getOccupations(null);
			List<PathwayBean> pathways = _getPathways();
			List<GenderBean> genders = _getGenders();
			List<LocationBean> locations = _getlocations();
			List<LevelBean> levels = _getLevels(null);
			bean.setLoginId("");
			bean.setPassword("");
			bean.setRePassword("");
			
			bean.setMylock(false);
			bean.setMylockedOn(null);
			bean.setPwChangeOn(null);

			bean.setLocation(locations.get(0));
			bean.setFunction(functions.get(0));
			bean.setJobTitle(occupations.get(0));
			bean.setLearningPathway(pathways.get(0));
			bean.setGender(genders.get(0));
			bean.setUserType(userTypes.get(0));
			bean.setLevel(levels.get(0));

			bean.setGenders(_getGenders());
			bean.setPathways(pathways);
			bean.setUserTypes(userTypes);
			bean.setFunctions(functions);
			bean.setJobTitles(occupations);
			bean.setLocations(locations);
			bean.setLevels(levels);

			return bean;

		} catch (Exception e) {
			return new ProfileListItemBean();
		}

	}

	private List<LocationBean> _getlocations() {

		List<LocationBean> beans = new ArrayList<LocationBean>();

		LocationBean pleaseSelect = new LocationBean();
		pleaseSelect.setLocationId(-1);
		pleaseSelect.setLocationName("Please Select ");
		beans.add(pleaseSelect);

		try {
			LocationDAO dao = new LocationDAO();
			List<Location> entities = dao.getAll();
			Collections.sort(entities, Location.locationNameComparator);
			for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
				Location locatin = (Location) iterator.next();
				LocationBean bean = LocationBean.get(locatin);
				beans.add(bean);

			}
			return beans;
		} catch (Exception e) {
			return beans;
		}

	}

	public ProfileListItemBean getSelectedUserProfile(String selectedUserLoginId) {
		ProfileListItemBean bean = new ProfileListItemBean();
		try {
			IndividualDAO dao = new IndividualDAO();
			Individual selectedIndividual = dao.getByLogin(selectedUserLoginId);

			bean = _getProfileItem(selectedIndividual);

			bean.setGenders(_getGenders());
			bean.setPathways(_getPathways());
			bean.setUserTypes(_getUserTypes());
			bean.setFunctions(_getFunctions());
			Function function = selectedIndividual.getFunction();
			bean.setJobTitles(_getJobTitles(function));
			Occupation occupation = selectedIndividual.getOccupation();
			bean.setLevels(_getLevels(occupation));
			return bean;

		} catch (Exception e) {
			return new ProfileListItemBean();
		}

	}

	private List<SurveryAnswer> _getSurveyAnswersByNeed(Need need, Survey survey)
			throws NoSuchSurveyExistException, NoAnsweredFoundForExistingSurvey {
		try {

			// Survey survey =_getSurvey(need);

			List<SurveryAnswer> answers = surveyAnswerDao.getBySurveyId(survey
					.getIdsurvey());
			if (answers == null || answers.size() == 0)
				throw new NullPointerException();
			return answers;

		} catch (NullPointerException e) {
			throw new NoAnsweredFoundForExistingSurvey();
		}
	}

	private List<UocQuestion> _getUocQuestions(List<Uoc> uocs) {
		List<UocQuestion> questions = new ArrayList<UocQuestion>();
		try {
			for (Iterator iterator = uocs.iterator(); iterator.hasNext();) {
				Uoc uoc = (Uoc) iterator.next();
				questions.addAll(uoc.getUocQuestions());

			}
			return questions;

		} catch (Exception e) {
			return questions;
		}
	}

	private List<Uoc> _getUocCollectionByNeedDapsso(Need need)
			throws NoUOCExistException {
		try {
			List<Uoc> uocs = new ArrayList<Uoc>();

			List<UocGroup> uocGroupsByDapssco = dapssco.getUocGroups();
			List<UocGroup> uocGroupsByNeed = need.getUocGroups();
			if (uocGroupsByDapssco.size() == 0 || uocGroupsByNeed.size() == 0)
				throw new NoUOCExistException("No UOCGROUPS Mapping exist");

			try {
				List<UocGroup> filteredUocGroups = new ArrayList<UocGroup>();
				for (Iterator iterator = uocGroupsByDapssco.iterator(); iterator
						.hasNext();) {
					UocGroup uocGroupofDapssco = (UocGroup) iterator.next();
					boolean isContains = uocGroupsByNeed
							.contains(uocGroupofDapssco);
					if (isContains) {
						filteredUocGroups.add(uocGroupofDapssco);
					}
				}

				// getting uoc from filtered uocgrups
				for (Iterator iterator = filteredUocGroups.iterator(); iterator
						.hasNext();) {
					UocGroup uocGroup = (UocGroup) iterator.next();
					List<Uoc> temp = uocGroup.getUocs();
					if (temp != null && temp.size() > 0)
						uocs.addAll(temp);

				}
				// / checking any duplicate uoc in collection of uocs

				List<Uoc> uniqueUocs = new ArrayList<Uoc>();
				for (Iterator iterator = uocs.iterator(); iterator.hasNext();) {
					Uoc uoc = (Uoc) iterator.next();
					if (!uniqueUocs.contains(uoc))
						uniqueUocs.add(uoc);
				}
				return uniqueUocs;

			} catch (Exception e1) {
				throw new NoUOCExistException(e1.getMessage());
			}

		} catch (Exception e) {
			throw new NoUOCExistException(e.getMessage());
		}
	}

	private Survey _getSurvey(Need need) throws NoSuchSurveyExistException {
		try {

			surveyDao = new SurveyDAO();
			Survey survey = surveyDao.getSurveyByPathwayofIndividual(user,
					need, dapssco);
			survey.getIdsurvey();

			return survey;
		} catch (Exception e) {
			throw new NoSuchSurveyExistException("There is no survey Exist");
		}
	}

	private Need _getNeed(String needName) throws NoSuchNeedExistException {
		Need need = null;
		try {
			NeedDAO needDao = new NeedDAO();
			need = needDao.getNeedByName(needName);
			need.getIdneed();
			return need;
		} catch (Exception e) {
			throw new NoSuchNeedExistException("No  Such Need Exist");
		}
	}

	private ProfileListItemBean _getProfileItem(Individual individualUser) {
		ProfileListItemBean bean = new ProfileListItemBean();
		try {

			Level level = individualUser.getLevel();
			Function function = individualUser.getFunction();
			Occupation occupation = individualUser.getOccupation();
			Location location = individualUser.getLocation();
			UserType userType = individualUser.getUsertypefk();
			Pathway pathway = individualUser.getPathway();
			
			//pathway.setName("");

			FunctionBean functionBean = null;
			OccupationBean occupationBean = null;
			LevelBean levelBean = null;
			LocationBean locationBean = null;
			UserTypeBean userTypeBean = null;
			PathwayBean pathwayBean  = null;
			
			if(pathway!=null){
				pathwayBean = PathwayBean.get(pathway);
			}
			
			if (function != null) {
				functionBean = FunctionBean.get(function);
			}

			if (level != null)
				levelBean = LevelBean.get(level);

			if (occupation != null)
				occupationBean = OccupationBean.get(occupation);

			if (location != null)
				locationBean = LocationBean.get(location);

			if (userType != null)
				userTypeBean = UserTypeBean.get(userType);

			bean.setMylockedOn(individualUser.getMylockedOn());
			bean.setMylock(individualUser.isMylock());
			bean.setPwChangeOn(individualUser.getPwChangeon());
			
			//String genderName = user.getGender().getName();

			GenderBean genderBean = _getGender(individualUser.getGender());
		   if (genderBean.getGenderId()==-1)
			   genderBean.setGenderName("");
			
		   bean.setFunction(functionBean);
			bean.setGender(genderBean);
			bean.setJobTitle(occupationBean);
			bean.setLearningPathway(pathwayBean);
			bean.setLevel(levelBean);
			bean.setLocation(locationBean);
			bean.setLoginId(individualUser.getLogin());
			bean.setUserType(userTypeBean);
			return bean;

		} catch (Exception e) {
			return new ProfileListItemBean();
		}

	}

	private ChoiceBean _getChoice1(Individual individual) {
		List<ChoiceBean> beans = _getChoices();
		ChoiceBean bean = new ChoiceBean();
		bean = beans.get(0);
		try {

			String ans1 = individual.getAnsofqtn1();
			if (ans1 != null) {
				for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
					ChoiceBean choiceBean = (ChoiceBean) iterator.next();
					if (choiceBean.getId() == Long.parseLong(ans1)) {
						return choiceBean;
					}

				}
				return bean;
			} // /

			return bean;
		} catch (Exception e) {
			return bean;

		}

	}

	private ChoiceBean _getChoice2(Individual individual) {
		List<ChoiceBean> beans = _getChoices();
		ChoiceBean bean = new ChoiceBean();
		bean = beans.get(0);
		try {

			String ans1 = individual.getAnsofqtn2();
			if (ans1 != null) {
				for (Iterator iterator = beans.iterator(); iterator.hasNext();) {
					ChoiceBean choiceBean = (ChoiceBean) iterator.next();
					if (choiceBean.getId() == Long.parseLong((ans1))) {
						return choiceBean;
					}

				}

			} // /
			return bean;
		} catch (Exception e) {
			return bean;
			// TODO: handle exception
		}
	}

	private List<ChoiceBean> _getChoices() {
		List<ChoiceBean> beans = new ArrayList<ChoiceBean>();
		try {

			ChoiceBean bean2 = new ChoiceBean();
			bean2.setId(0);
			bean2.setName("No");
			beans.add(bean2);

			ChoiceBean bean = new ChoiceBean();
			bean.setId(1);
			bean.setName("Yes");
			beans.add(bean);

			return beans;
		} catch (Exception e) {
			return beans;
		}
	}

	private List<GenderBean> _getGenders() {
		
		
		List<GenderBean> genders = new ArrayList<GenderBean>();

		GenderBean pleaseSelect = new GenderBean();
		pleaseSelect.setGenderId(-1);
		pleaseSelect.setGenderName("Please Select");
		genders.add(pleaseSelect);
 		GenderDao genderDao = new GenderDao();
		List<Gender> entities = genderDao.getAll();
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Gender gender = (Gender) iterator.next();
			GenderBean bean = GenderBean.get(gender);
			genders.add(bean);
		}
		return genders;
		 

	}

	private GenderBean _getGender(Gender gender) {
		GenderBean genderBean;
		
		try {
			if (gender != null) {
				genderBean = GenderBean.get(gender);
				return genderBean;
			}
			 

		} catch (Exception e) {
			

		}
		List<GenderBean> genders = _getGenders();
return genders.get(0);
	}

	private PathwayBean _getPathway(Pathway pathway) {

		if(pathway!=null){
			return PathwayBean.get(pathway);
			
			
		}
		List<PathwayBean> pathways = _getPathways();
		return pathways.get(0);
	}

	private List<PathwayBean> _getPathways() {
	
		List<PathwayBean> beans = new ArrayList<PathwayBean>();
		PathwayBean pleaseSelect = new PathwayBean();
		pleaseSelect.setId(-1);
		pleaseSelect.setName("Please Select");
		beans.add(pleaseSelect);

		PathwayDao dao = new PathwayDao();
		
		List<Pathway> entities = dao.getAll();
		for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
			Pathway pathway = (Pathway) iterator.next();
			PathwayBean bean = PathwayBean.get(pathway);
			beans.add(bean);
		}
		
		return beans;
		// try {
		//
		//
		// PathwayBean p1 = new PathwayBean();
		// p1.setId(-1);
		// p1.setName(SurveyDataServiceHelper.pleaseSelect);
		// pathways.add(p1);
		//
		// PathwayBean p2 = new PathwayBean();
		// p2.setId(1);
		// p2.setName("Accredited");
		// pathways.add(p2);
		//
		// PathwayBean p3 = new PathwayBean();
		// p3.setId(2);
		// p3.setName("People");
		// pathways.add(p3);
		//
		// PathwayBean p4 = new PathwayBean();
		// p4.setId(3);
		// p4.setName("Corporate");
		// pathways.add(p4);
		//
		// return pathways;
		// } catch (Exception e) {
		// return pathways;
		// }

	}

	public ProfileDetailsBean getProfiles() {
		ProfileDetailsBean bean = new ProfileDetailsBean();
		try {
			IndividualDAO dao = new IndividualDAO();
			List<Individual> individuals = dao.getAll();
			for (Iterator iterator = individuals.iterator(); iterator.hasNext();) {
				Individual individual = (Individual) iterator.next();
				ProfileListItemBean item = _getProfileItem(individual);
				bean.add(item);

			}

			return bean;
		} catch (Exception e) {
			return bean;
		}

	}

	private List<LevelBean> _getLevels(Occupation occupation) {

		List<LevelBean> beans = new ArrayList<LevelBean>();
		LevelBean pleaseSelect = new LevelBean();
		pleaseSelect.setId(-1);
		pleaseSelect.setCode("Please Select");
		pleaseSelect.setDescription("Please Select");
		beans.add(pleaseSelect);

		try {

			List<Level> entites = new ArrayList<Level>();
			entites = occupation.getLevels();
			Collections.sort(entites, Level.LevelNameComparator);
			for (Iterator iterator = entites.iterator(); iterator.hasNext();) {
				Level level = (Level) iterator.next();
				LevelBean bean = LevelBean.get(level);
				beans.add(bean);

			}
			return beans;
		} catch (Exception e) {
			return beans;
		}

	}

	private List<OccupationBean> _getJobTitles(Function function) {

		List<OccupationBean> beans = new ArrayList<OccupationBean>();
		OccupationBean pleaseSelect = new OccupationBean();
		pleaseSelect.setId(-1);
		pleaseSelect.setName("Please Select");
		pleaseSelect.setDescription("Please Select");
		beans.add(pleaseSelect);

		try {
			OccupationDAO dao = new OccupationDAO();
			List<Occupation> entities = new ArrayList<Occupation>();
			if (function != null) {
				entities = function.getOccupations();

				for (Iterator iterator = entities.iterator(); iterator
						.hasNext();) {
					Occupation level = (Occupation) iterator.next();
					OccupationBean bean = OccupationBean.get(level);
					beans.add(bean);

				}
				return beans;

			}
			return beans;

		} catch (Exception e) {
			return beans;
		}
		// ////////

	}

	private List<FunctionBean> _getFunctions() {
		List<FunctionBean> beans = new ArrayList<FunctionBean>();
		
		FunctionBean pleaseSelect = new  FunctionBean();
		pleaseSelect.setId(-1);
		pleaseSelect.setDescription(SurveyDataServiceHelper.pleaseSelect);
		pleaseSelect.setName(SurveyDataServiceHelper.pleaseSelect);
		beans.add(pleaseSelect);
		
		List<Function> entities = null;
		FunctionDAO dao = new FunctionDAO();
		try {
			entities = dao.getAll();
			Collections.sort(entities, Function.functionNameComparator);

			for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
				Function function = (Function) iterator.next();
				FunctionBean bean = FunctionBean.get(function);
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			return beans;
		}
	}

	public SurveyMergedReport getMergedReport1(){
		SurveyMergedReport report = new SurveyMergedReport();
		try {

			List<Need> userTypeNeed = _getUserTypeNeed();
			List<ReportMergedSurveyBean> collection = new ArrayList<ReportMergedSurveyBean>();
			report.setItems(collection);
		
			for (Iterator iterator = userTypeNeed.iterator(); iterator
					.hasNext();) {
				Need need = (Need) iterator.next();
				 ReportMergedSurveyBean singlereport = mergedReport(need.getName());
				 collection.add(singlereport);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
		report.setProfileInfo(_getReportProfileInfo());
	QuestionBean question1= (SurveyDataService.readQuestionProperties("1", request));
	if(question1!=null){
		report.setQuestion1(question1.getQuestion());
	}

ChoiceBean ans1Ben =_getChoice1(user);
String ans1 =ans1Ben.getName();
report.setAnswer1(ans1);
	
	QuestionBean question2= (SurveyDataService.readQuestionProperties("2", request));
	if(question2!=null){
		report.setQuestion2(question2.getQuestion());
	}
	 ChoiceBean ans2Bean =_getChoice2(user);
	 String ans2 = ans2Bean.getName();
	 report.setAnswer2(ans2);
	
	String title =SurveyDataService.readTextProperties("mrep_Title", request);
	 report.setTitle(title);
 
		return report ;
		
	}
	
	
	public SurveyMergedReport getMergedReport() {
		SurveyMergedReport report = new SurveyMergedReport();
		try {

			List<ReportMergedSurveyBean> items = new ArrayList<ReportMergedSurveyBean>();
			report.setItems(items);
			SurveyReportSkillRecognitionBean recognitionReport = getRecognitionReport();
			List<SkillRecogReportBean> reognitionItems = recognitionReport
					.getItems();
			SurveyGapReport gapReport = getGapReport();
			report.setTitle("Report");
			report.setProfileInfo(gapReport.getProfile());

			List<ReportSurveyBean> gapItems = gapReport.getItems();
			for (Iterator iterator = gapItems.iterator(); iterator.hasNext();) {
				ReportSurveyBean reportSurveyBean = (ReportSurveyBean) iterator
						.next();

				ReportMergedSurveyBean myItem = new ReportMergedSurveyBean();
				myItem.setNeedDescription(reportSurveyBean.getNeedDescription());
				myItem.setNeedName(reportSurveyBean.getNeedName());
				myItem.setProfileInfo(reportSurveyBean.getProfileInfo());

				items.add(myItem);
				List<UocCourseStatusRecognitionBean> courseStatusCollection = new ArrayList<UocCourseStatusRecognitionBean>();
				myItem.setItems(courseStatusCollection);

				String needName = reportSurveyBean.getNeedName();

				SkillRecogReportBean recognitionReportItem = _getReognitionItem(
						reognitionItems, needName);

				List<UocCourseStatusBean> uocCourseStatusStatues = reportSurveyBean
						.getUocCourses();
				for (Iterator iterator2 = uocCourseStatusStatues.iterator(); iterator2
						.hasNext();) {
					UocCourseStatusBean uocCourseStatusBean = (UocCourseStatusBean) iterator2
							.next();

					String uocCourseName = uocCourseStatusBean.getCourseName();

					UnitOfCompetencyBean recognitionUocCourseStutusBean = _getRecognitionUocCourse(
							recognitionReportItem, uocCourseName);

					UocCourseStatusRecognitionBean bean = new UocCourseStatusRecognitionBean();
					courseStatusCollection.add(bean);

					bean.setCourseName(uocCourseName);
					bean.setCompleted(uocCourseStatusBean.getCompleted());
					bean.setFututeLearning(uocCourseStatusBean
							.getFututeLearning());
					if (recognitionUocCourseStutusBean != null)
						bean.setRecognition(recognitionUocCourseStutusBean
								.getRecognition());
					else
						bean.setRecognition("");

				}

			}

//			List<QuestionBean> otherInformationQuestions = SurveyDataService
//					.readQuestionProperties(request);
			report.setQuestion1(SurveyDataService
					.readQuestionProperties("1", request).getQuestion());
			report.setAnswer1(_getChoice1(user).getName());
			report.setQuestion2(SurveyDataService
					.readQuestionProperties("2", request).getQuestion());
			report.setAnswer1(_getChoice1(user).getName());
			report.setAnswer2(_getChoice2(user).getName());

			return report;

		} catch (Exception e) {
			return report;
		}

	}

	private UnitOfCompetencyBean _getRecognitionUocCourse(
			SkillRecogReportBean recognitionReportItem, String uocCourseName) {
		try {
			List<UnitOfCompetencyBean> collection = recognitionReportItem
					.getItems();
			for (Iterator iterator = collection.iterator(); iterator.hasNext();) {
				UnitOfCompetencyBean unitOfCompetencyBean = (UnitOfCompetencyBean) iterator
						.next();
				String courseName = unitOfCompetencyBean.getUocName();
				if (courseName.equalsIgnoreCase(uocCourseName))
					return unitOfCompetencyBean;

			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	private SkillRecogReportBean _getReognitionItem(
			List<SkillRecogReportBean> reognitionItems, String needName) {
		try {
			for (Iterator iterator = reognitionItems.iterator(); iterator
					.hasNext();) {
				SkillRecogReportBean skillRecogReportBean = (SkillRecogReportBean) iterator
						.next();
				String recognitionNeedName = skillRecogReportBean.getNeedName();
				if (recognitionNeedName.equalsIgnoreCase(needName))
					return skillRecogReportBean;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	public SurveyGapReport getGapReport() {
		SurveyGapReport report = new SurveyGapReport();
		try {

			List<Need> userTypeNeed = _getUserTypeNeed();

			for (Iterator iterator = userTypeNeed.iterator(); iterator
					.hasNext();) {
				Need need = (Need) iterator.next();
				ReportSurveyBean bean = getNeedReport(need.getName());
				report.addItem(bean);

			}

			report.setTitle("Gap Report");
			report.setProfile(_getReportProfileInfo());

			return report;

		} catch (Exception e) {
			return report;
		}

	}

	private List<ReportProfileInfoBean> _getReportProfileInfo() {
		List<ReportProfileInfoBean> cProfileInfo = new ArrayList<ReportProfileInfoBean>();

		try {

			//
			ReportProfileInfoBean rpi1 = new ReportProfileInfoBean();
			String userid =SurveyDataService.readTextProperties("mrep_userid", request);
			rpi1.setName(userid);
			rpi1.setValue(user.getLogin());
			cProfileInfo.add(rpi1);

			String levelName = user.getLevel().getDescription();
			String occupationName = user.getOccupation().getDescription();
			ReportProfileInfoBean rpi2 = new ReportProfileInfoBean();
			String occupation =SurveyDataService.readTextProperties("mrep_occupation", request);
			
			rpi2.setName(occupation);
			rpi2.setValue(levelName + " " + occupationName);
			cProfileInfo.add(rpi2);

			ReportProfileInfoBean rpiLoca = new ReportProfileInfoBean();
			String location =SurveyDataService.readTextProperties("mrep_location", request);
			rpiLoca.setName(location);
			rpiLoca.setValue(user.getLocation().getName());
			cProfileInfo.add(rpiLoca);

//			ReportProfileInfoBean rpi3 = new ReportProfileInfoBean();
//			rpi3.setName("Preferred Learning Path");
////			long pathwayId = user.getPathway();
//			PathwayBean pathway = PathwayBean.get(user.getPathway());
//			rpi3.setValue(pathway.getName());
//			cProfileInfo.add(rpi3);

			ReportProfileInfoBean rpi4 = new ReportProfileInfoBean();

			String sup_manager =SurveyDataService.readTextProperties("mrep_super_man", request);
			rpi4.setName(sup_manager);

			String userTypeName = user.getUsertypefk().getUserType();
			if (userTypeName
					.equalsIgnoreCase(SurveyDataServiceHelper.individualType)) {
				rpi4.setValue("No");
			} else if (userTypeName
					.equalsIgnoreCase(SurveyDataServiceHelper.supervisorType)) {
				rpi4.setValue("Yes");
			}
			cProfileInfo.add(rpi4);

			return cProfileInfo;

		} catch (Exception e) {
			return cProfileInfo;
		}
	}

	private List<UserTypeBean> _getUserTypes() {
		List<UserTypeBean> beans = new ArrayList<UserTypeBean>();
		UserTypeDAO dao = new UserTypeDAO();
		try {

			UserTypeBean pleaseSelect = new UserTypeBean();
			pleaseSelect.setUserId(-1);
			pleaseSelect.setUserTypeName(SurveyDataServiceHelper.pleaseSelect);
			pleaseSelect.setUserTypeDescription(SurveyDataServiceHelper.pleaseSelect);
			
			beans.add(pleaseSelect);
			List<UserType> entities = dao.getAll();
			for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
				UserType userType = (UserType) iterator.next();
				UserTypeBean bean = new UserTypeBean();
				bean.setUserId(userType.getId());
				bean.setUserTypeName(userType.getUserType());
				bean.setUserTypeDescription(userType.getDescription());
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			return beans;
		}
	}

	private SkillRecogReportBean _getReognitionReport(Need need) {
		SkillRecogReportBean item = new SkillRecogReportBean();
		item.setNeedName(need.getName());
		item.setNeedDescription(need.getDescription());

		List<Uoc> uocs = null;
		try {

			uocs = _getUocCollectionByNeedDapsso(need);
			List<UocQuestion> uocQuestions = _getUocQuestions(uocs);
			Survey survey = _getSurvey(need);
			List<SurveryAnswer> answers = _getSurveyAnswersByNeed(need, survey);

			List<UocQuestion> allPrimaryQuestion = _getQuestionByQtnType(
					uocQuestions, questionTypePrimary);
			List<SurveryAnswer> allPrimaryAnswers = _filterAnswersByQtnType(
					answers, allPrimaryQuestion);

			List<SurveryAnswer> primaryYesAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerYes);
			List<SurveryAnswer> primaryNoAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers, SurveyDataServiceHelper.answerNo);
			List<SurveryAnswer> primaryNotAnswered = _filterAnswersByAnsType(
					allPrimaryAnswers,
					SurveyDataServiceHelper.answerNotAnswered);

			int yesSize = primaryYesAnswered.size();
			int noSize = primaryNoAnswered.size();
			int notAnsweredSize = primaryNotAnswered.size();

			int totalPrimary = yesSize + noSize + notAnsweredSize;
			int answeredPrimary = yesSize + noSize;
			int percentagePrimary = (answeredPrimary * 100) / totalPrimary;

			if (percentagePrimary == 100) {

				List<UnitOfCompetencyBean> unitBeans = _convertIntoUnitofCompetencyBeanCollection(
						uocs, SurveyDataServiceHelper.statusNo,
						SurveyDataServiceHelper.questionTypePrimary);

				item.setItems(unitBeans);
				return item;
			}

			List<Uoc> yesUocs = _getUocfromAnswers(primaryYesAnswered,
					allPrimaryQuestion);
			List<UnitOfCompetencyBean> yesUnitBeans = _convertIntoUnitofCompetencyBeanCollection(
					yesUocs, SurveyDataServiceHelper.statusNo,
					SurveyDataServiceHelper.questionTypePrimary);
			item.addAll(yesUnitBeans);

			if (notAnsweredSize > 0) {
				List<Uoc> notAnsweredUocs = _getUocfromAnswers(
						primaryNotAnswered, allPrimaryQuestion);
				List<UnitOfCompetencyBean> unitBeans = _convertIntoUnitofCompetencyBeanCollection(
						notAnsweredUocs,
						SurveyDataServiceHelper.statusNotApplicable,
						SurveyDataServiceHelper.questionTypePrimary);
				item.addAll(unitBeans);
			}

			// / if answres is set to No in primary Question

			List<Uoc> noAnsweredUOCS = _getUocfromAnswers(primaryNoAnswered,
					uocQuestions);

			List<UocQuestion> noSecondaryUocQuestions = _getUOCQuestionByType(
					noAnsweredUOCS, uocQuestions,
					SurveyDataServiceHelper.questionTypesecondary);
			List<SurveryAnswer> allSecondaryQuestinAnswers = _filterAnswersByQtnType(
					answers, noSecondaryUocQuestions);

			List<SurveryAnswer> secondaryYesAnswered = _filterAnswersByAnsType(
					allSecondaryQuestinAnswers,
					SurveyDataServiceHelper.answerYes);
			List<SurveryAnswer> secondaryNoAnswered = _filterAnswersByAnsType(
					allSecondaryQuestinAnswers,
					SurveyDataServiceHelper.answerNo);
			List<SurveryAnswer> secondaryNotAnswered = _filterAnswersByAnsType(
					allSecondaryQuestinAnswers,
					SurveyDataServiceHelper.answerNotAnswered);

			List<Uoc> yesSecondaryUocs = _getUocfromAnswers(
					secondaryYesAnswered, noSecondaryUocQuestions);
			List<UnitOfCompetencyBean> yesSecondaryUnitBeans = _convertIntoUnitofCompetencyBeanCollection(
					yesSecondaryUocs, SurveyDataServiceHelper.statusYes,
					SurveyDataServiceHelper.questionTypesecondary);
			item.addAll(yesSecondaryUnitBeans);

			List<Uoc> noSecondaryUocs = _getUocfromAnswers(secondaryNoAnswered,
					noSecondaryUocQuestions);
			List<UnitOfCompetencyBean> noSecondaryUnitBeans = _convertIntoUnitofCompetencyBeanCollection(
					noSecondaryUocs, SurveyDataServiceHelper.statusNo,
					SurveyDataServiceHelper.questionTypesecondary);
			item.addAll(noSecondaryUnitBeans);

			List<Uoc> notSecondaryUocs = _getUocfromAnswers(
					secondaryNotAnswered, noSecondaryUocQuestions);
			List<UnitOfCompetencyBean> notSecondaryUnitBeans = _convertIntoUnitofCompetencyBeanCollection(
					notSecondaryUocs, SurveyDataServiceHelper.statusYes,
					SurveyDataServiceHelper.questionTypesecondary);
			item.addAll(notSecondaryUnitBeans);

			int seoncdaryNotAnswered = secondaryNotAnswered.size();
			return item;

		} catch (NoUOCExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchSurveyExistException e) {

			List<UnitOfCompetencyBean> unitBeans = _convertIntoUnitofCompetencyBeanCollection(
					uocs, SurveyDataServiceHelper.statusNotApplicable,
					SurveyDataServiceHelper.questionTypePrimary);

			item.setItems(unitBeans);
			return item;

		} catch (NoAnsweredFoundForExistingSurvey e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return item;
	}

	 

	private List<UocQuestion> _getUOCQuestionByType(
			List<Uoc> noORNotAnsweredUOCS, List<UocQuestion> uocQuestions,
			short questiontypesecondary2) {

		List<UocQuestion> result = new ArrayList<UocQuestion>();
		try {
			for (Iterator iterator = noORNotAnsweredUOCS.iterator(); iterator
					.hasNext();) {
				Uoc uoc = (Uoc) iterator.next();
				for (Iterator iterator2 = uocQuestions.iterator(); iterator2
						.hasNext();) {
					UocQuestion uocQuestion = (UocQuestion) iterator2.next();
					if (uocQuestion.getUoc().equals(uoc)
							&& uocQuestion.isIsprimay() == questiontypesecondary2) {
						result.add(uocQuestion);
					}

				}

			}
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	private List<UnitOfCompetencyBean> _convertIntoUnitofCompetencyBeanCollection(
			List<Uoc> uocs, String string, short qtnType) {
		List<UnitOfCompetencyBean> beans = new ArrayList<UnitOfCompetencyBean>();
		for (Iterator iterator = uocs.iterator(); iterator.hasNext();) {
			Uoc uoc = (Uoc) iterator.next();

			UnitOfCompetencyBean bean = __convertUOCIntoUnitofCompetencyBean(
					uoc, string, qtnType);
			beans.add(bean);
		}
		return beans;
	}

	private List<Uoc> _getUocfromAnswers(List<SurveryAnswer> answered,
			List<UocQuestion> uocQuestions) {
		List<Uoc> uocs = new ArrayList<Uoc>();
		try {
			for (Iterator iterator = answered.iterator(); iterator.hasNext();) {
				SurveryAnswer answer = (SurveryAnswer) iterator.next();
				long uocQuestionId = answer.getUocQuestionId();
				Uoc uoc = _getUocByUocQuestionId(uocQuestionId, uocQuestions);
				uocs.add(uoc);
			}
			return uocs;
		} catch (Exception e) {
			return uocs;
		}
	}

	private Uoc _getUocByUocQuestionId(long uocQuestionId,
			List<UocQuestion> uocQuestions) {

		try {
			for (Iterator iterator = uocQuestions.iterator(); iterator
					.hasNext();) {
				UocQuestion uocQuestion = (UocQuestion) iterator.next();
				if (uocQuestion.getIduocquestion()==uocQuestionId)
					return uocQuestion.getUoc();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	private UnitOfCompetencyBean __convertUOCIntoUnitofCompetencyBean(Uoc uoc,
			String status, short qtnType) {
		UnitOfCompetencyBean bean = new UnitOfCompetencyBean();
		try {
			bean.setUocName(uoc.getName());

			if (qtnType == SurveyDataServiceHelper.questionTypePrimary) {

				bean.setRecognition(status);
			}
			if (qtnType == SurveyDataServiceHelper.questionTypesecondary) {
				bean.setRecognition(status);
			}
			return bean;

		} catch (Exception e) {
			return bean;
		}
	}

	public SurveyReportSkillRecognitionBean getRecognitionReport() {
		SurveyReportSkillRecognitionBean report = new SurveyReportSkillRecognitionBean();

		report.setTitle("Skills Recognition Report");
		try {

			List<Need> needs = _getNeeds();
			for (Iterator iterator = needs.iterator(); iterator.hasNext();) {
				Need need = (Need) iterator.next();

				if (need.getName().equalsIgnoreCase(
						SurveyDataServiceHelper.desirable)
						|| need.getName().equalsIgnoreCase(
								SurveyDataServiceHelper.highlyDesirable)) {
					SkillRecogReportBean item = _getReognitionReport(need);
					report.add(item);
				}

			}

			report.setProfile(_getReportProfileInfo());

			return report;

		} catch (Exception e) {
			return report;
		}

	}

	private List<Need> _getNeeds() {
		try {
			NeedDAO dao = new NeedDAO();
			List<Need> needs = dao.getAll();
			return needs;

		} catch (Exception e) {
			return null;
		}
	}

	public boolean editUserByAdmin(ProfileListItemBean bean) {
		try {

			String loginId = bean.getLoginId();
			
			long userTypeId =0;
			long functionId = 0;// bean.getFunction().getId();
			long occupationId = 0; // bean.getJobTitle().getId();
			long levelId = 0; // bean.getLevel().getId();
			long locationId = 0; // bean.getLocation().getLocationId();
			long genderId = 0; //bean.getGender().getGenderId();
			long pathwayId =0 ;// bean.getLearningPathway().getId();
		 	boolean mylock = false ; // bean.isMylock();
		 	
		 	
		 	
			UserType userType = null;
			Function function = null;
			Occupation occupation = null;
			Level level = null;
			Location location = null;
			Pathway pathway = null;
			Gender gender = null;
			
			IndividualDAO dao = new IndividualDAO();
			Individual individualUser = dao.getByLogin(loginId);

		 	
			UserTypeBean userTypeBean = bean.getUserType();
			if(userTypeBean!=null){
				userTypeId = userTypeBean.getUserId();
				userType = _getuserType(userTypeId);
			}
			individualUser.setUsertypefk(userType);
			
		 	FunctionBean functionBean = bean.getFunction();
		 	if(functionBean!=null){
		 		functionId= functionBean.getId();
		 		function = _getFunction(functionId);
			}
		 	individualUser.setFunction(function);
		 	
		 	
		 	
		 	OccupationBean occupationBean = bean.getJobTitle();
		 	if(occupationBean!=null)
		 	{occupationId = occupationBean.getId();
			occupation = _getOccupation(occupationId);
			}
		 	individualUser.setOccupation(occupation);

		 	
		 	LevelBean leveBean = bean.getLevel();
		 	{if(leveBean!=null)
		 		levelId = leveBean.getId();
			level = _getLevel(levelId);
			}
		 	individualUser.setLevel(level);
		 	
		 	
		 	LocationBean locationBean = bean.getLocation();
		 	
		 	if(locationBean!= null)
		 	{	locationId= locationBean.getLocationId();
		 	location = _getlocation(locationId);
		 	}
		 	individualUser.setLocation(location);

		 	GenderBean genderBean = bean.getGender();
		 	if(genderBean!=null)
		 	{	genderId= genderBean.getGenderId();
			gender = _getGenderById(genderId);
		 	}
			individualUser.setGender(gender);

		 	
		 	PathwayBean pathwayBean = bean.getLearningPathway();
		 	if(pathwayBean!=null)
		 	{
		 		pathwayId= pathwayBean.getId();
				pathway = _getPathway(pathwayId);
		 	}
		 	individualUser.setPathway(pathway );

		 	mylock = bean.isMylock();
		 	if(individualUser.isMylock()!= mylock){
					individualUser.setMylockedOn(new Date());
					individualUser.setMylock(mylock);					
		 	}
					
		 	
				individualUser = dao.save(individualUser);

			return true;

		} catch (Exception e) {
			return false;
		}

	}

	private UserType _getuserType(long userTypeId) {
		try {
			UserTypeDAO dao = new UserTypeDAO();
			UserType userType = dao.getById(userTypeId);
			return userType;
			
		} catch (Exception e) {
return null;
		}
	}

	private Pathway _getPathway(long pathwayId) {
		try {
			PathwayDao dao = new PathwayDao();
			Pathway pathway =dao.getById(pathwayId);
			return pathway;
		} catch (Exception e) {
		return null;	// TODO: handle exception
		}
	}

	public boolean createNewUserByAdmin(ProfileListItemBean bean) {

		try {

			String loginId = bean.getLoginId();
			Individual individual = _getIndividual(loginId);

		} catch (NoSuchUserExistException e) {

			String newPassword = bean.getPassword();
			String retypedNewPasswrod = bean.getRePassword();
			if(newPassword== null || retypedNewPasswrod==null ){
				return false;
			}
			if(!newPassword.equals(retypedNewPasswrod))
				return false;
			
			Individual individualUser = new Individual();
			individualUser.setLogin(bean.getLoginId());
			
			String inputPassword = bean.getPassword();
			
			String md5password =SurveyDataService.md5(inputPassword+SurveyDataServiceHelper.salt);
			individualUser.setPassword(md5password);
			

			long userTypeId =0;
			long functionId = functionId = 0;
			long occupationId = 0; // bean.getJobTitle().getId();
			long levelId = 0; //bean.getLevel().getId();
			long locationId =  0; //bean.getLocation().getLocationId();
			long genderId = 0; //bean.getGender().getGenderId();
			long pathwayId = 0;
			boolean mylock = false  ;//bean.isMylock();
//			PathwayBean pathwayBean = bean.getLearningPathway();
//			long pathwayId = pathwayBean.getId();
////			GenderBean genderBean = bean.getGender();

//			FunctionBean functionBean =		bean.getFunction(); //.getId();
//			if(functionBean!=null)
//			functionId = functionBean.getId();	
//			OccupationBean occupationBean = bean.getJobTitle();
//			if(occupationBean !=null)
//			occupationId =occupationBean.getId();
//			
			
			
			
			UserType userType = null;
			Function function = null;
			Occupation occupation = null;
			Level level = null;
			Location location = null;
			Pathway pathway = null;
			Gender gender = null;
		//	 individual.setPassword("abc");

//			if (functionId > 0)
//				function = _getFunction(functionId);
//			individual.setFunction(function);
//
//			if (occupationId > 0)
//				occupation = _getOccupation(occupationId);
//			individual.setOccupation(occupation);
//
//			if (levelId > 0)
//				level = _getLevel(levelId);
//			individual.setLevel(level);
//
//			if (locationId > 0)
//				location = _getlocation(locationId);
//			individual.setLocation(location);
//			
//			if(pathwayId >0)
//				pathway = _getPathway(pathwayId);
//			individual.setPathway(pathway);
//			
//			if(genderId>0)
//				gender = _getGenderById(genderId);
//			individual.setGender(gender);
//
//			 
//			individual.setMylock(mylock);
//			if (mylock)
//				individual.setMylockedOn(new Date());
//			
			
			UserTypeBean userTypeBean = bean.getUserType();
			if(userTypeBean!=null){
				userTypeId = userTypeBean.getUserId();
				userType = _getuserType(userTypeId);
			}
			individualUser.setUsertypefk(userType);
			
		 	FunctionBean functionBean = bean.getFunction();
		 	if(functionBean!=null){
		 		functionId= functionBean.getId();
		 		function = _getFunction(functionId);
			}
		 	individualUser.setFunction(function);
		 	
		 	
		 	
		 	OccupationBean occupationBean = bean.getJobTitle();
		 	if(occupationBean!=null)
		 	{occupationId = occupationBean.getId();
			occupation = _getOccupation(occupationId);
			}
		 	individualUser.setOccupation(occupation);

		 	
		 	LevelBean leveBean = bean.getLevel();
		 	{if(leveBean!=null)
		 		levelId = leveBean.getId();
			level = _getLevel(levelId);
			}
		 	individualUser.setLevel(level);
		 	
		 	
		 	LocationBean locationBean = bean.getLocation();
		 	
		 	if(locationBean!= null)
		 	{	locationId= locationBean.getLocationId();
		 	location = _getlocation(locationId);
		 	}
		 	individualUser.setLocation(location);

		 	GenderBean genderBean = bean.getGender();
		 	if(genderBean!=null)
		 	{	genderId= genderBean.getGenderId();
			gender = _getGenderById(genderId);
		 	}
			individualUser.setGender(gender);

		 	
		 	PathwayBean pathwayBean = bean.getLearningPathway();
		 	if(pathwayBean!=null)
		 	{
		 		pathwayId= pathwayBean.getId();
				pathway = _getPathway(pathwayId);
		 	}
		 	individualUser.setPathway(pathway );
		 	mylock = bean.isMylock();
		 	
		 	if(mylock)
		 	individualUser.setMylockedOn(new Date());
			
		 	individualUser.setMylock(mylock);					
		 	
					

			IndividualDAO dao = new IndividualDAO();
			dao.saveNew(individualUser);
			return true;
		}
		return false;
	}

	private void _getReportingGapReportByFunction() {

		try {

			FunctionDAO functionDao = new FunctionDAO();
			IndividualDAO individualDao = new IndividualDAO();
			DapsscoDAO dapsscoDao = new DapsscoDAO();
			List<Function> functions = functionDao.getAll();
			for (Iterator iterator = functions.iterator(); iterator.hasNext();) {
				Function function = (Function) iterator.next();
				List<Occupation> occupations = function.getOccupations();
				for (Iterator iterator2 = occupations.iterator(); iterator2
						.hasNext();) {
					Occupation occupation = (Occupation) iterator2.next();
					List<Level> levels = occupation.getLevels();
					for (Iterator iterator3 = levels.iterator(); iterator3
							.hasNext();) {
						Level level = (Level) iterator3.next();
						// List<Individual> individuals =
						Dapssco dapsscoEntity = dapsscoDao.getByLevelOccuFunc(
								level, occupation, function);

						// List<Individual> individuals =level.getIndividuals();
						// for (Iterator iterator4 = individuals.iterator();
						// iterator4
						// .hasNext();) {
						// Individual individual = (Individual) iterator4
						// .next();
						// List<Survey> surveys =individual.getSurveyes();
						// for (Iterator iterator5 = surveys.iterator();
						// iterator5
						// .hasNext();) {
						// Survey survey = (Survey) iterator5.next();
						//
						//
						// }
						// }
					}

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private Location _getlocation(long locationId) {
		Location entitiy = null;
		try {
			LocationDAO dao = new LocationDAO();
			entitiy = dao.getById(locationId);
			return entitiy;
		} catch (Exception e) {
			return entitiy;
		}
	}

	private Level _getLevel(long levelId) {
		Level entitiy = null;
		try {
			LevelDAO dao = new LevelDAO();
			entitiy = dao.getById(levelId);
			return entitiy;
		} catch (Exception e) {
			return entitiy;
		}
	}

	private Occupation _getOccupation(long occupationId) {
		Occupation entitiy = null;
		try {
			OccupationDAO dao = new OccupationDAO();
			entitiy = dao.getById(occupationId);
			return entitiy;
		} catch (Exception e) {
			return entitiy;
		}
	}

	private Function _getFunction(long functionId) {
		Function function = null;
		try {
			FunctionDAO dao = new FunctionDAO();
			function = dao.getById(functionId);
			return function;
		} catch (Exception e) {
			return function;
		}
	}

	private Individual _getIndividual(String loginId)
			throws NoSuchUserExistException {

		try {
			IndividualDAO individualDao = new IndividualDAO();
			Individual individual = individualDao.getByLogin(loginId);
			if(individual==null)
				throw new NoSuchUserExistException("No User for login id "+ loginId);
			return individual;
		} catch (Exception e) {
			throw new NoSuchUserExistException("No User Exist");

		}
	}

	public ProfileSurveyBean getProfile() {
		Individual individual = null;
		Level level = null;
		Occupation occupation = null;
		Function function = null;
		Location location = null;
		UserType userType = null;
		Pathway  pathway = null;
		


		try {
			level = user.getLevel();
			function = user.getFunction(); // _getFunction(individual);
			occupation = user.getOccupation(); // _getOccupation(individual);
			location = user.getLocation();// _getLocation(individual);

			userType = user.getUsertypefk();// _getUserType(individual);
			pathway = user.getPathway();
			LevelBean levelBean = LevelBean.get(level);
			FunctionBean functionBean = FunctionBean.get(function);
			OccupationBean occupationBean = OccupationBean.get(occupation);
			LocationBean locationBean = LocationBean.get(location);
			UserTypeBean userTypeBean = UserTypeBean.get(userType);
			PathwayBean pathwayBean =_getPathway(pathway);
			GenderBean genderBean = _getGender(user.getGender());
			ChoiceBean ans1Choice = _getChoice1(user);
			ChoiceBean ans2Choice = _getChoice2(user);

			List<FunctionBean> functionBeans = _getFunctions();// _convertFunctions(functions);
			List<OccupationBean> occupationBeans = _getOccupations(function);
			List<LevelBean> levelBeans = _getLevels(occupation); // convertLevels(levels);

			List<LocationBean> locationBeans = _getlocations();// _convertLocations(locations);
			List<UserTypeBean> userTypeBeans = _getUserTypes();// _convertUserTypes(userTypes);
			List<GenderBean> genders =_getGenders();
			List<ChoiceBean> choices = _getChoices();
			List<PathwayBean> pathways = _getPathways();

			ProfileSurveyBean profile = new ProfileSurveyBean();

			profile.setLoginId(user.getLogin());

			profile.setUserType(userTypeBean);
			profile.setUserTypes(userTypeBeans);

			profile.setGender(genderBean);
			profile.setGenders(genders);

			profile.setLocation(locationBean);
			profile.setLocations(locationBeans);

			profile.setFunction(functionBean);
			profile.setFunctions(functionBeans);

			profile.setJobTitle(occupationBean);
			profile.setJobTitles(occupationBeans);

			profile.setLevel(levelBean);
			profile.setLevels(levelBeans);

			profile.setLearningPathway(pathwayBean);
			profile.setPathways(pathways);

			
			// profile.setQuestion1("Do you currently hold or are you currently undertaking Higher Education/Tertiary Qualifications ?");

			// profile.setQuestion2("If supported by Studybank, would you like to pursue Higher Education/Tertiary Qualification?");
			QuestionBean question1Bean =SurveyDataService
			.readQuestionProperties("1", request);
			QuestionBean question2Bean =SurveyDataService
					.readQuestionProperties("2", request);
				if(question1Bean!=null){
			if(question1Bean!=null)
			profile.setQuestion1(question1Bean.getQuestion());
			
			if(question2Bean!=null)
			profile.setQuestion2(question2Bean.getQuestion());
			}
			
			profile.setAnswerQuestion1(ans1Choice);
			profile.setAnswerQuestion2(ans2Choice);

			profile.setResponseTypes(choices);

			return profile;

		} catch (Exception e) {
			return new ProfileSurveyBean();
		}

	}

	private List<OccupationBean> _getOccupations(Function function) {
		List<OccupationBean> occupationBeans = new ArrayList<OccupationBean>();
		 OccupationBean pleaseSelct = new OccupationBean();
		 pleaseSelct.setId(-1);
		 pleaseSelct.setDescription(SurveyDataServiceHelper.pleaseSelect);
		 pleaseSelct.setName(SurveyDataServiceHelper.pleaseSelect);
		 occupationBeans.add(pleaseSelct);
		try {
			if (function != null) {
				// long id = function.getIdfunction();
				// if (id!=0){
				List<Occupation> entities = function.getOccupations();
				Collections.sort(entities, Occupation.occupationNameComparator);

				for (Iterator iterator = entities.iterator(); iterator
						.hasNext();) {
					Occupation occupation = (Occupation) iterator.next();
					OccupationBean bean = OccupationBean.get(occupation);
					occupationBeans.add(bean);
				}
			}
			return occupationBeans;
		} catch (Exception e) {
			return occupationBeans;
		}
	}

	public boolean saveProfile(ProfileSurveyBean profile) {
		try {

			UserTypeBean userTypeBean = profile.getUserType();
//			long genderId = profile.getGender().getGenderId();
//			GenderBean genderBean = _getGenderById(profile.getGender()
//					.getGenderId());
			LocationBean locationBean = profile.getLocation();
			FunctionBean functionBean = profile.getFunction();
			OccupationBean jobTitleBean = profile.getJobTitle();
			LevelBean levelBean = profile.getLevel();
			PathwayBean pathwayBean =profile.getLearningPathway();
						
			ChoiceBean answerBean1 = profile.getAnswerQuestion1();
			ChoiceBean answerBean2 = profile.getAnswerQuestion2();

			long userTypeId = userTypeBean.getUserId();
			UserTypeDAO userTypeDao = new UserTypeDAO();
			UserType userType = userTypeDao.getById(userTypeId);
			user.setUsertypefk(userType);

			long genderId = profile.getGender().getGenderId();
			GenderDao genderDao = new GenderDao();
			Gender gender = genderDao.getById(genderId);
			user.setGender(gender);

			long locationId = locationBean.getLocationId();
			LocationDAO dao = new LocationDAO();
			Location location = dao.getById(locationId);
			user.setLocation(location);

			long functionId = functionBean.getId();
			FunctionDAO fdao = new FunctionDAO();
			Function functionN = fdao.getById(functionId);
			user.setFunction(functionN);

			long occupationId = jobTitleBean.getId();
			OccupationDAO cdao = new OccupationDAO();
			Occupation entity = cdao.getById(occupationId);
			user.setOccupation(entity);

			long levelId = levelBean.getId();
			LevelDAO ldao = new LevelDAO();
			Level entityl = ldao.getById(levelId);

			user.setLevel(entityl);
			long pathwayid = pathwayBean.getId();
			PathwayDao pdao = new PathwayDao();
			Pathway entityP = pdao.getById(pathwayid);
			user.setPathway(entityP);

			user.setAnsofqtn1(String.valueOf(answerBean1.getId()));
			user.setAnsofqtn2(String.valueOf(answerBean2.getId()));

			IndividualDAO IndividualDao = new IndividualDAO();

			user = IndividualDao.save(user);
			IndividualDao.refresh(user);

			DapsscoDAO dapsscoDao = new DapsscoDAO();

			if (level != null && occupation != null && function != null) {
				Dapssco existDapssco = dapsscoDao.getByLevelOccuFunc(level,
						occupation, function);
				if (existDapssco == null) {
					Dapssco dapsscoEntity = new Dapssco();
					dapsscoEntity.setLevelId(level);
					dapsscoEntity.setOccupation(occupation);
					dapsscoEntity.setFunction(function);
					dapsscoDao.saveNew(dapsscoEntity);

				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	private Gender  _getGenderById(long genderId) {
		 
		try {
			GenderDao dao =new GenderDao();
			Gender gender = dao.getById(genderId);
			return gender;
	 

		} catch (Exception e) {
			return null;

		}
	}

	public ProfileListItemBean getEditUserByAdmin(String userId) {
		ProfileListItemBean bean = new ProfileListItemBean();
		try {

			IndividualDAO individualDao = new IndividualDAO();
			Individual individualUser = individualDao.getByLogin(userId);
			
			

			List<UserTypeBean> userTypes = _getUserTypes();
			List<FunctionBean> functions = _getFunctions();
	
			List<OccupationBean> occupations = _getOccupations(individualUser
					.getFunction());
			List<PathwayBean> pathways = _getPathways();
			List<GenderBean> genders = _getGenders();
			List<LocationBean> locations = _getlocations();
			
			
			
			List<LevelBean> levels = _getLevels(individualUser.getOccupation());

			FunctionBean functionBean = FunctionBean.get(individualUser
					.getFunction());
			OccupationBean occupationBean = OccupationBean.get(individualUser
					.getOccupation());
			LevelBean levelBean = LevelBean.get(individualUser.getLevel());
			LocationBean locationBean = LocationBean.get(individualUser
					.getLocation());
			UserTypeBean userTypeBean = UserTypeBean.get(individualUser
					.getUsertypefk());
			GenderBean genderBean = _getGender(individualUser.getGender());
			PathwayBean pathwayBean = _getPathway(individualUser.getPathway());

			bean.setLoginId(individualUser.getLogin());
			bean.setFunction(functionBean);
			bean.setJobTitles(occupations);
			bean.setJobTitle(occupationBean);
			bean.setLevel(levelBean);
			bean.setLocation(locationBean);
			bean.setGender(genderBean);
			bean.setUserType(userTypeBean);
			bean.setLearningPathway(pathwayBean);

	//		bean.setPassword(individualUser.getPassword());
			bean.setMylock(individualUser.isMylock());
			bean.setMylockedOn(individualUser.getMylockedOn());
			bean.setPwChangeOn(individualUser.getPwChangeon());

			if (individualUser.getFunction() == null)
				bean.setFunction(functions.get(0));

			if (individualUser.getOccupation() == null)
				bean.setJobTitle(occupations.get(0));
			if (individualUser.getLevel() == null)
				bean.setLevel(levels.get(0));

			if (individualUser.getLocation() == null)
				bean.setLocation(locations.get(0));

			if (individualUser.getPathway() == null)
				bean.setLearningPathway(pathways.get(0));

			if (individualUser.getGender() == null)
				bean.setGender(genders.get(0));

			if (individualUser.getUsertypefk() == null)
				bean.setUserType(userTypes.get(0));

			
			
			
			bean.setGenders(_getGenders());
			bean.setPathways(pathways);
			bean.setUserTypes(userTypes);
			bean.setFunctions(functions);
			bean.setLocations(locations);
			bean.setLevels(levels);

			return bean;
		} catch (Exception e) {
			return bean;
		}
	}

	public boolean adminChangePassword(ProfilePwdChangeBean profilePwd) {
		try {

			String newPassword1 = profilePwd.getPassword();
			String newPassword2 = profilePwd.getRePassword();
			if(newPassword1==null || newPassword2== null)
			return false;
			
			if (!newPassword1.equals(newPassword2)) {
				return false;
			}

			IndividualDAO dao = new IndividualDAO();
			String loginId = profilePwd.getLoginId();
			if(loginId==null)
				return false;
			Individual individualUser = dao.getByLogin(loginId);
			 

		
			individualUser.setPassword(	SurveyDataService.md5(newPassword1));
			individualUser.setPwChangeon(new Date());
			individualUser = dao.save(individualUser);
			dao.refresh(individualUser);
			return true;

		} catch (Exception e) {
			 
		}
		return false;
	}

	public boolean lockUser(String loginId) {
		try {
			IndividualDAO dao = new IndividualDAO();
			Individual individualUser = dao.getByLogin(loginId);
			individualUser.setMylock(true);
			individualUser.setMylockedOn(new Date());
			individualUser = dao.save(individualUser);
			dao.refresh(individualUser);
		} catch (Exception e) {
			 
		}
		return false;
	}

	public boolean unlockUser(String loginId) {
		try {
			IndividualDAO dao = new IndividualDAO();
			Individual individualUser = dao.getByLogin(loginId);
			individualUser.setMylock(false);
			individualUser.setMylockedOn(null);
			individualUser = dao.save(individualUser);
			dao.refresh(individualUser);
		} catch (Exception e) {
			 
		}
		return false;
	}

	public List<RoleBean> getRoles() {

		List<RoleBean> roleBeans = new ArrayList<RoleBean>();
		RoleBean bean = new RoleBean();
		bean.setId(-1);
		bean.setName("Please Select");
		bean.setDescription("Please Select");
		roleBeans.add(bean);
		try {
			RoleDAO roleDao = new RoleDAO();
			List<Role> roles = roleDao.getAll();

			for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
				Role role = (Role) iterator.next();
				RoleBean roleBean = RoleBean.get(role);
				roleBeans.add(roleBean);
			}
			return roleBeans;
		} catch (Exception e) {
			return roleBeans;
		}
	}

	public ProfileRoleBean admingetProfileRolesById(String loginId) {
		ProfileRoleBean prb= new ProfileRoleBean();
		prb.setLoginId(loginId);
		try {
			
			IndividualDAO individualDao = new IndividualDAO();
			Individual individualUser = individualDao.getByLogin(loginId);
			Role role =  null;
			if(individualUser!=null){
				role =individualUser.getRole();	
			}
			RoleBean roleBean = null;
			RoleBean pleaseSelect = new RoleBean();
			pleaseSelect.setId(-1);
			pleaseSelect.setDescription(SurveyDataServiceHelper.pleaseSelect);
			pleaseSelect.setName(SurveyDataServiceHelper.pleaseSelect);
			if(role ==null)
			{
				prb.setRole(pleaseSelect);
			}else {
			roleBean = RoleBean.get(role);
			prb.setRole(roleBean);
			}
			RoleDAO roleDao = new RoleDAO();
			List<Role> roles =roleDao.getAll();
			
			List<RoleBean> roleBeans = new ArrayList<RoleBean>();
			roleBeans.add(pleaseSelect);
			for (Iterator iterator = roles.iterator(); iterator.hasNext();) {
				Role role2 = (Role) iterator.next();
				RoleBean bean = RoleBean.get(role2);
				roleBeans.add(bean)
;				
			}
			
			
			
			
			prb.setRoles(roleBeans);
			
			return prb;
			
		} catch (Exception e) {
			return prb;
		}
	}

	public boolean adminEditRoleSave(ProfileRoleBean profile) {
		try {
			IndividualDAO dao = new IndividualDAO();
			String loginId = profile.getLoginId();
			Individual individualUser =dao.getByLogin(loginId);
			if(individualUser== null)
				return false;
			
			long individualId = individualUser.getIdindividual();
			RoleBean roleBean = profile.getRole();
			if(roleBean==null)
				return false;
			
			long roleId  = roleBean.getId();
			 
			RoleAssignmentDAO raDao = new RoleAssignmentDAO();
			
			RoleAssignment entity =raDao.getByIndividual(individualId);
			
			

			if(entity==null)
			{
				entity = new RoleAssignment();
				entity.setIndividualId(individualId);
				entity.setRoleId(roleId);
				entity =raDao.saveNew(entity);
				raDao.refresh(entity);
				dao.refresh(individualUser);
			}
			else
			{
				raDao.delete(entity);
				entity.setRoleId(roleId);
				entity =raDao.saveNew(entity);				
				raDao.refresh(entity);
				dao.refresh(individualUser);
			}
		 
			
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public ProfilePwdChangeBean adminGetBlankPasswordBean(String loginId) {
		ProfilePwdChangeBean bean = new ProfilePwdChangeBean();
		bean.setLoginId(loginId);
		bean.setPassword("");
		bean.setRePassword("");
		return bean;
		
	}
	public FunctionalReportDetailRepItemBean getFR_BY_GENDER_UOC_COURSE(){
		FunctionalReportDetailRepItemBean report = new FunctionalReportDetailRepItemBean();
		try {
			Function function =user.getFunction();
			if(function==null)
				return report;
			
			long functionId = function.getIdfunction();
			List<FunctionalReport> reportByNeed = new ArrayList<FunctionalReport>();
			report.setReportItems(reportByNeed);
			if(functionId==0)
				return report;
			 ReportDetailBean detail = new ReportDetailBean();
			 detail.setFunctionName(function.getName());
			 detail.setFunctionDesc(function.getDescription());
			 detail.setReportBy("Report by APS Course");
			 report.setReportDetail(detail);
					
					IndividualDAO dao  = new IndividualDAO();
					String spName = SurveyDataService.readConfigProperties("SP_FR_BY_GENDER_UOCCOURSE", request);
					List<FR_BY_GENDER_UOC_COURSE>  fr_by_gender_UocCourse =dao.callSPforFR_BY_GENDER_UOC_COURSE(functionId,spName);
				
					if (fr_by_gender_UocCourse==null)
						return report;
					
					
					NeedDAO needDao = new NeedDAO();
					List<Need> needs = needDao.getAll();
					
			for (Iterator needIterator = needs.iterator(); needIterator.hasNext();) {
				Need need = (Need) needIterator.next();
				 
				long needId = need.getIdneed();
				int needId1= (int) needId;
				FunctionalReport needReport = new FunctionalReport();
				
				reportByNeed.add(needReport);
				needReport.setNeedId(need.getIdneed());
				needReport.setNeedName(need.getName());
				List<CourseBean> courseLevelCollection = new ArrayList<CourseBean>();
				needReport.setCourses(courseLevelCollection);
				
				List<FR_BY_GENDER_UOC_COURSE> fr_course_by_Gender_of_a_need = _filter_fr_by_course_of_a_need(fr_by_gender_UocCourse, needId1);
				for (Iterator iterator = fr_course_by_Gender_of_a_need.iterator(); iterator
						.hasNext();) {
					
					FR_BY_GENDER_UOC_COURSE fr_course = (FR_BY_GENDER_UOC_COURSE) iterator.next();
					 
					CourseBean courseLevel = new CourseBean();
					courseLevelCollection.add(courseLevel);
					courseLevel.setUocCourseName(fr_course.getUocName());
					
				List<Count>  countCollection = new ArrayList<Count>();
				courseLevel.setItems(countCollection);
				
				Count bean = new Count();
				countCollection.add(bean);
				
				bean.setCmpMale(String.valueOf(fr_course.getCoCntMale()));
				bean.setCmpFemale(String.valueOf(fr_course.getCoCntFemale()));
				bean.setCmpOther(String.valueOf(fr_course.getCoCntOther()));
				bean.setCmpAll(String.valueOf(fr_course.getCoCntAll()));
				
				bean.setNotCmpMale(String.valueOf(fr_course.getInCoCntMale()));
				bean.setNotCmpFemale(String.valueOf(fr_course.getInCoCntFemale()));
				bean.setNotCmpOther(String.valueOf(fr_course.getInCoCntOther()));
				bean.setNotCmpAll(String.valueOf(fr_course.getInCoCntAll()));
	
				
				}	
			
			}
			return report;		
		} catch (Exception e) {
			return report;
		}
	} 
	
	private List<FR_BY_GENDER_UOC_COURSE> _filter_fr_by_course_of_a_need(
			List<FR_BY_GENDER_UOC_COURSE> fr_by_gender_UocCourse, int lneedId) {
		List<FR_BY_GENDER_UOC_COURSE> coll = new ArrayList<FR_BY_GENDER_UOC_COURSE>();
		try {
			for (Iterator iterator = fr_by_gender_UocCourse.iterator(); iterator
					.hasNext();) {
				FR_BY_GENDER_UOC_COURSE fr_BY_GENDER_UOC_COURSE = (FR_BY_GENDER_UOC_COURSE) iterator
						.next();
				int needId = fr_BY_GENDER_UOC_COURSE.getNeedId();
				if(lneedId== needId)
					coll.add(fr_BY_GENDER_UOC_COURSE);
					
			}
			return coll;
		} catch (Exception e) {
			return coll;
		}
	}

	public FunctionalReportDetailRepItemBean getFR_BY_GENDER_LOCATION(){

		FunctionalReportDetailRepItemBean report = new FunctionalReportDetailRepItemBean();
		try {
			Function function =user.getFunction();
			if(function==null)
				return report;
			
			long functionId = function.getIdfunction();
			List<FunctionalReport> reportByNeed = new ArrayList<FunctionalReport>();
			report.setReportItems(reportByNeed);
			if(functionId==0)
				return report;
			 ReportDetailBean detail = new ReportDetailBean();
			 detail.setFunctionName(function.getName());
			 detail.setFunctionDesc(function.getDescription());
			 detail.setReportBy("Report by APS Location");
			report.setReportDetail(detail);
					
					IndividualDAO dao  = new IndividualDAO();
					String spName = SurveyDataService.readConfigProperties("SP_FR_BY_GENDER_LOCATION", request);
					
					List<FR_BY_GENDER_LOCATION>  allfr_by_gender_location =dao.callSPforFR_BY_GENDER_LOCATION(functionId,spName);
					List<FR_BY_GENDER_LOCATION>  fr_by_gender_location = allfr_by_gender_location; 
					
					
					/////////////////
					HashMap<Integer, LocationBean> locations = new HashMap<Integer, LocationBean>();
					for (Iterator iterator = fr_by_gender_location.iterator(); iterator
							.hasNext();) {
						FR_BY_GENDER_LOCATION locationData = (FR_BY_GENDER_LOCATION) iterator
								.next();
						LocationBean location = new LocationBean();
						location.setLocationId(locationData.getLocationId());
						location.setLocationName(locationData.getLocationDesc());
						Integer  key = new Integer(locationData.getLocationId());
						if(locations.containsKey(key)){
							
						} else {	locations.put(key,	location);
						}
						
						
					}
					
					//////////////////////
					//_filterRemoveNullLocationId(allfr_by_gender_location);
					
					if (fr_by_gender_location==null)
						return report;
					
					
					NeedDAO needDao = new NeedDAO();
					List<Need> needs = needDao.getAll();
					
			for (Iterator needIterator = needs.iterator(); needIterator.hasNext();) {
				Need need = (Need) needIterator.next();
				 
				long needId = need.getIdneed();
				int needId1= (int) needId;
				FunctionalReport needReport = new FunctionalReport();
				reportByNeed.add(needReport);
				needReport.setNeedId(need.getIdneed());
				needReport.setNeedName(need.getName());
				List<CourseBean> courseLevelCollection = new ArrayList<CourseBean>();
				needReport.setCourses(courseLevelCollection);
				
				List<FR_BY_GENDER_LOCATION> fr_course_by_Gender_of_a_need = _filter_fr_by_location_of_a_need(fr_by_gender_location, needId1);
				List<ReportUocBean> fr_Gender_Location_by_uoc = _filter_fr_By_UOC(fr_course_by_Gender_of_a_need);
				
				for (Iterator iterator = fr_Gender_Location_by_uoc.iterator(); iterator
						.hasNext();) {
					ReportUocBean reportUocBean = (ReportUocBean) iterator
							.next();
					
					CourseBean courseLevel = new CourseBean();
					courseLevelCollection.add(courseLevel);
					courseLevel.setUocCourseName(reportUocBean.getUocName());
					
				List<Count> locationcountCollection = new ArrayList<Count>();
				courseLevel.setItems(locationcountCollection);
				int toCoCntMale=0;
				int toCoCntFemale=0;
				int toCoCntOther=0;
				int toCoCntAll=0;
				int toincoCntMale=0;
				int toincoCntFemale=0;
				int toincoCntOther=0;
				int toincoCntAll=0;
						
			//		List<FR_BY_GENDER_LOCATION> locations = new ArrayList<FR_BY_GENDER_LOCATION>();
					for (Iterator iterator2 = fr_course_by_Gender_of_a_need
							.iterator(); iterator2.hasNext();) {
						FR_BY_GENDER_LOCATION fr_byGenderLocationNeed = (FR_BY_GENDER_LOCATION) iterator2
								.next();
						
						if(reportUocBean.getUocId().equalsIgnoreCase(fr_byGenderLocationNeed.getUocId())){
							 
					 
						
						
						 
							String noLocation="No Location = 0";
							Count bean = new Count();
							
							locationcountCollection.add(bean);
					
							if(fr_byGenderLocationNeed.getCoCntFemale()!=0)
							bean.setCmpFemale(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getCoCntFemale());
							else
								bean.setCmpFemale(fr_byGenderLocationNeed.getLocationId()+"-0" );
//								bean.setCmpFemale(noLocation );
							
							
							if(fr_byGenderLocationNeed.getCoCntMale()!=0)
							bean.setCmpMale(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getCoCntMale());
							else
							bean.setCmpMale(fr_byGenderLocationNeed.getLocationId()+"-0");
//							bean.setCmpMale(noLocation);
							
							if(fr_byGenderLocationNeed.getCoCntOther()!=0)
							bean.setCmpOther(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getCoCntOther());
							else
							bean.setCmpOther(fr_byGenderLocationNeed.getLocationId()+"-0");
//							bean.setCmpOther(noLocation);
								
							if(fr_byGenderLocationNeed.getCoCntAll()!=0)
							bean.setCmpAll(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getCoCntAll());
							else 
							bean.setCmpAll(fr_byGenderLocationNeed.getLocationId()+"-0");
//							bean.setCmpAll(noLocation);
							
							if(fr_byGenderLocationNeed.getCoCntMale()!=0)
							toCoCntMale= toCoCntMale+fr_byGenderLocationNeed.getCoCntMale();
							
							if(fr_byGenderLocationNeed.getCoCntFemale()!=0)
							toCoCntFemale= toCoCntFemale+fr_byGenderLocationNeed.getCoCntFemale();
							
							if(fr_byGenderLocationNeed.getCoCntOther()!=0)
							toCoCntOther = toCoCntOther+fr_byGenderLocationNeed.getCoCntOther();
							
							if(fr_byGenderLocationNeed.getCoCntAll()!=0)
							toCoCntAll= toCoCntAll+fr_byGenderLocationNeed.getCoCntAll();
							
							if(fr_byGenderLocationNeed.getInCoCntFemale()!=0)
							bean.setNotCmpFemale(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getInCoCntFemale());
							else
								bean.setNotCmpFemale(fr_byGenderLocationNeed.getLocationId()+"-0");							
//										bean.setNotCmpFemale(noLocation);
							
							if(fr_byGenderLocationNeed.getInCoCntMale()!=0)
							bean.setNotCmpMale(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getInCoCntMale());
							else
								bean.setNotCmpMale(fr_byGenderLocationNeed.getLocationId()+"-0");
//								bean.setNotCmpMale(noLocation);
							
							if(fr_byGenderLocationNeed.getInCoCntOther()!=0)
							bean.setNotCmpOther(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getInCoCntOther());
							else
								bean.setNotCmpOther(fr_byGenderLocationNeed.getLocationId()+"-0");
//								bean.setNotCmpOther(noLocation);
							
							if(fr_byGenderLocationNeed.getInCoCntAll()!=0)
							bean.setNotCmpAll(fr_byGenderLocationNeed.getLocationId()+ "-"+ fr_byGenderLocationNeed.getInCoCntAll());
							else
							bean.setNotCmpAll(fr_byGenderLocationNeed.getLocationId()+"-0");
//							bean.setNotCmpAll(noLocation);	
							if(fr_byGenderLocationNeed.getInCoCntMale()!=0)
							toincoCntMale= toincoCntMale+fr_byGenderLocationNeed.getInCoCntMale();
						 
							
							if(fr_byGenderLocationNeed.getInCoCntFemale()!=0)
							toincoCntFemale= toincoCntFemale+fr_byGenderLocationNeed.getInCoCntFemale();
							
							if(fr_byGenderLocationNeed.getInCoCntOther()!=0)
							toincoCntOther= toincoCntOther+fr_byGenderLocationNeed.getInCoCntOther();
								
							
							
							if(fr_byGenderLocationNeed.getInCoCntAll()!=0)
							toincoCntAll= toincoCntAll+fr_byGenderLocationNeed.getInCoCntAll();
							
							
						} //if uocCouseId.equ
						
					} // end iterator2
					
					//now iterate all locations
					
					
					
				}
				
			} //  end for needIterator
					
			Collection<LocationBean > locationCollection =  locations.values();
			List<LocationBean > locationBeans = new ArrayList<LocationBean>(locationCollection);
			
					report.setLocations(locationBeans);
					
			return report;
			
			
		} catch (Exception e) {
			return report;
		}
	}
	
	private List<ReportUocBean> _filter_fr_By_UOC(
			
			List<FR_BY_GENDER_LOCATION> fr_course_by_Gender_of_a_need) {
		//List<ReportUocBean> uocs = new ArrayList<ReportUocBean>();

List<ReportUocBean > result = new ArrayList<ReportUocBean>( );
		HashMap<String, ReportUocBean> uocs = new HashMap<String, ReportUocBean>();
		try {
			for (Iterator iterator = fr_course_by_Gender_of_a_need.iterator(); iterator
					.hasNext();) {
				FR_BY_GENDER_LOCATION fr_BY_GENDER_LOCATION = (FR_BY_GENDER_LOCATION) iterator
						.next();
				String uocId = fr_BY_GENDER_LOCATION.getUocId();
				
				ReportUocBean bean = new ReportUocBean();
				String key = fr_BY_GENDER_LOCATION.getUocId();
				bean.setUocId(fr_BY_GENDER_LOCATION.getUocId());
				bean.setUocName(fr_BY_GENDER_LOCATION.getUocName());
				if(!uocs.containsKey(key))
				uocs.put(key, bean);
				//boolean status= uocs.contains(bean);
//			
	
			}
			result.addAll(uocs.values());
 			return result;
		} catch (Exception e) {
			return result;
		}
	}

	private List<FR_BY_GENDER_LOCATION> _filter_fr_by_location_of_a_need(
			List<FR_BY_GENDER_LOCATION> fr_by_gender_location, int needId) {
		List<FR_BY_GENDER_LOCATION> coll = new ArrayList<FR_BY_GENDER_LOCATION>();
		try {
			for (Iterator iterator = fr_by_gender_location.iterator(); iterator
					.hasNext();) {
				FR_BY_GENDER_LOCATION fr_BY_GENDER_LOCATION2 = (FR_BY_GENDER_LOCATION) iterator
						.next();
				if(fr_BY_GENDER_LOCATION2.getNeedId()== needId){
					coll.add(fr_BY_GENDER_LOCATION2);
				}
			}
			return coll;
		} catch (Exception e) {
			return coll;
		}
	}

	private List<FR_BY_GENDER_LOCATION> _filterRemoveNullLocationId(
			List<FR_BY_GENDER_LOCATION> allfr_by_gender_location) {
		List<FR_BY_GENDER_LOCATION> coll = new ArrayList<FR_BY_GENDER_LOCATION>();
		
			try {
			for (Iterator iterator = allfr_by_gender_location.iterator(); iterator
					.hasNext();) {
				FR_BY_GENDER_LOCATION fr_BY_GENDER_LOCATION = (FR_BY_GENDER_LOCATION) iterator
						.next();
				
				if(fr_BY_GENDER_LOCATION.getLocationId()!=0)
					coll.add(fr_BY_GENDER_LOCATION);
				
			}
			return coll;
		} catch (Exception e) {
			return null;
		}
	}

	public FunctionalReportDetailRepItemBean getFR_BY_GENDER_LEVEL(){
		
FunctionalReportDetailRepItemBean report = new FunctionalReportDetailRepItemBean();
		
try {

		Function function =user.getFunction();
		if(function==null)
			return report;
		
		long functionId = function.getIdfunction();
		List<FunctionalReport> reportByNeed = new ArrayList<FunctionalReport>();
		report.setReportItems(reportByNeed);
		if(functionId==0)
			return report;
			
		
		
		
		
 ReportDetailBean detail = new ReportDetailBean();
 detail.setFunctionName(function.getName());
 detail.setFunctionDesc(function.getDescription());
 detail.setReportBy("Report by APS Level");
report.setReportDetail(detail);
		
		IndividualDAO dao  = new IndividualDAO();
		String spName = SurveyDataService.readConfigProperties("SP_FR_BY_GENDER_LEVEL", request);
		
 		List<FR_COURSES_BY_GENDER_LEVEL>  fr_course_by_gender =dao.callSPforFR_BY_GENDER_LEVEL(functionId, spName);
		if (fr_course_by_gender==null)
			return report;
		
		
	  	
		NeedDAO needDao = new NeedDAO();
		List<Need> needs = needDao.getAll();
		
for (Iterator needIterator = needs.iterator(); needIterator.hasNext();) {
	Need need = (Need) needIterator.next();
	 
	long needId = need.getIdneed();
	FunctionalReport needReport = new FunctionalReport();
	reportByNeed.add(needReport);
	needReport.setNeedId(need.getIdneed());
	needReport.setNeedName(need.getName());
	List<CourseBean> courseLevelCollection = new ArrayList<CourseBean>();
	needReport.setCourses(courseLevelCollection);
	
	List<FR_COURSES_BY_GENDER_LEVEL> fr_course_by_Gender_of_a_need = _filter_fr_courses_by_gender_of_a_need(fr_course_by_gender, needId);
	List<String> uocCourseNameCollection = _filter_fr_Course_By_UocCourseId(fr_course_by_Gender_of_a_need);
	for (Iterator uocIterator = uocCourseNameCollection.iterator(); uocIterator
			.hasNext();) {
		String uocCourseName = (String) uocIterator.next();
		CourseBean courseLevel = new CourseBean();
		courseLevelCollection.add(courseLevel);
		courseLevel.setUocCourseName(uocCourseName);
	List<Count> levelcountCollection = new ArrayList<Count>();
	courseLevel.setItems(levelcountCollection);
	int toCoCntMale=0;
	int toCoCntFemale=0;
	int toCoCntOther=0;
	int toCoCntAll=0;
	int toincoCntMale=0;
	int toincoCntFemale=0;
	int toincoCntOther=0;
	int toincoCntAll=0;
			
	
		for (Iterator iterator = fr_course_by_Gender_of_a_need.iterator(); iterator
				.hasNext();) {
			FR_COURSES_BY_GENDER_LEVEL frCourse = (FR_COURSES_BY_GENDER_LEVEL) iterator.next();
			String itUocCourseName = frCourse.getUocName() ;
			if(uocCourseName.equalsIgnoreCase(itUocCourseName)){
				
				Count bean = new Count();
				levelcountCollection.add(bean);
				
				bean.setCmpFemale(frCourse.getLevelDesc()+ "-"+ frCourse.getCoCntFemale());
				bean.setCmpMale(frCourse.getLevelDesc()+ "-"+ frCourse.getCoCntMale());
				bean.setCmpOther(frCourse.getLevelDesc()+ "-"+ frCourse.getCoCntOther());
				bean.setCmpAll(frCourse.getLevelDesc()+ "-"+ frCourse.getCoCntAll());
				toCoCntMale= toCoCntMale+frCourse.getCoCntMale();
				toCoCntFemale= toCoCntFemale+frCourse.getCoCntFemale();
				toCoCntOther = toCoCntOther+frCourse.getCoCntOther();
				toCoCntAll= toCoCntAll+frCourse.getCoCntAll();
				
				
				bean.setNotCmpFemale(frCourse.getLevelDesc()+ "-"+ frCourse.getInCoCntFemale());
				bean.setNotCmpMale(frCourse.getLevelDesc()+ "-"+ frCourse.getInCoCntMale());
				bean.setNotCmpOther(frCourse.getLevelDesc()+"-"+frCourse.getInCoCntOther());
				bean.setNotCmpAll(frCourse.getLevelDesc()+ "-"+ frCourse.getInCoCntAll());
				toincoCntMale= toincoCntMale+frCourse.getInCoCntMale();
				toincoCntFemale= toincoCntFemale+frCourse.getInCoCntFemale();
				toincoCntOther = toincoCntOther+frCourse.getInCoCntOther();
				toincoCntAll= toincoCntAll+frCourse.getInCoCntAll();
				
				
				
			} //if uocCouseId.equ
			
		} // fr_course iterator

//		String totalString = "Total = ";
//		LevelCount totalBean = new LevelCount();
//		totalBean.setCmpMale(totalString+ toCoCntMale);
//		totalBean.setCmpFemale(totalString+ toCoCntFemale);
//		totalBean.setCmpAll(totalString+toCoCntAll);
//		totalBean.setNotCmpMale(totalString+ toincoCntMale);
//		totalBean.setNotCmpFemale(totalString+ toincoCntFemale);
//		totalBean.setNotCmpAll(totalString+ toincoCntAll);
		//levelcountCollection.add(totalBean);
		
		
	} //uocIterator
	
	
}  //needIterator
return report;
	} catch (Exception e) {
	return report;
	}
	}
	
	private List<String> _filter_fr_Course_By_UocCourseId(
			List<FR_COURSES_BY_GENDER_LEVEL> fr_course_by_Gender_of_a_need) {
		
		List<String> collection = new ArrayList<String>();
		try {
			for (Iterator iterator = fr_course_by_Gender_of_a_need.iterator(); iterator
					.hasNext();) {
				FR_COURSES_BY_GENDER_LEVEL fr_COURSES_BY_GENDER_LEVEL = (FR_COURSES_BY_GENDER_LEVEL) iterator
						.next();
				
//				String uocCouseId = fr_COURSES_BY_GENDER_LEVEL.getUocId();
				String uocName = fr_COURSES_BY_GENDER_LEVEL.getUocName();
				boolean isContains = collection.contains(uocName);
				if(!isContains)
					collection.add(uocName);
				
				
				
			}
			return collection;
		} catch (Exception e) { 
			return collection;
		}
	}

	private List<FR_COURSES_BY_GENDER_LEVEL> _filter_fr_courses_by_gender_of_a_need(
			List<FR_COURSES_BY_GENDER_LEVEL> fr_course_by_gender, long needId) {
		List<FR_COURSES_BY_GENDER_LEVEL>  result = new ArrayList<FR_COURSES_BY_GENDER_LEVEL>();
		try {
			for (Iterator iterator = fr_course_by_gender.iterator(); iterator
					.hasNext();) {
				FR_COURSES_BY_GENDER_LEVEL fr_COURSES_BY_GENDER_LEVEL = (FR_COURSES_BY_GENDER_LEVEL) iterator
						.next();
				if(fr_COURSES_BY_GENDER_LEVEL.getNeedId()== needId){
					result.add(fr_COURSES_BY_GENDER_LEVEL);
				}
				
			}
			return result;
		} catch (Exception e) {
			return result;
		}
	}

	public ReportHigherEducationPathwayBean getReportEducationPathway() {
		ReportHigherEducationPathwayBean report = new ReportHigherEducationPathwayBean();
		try {
			
			Function function =user.getFunction();
			if(function==null)
				return report;
			
			long functionId = function.getIdfunction();
		 	if(functionId==0)
				return report;
		 	
		 	report.setFunctionName(function.getDescription());
		 	
		IndividualDAO dao = new IndividualDAO();
		
		String spName = SurveyDataService.readConfigProperties("SP_HIGHEREDUCATION", request);
		/// for higher education
		QuestionBean qtn1Bean = SurveyDataService.readQuestionProperties("1", request);
		QuestionBean qtn2Bean = SurveyDataService.readQuestionProperties("2", request);
		String qtn1 ="";
		String qtn2 = "";
		if(qtn1Bean!=null)
		qtn1= qtn1Bean.getQuestion();
		
		if(qtn2Bean!=null)
			qtn2= qtn2Bean.getQuestion();
		
		List<FR_BY_HIGHER_EDUCATION> result = dao.callSPfor_Higher_Education(functionId ,spName,qtn1, qtn2);
		if(result!=null){
		List<ReportHigherEducationItemBean> higherBeans = new ArrayList<ReportHigherEducationItemBean>();
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			FR_BY_HIGHER_EDUCATION data = (FR_BY_HIGHER_EDUCATION) iterator
					.next();
			
			ReportHigherEducationItemBean bean = new ReportHigherEducationItemBean();
			higherBeans.add(bean);
			bean.setQuestion(data.getQuestion());
			bean.setCntMale(data.getYesCntMale());
			bean.setCntFemale(data.getYesCntFemale());
			bean.setCntOther(data.getYesCntOther());
			bean.setCntAll(data.getYesCntAll());
		}
		report.setHigherEducationItems(higherBeans);
			
		}
		//// for Pathway report
		String spName1 = SurveyDataService.readConfigProperties("SP_PATHWAY", request);
		
		List<FR_BY_PATHWAY> pathwayResult = dao.callSPfor_PathwayData(functionId, spName1);
		List<ReportLearningPathwayItemBean> pathwayBeans = new ArrayList<ReportLearningPathwayItemBean>();
		report.setPathwayItems(pathwayBeans);
		if(pathwayResult!=null){
		for (Iterator iterator = pathwayResult.iterator(); iterator.hasNext();) {
			FR_BY_PATHWAY data = (FR_BY_PATHWAY) iterator.next();
			
			ReportLearningPathwayItemBean bean = new ReportLearningPathwayItemBean();
			pathwayBeans.add(bean);
			bean.setPathway(data.getPathway());
			bean.setCntMale(data.getCntMale());
			bean.setCntFemale(data.getCntFemale());
			bean.setCntOther(data.getCntOther());
			bean.setCntAll(data.getCntAll());
			
		}
		report.setPathwayItems(pathwayBeans);
		
		}
			
		
		return report;
		} catch (Exception e) {
			// TODO: handle exception
		}
	return null;
	}

	public FunctionalReportDetailRepItemBean getFR_BY_GENDER_FUNCTION() {
		FunctionalReportDetailRepItemBean report = new FunctionalReportDetailRepItemBean();
		try {
			Function function =user.getFunction();
			if(function==null)
				return report;
			
			long functionId = function.getIdfunction();
			List<FunctionalReport> reportByNeed = new ArrayList<FunctionalReport>();
			report.setReportItems(reportByNeed);
			if(functionId==0)
				return report;
			 ReportDetailBean detail = new ReportDetailBean();
			 detail.setFunctionName(function.getName());
			 detail.setFunctionDesc(function.getDescription());
			 detail.setReportBy("Report by APS Location");
			report.setReportDetail(detail);
					
					IndividualDAO dao  = new IndividualDAO();
					String spName = SurveyDataService.readConfigProperties("SP_FR_BY_GENDER_FUNCTION", request);
					
					List<FR_BY_GENDER_FUNCTION>  allfr_by_gender_location =dao.callSPforFR_BY_GENDER_FUNCTION(spName );
					List<FR_BY_GENDER_FUNCTION>  fr_by_gender_function = allfr_by_gender_location; 
					//_filterRemoveNullLocationId(allfr_by_gender_location);
					
					if (fr_by_gender_function==null)
						return report;
					
					
					NeedDAO needDao = new NeedDAO();
					List<Need> needs = needDao.getAll();
					
			for (Iterator needIterator = needs.iterator(); needIterator.hasNext();) {
				Need need = (Need) needIterator.next();
				 
				long needId = need.getIdneed();
				int needId1= (int) needId;
				FunctionalReport needReport = new FunctionalReport();
				reportByNeed.add(needReport);
				needReport.setNeedId(need.getIdneed());
				needReport.setNeedName(need.getName());
				List<CourseBean> courseLevelCollection = new ArrayList<CourseBean>();
				needReport.setCourses(courseLevelCollection);
				
				List<FR_BY_GENDER_FUNCTION> fr_course_by_Gender_of_a_need = _filter_fr_by_function_of_a_need(fr_by_gender_function, needId1);
				List<ReportUocBean> fr_Gender_Location_by_uoc = _filter_fr_By_UOC_by_function(fr_course_by_Gender_of_a_need);
				
				for (Iterator iterator = fr_Gender_Location_by_uoc.iterator(); iterator
						.hasNext();) {
					ReportUocBean reportUocBean = (ReportUocBean) iterator
							.next();
					
					CourseBean courseLevel = new CourseBean();
					courseLevelCollection.add(courseLevel);
					courseLevel.setUocCourseName(reportUocBean.getUocName());
					
				List<Count> countCollection = new ArrayList<Count>();
				courseLevel.setItems(countCollection);
				int toCoCntMale=0;
				int toCoCntFemale=0;
				int toCoCntOther=0;
				int toCoCntAll=0;
				int toincoCntMale=0;
				int toincoCntFemale=0;
				int toincoCntOther=0;
				int toincoCntAll=0;
						
			//		List<FR_BY_GENDER_LOCATION> locations = new ArrayList<FR_BY_GENDER_LOCATION>();
					for (Iterator iterator2 = fr_course_by_Gender_of_a_need
							.iterator(); iterator2.hasNext();) {
						FR_BY_GENDER_FUNCTION fr_byGenderLocationNeed = (FR_BY_GENDER_FUNCTION) iterator2
								.next();
						
						if(reportUocBean.getUocId().equalsIgnoreCase(fr_byGenderLocationNeed.getUocId())){
							 
					 
						
						
						 
							 
							Count bean = new Count();
							
							countCollection.add(bean);
					
							 bean.setCmpFemale(fr_byGenderLocationNeed.getFunctionDesc() +"-"+String.valueOf( fr_byGenderLocationNeed.getCoCntFemale()));
							 bean.setCmpMale(fr_byGenderLocationNeed.getFunctionDesc() +"-"+ String.valueOf( fr_byGenderLocationNeed.getCoCntMale()));
							 bean.setCmpOther(fr_byGenderLocationNeed.getFunctionDesc()+"-" + String.valueOf(fr_byGenderLocationNeed.getCoCntOther()));
							 bean.setCmpAll(fr_byGenderLocationNeed.getFunctionDesc() +"-"+String.valueOf( fr_byGenderLocationNeed.getCoCntAll()));
							 bean.setNotCmpFemale(fr_byGenderLocationNeed.getFunctionDesc() +"-"+String.valueOf( fr_byGenderLocationNeed.getInCoCntFemale()));
							 bean.setNotCmpMale(fr_byGenderLocationNeed.getFunctionDesc() +"-"+String.valueOf(fr_byGenderLocationNeed.getInCoCntMale()));
							 bean.setNotCmpOther(fr_byGenderLocationNeed.getFunctionDesc() +"-"+String.valueOf(fr_byGenderLocationNeed.getInCoCntOther()));
								
							 bean.setNotCmpAll(fr_byGenderLocationNeed.getFunctionDesc() +"-"+String.valueOf( fr_byGenderLocationNeed.getInCoCntAll()));
							 	
						 
							
						} //if uocCouseId.equ
						
					} // end iterator2
					
					//now iterate all locations
					
					
					
				}
				
			} //  end for needIterator
					
					
					
			return report;
			
			
		} catch (Exception e) {
			return report;
		}
	}

	private List<ReportUocBean> _filter_fr_By_UOC_by_function(
			List<FR_BY_GENDER_FUNCTION> fr_course_by_Gender_of_a_need) {

		List<ReportUocBean> uocs = new ArrayList<ReportUocBean>();
		try {
			for (Iterator iterator = fr_course_by_Gender_of_a_need.iterator(); iterator
					.hasNext();) {
				FR_BY_GENDER_FUNCTION fr_BY_GENDER_LOCATION = (FR_BY_GENDER_FUNCTION) iterator
						.next();
				String uocId = fr_BY_GENDER_LOCATION.getUocId();
				
				ReportUocBean bean = new ReportUocBean();
				bean.setUocId(fr_BY_GENDER_LOCATION.getUocId());
				bean.setUocName(fr_BY_GENDER_LOCATION.getUocName());
				boolean status= isContainUocCourse(uocs, bean);
				if(!status)
					uocs.add(bean);
				
			}
			return uocs;
		} catch (Exception e) {
			return uocs;
		}
	}


	private boolean isContainUocCourse(List<ReportUocBean> uocs,
			ReportUocBean bean) {
		try {
			for (Iterator iterator = uocs.iterator(); iterator.hasNext();) {
				ReportUocBean reportUocBean = (ReportUocBean) iterator.next();
				
				if(reportUocBean.getUocId().equalsIgnoreCase(bean.getUocId()))
					return true;
			}
			
			return false;
		} catch (Exception e) {

			return false;
		}
		 
	}

	private List<FR_BY_GENDER_FUNCTION> _filter_fr_by_function_of_a_need(
			List<FR_BY_GENDER_FUNCTION> fr_by_gender_function, int needId1) {
		 
			List<FR_BY_GENDER_FUNCTION> coll = new ArrayList<FR_BY_GENDER_FUNCTION>();
			try {
				for (Iterator iterator = fr_by_gender_function.iterator(); iterator
						.hasNext();) {
					FR_BY_GENDER_FUNCTION fr_BY_GENDER_function2 = (FR_BY_GENDER_FUNCTION) iterator
							.next();
					int needId = fr_BY_GENDER_function2.getNeedId();
					if(needId1== needId){
						coll.add(fr_BY_GENDER_function2);
					}
				}
				return coll;
			} catch (Exception e) {
				return coll;
			}
	}

	public boolean sendMail(String emailId,String subject, String inputMessage) throws SurveyDataHelperException {
		
		try {
			final String username = SurveyDataService.readMailConfigProperties("username", request);
			final String password = SurveyDataService.readMailConfigProperties("password", request);
			String fromEmailId =SurveyDataService.readMailConfigProperties("fromemailid", request);
			if(username==null || password==null)
				throw new SurveyDataHelperException("smtp userid/password not setup properly");
			
		 	
			String smtpAuth  = SurveyDataService.readMailConfigProperties("smtpAuthentication", request);
		 
			if(smtpAuth ==null)
			smtpAuth= "true";
			
			 	String smtpttlsenable  = SurveyDataService.readMailConfigProperties("smtpttlsenable", request);
			
			
			if(smtpttlsenable ==null)
			smtpttlsenable = "true";
			
			String smtphost = SurveyDataService.readMailConfigProperties("smtphost", request);
			String smtpport = SurveyDataService.readMailConfigProperties("smtpport", request);
			String sslport = SurveyDataService.readMailConfigProperties("sslport", request);
			if(smtphost==null || smtpport==null){
				throw new SurveyDataHelperException("Host name or port not configured correctly");
			}
			
			
			
			 
			
			String sslStr  = SurveyDataService.readMailConfigProperties("ssl", request);
			boolean ssl =false;
			if(sslStr!=null)
				ssl  =Boolean.getBoolean(sslStr);
			
			Properties props = new Properties();
			
			props.put("mail.smtp.auth", smtpAuth);			
			props.put("mail.smtp.host", smtphost);
			
			if(ssl){
				props.put("mail.smtp.socketFactory.port", sslport);
				props.put("mail.smtp.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.port", sslport);
			}
			else 
			{
				props.put("mail.smtp.port", smtpport);
				props.put("mail.smtp.starttls.enable", smtpttlsenable);
			}
			
			
			Session session = Session.getInstance(props,
					  new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(username, password);
						}
					  });
			
			
			 
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(fromEmailId));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
				message.setSubject(subject);
				message.setText(inputMessage);
	 
				Transport.send(message);
	 
				return true;
			 
			 

			
			
		} catch (Exception e) {
			throw new SurveyDataHelperException(e.getMessage());
			 
		}
		// TODO Auto-generated method stub
		
	}

	public SQLViewCollBean  getSURVEY_RESPONSE(String keyValue) {
		
		SQLViewConfigReader reader = new SQLViewConfigReader(request);
		String query = reader.getQuery(keyValue);
		List<SQLVIEWBean>  result = new ArrayList<SQLVIEWBean>();
		SQLViewCollBean coll = new SQLViewCollBean(result);
	try {
		IndividualDAO dao = new IndividualDAO();
	List<SurveyResponseBySQLView> entities =	dao.callVIEW_SurveyResponse(query);
	for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
		SurveyResponseBySQLView surveyResponseBySQLView = (SurveyResponseBySQLView) iterator
				.next();
		
		SQLVIEWBean  bean = new SQLVIEWBean(surveyResponseBySQLView.getCol1(),
				surveyResponseBySQLView.getCol2(),
				surveyResponseBySQLView.getCol3(),
				surveyResponseBySQLView.getCol4(),
				surveyResponseBySQLView.getCol5(),
				surveyResponseBySQLView.getCol6(),
				surveyResponseBySQLView.getCol7(),
				surveyResponseBySQLView.getCol8(),
				surveyResponseBySQLView.getCol9(),
				surveyResponseBySQLView.getCol10(),
				surveyResponseBySQLView.getCol11(),
				surveyResponseBySQLView.getCol12(),
				surveyResponseBySQLView.getCol13(),
				surveyResponseBySQLView.getCol14(),
				surveyResponseBySQLView.getCol15(),
				surveyResponseBySQLView.getCol16(),
				surveyResponseBySQLView.getCol17(),
				surveyResponseBySQLView.getCol18(),
				surveyResponseBySQLView.getCol19(),
				surveyResponseBySQLView.getCol20()			
							
				);
	
		result.add(bean);
	}
	
	
	
	
	
	return coll;
	
		 
	} catch (Exception e) {
		return coll;
	}
		
	}

	public List<SqlViewDropDownBean> getCSVDropDownList() {
		
	try {
		SQLViewConfigReader reader = new SQLViewConfigReader(request);
		return reader.getLabels();
	} catch (Exception e) {
		return (List) new ArrayList<SQLViewConfigReader>();
	}
		
	}

}
