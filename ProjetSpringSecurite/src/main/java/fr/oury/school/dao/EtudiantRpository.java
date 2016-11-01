package fr.oury.sow.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.oury.sow.entities.Etudiant;

public interface EtudiantRpository extends JpaRepository<Etudiant, Long>{
	

}
