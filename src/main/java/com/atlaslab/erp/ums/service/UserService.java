/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 9:48:31 PM
 */
package com.atlaslab.erp.ums.service;

import org.springframework.http.ResponseEntity;

import com.atlaslab.erp.ums.dto.RoleDto;
import com.atlaslab.erp.ums.dto.UserDto;
import com.atlaslab.erp.ums.entity.UserEntity;
import com.atlaslab.erp.ums.entity.UserRoleEntity;

/**
 *
 */
public interface UserService {

	/**
	 * @param userDto
	 * @return
	 */
	ResponseEntity<Object> save(UserDto userDto) throws Exception;

	/**
	 * @param userDto
	 * @return
	 */
	ResponseEntity<Object> update(UserDto userDto) throws Exception;

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
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	UserEntity getUserEntity(UserDto userDto) throws Exception;

	/**
	 * @param userEntity
	 * @param e
	 * @return
	 * @throws Exception 
	 */
	UserRoleEntity getUserRoleEntity(UserEntity userEntity, RoleDto e) throws Exception;

	/**
	 * @param entity
	 * @return
	 */
	UserDto getUserDto(UserEntity entity) throws Exception;

}
