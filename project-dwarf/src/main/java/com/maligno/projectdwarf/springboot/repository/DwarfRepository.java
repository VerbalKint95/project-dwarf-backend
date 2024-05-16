package com.maligno.projectdwarf.springboot.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.maligno.projectdwarf.springboot.model.Dwarf;

public interface DwarfRepository extends JpaRepository<Dwarf, Long>{
	
//find
	Optional<Dwarf> findById(long id);
	Optional<Dwarf> findByName(String name);
	
	

}
