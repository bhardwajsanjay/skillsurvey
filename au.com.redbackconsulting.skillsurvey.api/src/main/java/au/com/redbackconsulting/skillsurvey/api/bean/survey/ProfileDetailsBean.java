package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import au.com.redbackconsulting.skillsurvey.api.bean.FunctionBean;
import au.com.redbackconsulting.skillsurvey.api.bean.LevelBean;
import au.com.redbackconsulting.skillsurvey.api.bean.OccupationBean;
import au.com.redbackconsulting.skillsurvey.persistence.model.UserType;

import com.google.gson.annotations.Expose;

public class ProfileDetailsBean implements Serializable {
	
	@Expose
	private List<ProfileListItemBean> items = new ArrayList<ProfileListItemBean>();

	
	public void add(ProfileListItemBean item) {
		this.items.add(item);
		
	}

}
