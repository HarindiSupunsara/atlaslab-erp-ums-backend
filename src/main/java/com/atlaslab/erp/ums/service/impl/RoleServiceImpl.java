/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 8:37:21 PM
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
import com.atlaslab.erp.ums.dto.RoleDto;
import com.atlaslab.erp.ums.entity.RoleEntity;
import com.atlaslab.erp.ums.entity.RolePermissionEntity;
import com.atlaslab.erp.ums.repository.PermissionRepositiry;
import com.atlaslab.erp.ums.repository.RolePermissionRepository;
import com.atlaslab.erp.ums.repository.RoleRepository;
import com.atlaslab.erp.ums.service.PermissionService;
import com.atlaslab.erp.ums.service.RoleService;
import com.atlaslab.erp.ums.util.AppConstant;

/**
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@Autowired
	private PermissionRepositiry permissionRepositiry;

	@Autowired
	private PermissionService permissionService;

	@Override
	public ResponseEntity<Object> save(RoleDto roleDto) throws Exception {
		RoleEntity tempRoleEntity = getRoleEntity(roleDto);
		List<RolePermissionEntity> rolePermissionEntities = new ArrayList<RolePermissionEntity>();
		RoleEntity roleEntity = roleRepository.save(tempRoleEntity);
		if (roleEntity != null) {
			roleDto.getPermissionDtos().forEach(e -> {
				try {
					rolePermissionEntities.add(getRolePermissionEntity(roleEntity, e));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			if (rolePermissionRepository.saveAll(rolePermissionEntities) != null) {
				return new ResponseEntity<>("Success", HttpStatus.CREATED);
			}
		}

		return new ResponseEntity<Object>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public RolePermissionEntity getRolePermissionEntity(RoleEntity roleEntity, PermissionDto e) throws Exception {

		RolePermissionEntity entity = new RolePermissionEntity();

		entity.setCreateDate(new Date());
		entity.setPermissionEntity(permissionRepositiry.findById(e.getId()).get());
		entity.setRoleEntity(roleEntity);
		entity.setStatus(AppConstant.ACTIVE);
		entity.setUpdateDate(new Date());

		return entity;
	}

	@Override
	public RoleEntity getRoleEntity(RoleDto roleDto) throws Exception {

		RoleEntity entity = null;
		if (roleDto.getId() != null) {
			entity = roleRepository.findById(roleDto.getId()).get();
		}

		if (entity == null) {
			entity = new RoleEntity();
			entity.setCreateDate(new Date());
		}

		entity.setName(roleDto.getName());
		entity.setStatus(AppConstant.ACTIVE);
		entity.setUpdateDate(new Date());

		return entity;
	}

	@Override
	public ResponseEntity<Object> update(RoleDto roleDto) throws Exception {
		RoleEntity roleEntity = getRoleEntity(roleDto);
		if (roleEntity != null) {
			List<RolePermissionEntity> existList = roleEntity.getRolePermissionEntities();
			List<RolePermissionEntity> newList = new ArrayList<RolePermissionEntity>();
			List<RolePermissionEntity> currentExistList = new ArrayList<RolePermissionEntity>();

			existList.forEach(exist -> {
				boolean isAvailable = false;
				for (PermissionDto e : roleDto.getPermissionDtos()) {
					if (exist.getPermissionEntity().getId().equals(e.getId())) {
						isAvailable = true;
					}
				}
				if (!isAvailable) {
					exist.setStatus(AppConstant.INACTIVE);
				} else {
					exist.setStatus(AppConstant.ACTIVE);
				}
				currentExistList.add(exist);
			});

			roleDto.getPermissionDtos().forEach(e -> {
				boolean isAvailable = false;
				for (RolePermissionEntity rolePermissionEntity : existList) {
					if (rolePermissionEntity.getPermissionEntity().getId().equals(e.getId())) {
						isAvailable = true;
					}
				}
				if (!isAvailable) {
					try {
						newList.add(getRolePermissionEntity(roleEntity, e));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			});
			if (roleRepository.save(roleEntity) != null) {
				if (rolePermissionRepository.saveAll(currentExistList) != null) {
					if (rolePermissionRepository.saveAll(newList) != null) {
						return new ResponseEntity<>("Success", HttpStatus.CREATED);
					}
				}
			}

		} else {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> delete(Integer id) throws Exception {
		RoleEntity entity = roleRepository.findById(id).get();
		if (entity == null) {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		} else {
			if (entity.getStatus().equals(AppConstant.ACTIVE)) {
				entity.setStatus(AppConstant.INACTIVE);
			} else {
				entity.setStatus(AppConstant.ACTIVE);
			}
			if (roleRepository.save(entity) != null) {
				return new ResponseEntity<>("Success", HttpStatus.CREATED);
			}
			return new ResponseEntity<Object>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Object> findById(Integer id) throws Exception {
		RoleEntity entity = roleRepository.findFirstByIdAndStatus(id, AppConstant.ACTIVE);
		if (entity == null) {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		} else {
			RoleDto roleDto = getRoleDto(entity);
			return new ResponseEntity<Object>(roleDto, HttpStatus.OK);
		}
	}

	@Override
	public RoleDto getRoleDto(RoleEntity entity) {
		RoleDto dto = new RoleDto();
		dto.setCreateDate(entity.getCreateDate());
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setStatus(entity.getStatus());
		dto.setUpdateDate(entity.getUpdateDate());

		List<PermissionDto> permissionDtos = new ArrayList<>();
		entity.getRolePermissionEntities().forEach(e -> {
			if (e.getStatus().equals(AppConstant.ACTIVE)) {
				try {
					permissionDtos.add(permissionService.getPermissionDto(e.getPermissionEntity()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		dto.setPermissionDtos(permissionDtos);

		return dto;
	}

	@Override
	public ResponseEntity<Object> getAll() throws Exception {
		List<RoleEntity> roleEntities = roleRepository.findAll();

		List<RoleDto> roleDtos = new ArrayList<RoleDto>();

		roleEntities.forEach(e -> {
			roleDtos.add(getRoleDto(e));
		});
		return new ResponseEntity<Object>(roleDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllAvtive() throws Exception {
		List<RoleEntity> roleEntities = roleRepository.findByStatus(AppConstant.ACTIVE);

		List<RoleDto> roleDtos = new ArrayList<RoleDto>();

		roleEntities.forEach(e -> {
			roleDtos.add(getRoleDto(e));
		});
		return new ResponseEntity<Object>(roleDtos, HttpStatus.OK);
	}

}
