package fr.oury.sow;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.oury.sow.dao.EtudiantRpository;
import fr.oury.sow.dao.RoleRepository;
import fr.oury.sow.dao.UserRepository;
import fr.oury.sow.entities.Etudiant;
import fr.oury.sow.entities.Role;
import fr.oury.sow.entities.User;

//tst,

//@ComponentScan("fr.oury.sow.app")
@SpringBootApplication
public class Application {

	public static void main(String[] args) throws ParseException {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		
		EtudiantRpository etudiantRpository = ctx.getBean(EtudiantRpository.class);
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    // enregistrer ses données dans la table etudiant a chaque execution
		//etudiantRpository.save(new Etudiant("diallo", "jul", df.parse("2016-10-03")));
		//etudiantRpository.save(new Etudiant("Bahnj", "Adrr", df.parse("2016-11-03")));
	   // etudiantRpository.delete(new Etudiant(230L));
		
		userRepository.save(new User("ibra", "124", true));
	
		List<Etudiant> lEtudiants = etudiantRpository.findAll();
		
		//lEtudiants.forEach(e->System.out.println("Nom: "+e.getNom()+"Prenoom: "+e.getPrenom()+"Date naissance: "+e.getDateNaissance()));	    
	  for (Etudiant etudiant : lEtudiants) {
		   System.out.println("Nom: "+etudiant.getNom()+" Prenom: "+etudiant.getPrenom());
		
	      }
	    List<User> lUser = userRepository.findAll();
	    
	    for (User user : lUser) {
			   System.out.println("Nom: "+user.getUsername()+" Prenom: "+user.getPassword());
			
		      }
	    
	    // on ajoute un role a un User
	    List<Role> lRole = new ArrayList<>(); 
	    lRole.add(new Role("admin", "nouveau role"));
	    userRepository.save(new User("ibra", lRole));
	    
	}
}
