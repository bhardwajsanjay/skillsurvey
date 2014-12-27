package au.com.redbackconsulting.skillsurvey.persistence;

 import au.com.redbackconsulting.skillsurvey.persistence.manager.PersistenceManager;
import au.com.redbackconsulting.skillsurvey.persistence.model.Gender;

public class GenderDao extends BasicDAO<Gender>{

	public GenderDao() {
		  super(PersistenceManager.getInstance().getEntityManagerProvider());

	}

	@Override
	protected String getidFieldName() {
		// TODO Auto-generated method stub
		return "id";
	}

}
