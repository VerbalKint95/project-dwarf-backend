package com.maligno.projectdwarf.springboot.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DwarfResponse {

//attributes

	private long id;

	private String name;
	
	private String bio;
	
	//stats
	private long health;
	
	private long strength;
	
	private long agility;
	
	private long stamina;
	
	private long highAtk;
	
	private long lowAtk;
	
	private long highDef;
	
	private long lowDef;
	
	private String atkSpe;
}
