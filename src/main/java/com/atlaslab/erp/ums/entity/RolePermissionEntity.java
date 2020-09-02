/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 7:28:00 PM
 */
package com.atlaslab.erp.ums.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name = "role_permission")
public class RolePermissionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private Integer status;

	@Column(columnDefinition = "DATETIME", nullable = false)
	private Date createDate;

	@Column(columnDefinition = "DATETIME")
	private Date updateDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "permission_id", nullable = false)
	private PermissionEntity permissionEntity;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", nullable = false)
	private RoleEntity roleEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public PermissionEntity getPermissionEntity() {
		return permissionEntity;
	}

	public void setPermissionEntity(PermissionEntity permissionEntity) {
		this.permissionEntity = permissionEntity;
	}

	public RoleEntity getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
	}
}
