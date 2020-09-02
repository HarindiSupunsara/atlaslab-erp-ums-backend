/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 8:38:17 PM
 */
package com.atlaslab.erp.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atlaslab.erp.ums.entity.RolePermissionEntity;

/**
 *
 */
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, Integer> {

}
