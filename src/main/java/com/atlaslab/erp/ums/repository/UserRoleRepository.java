/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 9:53:03 PM
 */
package com.atlaslab.erp.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlaslab.erp.ums.entity.UserRoleEntity;

/**
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {

}
