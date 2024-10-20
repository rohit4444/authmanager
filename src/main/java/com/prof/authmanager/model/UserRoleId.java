package com.prof.authmanager.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRoleId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}