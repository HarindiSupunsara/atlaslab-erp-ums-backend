/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 8:38:46 PM
 */
package com.atlaslab.erp.ums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlaslab.erp.ums.entity.RoleEntity;

/**
 *
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

	/**
	 * @param id
	 * @param active
	 * @return
	 */
	RoleEntity findFirstByIdAndStatus(Integer id, Integer active);

	/**
	 * @param active
	 * @return
	 */
	List<RoleEntity> findByStatus(Integer active);

}
