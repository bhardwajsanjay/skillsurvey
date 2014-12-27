package au.com.redbackconsulting.skillsurvey.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.queries.DataReadQuery;
import org.eclipse.persistence.queries.StoredProcedureCall;
import org.eclipse.persistence.sessions.DatabaseRecord;
import org.eclipse.persistence.sessions.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.redbackconsulting.skillsurvey.persistence.manager.EntityManagerProvider;
import au.com.redbackconsulting.skillsurvey.persistence.manager.PersistenceManager;
import au.com.redbackconsulting.skillsurvey.persistence.model.DBQueries;
import au.com.redbackconsulting.skillsurvey.persistence.model.Individual;
import au.com.redbackconsulting.skillsurvey.persistence.model.sqlview.SurveyResponseBySQLView;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_GENDER_FUNCTION;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_GENDER_LOCATION;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_GENDER_UOC_COURSE;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_HIGHER_EDUCATION;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_BY_PATHWAY;
import au.com.redbackconsulting.skillsurvey.persistence.report.FR_COURSES_BY_GENDER_LEVEL;

public class IndividualDAO extends BasicDAO<Individual> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected EntityManagerProvider emProvider;

	public IndividualDAO() {
		super(PersistenceManager.getInstance().getEntityManagerProvider());
	}

	@Override
	protected String getidFieldName() {

		return "idindividual";
	}

	public Individual getReferenceById(long id) {

		EntityManager em = super.emProvider.get();
		Individual entity = em.find(Individual.class, id);
		return entity;
	}

	public Individual getByLogin(String loginId) {

		EntityManager em = super.emProvider.get();
		Individual entity = null;

		try {
			Query query = em.createNamedQuery(DBQueries.GET_LOGGED_IN_USER,
					Individual.class); //$NON-NLS-1$ //$NON-NLS-2$

			query.setParameter("name", loginId); //$NON-NLS-1$
			entity = (Individual) query.getSingleResult();
			return entity;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	public boolean getProfileUpdateStatus(String loginId) {
		EntityManager em = super.emProvider.get();
		Individual entity = null;
		boolean result = true;
		try {
			TypedQuery<Individual> query = em.createNamedQuery(
					DBQueries.GET_PROFILE_STATUS, Individual.class); //$NON-NLS-1$ //$NON-NLS-2$

			query.setParameter("name", loginId); //$NON-NLS-1$
			entity = (Individual) query.getSingleResult();
			if (entity != null)
				result = false;
			return result;
		} catch (Exception e) {

		}
		return result;
	}

	public List<FR_COURSES_BY_GENDER_LEVEL> callSPforFR_BY_GENDER_LEVEL(
			long functionId, String spPathName) {
		List<FR_COURSES_BY_GENDER_LEVEL> result = new ArrayList<FR_COURSES_BY_GENDER_LEVEL>();

		try {

			EntityManager em = super.emProvider.get();

			String needId = "NEEDID";
			String needDesc = "NEEDDESC";
			String uocId = "UOCID";
			String uocName = "UOCNAME";
			String levelId = "LEVELID";
			String levelDesc = "LEVELDESC";

			String coCntMale = "COCNTMALE";
			String coCntFemale = "COCNTFEMALE";
			String coCntOther = "COCNTOTHER";
			String coCntAll = "COCNTALL";

			String incoCntMale = "INCOCNTMALE";
			String incoCntFemale = "INCOCNTFEMALE";
			String incoCntOther = "INCOCNTOTHER";
			String incoCntAll = "INCOCNTALL";

			String nsCntMale = "NSCNTMALE";
			String nsCntFemale = "NSCNTFEMALE";
			String nsCntOther = "NSCNTOTHER";
			String nsCntAll = "NSCNTALL";

			StoredProcedureCall storedProcedureCall = new StoredProcedureCall();
			storedProcedureCall.setProcedureName(spPathName);
			storedProcedureCall.addNamedArgument("FUNCTIONID");
			Long argumentValue = new Long(functionId);
			DataReadQuery query = new DataReadQuery();
			query.addArgument("FUNCTIONID");

			List<Long> argumentValues = new ArrayList<Long>();
			argumentValues.add(argumentValue);
			query.setCall(storedProcedureCall);

			Session session = em.unwrap(Session.class);
			List<?> results = (List<?>) session.executeQuery(query,
					argumentValues);
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				DatabaseRecord record = (DatabaseRecord) iterator.next();

				Object objNeedId = record.get(needId);
				int valueOfNeedId = 0;
				if (objNeedId != null)
					valueOfNeedId = (int) record.get(needId);

				Object objNeedDesc = record.get(needDesc);
				String valueOfNeedDesc = "";
				if (objNeedDesc != null)
					valueOfNeedDesc = (String) record.get(needDesc);

				Object objUocId = record.get(uocId);
				String valueOfUocId = "";
				if (objUocId != null)
					valueOfUocId = (String) record.get(uocId);

				Object objUocName = record.get(uocName);
				String valueOfUocName = "";
				if (objUocName != null)
					valueOfUocName = (String) record.get(uocName);

				Object objLevelId = record.get(levelId);
				int valueOfLevelId = 0;
				if (objLevelId != null)
					valueOfLevelId = (int) record.get(levelId);

				Object objLevelDesc = record.get(levelDesc);
				String valueOfLevelDesc = "";
				if (objLevelDesc != null)
					valueOfLevelDesc = (String) record.get(levelDesc);

				Object objCoCntMale = record.get(coCntMale);
				int valueOfCoCntMale = 0;
				if (objCoCntMale != null)
					valueOfCoCntMale = (int) record.get(coCntMale);

				Object objCoCntFemale = record.get(coCntFemale);
				int valueOfCoCntFemale = 0;
				if (objCoCntFemale != null)
					valueOfCoCntFemale = (int) record.get(coCntFemale);

				Object objCoCntOther = record.get(coCntOther);
				int valueOfCoCntOther = 0;
				if (objCoCntOther != null)
					valueOfCoCntOther = (int) record.get(coCntOther);

				Object objCoCntAll = record.get(coCntAll);
				int valueOfCoCntAll = 0;
				if (objCoCntAll != null)
					valueOfCoCntAll = (int) record.get(coCntAll);

				Object objInCoCntMale = record.get(incoCntMale);
				int valueOfInCoCntMale = 0;
				if (objInCoCntMale != null)
					valueOfInCoCntMale = (int) record.get(incoCntMale);

				Object objInCoCntFemale = record.get(incoCntFemale);
				int valueOfInCoCntFemale = 0;
				if (objInCoCntFemale != null)
					valueOfInCoCntFemale = (int) record.get(incoCntFemale);

				Object objInCoCntOther = record.get(incoCntOther);
				int valueOfInCoCntOther = 0;
				if (objInCoCntOther != null)
					valueOfInCoCntOther = (int) record.get(incoCntOther);

				Object objInCoCntAll = record.get(incoCntAll);
				int valueOfInCoCntAll = 0;
				if (objInCoCntAll != null)
					valueOfInCoCntAll = (int) record.get(incoCntAll);

				Object objNsCntMale = record.get(nsCntMale);
				int valueOfNsCntmale = 0;
				if (objNsCntMale != null)
					valueOfNsCntmale = (int) objNsCntMale;

				Object objNsCntFemale = record.get(nsCntFemale);
				int valueOfNsCntFemale = 0;
				if (objNsCntFemale != null)
					valueOfNsCntFemale = (int) objNsCntFemale;

				Object objNsCntOther = record.get(nsCntOther);
				int valueOfNsCntOther = 0;
				if (objNsCntOther != null)
					valueOfNsCntOther = (int) objNsCntOther;

				Object objNsCntAll = record.get(nsCntAll);
				int valueOfNsCntAll = 0;
				if (objNsCntAll != null)
					valueOfNsCntAll = (int) objNsCntAll;

				FR_COURSES_BY_GENDER_LEVEL obj = new FR_COURSES_BY_GENDER_LEVEL(
						valueOfNeedId, valueOfNeedDesc, valueOfUocId,
						valueOfUocName, valueOfLevelId, valueOfLevelDesc,
						valueOfCoCntMale, valueOfCoCntFemale,
						valueOfCoCntOther, valueOfCoCntAll, valueOfInCoCntMale
								+ valueOfNsCntmale, valueOfInCoCntFemale
								+ valueOfNsCntFemale, valueOfInCoCntOther
								+ valueOfNsCntOther, valueOfInCoCntAll
								+ valueOfNsCntAll);
				result.add(obj);

			}

			return result;

		} catch (Exception e) {
			return null;
		}
	}

	public List<FR_BY_GENDER_LOCATION> callSPforFR_BY_GENDER_LOCATION(
			long functionId, String spPathName) {

		try {
			List<FR_BY_GENDER_LOCATION> result = new ArrayList<FR_BY_GENDER_LOCATION>();

			EntityManager em = super.emProvider.get();

			String needId = "NEEDID";
			String needDesc = "NEEDDESC";
			String uocId = "UOCID";
			String uocName = "UOCNAME";
			String locationId = "LOCATIONID";
			String locationDesc = "LOCATIONDESC";

			String coCntMale = "COCNTMALE";
			String coCntFemale = "COCNTFEMALE";
			String coCntOther = "COCNTOTHER";
			String coCntAll = "COCNTALL";

			String incoCntMale = "INCOCNTMALE";
			String incoCntFemale = "INCOCNTFEMALE";
			String incoCntOther = "INCOCNTOTHER";
			String incoCntAll = "INCOCNTALL";

			String nsCntMale = "NSCNTMALE";
			String nsCntFemale = "NSCNTFEMALE";
			String nsCntOther = "NSCNTOTHER";
			String nsCntAll = "NSCNTALL";

			StoredProcedureCall storedProcedureCall = new StoredProcedureCall();
			storedProcedureCall.setProcedureName(spPathName);
			storedProcedureCall.addNamedArgument("FUNCTIONID");
			Long argumentValue = new Long(functionId);
			DataReadQuery query = new DataReadQuery();
			query.addArgument("FUNCTIONID");

			List<Long> argumentValues = new ArrayList<Long>();
			argumentValues.add(argumentValue);
			query.setCall(storedProcedureCall);

			Session session = em.unwrap(Session.class);
			List<?> results = (List<?>) session.executeQuery(query,
					argumentValues);
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				DatabaseRecord record = (DatabaseRecord) iterator.next();

				Object objNeedId = record.get(needId);
				int valueOfNeedId = 0;
				if (objNeedId != null)
					valueOfNeedId = (int) record.get(needId);

				Object objNeedDesc = record.get(needDesc);
				String valueOfNeedDesc = "";
				if (objNeedDesc != null)
					valueOfNeedDesc = (String) record.get(needDesc);

				Object objUocId = record.get(uocId);
				String valueOfUocId = "";
				if (objUocId != null)
					valueOfUocId = (String) record.get(uocId);

				Object objUocName = record.get(uocName);
				String valueOfUocName = "";
				if (objUocName != null)
					valueOfUocName = (String) record.get(uocName);

				Object objLocationId = record.get(locationId);
				int valueOfLocationId = 0;
				if (objLocationId != null)
					valueOfLocationId = (int) record.get(locationId);

				Object objLocationDesc = record.get(locationDesc);
				String valueOfLocationDesc = "";
				if (objLocationDesc != null)
					valueOfLocationDesc = (String) record.get(locationDesc);

				Object objCoCntMale = record.get(coCntMale);
				int valueOfCoCntMale = 0;
				if (objCoCntMale != null)
					valueOfCoCntMale = (int) record.get(coCntMale);

				Object objCoCntFemale = record.get(coCntFemale);
				int valueOfCoCntFemale = 0;
				if (objCoCntFemale != null)
					valueOfCoCntFemale = (int) record.get(coCntFemale);

				Object objCoCntOther = record.get(coCntOther);
				int valueOfCoCntOther = 0;
				if (objCoCntOther != null)
					valueOfCoCntOther = (int) record.get(coCntOther);

				Object objCoCntAll = record.get(coCntAll);
				int valueOfCoCntAll = 0;
				if (objCoCntAll != null)
					valueOfCoCntAll = (int) record.get(coCntAll);

				Object objInCoCntMale = record.get(incoCntMale);
				int valueOfInCoCntMale = 0;
				if (objInCoCntMale != null)
					valueOfInCoCntMale = (int) record.get(incoCntMale);

				Object objInCoCntFemale = record.get(incoCntFemale);
				int valueOfInCoCntFemale = 0;
				if (objInCoCntFemale != null)
					valueOfInCoCntFemale = (int) record.get(incoCntFemale);

				Object objInCoCntOther = record.get(incoCntOther);
				int valueOfInCoCntOther = 0;
				if (objInCoCntOther != null)
					valueOfInCoCntOther = (int) record.get(incoCntOther);

				Object objInCoCntAll = record.get(incoCntAll);
				int valueOfInCoCntAll = 0;
				if (objInCoCntAll != null)
					valueOfInCoCntAll = (int) record.get(incoCntAll);

				Object objNsCntMale = record.get(nsCntMale);
				int valueOfNsCntmale = 0;
				if (objNsCntMale != null)
					valueOfNsCntmale = (int) objNsCntMale;

				Object objNsCntFemale = record.get(nsCntFemale);
				int valueOfNsCntFemale = 0;
				if (objNsCntFemale != null)
					valueOfNsCntFemale = (int) objNsCntFemale;

				Object objNsCntOther = record.get(nsCntOther);
				int valueOfNsCntOther = 0;
				if (objNsCntOther != null)
					valueOfNsCntOther = (int) objNsCntOther;

				Object objNsCntAll = record.get(nsCntAll);
				int valueOfNsCntAll = 0;
				if (objNsCntAll != null)
					valueOfNsCntAll = (int) objNsCntAll;

				FR_BY_GENDER_LOCATION obj = new FR_BY_GENDER_LOCATION(
						valueOfNeedId, valueOfNeedDesc, valueOfUocId,
						valueOfUocName, valueOfLocationId, valueOfLocationDesc,
						valueOfCoCntMale, valueOfCoCntFemale,
						valueOfCoCntOther, valueOfCoCntAll, valueOfInCoCntMale
								+ valueOfNsCntmale, valueOfInCoCntFemale
								+ valueOfNsCntFemale, valueOfInCoCntOther
								+ valueOfNsCntOther, valueOfInCoCntAll
								+ valueOfNsCntAll);
				// if(objLocationId!=null){
				result.add(obj);
				// }

			}

			return result;

		} catch (Exception e) {
			return null;
		}
	}

	public List<FR_BY_GENDER_UOC_COURSE> callSPforFR_BY_GENDER_UOC_COURSE(
			long functionId, String spPathName) {
		List<FR_BY_GENDER_UOC_COURSE> result = new ArrayList<FR_BY_GENDER_UOC_COURSE>();

		try {

			EntityManager em = super.emProvider.get();

			String needId = "NEEDID";
			String needDesc = "NEEDDESC";
			String uocId = "UOCID";
			String uocName = "UOCNAME";

			String coCntMale = "COCNTMALE";
			String coCntFemale = "COCNTFEMALE";
			String coCntOther = "COCNTOTHER";
			String coCntAll = "COCNTALL";

			String incoCntMale = "INCOCNTMALE";
			String incoCntFemale = "INCOCNTFEMALE";
			String incoCntOther = "INCOCNTOTHER";
			String incoCntAll = "INCOCNTALL";

			String nsCntMale = "NSCNTMALE";
			String nsCntFemale = "NSCNTFEMALE";
			String nsCntOther = "NSCNTOTHER";
			String nsCntAll = "NSCNTALL";

			StoredProcedureCall storedProcedureCall = new StoredProcedureCall();
			storedProcedureCall.setProcedureName(spPathName);
			storedProcedureCall.addNamedArgument("FUNCTIONID");
			Long argumentValue = new Long(functionId);
			DataReadQuery query = new DataReadQuery();
			query.addArgument("FUNCTIONID");

			List<Long> argumentValues = new ArrayList<Long>();
			argumentValues.add(argumentValue);
			query.setCall(storedProcedureCall);

			Session session = em.unwrap(Session.class);
			List<?> results = (List<?>) session.executeQuery(query,
					argumentValues);
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				DatabaseRecord record = (DatabaseRecord) iterator.next();

				Object objNeedId = record.get(needId);
				int valueOfNeedId = 0;
				if (objNeedId != null)
					valueOfNeedId = (int) record.get(needId);

				Object objNeedDesc = record.get(needDesc);
				String valueOfNeedDesc = "";
				if (objNeedDesc != null)
					valueOfNeedDesc = (String) record.get(needDesc);

				Object objUocId = record.get(uocId);
				String valueOfUocId = "";
				if (objUocId != null)
					valueOfUocId = (String) record.get(uocId);

				Object objUocName = record.get(uocName);
				String valueOfUocName = "";
				if (objUocName != null)
					valueOfUocName = (String) record.get(uocName);

				Object objCoCntMale = record.get(coCntMale);
				int valueOfCoCntMale = 0;
				if (objCoCntMale != null)
					valueOfCoCntMale = (int) record.get(coCntMale);

				Object objCoCntFemale = record.get(coCntFemale);
				int valueOfCoCntFemale = 0;
				if (objCoCntFemale != null)
					valueOfCoCntFemale = (int) record.get(coCntFemale);

				Object objCoCntOther = record.get(coCntOther);
				int valueOfCoCntOther = 0;
				if (objCoCntOther != null)
					valueOfCoCntOther = (int) record.get(coCntOther);

				Object objCoCntAll = record.get(coCntAll);
				int valueOfCoCntAll = 0;
				if (objCoCntAll != null)
					valueOfCoCntAll = (int) record.get(coCntAll);

				Object objInCoCntMale = record.get(incoCntMale);
				int valueOfInCoCntMale = 0;
				if (objInCoCntMale != null)
					valueOfInCoCntMale = (int) record.get(incoCntMale);

				Object objInCoCntFemale = record.get(incoCntFemale);
				int valueOfInCoCntFemale = 0;
				if (objInCoCntFemale != null)
					valueOfInCoCntFemale = (int) record.get(incoCntFemale);

				Object objInCoCntOther = record.get(incoCntOther);
				int valueOfInCoCntOther = 0;
				if (objInCoCntOther != null)
					valueOfInCoCntOther = (int) record.get(incoCntOther);

				Object objInCoCntAll = record.get(incoCntAll);
				int valueOfInCoCntAll = 0;
				if (objInCoCntAll != null)
					valueOfInCoCntAll = (int) record.get(incoCntAll);

				Object objNsCntMale = record.get(nsCntMale);
				int valueOfNsCntmale = 0;
				if (objNsCntMale != null)
					valueOfNsCntmale = (int) objNsCntMale;

				Object objNsCntFemale = record.get(nsCntFemale);
				int valueOfNsCntFemale = 0;
				if (objNsCntFemale != null)
					valueOfNsCntFemale = (int) objNsCntFemale;

				Object objNsCntOther = record.get(nsCntOther);
				int valueOfNsCntOther = 0;
				if (objNsCntOther != null)
					valueOfNsCntOther = (int) objNsCntOther;

				Object objNsCntAll = record.get(nsCntAll);
				int valueOfNsCntAll = 0;
				if (objNsCntAll != null)
					valueOfNsCntAll = (int) objNsCntAll;

				FR_BY_GENDER_UOC_COURSE obj = new FR_BY_GENDER_UOC_COURSE(
						valueOfNeedId, valueOfNeedDesc, valueOfUocId,
						valueOfUocName, valueOfCoCntMale, valueOfCoCntFemale,
						valueOfCoCntOther, valueOfCoCntAll, valueOfInCoCntMale
								+ valueOfNsCntmale, valueOfInCoCntFemale
								+ valueOfNsCntFemale, valueOfInCoCntOther
								+ valueOfNsCntOther, valueOfInCoCntAll
								+ valueOfNsCntAll);
				result.add(obj);

			}

			return result;

		} catch (Exception e) {
			return null;
		}
	}

	public List<FR_BY_GENDER_FUNCTION> callSPforFR_BY_GENDER_FUNCTION(
			String spPathName) {

		try {
			List<FR_BY_GENDER_FUNCTION> result = new ArrayList<FR_BY_GENDER_FUNCTION>();

			EntityManager em = super.emProvider.get();

			String needId = "NEEDID";
			String needDesc = "NEEDDESC";
			String uocId = "UOCID";
			String uocName = "UOCNAME";
			String functionId = "FUNCTIONID";
			String functionDesc = "FUNCTIONDESC";

			String coCntMale = "COCNTMALE";
			String coCntFemale = "COCNTFEMALE";
			String coCntOther = "COCNTOTHER";
			String coCntAll = "COCNTALL";

			String incoCntMale = "INCOCNTMALE";
			String incoCntFemale = "INCOCNTFEMALE";
			String incoCntOther = "INCOCNTOTHER";
			String incoCntAll = "INCOCNTALL";

			String nsCntMale = "NSCNTMALE";
			String nsCntFemale = "NSCNTFEMALE";
			String nsCntOther = "NSCNTOTHER";
			String nsCntAll = "NSCNTALL";

			StoredProcedureCall storedProcedureCall = new StoredProcedureCall();
			storedProcedureCall.setProcedureName(spPathName);
			// storedProcedureCall.addNamedArgument("FUNCTIONID");
			// Long argumentValue = new Long(lfunctionId);
			DataReadQuery query = new DataReadQuery();
			// query.addArgument("FUNCTIONID");

			List<Long> argumentValues = new ArrayList<Long>();
			// argumentValues.add(argumentValue);
			query.setCall(storedProcedureCall);

			Session session = em.unwrap(Session.class);
			List<?> results = (List<?>) session.executeQuery(query);
			// (List<?>) session.executeQuery(query, argumentValues );
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				DatabaseRecord record = (DatabaseRecord) iterator.next();

				Object objNeedId = record.get(needId);
				int valueOfNeedId = 0;
				if (objNeedId != null)
					valueOfNeedId = (int) record.get(needId);

				Object objNeedDesc = record.get(needDesc);
				String valueOfNeedDesc = "";
				if (objNeedDesc != null)
					valueOfNeedDesc = (String) record.get(needDesc);

				Object objUocId = record.get(uocId);
				String valueOfUocId = "";
				if (objUocId != null)
					valueOfUocId = (String) record.get(uocId);

				Object objUocName = record.get(uocName);
				String valueOfUocName = "";
				if (objUocName != null)
					valueOfUocName = (String) record.get(uocName);

				Object objfunctionId = record.get(functionId);
				int valueOfLocationId = 0;
				if (objfunctionId != null)
					valueOfLocationId = (int) objfunctionId;

				Object objFunctionDesc = record.get(functionDesc);
				String valueOfLocationDesc = "";
				if (objFunctionDesc != null)
					valueOfLocationDesc = (String) record.get(functionDesc);

				Object objCoCntMale = record.get(coCntMale);
				int valueOfCoCntMale = 0;
				if (objCoCntMale != null)
					valueOfCoCntMale = (int) record.get(coCntMale);

				Object objCoCntFemale = record.get(coCntFemale);
				int valueOfCoCntFemale = 0;
				if (objCoCntFemale != null)
					valueOfCoCntFemale = (int) record.get(coCntFemale);

				Object objCoCntOther = record.get(coCntOther);
				int valueOfCoCntOther = 0;
				if (objCoCntOther != null)
					valueOfCoCntOther = (int) record.get(coCntOther);

				Object objCoCntAll = record.get(coCntAll);
				int valueOfCoCntAll = 0;
				if (objCoCntAll != null)
					valueOfCoCntAll = (int) record.get(coCntAll);

				Object objInCoCntMale = record.get(incoCntMale);
				int valueOfInCoCntMale = 0;
				if (objInCoCntMale != null)
					valueOfInCoCntMale = (int) record.get(incoCntMale);

				Object objInCoCntFemale = record.get(incoCntFemale);
				int valueOfInCoCntFemale = 0;
				if (objInCoCntFemale != null)
					valueOfInCoCntFemale = (int) record.get(incoCntFemale);

				Object objInCoCntOther = record.get(incoCntOther);
				int valueOfInCoCntOther = 0;
				if (objInCoCntOther != null)
					valueOfInCoCntOther = (int) record.get(incoCntOther);

				Object objInCoCntAll = record.get(incoCntAll);
				int valueOfInCoCntAll = 0;
				if (objInCoCntAll != null)
					valueOfInCoCntAll = (int) record.get(incoCntAll);

				Object objNsCntMale = record.get(nsCntMale);
				int valueOfNsCntmale = 0;
				if (objNsCntMale != null)
					valueOfNsCntmale = (int) objNsCntMale;

				Object objNsCntFemale = record.get(nsCntFemale);
				int valueOfNsCntFemale = 0;
				if (objNsCntFemale != null)
					valueOfNsCntFemale = (int) objNsCntFemale;

				Object objNsCntOther = record.get(nsCntOther);
				int valueOfNsCntOther = 0;
				if (objNsCntOther != null)
					valueOfNsCntOther = (int) objNsCntOther;

				Object objNsCntAll = record.get(nsCntAll);
				int valueOfNsCntAll = 0;
				if (objNsCntAll != null)
					valueOfNsCntAll = (int) objNsCntAll;

				FR_BY_GENDER_FUNCTION obj = new FR_BY_GENDER_FUNCTION(
						valueOfNeedId, valueOfNeedDesc, valueOfUocId,
						valueOfUocName, valueOfLocationId, valueOfLocationDesc,
						valueOfCoCntMale, valueOfCoCntFemale,
						valueOfCoCntOther, valueOfCoCntAll, valueOfInCoCntMale
								+ valueOfNsCntmale, valueOfInCoCntFemale
								+ valueOfNsCntFemale, valueOfInCoCntOther
								+ valueOfNsCntOther, valueOfInCoCntAll
								+ valueOfNsCntAll);
				// if(objLocationId!=null){
				result.add(obj);
				// }

			}

			return result;

		} catch (Exception e) {
			return null;
		}
	}

	public List<FR_BY_HIGHER_EDUCATION> callSPfor_Higher_Education(
			long functionId, String spPathName, String qtn1, String qtn2) {
		try {
			List<FR_BY_HIGHER_EDUCATION> resultBeans = new ArrayList<FR_BY_HIGHER_EDUCATION>();

			EntityManager em = super.emProvider.get();

			String Q1YESCNTMALE = "Q1YESCNTMALE";
			String Q1YESCNTFEMALE = "Q1YESCNTFEMALE";
			String Q1YESCNTOTHER = "Q1YESCNTOTHER";

			String Q2YESCNTMALE = "Q2YESCNTMALE";
			String Q2YESCNTFEMALE = "Q2YESCNTFEMALE";
			String Q2YESCNTOTHER = "Q2YESCNTOTHER";

			// String Q1NOCNTMALE="Q1NOCNTMALE";
			// String Q1ALLCNTMALE="Q1ALLCNTMALE";

			// String Q2NOCNTMALE="Q2NOCNTMALE";
			// String Q2ALLCNTMALE="Q2ALLCNTMALE";

			// String Q1NOCNTFEMALE="Q1NOCNTFEMALE";
			// String Q1ALLCNTFEMALE="Q1ALLCNTFEMALE";

			// String Q2NOCNTFEMALE="Q2NOCNTFEMALE";

			// String Q1NOCNTOTHER="Q1NOCNTOTHER";
			// String Q1ALLCNTOTHER="Q1ALLCNTOTHER";

			// String Q2NOCNTOTHER="Q2NOCNTOTHER";

			StoredProcedureCall storedProcedureCall = new StoredProcedureCall();
			storedProcedureCall.setProcedureName(spPathName);
			DataReadQuery query = new DataReadQuery();
			storedProcedureCall.addNamedArgument("FUNCTIONID");
			Long argumentValue = new Long(functionId);
			query.addArgument("FUNCTIONID");
			List<Long> argumentValues = new ArrayList<Long>();
			argumentValues.add(argumentValue);

			query.setCall(storedProcedureCall);

			Session session = em.unwrap(Session.class);
			List<?> results = (List<?>) session.executeQuery(query,
					argumentValues);
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				DatabaseRecord record = (DatabaseRecord) iterator.next();

				Object OQ1YESCNTMALE = record.get(Q1YESCNTMALE);
				BigDecimal valYesCntMale = new BigDecimal(0);
				if (OQ1YESCNTMALE != null)
					valYesCntMale = (BigDecimal) OQ1YESCNTMALE;

				Object OQ1YESCNTFEMALE = record.get(Q1YESCNTFEMALE);
				BigDecimal valYesCntFemale = new BigDecimal(0);
				if (OQ1YESCNTFEMALE != null)
					valYesCntFemale = (BigDecimal) OQ1YESCNTFEMALE;

				Object OQ1YESCNTOTHER = record.get(Q1YESCNTOTHER);
				BigDecimal valYesCntOther = new BigDecimal(0);
				if (OQ1YESCNTOTHER != null)
					valYesCntOther = (BigDecimal) OQ1YESCNTOTHER;

				BigDecimal totalYesCntMF = valYesCntMale.add(valYesCntFemale)
						.add(valYesCntOther);
				FR_BY_HIGHER_EDUCATION entity1 = new FR_BY_HIGHER_EDUCATION();
				entity1.setQuestion(qtn1);
				entity1.setYesCntMale(valYesCntMale);
				entity1.setYesCntFemale(valYesCntFemale);
				entity1.setYesCntOther(valYesCntOther);
				entity1.setYesCntAll(totalYesCntMF);
				resultBeans.add(entity1);

				Object OQ2YESCNTMALE = record.get(Q2YESCNTMALE);
				BigDecimal val2YesCntMale = new BigDecimal(0);
				if (OQ2YESCNTMALE != null)
					val2YesCntMale = (BigDecimal) OQ2YESCNTMALE;
				Object OQ2YESCNTFEMALE = record.get(Q2YESCNTFEMALE);
				BigDecimal val2YesCntFemale = new BigDecimal(0);
				if (OQ2YESCNTFEMALE != null)
					val2YesCntFemale = (BigDecimal) OQ2YESCNTFEMALE;

				Object OQ2YESCNTOTHER = record.get(Q2YESCNTOTHER);
				BigDecimal val2YesCntOther = new BigDecimal(0);
				if (OQ2YESCNTOTHER != null)
					val2YesCntOther = (BigDecimal) OQ2YESCNTOTHER;

				BigDecimal total2YesCntMF = val2YesCntMale
						.add(val2YesCntFemale).add(val2YesCntOther);

				FR_BY_HIGHER_EDUCATION entity2 = new FR_BY_HIGHER_EDUCATION();
				entity2.setQuestion(qtn2);
				entity2.setYesCntMale(val2YesCntMale);
				entity2.setYesCntFemale(val2YesCntFemale);
				entity2.setYesCntOther(val2YesCntOther);
				entity2.setYesCntAll(total2YesCntMF);
				resultBeans.add(entity2);

			} // end For

			return resultBeans;
		} catch (Exception e) {
		}
		return null;
	}

	public List<FR_BY_PATHWAY> callSPfor_PathwayData(long functionId,
			String spPathName) {
		try {
			List<FR_BY_PATHWAY> result = new ArrayList<FR_BY_PATHWAY>();

			EntityManager em = super.emProvider.get();

			String pathway = "PATHWAYDESC";
			String cntMale = "CNTMALE";
			String cntFemale = "CNTFEMALE";
			String cntOther = "CNTOTHER";
			String cntAll = "CNTALL";

			StoredProcedureCall storedProcedureCall = new StoredProcedureCall();
			storedProcedureCall.setProcedureName(spPathName);
			DataReadQuery query = new DataReadQuery();
			storedProcedureCall.addNamedArgument("FUNCTIONID");
			Long argumentValue = new Long(functionId);
			query.addArgument("FUNCTIONID");

			List<Long> argumentValues = new ArrayList<Long>();
			argumentValues.add(argumentValue);

			query.setCall(storedProcedureCall);

			Session session = em.unwrap(Session.class);
			List<?> results = (List<?>) session.executeQuery(query,
					argumentValues);
			for (Iterator iterator = results.iterator(); iterator.hasNext();) {
				DatabaseRecord record = (DatabaseRecord) iterator.next();

				Object objQuestion = record.get(pathway);
				String valueOfQuestion = "";
				if (objQuestion != null)
					valueOfQuestion = (String) objQuestion;

				Object objCntMale = record.get(cntMale);
				BigDecimal valueOfCntMale = new BigDecimal(0);
				if (objCntMale != null)
					valueOfCntMale = (BigDecimal) objCntMale;

				Object objCntFemale = record.get(cntFemale);
				BigDecimal valueOfCntFemale = new BigDecimal(0);
				if (objCntFemale != null)
					valueOfCntFemale = (BigDecimal) objCntFemale;

				Object objCntOther = record.get(cntOther);
				BigDecimal valueOfCntOther = new BigDecimal(0);
				if (objCntOther != null)
					valueOfCntOther = (BigDecimal) objCntOther;

				Object objCntAll = record.get(cntAll);
				BigDecimal valueOfCntAll = new BigDecimal(0);
				if (objCntAll != null)
					valueOfCntAll = (BigDecimal) objCntAll;
				FR_BY_PATHWAY bean = new FR_BY_PATHWAY(valueOfQuestion,
						valueOfCntMale, valueOfCntFemale, valueOfCntOther,
						valueOfCntAll);
				result.add(bean);

			} // end For

			return result;
		} catch (Exception e) {
			return null;

		}

	}

	public List<SurveyResponseBySQLView> callVIEW_SurveyResponse(
			String queryString) {

		List<SurveyResponseBySQLView> data = new ArrayList<SurveyResponseBySQLView>();
		try {

			EntityManager em = super.emProvider.get();

			Query query = em.createNativeQuery(queryString);
			List<?> list = query.getResultList();
			Iterator it = list.iterator();

			while (it.hasNext()) {
				Object[] result = (Object[]) it.next();

				String col1 = null;
				String col2 = null;
				String col3 = null;
				String col4 = null;
				String col5 = null;
				String col6 = null;
				String col7 = null;
				String col8 = null;
				String col9 = null;
				String col10 = null;
				String col11 = null;
				String col12 = null;
				String col13 = null;
				String col14 = null;
				String col15 = null;
				String col16 = null;
				String col17 = null;
				String col18 = null;
				String col19 = null;
				String col20 = null;

				if (result != null) {
					int len = result.length;
					if (len >= 1) {
						Object obj1 = result[0];
						if (obj1 instanceof String)
							col1 = (String) obj1;
						else
							col1 = String.valueOf(obj1);

						if (len >= 2) {
							Object obj2 = result[1];
							if (obj2 instanceof String)
								col2 = (String) obj2;
							else
								col2 = String.valueOf(obj2);

							if (len >= 3) {
								Object obj3 = result[2];
								if (obj3 instanceof String)
									col3 = (String) obj3;
								else
									col3 = String.valueOf(obj3);

								if (len >= 4) {
									Object obj4 = result[3];

									if (obj4 instanceof String)
										col4 = (String) obj4;
									else
										col4 = String.valueOf(obj4);

									if (len >= 5) {
										Object obj5 = result[4];

										if (obj5 instanceof String)
											col5 = (String) obj5;
										else
											col5 = String.valueOf(obj5);

										if (len >= 6) {
											Object obj6 = result[5];

											if (obj6 instanceof String)
												col6 = (String) obj6;
											else
												col6 = String.valueOf(obj6);

											if (len >= 7) {
												Object obj7 = result[6];

												if (obj7 instanceof String)
													col7 = (String) obj7;
												else
													col7 = String.valueOf(obj7);

												if (len >= 8) {
													Object obj8 = result[7];

													if (obj8 instanceof String)
														col8 = (String) obj8;
													else
														col8 = String
																.valueOf(obj8);

													if (len >= 9) {
														Object obj9 = result[8];

														if (obj9 instanceof String)
															col9 = (String) obj9;
														else
															col9 = String
																	.valueOf(obj9);

														if (len >= 10) {
															Object obj10 = result[9];

															if (obj10 instanceof String)
																col10 = (String) obj10;
															else
																col10 = String
																		.valueOf(obj10);

															if (len >= 11) {
																Object obj11 = result[10];
																if (obj11 instanceof String)
																	col11 = (String) obj11;
																else
																	col11 = String
																			.valueOf(obj11);

																if (len >= 12) {
																	Object obj12 = result[11];
																	if (obj12 instanceof String)
																		col12 = (String) obj12;
																	else
																		col12 = String
																				.valueOf(obj12);

																	if (len >= 13) {
																		Object obj13 = result[12];
																		if (obj13 instanceof String)
																			col13 = (String) obj13;
																		else
																			col13 = String
																					.valueOf(obj13);

																		if (len >= 14) {
																			Object obj14 = result[13];
																			if (obj14 instanceof String)
																				col14 = (String) obj14;
																			else
																				col14 = String
																						.valueOf(obj14);

																			if (len >= 15) {
																				Object obj15 = result[14];
																				if (obj15 instanceof String)
																					col15 = (String) obj15;
																				else
																					col15 = String
																							.valueOf(obj15);

																				if (len >= 16) {
																					Object obj16 = result[15];

																					if (obj16 instanceof String)
																						col16 = (String) obj16;
																					else
																						col16 = String
																								.valueOf(obj16);

																					if (len >= 17) {
																						Object obj17 = result[16];
																						if (obj17 instanceof String)
																							col17 = (String) obj17;
																						else
																							col17 = String
																									.valueOf(obj17);

																						if (len >= 18) {
																							Object obj18 = result[17];
																							if (obj18 instanceof String)
																								col18 = (String) obj18;
																							else
																								col18 = String
																										.valueOf(obj18);

																							if (len >= 19) {
																								Object obj19 = result[18];
																								if (obj19 instanceof String)
																									col19 = (String) obj19;
																								else
																									col19 = String
																											.valueOf(obj19);

																								if (len >= 20) {
																									Object obj20 = result[19];
																									if (obj20 instanceof String)
																										col20 = (String) obj20;
																									else
																										col20 = String
																												.valueOf(obj20);

																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}

				SurveyResponseBySQLView entity = new SurveyResponseBySQLView(
						col1, col2, col3, col4, col5, col6, col7, col8, col9,
						col10, col11, col12 ,col13 , col14 ,col15 ,col16 ,col17 ,col18 ,col19 ,col20);
				data.add(entity);

			}

			return data;
		} catch (Exception e) {
			return data;

		}

	}
}
