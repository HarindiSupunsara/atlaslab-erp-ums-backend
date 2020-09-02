/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 9:48:48 PM
 */
package com.atlaslab.erp.ums.dto;

import java.util.Date;
import java.util.List;

/**
 *
 */
public class UserDto {

	private Integer id;
	private String name;
	private String mobile;
	private String address1;
	private String address2;
	private String address3;
	private String nic;
	private Integer status;
	private Date createDate;
	private Date updateDate;
	private List<RoleDto> roleDtos;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
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

	public List<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(List<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", mobile=" + mobile + ", address1=" + address1 + ", address2="
				+ address2 + ", address3=" + address3 + ", nic=" + nic + ", status=" + status + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", roleDtos=" + roleDtos + "]";
	}
}
