package org.gestion.bp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.gestion.bp.dao.IBanqueDao;
import org.gestion.bp.entities.Client;
import org.gestion.bp.entities.Compte;
import org.gestion.bp.entities.Employe;
import org.gestion.bp.entities.Groupe;
import org.gestion.bp.entities.Operation;

public class BanqueDaoImpl  implements IBanqueDao{
	/**
	 * L'objet EntityManager est responsable de la gestion des entités et de leurs états. 
	 * Il va ainsi permettre les opérations de base offertes par le langage relationnel que sont :
	 * l'ajout ;la lecture ;la mise à jour ;la suppression.
	 * 
	 * L'EntityManager est donc l'objet qui va permettre au développeur de manipuler ses objets Java 
	 * devenus des entités et ainsi lui permettre de les persister. Il est donc nécessaire d'obtenir
	 * une référence vers un objet EntityManager; cela s'effectue par l'appel à la méthode factory de
	 * la classe EntityManagerFactory, comme montré ci-dessous 
	 */
	 
	// On appel la JPA par EntityManager entityManager pour gerer la persistance
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public Client addClient(Client c) {
		entityManager.persist(c);
		return c;
	}
	@Override
	public Employe addEmploye(Employe e, Long codeSup) {
		if(codeSup!=null){
			Employe sup=entityManager.find(Employe.class, codeSup);
			e.setEmployeSup(sup);
		}
		entityManager.persist(e);
		return e;
	}

	@Override
	public Groupe addGroupe(Groupe g) {
		entityManager.persist(g);
		return g;
	}

	@Override
	public void addEmployeToGroupe(Long codeEmp, Long codeGr) {
		Employe e=entityManager.find(Employe.class,codeEmp);		
		Groupe g=entityManager.find(Groupe.class,codeGr);
		e.getGroupes().add(g);
		g.getEmployes().add(e);
	}

	@Override
	public Compte addCompte(Compte cp, Long codeCli, Long codeEmp) {
		Client cli=entityManager.find(Client.class, codeCli);
		Employe emp=entityManager.find(Employe.class, codeEmp);
		cp.setClient(cli);
		cp.setEmploye(emp);
		entityManager.persist(cp);
		return cp;
	}

	@Override
	public Operation addOperation(Operation op, String codeCpte, Long codeEmp) {
		Compte cp=consulterCompte(codeCpte);
		Employe emp=entityManager.find(Employe.class, codeEmp);
		op.setCompte(cp);
		op.setEmploye(emp);
		entityManager.persist(op);
		return op;
	}

	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cp=entityManager.find(Compte.class, codeCpte);
		if(cp==null) throw new RuntimeException("Compte "+codeCpte+" introuvable!");
		return cp ;
	}

	@Override
	public List<Operation> consulterOperations(String codeCpte,int position,int nboperation) {
		Query req=entityManager.createQuery("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation desc");
		req.setParameter("x", codeCpte);
		req.setFirstResult(position);
		req.setMaxResults(nboperation);
		return req.getResultList();
	}

	@Override
	public Client consulterClient(Long codeCli) {
		Client c=entityManager.find(Client.class, codeCli);
		if(c==null) throw new RuntimeException("Client introuvable!");
		return c ;
	}

	@Override
	public List<Client> consulterClients(String mc) {
		Query req=entityManager.createQuery("select c from Client c where c.nomClient like :x");
		req.setParameter("x","%"+ mc+"%");
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByClient(Long codeCli) {
		Query req=entityManager.createQuery("select c from Compte c where c.client.codeClient=:x");
		req.setParameter("x",codeCli);
		return req.getResultList();
	}

	@Override
	public List<Compte> getComptesByEmploye(Long codeEmp) {
		Query req=entityManager.createQuery("select c from Compte c where c.employe.codeEmploye=:x");
		req.setParameter("x",codeEmp);
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployes() {
		Query req=entityManager.createQuery("select e from Employe e");
		return req.getResultList();
	}

	@Override
	public List<Groupe> getGroupes() {
		Query req=entityManager.createQuery("select g from Groupe g");
		return req.getResultList();
	}

	@Override
	public List<Employe> getEmployesByGroupe(Long codeGr) {
		Query req=entityManager.createQuery("select e from Employe e where e.groupes.codeGroupe=:x");
		req.setParameter("x",codeGr);
		return req.getResultList();	}
	@Override
	public long getNombteOperation(String numCpte) {
		Query req=entityManager.createQuery("select count(o) from Operation o where o.compte.codeCompte=:x");
		req.setParameter("x",numCpte);
		return (Long)req.getResultList().get(0);
	}

}
