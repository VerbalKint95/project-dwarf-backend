package com.maligno.projectdwarf.springboot.fight;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FightResult {

	
//attributes
	private String msg;
	private long dwarf1Id;
	private String dwarf1Name;
	private long dwarf2Id;
	private String dwarf2Name;
	private FightStatus status;
	private int nbRound;
	
}
