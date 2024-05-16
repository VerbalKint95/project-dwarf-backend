package com.maligno.projectdwarf.springboot.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimulateFightResponse {

//attributes
	private String msg;
	private long dwarf1Id;
	private String dwarf1Name;
	private long dwarf2Id;
	private String dwarf2Name;
	private String status;
	private int nbRound;
	
}
