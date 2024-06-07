package com.maligno.projectdwarf.springboot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.maligno.projectdwarf.springboot.exception.DwarfNotFoundException;
import com.maligno.projectdwarf.springboot.exception.IllegalAtkSpeException;
import com.maligno.projectdwarf.springboot.model.AtkSpe;
import com.maligno.projectdwarf.springboot.model.Dwarf;
import com.maligno.projectdwarf.springboot.repository.DwarfRepository;
import com.maligno.projectdwarf.springboot.request.NewDwarfRequest;
import com.maligno.projectdwarf.springboot.response.DwarfListResponse;
import com.maligno.projectdwarf.springboot.response.DwarfResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DwarfService {
	
//attributes
	private final DwarfRepository dwarfRepository;

	
//operations
	
	public long addDwarf(NewDwarfRequest request) throws IllegalAtkSpeException {
		Dwarf d = Dwarf.builder()
				.name(request.getName())
				.bio(request.getBio())
				.agility(request.getAgility())
				.health(request.getHealth())
				.highAtk(request.getHighAtk())
				.highDef(request.getHighDef())
				.lowAtk(request.getLowAtk())
				.lowDef(request.getLowDef())
				.stamina(request.getStamina())
				.strength(request.getStrength())
				.atkSpe(AtkSpe.fromString(request.getAtkSpe()))
			.build();
		saveDwarf(d);
		return saveDwarf(d).getId();
	}
	
	


	public void deleteDwarf(long dwarfId) throws DwarfNotFoundException {
		Dwarf d = findByID(dwarfId);
		dwarfRepository.delete(d);
	}


	public DwarfResponse getDwarfById(long dwarfId) throws DwarfNotFoundException {
		Dwarf d = findByID(dwarfId);
		DwarfResponse response = createDwarfResponseFromDwarf(d);
		return response;
	}
	
	public DwarfListResponse getAllDwarfs() {
		List<Dwarf> dwarfs = findAll();
		DwarfListResponse response = new DwarfListResponse();
		response.setDwarfResponses(new ArrayList<DwarfResponse>());
		for (Dwarf d:dwarfs) {
			response.getDwarfResponses().add(createDwarfResponseFromDwarf(d));
		}
		return response;
	}

	
	//repository operations	
	private List<Dwarf> findAll(){
		return dwarfRepository.findAll();
	}
	
	public Dwarf findByID(long id) throws DwarfNotFoundException {
		Optional<Dwarf> optionalDwarf = dwarfRepository.findById(id);
		Dwarf dwarf = optionalDwarf.orElseThrow(() -> new DwarfNotFoundException() );
		return dwarf;
	}
	
	private Dwarf saveDwarf(Dwarf d) {
		return dwarfRepository.save(d);
	}


	//response operation
	private DwarfResponse createDwarfResponseFromDwarf(Dwarf d) {
		DwarfResponse response = DwarfResponse.builder()
				.id(d.getId())
				.name(d.getName())
				.bio(d.getBio())
				.agility(d.getAgility())
				.health(d.getHealth())
				.highAtk(d.getHighAtk())
				.highDef(d.getHighDef())
				.lowAtk(d.getLowAtk())
				.lowDef(d.getLowDef())
				.stamina(d.getStamina())
				.strength(d.getStrength())
				.atkSpe(d.getAtkSpe().toString())
			.build();
		return response;
	}


	
}
