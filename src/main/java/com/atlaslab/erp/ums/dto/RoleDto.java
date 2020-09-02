/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 8:35:15 PM
 */
package com.atlaslab.erp.ums.dto;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class RoleDto {
	
	private Integer id;
	private String name;
	private Integer status;
	private Date createDate;
	private Date updateDate; 
	private List<PermissionDto> permissionDtos;

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

	public List<PermissionDto> getPermissionDtos() {
		return permissionDtos;
	}

	public void setPermissionDtos(List<PermissionDto> permissionDtos) {
		this.permissionDtos = permissionDtos;
	}

	@Override
	public String toString() {
		return "RoleDto [id=" + id + ", name=" + name + ", status=" + status + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", permissionDtos=" + permissionDtos + "]";
	}

}
