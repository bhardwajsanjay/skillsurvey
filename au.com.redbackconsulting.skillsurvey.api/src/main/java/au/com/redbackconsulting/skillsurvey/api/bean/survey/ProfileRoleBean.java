package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;
import java.util.List;

import au.com.redbackconsulting.skillsurvey.api.bean.RoleBean;

import com.google.gson.annotations.Expose;

public class ProfileRoleBean  implements Serializable {
	
	@Expose
	private String loginId;
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Expose
	private RoleBean role;
	
	@Expose
	private List<RoleBean> roles;

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

	public List<RoleBean> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBean> roles) {
		this.roles = roles;
	}
	
	

}
