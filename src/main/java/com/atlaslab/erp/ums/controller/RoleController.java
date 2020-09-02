/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 8:33:11 PM
 */
package com.atlaslab.erp.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atlaslab.erp.ums.dto.RoleDto;
import com.atlaslab.erp.ums.service.RoleService;

/**
 *
 */
@RestController
@RequestMapping(value = "/role")
@CrossOrigin("*")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody RoleDto roleDto) {
		try {
			return roleService.save(roleDto);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody RoleDto roleDto) {
		System.out.println(roleDto.toString());
		try {
			return roleService.update(roleDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Integer id) {
		try {
			return roleService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable(name = "id") Integer id) {
		try {
			return roleService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> getAll() {
		try {
			return roleService.getAll();
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/active")
	public ResponseEntity<Object> getAllAvtive() {
		try {
			return roleService.getAllAvtive();
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
