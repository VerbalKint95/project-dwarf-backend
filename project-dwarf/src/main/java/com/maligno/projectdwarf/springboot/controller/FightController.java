package com.maligno.projectdwarf.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maligno.projectdwarf.springboot.exception.DwarfNotFoundException;
import com.maligno.projectdwarf.springboot.request.SimulateFightRequest;
import com.maligno.projectdwarf.springboot.response.SimulateFightResponse;
import com.maligno.projectdwarf.springboot.service.FightService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/api/fight")
public class FightController {

	//attributes
	private final FightService fightService;
	
	
	@PostMapping()
	public ResponseEntity<SimulateFightResponse> simulate(
			@RequestBody SimulateFightRequest simulateFightRequest
		){
		SimulateFightResponse response;
		try {
			response = fightService.simulate(simulateFightRequest);
		} catch (DwarfNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Dwarf not found", e);
		}
		return ResponseEntity.ok(response);
	}
	
}
