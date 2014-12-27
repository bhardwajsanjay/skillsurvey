

package au.com.redbackconsulting.skillsurvey.persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.redbackconsulting.skillsurvey.persistence.manager.EntityManagerProvider;
import au.com.redbackconsulting.skillsurvey.persistence.manager.PersistenceManager;
import au.com.redbackconsulting.skillsurvey.persistence.model.DBQueries;
import au.com.redbackconsulting.skillsurvey.persistence.model.IDBEntity;
import au.com.redbackconsulting.skillsurvey.persistence.model.Individual;
import au.com.redbackconsulting.skillsurvey.persistence.model.UocQuestion;

public class UocQuestionDAO extends BasicDAO<UocQuestion>  {



    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected EntityManagerProvider emProvider;

    public UocQuestionDAO() {
super(PersistenceManager.getInstance().getEntityManagerProvider());
    }

	@Override
	protected String getidFieldName() {
		// TODO Auto-generated method stub
		return "iduocquestion";
	}

 
	public UocQuestion getById(String id) {
		long lId= Long.parseLong(id);
		return super.getById(lId);
	}
	
	
	
	private UocQuestion getUocQuestion(String uocId, short isprimary ){
		EntityManager em = super.emProvider.get();
		UocQuestion entity = null;
		
		try {
			   Query query = em.createNamedQuery(DBQueries.GET_FIND_BY_UOC_AND_TYPE, UocQuestion.class); //$NON-NLS-1$ //$NON-NLS-2$
	            
	            query.setParameter("uoc", uocId); //$NON-NLS-1$
	            query.setParameter("questionLevel", isprimary); //$NON-NLS-1$
	            entity = (UocQuestion) query.getSingleResult();
		return entity;
		} catch (Exception e) {
		return null;
			// TODO: handle exception
		}
		
	}

	public UocQuestion getPrimaryQuestion(String uocId){
		
		try {
			return getUocQuestion(uocId, Short.parseShort("1"));
		} catch (Exception e) {
return null;
		}
	}
	
	
public UocQuestion getSecondaryQuestion(String uocId){
		

	try {
		return getUocQuestion(uocId, Short.parseShort("2"));
	} catch (Exception e) {
return null;
	}
	}

@SuppressWarnings("unchecked")
public List<UocQuestion> getThirdLevelQuestions(String uocId){
	EntityManager em = super.emProvider.get();
	List<UocQuestion> entities = null;
	
	try {
		   Query query = em.createNamedQuery(DBQueries.GET_FIND_BY_UOC_AND_TYPE, UocQuestion.class); //$NON-NLS-1$ //$NON-NLS-2$
            
            query.setParameter("uoc", uocId); //$NON-NLS-1$
            query.setParameter("questionLevel", Short.parseShort("3")); //$NON-NLS-1$
            entities = query.getResultList();
	return entities;
	} catch (Exception e) {
	return null;
		 
	}
	
	
	
}
}
