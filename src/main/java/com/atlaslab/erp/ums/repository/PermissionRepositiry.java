/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 7:53:49 PM
 */
package com.atlaslab.erp.ums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlaslab.erp.ums.entity.PermissionEntity;

/**
 *
 */
public interface PermissionRepositiry extends JpaRepository<PermissionEntity, Integer>{

	/**
	 * @param active
	 * @return
	 */
	List<PermissionEntity> findByStatus(Integer active);

	/**
	 * @param id
	 * @param active
	 * @return
	 */
	PermissionEntity findFirstByIdAndStatus(Integer id, Integer active);

}
