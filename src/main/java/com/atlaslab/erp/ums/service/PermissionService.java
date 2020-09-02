/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 7:47:21 PM
 */
package com.atlaslab.erp.ums.service;

import org.springframework.http.ResponseEntity;

import com.atlaslab.erp.ums.dto.PermissionDto;
import com.atlaslab.erp.ums.entity.PermissionEntity;

/**
 *
 */
public interface PermissionService {

	/**
	 * @param permissionDto
	 * @return
	 */
	public ResponseEntity<Object> save(PermissionDto permissionDto) throws Exception;

	/**
	 * @param permissionDto
	 * @return
	 */
	PermissionEntity getPermissionEntity(PermissionDto permissionDto) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	public ResponseEntity<Object> delete(Integer id) throws Exception;

	/**
	 * @param id
	 * @return
	 */
	public ResponseEntity<Object> findById(Integer id) throws Exception;

	/**
	 * @return
	 */
	public ResponseEntity<Object> getAll() throws Exception;

	/**
	 * @param entity
	 * @return
	 */
	PermissionDto getPermissionDto(PermissionEntity entity) throws Exception;

	/**
	 * @return
	 */
	public ResponseEntity<Object> getAllAvtive() throws Exception;
}
