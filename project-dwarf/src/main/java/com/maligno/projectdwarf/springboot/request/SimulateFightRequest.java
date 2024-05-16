package com.maligno.projectdwarf.springboot.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SimulateFightRequest {

//attributes
	private int nbOfRound;
	private long dwarf1Id;
	private long dwarf2Id;
}
