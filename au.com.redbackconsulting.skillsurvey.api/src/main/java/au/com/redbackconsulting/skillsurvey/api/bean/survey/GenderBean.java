package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

import au.com.redbackconsulting.skillsurvey.persistence.model.Gender;

import com.google.gson.annotations.Expose;

public class GenderBean implements Serializable{
@Expose
private long genderId;

@Expose
private String genderName;

public long getGenderId() {
	return genderId;
}

public void setGenderId(long genderId) {
	this.genderId = genderId;
}

public String getGenderName() {
	return genderName;
}

public void setGenderName(String genderName) {
	this.genderName = genderName;
}

public static GenderBean get(Gender gender) {
	if(gender!=null){
		GenderBean bean = new GenderBean();
		bean.setGenderId(gender.getId());
		bean.setGenderName(gender.getName());
		return bean;
	}
	return new GenderBean();
}

}
