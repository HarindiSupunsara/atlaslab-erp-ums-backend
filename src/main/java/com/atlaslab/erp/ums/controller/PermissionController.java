/**
 * @author Harindi Supunsara
 * Sep 2, 2020
 * 7:43:15 PM
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

import com.atlaslab.erp.ums.dto.PermissionDto;
import com.atlaslab.erp.ums.service.PermissionService;

/**
 *
 */
@RestController
@RequestMapping(value = "/permission")
@CrossOrigin("*")
public class PermissionController {
	
	@Autowired
	private PermissionService permissionService;

	@PostMapping
	public ResponseEntity<Object> save(@RequestBody PermissionDto permissionDto) {
		try {
			return permissionService.save(permissionDto);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@RequestBody PermissionDto permissionDto) {
		try {
			return permissionService.save(permissionDto);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Object> delete(@PathVariable(name = "id") Integer id) {
		try {
			return permissionService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable(name = "id") Integer id) {
		try {
			return permissionService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping
	public ResponseEntity<Object> getAll() {
		try {
			return permissionService.getAll();
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/active")
	public ResponseEntity<Object> getAllAvtive() {
		try {
			return permissionService.getAllAvtive();
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
