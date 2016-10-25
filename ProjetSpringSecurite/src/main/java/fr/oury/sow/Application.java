package fr.oury.sow;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.oury.sow.dao.EtudiantRpository;
import fr.oury.sow.entities.Etudiant;

//tst,

//@ComponentScan("fr.oury.sow.app")
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		EtudiantRpository etudiantRpository = ctx.getBean(EtudiantRpository.class);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
		etudiantRpository.save(new Etudiant("diallo", "jul", df.parse("2016-10-03")));
		etudiantRpository.save(new Etudiant("Bah", "Adra", df.parse("2016-11-03")));
		etudiantRpository.save(new Etudiant("Sall", "Aicha", df.parse("2016-11-01")));
		etudiantRpository.save(new Etudiant("Sow", "Mamadou", df.parse("2016-11-05")));

		List<Etudiant> lEtudiants = etudiantRpository.findAll();
		
		//lEtudiants.forEach(e->System.out.println("Nom: "+e.getNom()+"Prenoom: "+e.getPrenom()+"Date naissance: "+e.getDateNaissance()));	    
	  for (Etudiant etudiant : lEtudiants) {
		   System.out.println("Nom: "+etudiant.getNom()+" Prenom: "+etudiant.getPrenom());
		
	      }
	
	}
}
