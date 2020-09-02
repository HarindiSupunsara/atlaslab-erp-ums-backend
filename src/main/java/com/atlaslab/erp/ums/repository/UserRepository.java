/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 9:52:34 PM
 */
package com.atlaslab.erp.ums.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlaslab.erp.ums.entity.UserEntity;

/**
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	/**
	 * @param id
	 * @param active
	 * @return
	 */
	UserEntity findFirstByIdAndStatus(Integer id, Integer active);

	/**
	 * @param active
	 * @return
	 */
	List<UserEntity> findByStatus(Integer active);

}
