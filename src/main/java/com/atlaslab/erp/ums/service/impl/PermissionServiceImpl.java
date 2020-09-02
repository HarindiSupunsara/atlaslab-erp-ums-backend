/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 7:48:34 PM
 */
package com.atlaslab.erp.ums.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atlaslab.erp.ums.dto.PermissionDto;
import com.atlaslab.erp.ums.entity.PermissionEntity;
import com.atlaslab.erp.ums.repository.PermissionRepositiry;
import com.atlaslab.erp.ums.service.PermissionService;
import com.atlaslab.erp.ums.util.AppConstant;

/**
 *
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepositiry permissionRepositiry;

	@Override
	public ResponseEntity<Object> save(PermissionDto permissionDto) throws Exception {
		PermissionEntity permissionEntity = getPermissionEntity(permissionDto);
		if (permissionRepositiry.save(permissionEntity) != null) {
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		}
		return new ResponseEntity<Object>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public PermissionEntity getPermissionEntity(PermissionDto permissionDto) {
		PermissionEntity entity = null;
		if (permissionDto.getId() != null) {
			entity = permissionRepositiry.findById(permissionDto.getId()).get();
		}

		if (entity == null) {
			entity = new PermissionEntity();
			entity.setCreateDate(new Date());
		}

		entity.setName(permissionDto.getName());
		entity.setStatus(AppConstant.ACTIVE);
		entity.setUpdateDate(new Date());

		return entity;
	}

	@Override
	public ResponseEntity<Object> delete(Integer id) throws Exception {
		PermissionEntity entity = permissionRepositiry.findById(id).get();
		if (entity == null) {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		} else {
			if (entity.getStatus().equals(AppConstant.ACTIVE)) {
				entity.setStatus(AppConstant.INACTIVE);
			} else {
				entity.setStatus(AppConstant.ACTIVE);
			}
			if (permissionRepositiry.save(entity) != null) {
				return new ResponseEntity<>("Success", HttpStatus.CREATED);
			}
			return new ResponseEntity<Object>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Object> findById(Integer id) throws Exception {
		PermissionEntity entity = permissionRepositiry.findFirstByIdAndStatus(id, AppConstant.ACTIVE);
		if (entity == null) {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		} else {
			PermissionDto permissionDto = getPermissionDto(entity);
			return new ResponseEntity<Object>(permissionDto, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Object> getAll() throws Exception {
		List<PermissionEntity> permissionEntities = permissionRepositiry.findAll();

		List<PermissionDto> permissionDtos = new ArrayList<PermissionDto>();

		permissionEntities.forEach(e -> {
			permissionDtos.add(getPermissionDto(e));
		});
		return new ResponseEntity<Object>(permissionDtos, HttpStatus.OK);
	}

	@Override
	public PermissionDto getPermissionDto(PermissionEntity entity) {
		PermissionDto dto = new PermissionDto();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setCreateDate(entity.getCreateDate());
		return dto;
	}

	@Override
	public ResponseEntity<Object> getAllAvtive() throws Exception {
		List<PermissionEntity> permissionEntities = permissionRepositiry.findByStatus(AppConstant.ACTIVE);

		List<PermissionDto> permissionDtos = new ArrayList<PermissionDto>();

		permissionEntities.forEach(e -> {
			permissionDtos.add(getPermissionDto(e));
		});
		return new ResponseEntity<Object>(permissionDtos, HttpStatus.OK);
	}

}
