package com.maligno.projectdwarf.springboot.service;


import org.springframework.stereotype.Service;

import com.maligno.projectdwarf.springboot.exception.DwarfNotFoundException;
import com.maligno.projectdwarf.springboot.fight.Fight;
import com.maligno.projectdwarf.springboot.request.SimulateFightRequest;
import com.maligno.projectdwarf.springboot.response.SimulateFightResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FightService {
	
//attributes
	private final DwarfService dwarfService;
	
//operations
	public SimulateFightResponse simulate(SimulateFightRequest request) throws DwarfNotFoundException {
		var d1 = dwarfService.findByID(request.getDwarf1Id());
		var d2 = dwarfService.findByID(request.getDwarf2Id());
		int nbRound = request.getNbOfRound();
		Fight fight = new Fight(d1, d2);
		var result = fight.fight(nbRound);
		
		SimulateFightResponse response = SimulateFightResponse.builder()
				.dwarf1Id(result.getDwarf1Id())
				.dwarf1Name(result.getDwarf1Name())
				.dwarf2Id(result.getDwarf2Id())
				.dwarf2Name(result.getDwarf2Name())
				.status(result.getStatus().name())
				.msg(result.getMsg())
				.nbRound(result.getNbRound())
			.build();
		
		return response;
	}

}		
