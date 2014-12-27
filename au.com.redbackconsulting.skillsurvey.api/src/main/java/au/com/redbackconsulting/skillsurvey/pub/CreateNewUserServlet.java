package au.com.redbackconsulting.skillsurvey.pub;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.redbackconsulting.skillsurvey.api.SurveyDataService;
import au.com.redbackconsulting.skillsurvey.api.SurveyDataServiceHelper;
import au.com.redbackconsulting.skillsurvey.api.exception.InputParameterNotSufficentException;
import au.com.redbackconsulting.skillsurvey.persistence.IndividualDAO;
import au.com.redbackconsulting.skillsurvey.persistence.RoleAssignmentDAO;
import au.com.redbackconsulting.skillsurvey.persistence.model.Individual;
import au.com.redbackconsulting.skillsurvey.persistence.model.RoleAssignment;
import au.com.redbackconsulting.skillsurvey.persistence.model.RoleAssignmentPK;

/**
 * Servlet implementation class CreateNewUserServlet
 */
@WebServlet("/CreateNewUserServlet")
public class CreateNewUserServlet extends HttpServlet {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		try {
			String userName = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String authCode = request.getParameter("securitycode");
			
			logger.debug(userName);
			String serverAuthCode = ReadToken.readTokenProperties(ReadToken.usertokenName, request);
			
			checkParameter(userName, password1, password2, authCode,serverAuthCode);
			
	
		//	if(!serverAuthCode.equals(authCode))
			//throw new InputParameterNotSufficentException("Security code does not match");
			
				IndividualDAO dao = new IndividualDAO();
			Individual individual =dao.getByLogin(userName);
			String hashpassword =null;
			if(individual==null){
				logger.info("No user exist for the user name : " + userName);
				Individual entity= new Individual();
				entity.setLogin(userName);
				  hashpassword =SurveyDataService.md5(password1+SurveyDataServiceHelper.salt);
				entity.setPassword(hashpassword);
				entity =dao.saveNew(entity);
				dao.refresh(entity);
				RoleAssignmentDAO raDao = new RoleAssignmentDAO();
				RoleAssignment raEntity = new RoleAssignment();
				raEntity.setIndividualId(entity.getIdindividual());
				raEntity.setRoleId(1);
				raDao.saveNew(raEntity);
				logger.info("New user Created  : " + userName);
				String url = request.getContextPath() + "/j_security_check";
				response.sendRedirect(url + "?j_username="
				       + URLEncoder.encode(userName, "UTF-8")
				       + "&j_password="
				       + URLEncoder.encode(hashpassword, "UTF-8"));

			return ;	
			} else{
				
				throw new InputParameterNotSufficentException("User already Exists");
			}
			
			
		} catch (InputParameterNotSufficentException e1){
//			request.setAttribute("message", e1.getMessage());
//			RequestDispatcher forward =request.getRequestDispatcher("/register.jsp");
//			forward.forward(request, response);
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();

			    out.println("<html>");
			    out.println("<head>");
			//    out.println("<title>Hola</title>");
			    out.println("</head>");
			    out.println("<body bgcolor=\"white\">");
			    out.println(e1.getMessage());	
			    out.println("</BR>");	
			    out.println( " <h3><a href=\"../register.jsp\"> Click to go back </a></h3>");

			    out.println("</body>");
			    out.println("</html>");
	// 	response.getWriter().println(e1.getMessage());	
	 // response.getWriter().println( " <h2><a href=\"register.jsp\">New User Registration Click Here</a></h2>");

		} catch (Exception e) {
			logger.debug("Error"+e.getMessage());
			
		}
		
			
		
		
		
	}
	
	
	private void checkParameter(String userName, String password1, String password2, String authCode, String serverAuthCode) throws InputParameterNotSufficentException{
		if(userName==null)
		{
			 throw new InputParameterNotSufficentException("User Name is null");
			 
		}
		
		
		if(password1==null || password2==null)
		{
			 throw new InputParameterNotSufficentException("Password can not be blank");
		}	
		
		if(!password1.equals(password2))
		{
			 throw new InputParameterNotSufficentException("Password does not match");
			 
		}	
		
		if(authCode==null) 
		{ 
			 throw new InputParameterNotSufficentException("Security Code can not be blank");
		}	 
		if(userName.startsWith(SurveyDataServiceHelper.adminType)){
			 throw new InputParameterNotSufficentException("User name can not prefix :"+ SurveyDataServiceHelper.adminType  );
				
			 
		}
		
		if(userName.startsWith(SurveyDataServiceHelper.reportingType)){
			 throw new InputParameterNotSufficentException("User name can not prefix :"+ SurveyDataServiceHelper.reportingType  );
				
		}
		if(serverAuthCode.equalsIgnoreCase(authCode)){
			//nothing to do
		} else {
			 throw new InputParameterNotSufficentException("Security code does not match"  );

		}
		
		 
	}

}
