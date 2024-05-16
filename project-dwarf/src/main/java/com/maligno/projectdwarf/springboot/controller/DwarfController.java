package com.maligno.projectdwarf.springboot.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maligno.projectdwarf.springboot.exception.DwarfNotFoundException;
import com.maligno.projectdwarf.springboot.exception.IllegalAtkSpeException;
import com.maligno.projectdwarf.springboot.request.NewDwarfRequest;
import com.maligno.projectdwarf.springboot.response.DwarfListResponse;
import com.maligno.projectdwarf.springboot.response.DwarfResponse;
import com.maligno.projectdwarf.springboot.service.DwarfService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/dwarf")
public class DwarfController {

//attributes
	private final DwarfService dwarfService;
	
//operations
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping()
	public ResponseEntity<URI> addDwarf(
			@RequestBody NewDwarfRequest request
	) {
		long id;
		URI uri;
		try {
			id = dwarfService.addDwarf(request);
            URI baseUri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri();
			uri = new URI(baseUri + "api/dwarf/"+id);
		} catch (IllegalAtkSpeException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		} catch (URISyntaxException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
		}
		
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDwarf(
			@PathVariable long dwarfId
	) {
		
		try {
			dwarfService.deleteDwarf(dwarfId);
		} catch (DwarfNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find specified Dwarf. Unable to delete", e);
		}
		return ResponseEntity.ok("");
		
	}
	
	@GetMapping("/{id}")
	public DwarfResponse getDwarfById(
			@PathVariable long id
	) {
		
		DwarfResponse response;
		
		try {
			response = dwarfService.getDwarfById(id);
		} catch (DwarfNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find specified Dwarf.", e);
		}
		return response;
	}
	
	@GetMapping()
	public DwarfListResponse getAllDwarfs()
	{
		
		DwarfListResponse response;
		
		response = dwarfService.getAllDwarfs();
		
		return response;
	}
	

}
