/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 8:36:54 PM
 */
package com.atlaslab.erp.ums.service;

import org.springframework.http.ResponseEntity;

import com.atlaslab.erp.ums.dto.PermissionDto;
import com.atlaslab.erp.ums.dto.RoleDto;
import com.atlaslab.erp.ums.entity.RoleEntity;
import com.atlaslab.erp.ums.entity.RolePermissionEntity;

/**
 *
 */
public interface RoleService {

	/**
	 * @param roleDto
	 * @return
	 */
	ResponseEntity<Object> save(RoleDto roleDto) throws Exception;

	/**
	 * @param roleEntity
	 * @param e
	 * @return
	 */
	RolePermissionEntity getRolePermissionEntity(RoleEntity roleEntity, PermissionDto e) throws Exception;

	/**
	 * @param roleDto
	 * @return
	 * @throws Exception
	 */
	RoleEntity getRoleEntity(RoleDto roleDto) throws Exception;

	/**
	 * @param roleDto
	 * @return
	 * @throws Exception
	 */
	ResponseEntity<Object> update(RoleDto roleDto) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	ResponseEntity<Object> delete(Integer id) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	ResponseEntity<Object> findById(Integer id) throws Exception;

	/**
	 * @return
	 */
	ResponseEntity<Object> getAll() throws Exception;

	/**
	 * @return
	 */
	ResponseEntity<Object> getAllAvtive() throws Exception;

	/**
	 * @param entity
	 * @return
	 */
	RoleDto getRoleDto(RoleEntity entity) throws Exception;

}
