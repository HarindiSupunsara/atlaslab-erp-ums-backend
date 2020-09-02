/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 7:13:51 PM
 */
package com.atlaslab.erp.ums.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class PermissionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer status;

	@Column(columnDefinition = "DATETIME", nullable = false)
	private Date createDate;
	
	@Column(columnDefinition = "DATETIME")
	private Date updateDate;
	
	@OneToMany(mappedBy = "permissionEntity", targetEntity = RolePermissionEntity.class)
	private List<RolePermissionEntity> rolePermissionEntities;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<RolePermissionEntity> getRolePermissionEntities() {
		return rolePermissionEntities;
	}

	public void setRolePermissionEntities(List<RolePermissionEntity> rolePermissionEntities) {
		this.rolePermissionEntities = rolePermissionEntities;
	}

}
