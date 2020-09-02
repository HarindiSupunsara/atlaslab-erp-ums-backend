/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 9:51:52 PM
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

import com.atlaslab.erp.ums.dto.RoleDto;
import com.atlaslab.erp.ums.dto.UserDto;
import com.atlaslab.erp.ums.entity.UserEntity;
import com.atlaslab.erp.ums.entity.UserRoleEntity;
import com.atlaslab.erp.ums.repository.RoleRepository;
import com.atlaslab.erp.ums.repository.UserRepository;
import com.atlaslab.erp.ums.repository.UserRoleRepository;
import com.atlaslab.erp.ums.service.RoleService;
import com.atlaslab.erp.ums.service.UserService;
import com.atlaslab.erp.ums.util.AppConstant;

/**
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Override
	public ResponseEntity<Object> save(UserDto userDto) throws Exception {
		UserEntity tempUserEntity = getUserEntity(userDto);
		List<UserRoleEntity> userRoleEntities = new ArrayList<UserRoleEntity>();
		UserEntity userEntity = userRepository.save(tempUserEntity);
		if (userEntity != null) {
			userDto.getRoleDtos().forEach(e -> {
				try {
					userRoleEntities.add(getUserRoleEntity(userEntity, e));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			});
			if (userRoleRepository.saveAll(userRoleEntities) != null) {
				return new ResponseEntity<>("Success", HttpStatus.CREATED);
			}
		}

		return new ResponseEntity<Object>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> update(UserDto userDto) throws Exception {
		System.out.println(userDto.toString());
		UserEntity userEntity = getUserEntity(userDto);
		if (userEntity != null) {
			List<UserRoleEntity> existList = userEntity.getUserRoleEntities();
			List<UserRoleEntity> currentExistList = new ArrayList<UserRoleEntity>();
			List<UserRoleEntity> newList = new ArrayList<UserRoleEntity>();

			existList.forEach(exist -> {
				boolean isAvailable = false;
				for (RoleDto e : userDto.getRoleDtos()) {
					if (exist.getRoleEntity().getId().equals(e.getId())) {
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

			userDto.getRoleDtos().forEach(e -> {
				boolean isAvailable = false;
				for (UserRoleEntity userRoleEntity : existList) {
					if (userRoleEntity.getRoleEntity().getId().equals(e.getId())) {
						isAvailable = true;
					}
				}
				if (!isAvailable) {
					try {
						newList.add(getUserRoleEntity(userEntity, e));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			});

			if (userRepository.save(userEntity) != null) {
				if (userRoleRepository.saveAll(currentExistList) != null) {
					if (userRoleRepository.saveAll(newList) != null) {
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
		UserEntity entity = userRepository.findById(id).get();
		if (entity == null) {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		} else {
			if (entity.getStatus().equals(AppConstant.ACTIVE)) {
				entity.setStatus(AppConstant.INACTIVE);
			} else {
				entity.setStatus(AppConstant.ACTIVE);
			}
			if (userRepository.save(entity) != null) {
				return new ResponseEntity<>("Success", HttpStatus.CREATED);
			}
			return new ResponseEntity<Object>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Object> findById(Integer id) throws Exception {
		UserEntity entity = userRepository.findFirstByIdAndStatus(id, AppConstant.ACTIVE);
		if (entity == null) {
			return new ResponseEntity<Object>("Not Found", HttpStatus.NOT_FOUND);
		} else {
			UserDto userDto = getUserDto(entity);
			return new ResponseEntity<Object>(userDto, HttpStatus.OK);
		}
	}

	@Override
	public UserDto getUserDto(UserEntity entity) throws Exception {
		UserDto userDto = new UserDto();
		userDto.setAddress1(entity.getAddress1());
		userDto.setAddress2(entity.getAddress2());
		userDto.setAddress3(entity.getAddress3());
		userDto.setCreateDate(entity.getCreateDate());
		userDto.setId(entity.getId());
		userDto.setMobile(entity.getMobile());
		userDto.setName(entity.getName());
		userDto.setNic(entity.getNic());
		userDto.setStatus(entity.getStatus());
		userDto.setUpdateDate(entity.getUpdateDate());

		List<RoleDto> roleDtos = new ArrayList<>();

		entity.getUserRoleEntities().forEach(e -> {
			if (e.getStatus().equals(AppConstant.ACTIVE)) {
				try {
					roleDtos.add(roleService.getRoleDto(e.getRoleEntity()));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		userDto.setRoleDtos(roleDtos);

		return userDto;
	}

	@Override
	public ResponseEntity<Object> getAll() throws Exception {
		List<UserEntity> userEntities = userRepository.findAll();

		List<UserDto> userDtos = new ArrayList<UserDto>();

		userEntities.forEach(e -> {
			try {
				userDtos.add(getUserDto(e));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		return new ResponseEntity<Object>(userDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllAvtive() throws Exception {
		List<UserEntity> userEntities = userRepository.findByStatus(AppConstant.ACTIVE);

		List<UserDto> userDtos = new ArrayList<UserDto>();

		userEntities.forEach(e -> {
			try {
				userDtos.add(getUserDto(e));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		return new ResponseEntity<Object>(userDtos, HttpStatus.OK);
	}

	@Override
	public UserRoleEntity getUserRoleEntity(UserEntity userEntity, RoleDto e) throws Exception {
		UserRoleEntity entity = new UserRoleEntity();
		entity.setCreateDate(new Date());
		entity.setStatus(AppConstant.ACTIVE);
		entity.setUpdateDate(new Date());
		entity.setUserEntity(userEntity);
		entity.setRoleEntity(roleRepository.findById(e.getId()).get());

		return entity;
	}

	@Override
	public UserEntity getUserEntity(UserDto userDto) throws Exception {
		UserEntity entity = null;
		if (userDto.getId() != null) {
			entity = userRepository.findById(userDto.getId()).get();
		}

		if (entity == null) {
			entity = new UserEntity();
			entity.setCreateDate(new Date());
		}

		entity.setAddress1(userDto.getAddress1());
		entity.setAddress2(userDto.getAddress2());
		entity.setAddress3(userDto.getAddress3());
		entity.setMobile(userDto.getMobile());
		entity.setName(userDto.getName());
		entity.setNic(userDto.getNic());
		entity.setStatus(AppConstant.ACTIVE);
		entity.setUpdateDate(new Date());

		return entity;
	}

}
