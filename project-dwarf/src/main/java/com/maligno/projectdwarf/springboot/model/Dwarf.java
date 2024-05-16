package com.maligno.projectdwarf.springboot.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dwarfs")
public class Dwarf {

//attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
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
	
	@Enumerated(EnumType.STRING)
	private AtkSpe atkSpe;
	
	
}
