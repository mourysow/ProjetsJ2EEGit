package org.gestion.bp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.gestion.bp.entities.Compte;
import org.gestion.bp.entities.Operation;
import org.gestion.bp.form.BanqueForm;
import org.gestion.bp.service.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/appi/myBanque")
public class BanqueController extends AbstractFrontController {
		
	@RequestMapping(value="/index")
	public String index(Model model){
		model.addAttribute("banqueForm", new BanqueForm());
		return "banque";
	}
	
	@RequestMapping(value="/chargerCompte")
	public String charger(@Valid BanqueForm banqueForm, 
			BindingResult bindingResult,Model model){
		
		if(bindingResult.hasErrors()){
			return "banque";
		}
		chargerCompte(banqueForm);
		model.addAttribute("banqueForm", banqueForm);
		return "banque";
	}
	
	@RequestMapping(value="/saveOperation")
	public String saveOp(@Valid BanqueForm banqueForm,BindingResult bindingResult){
		try {
			if(bindingResult.hasErrors()){
				return "banque";
			}
			if(banqueForm.getAction()!=null){
			if(banqueForm.getTypeOperation().equals("VER")){
				banqueService.verser(banqueForm.getMontant(), banqueForm.getCode(), 1L);
			}
			else if(banqueForm.getTypeOperation().equals("RET")){
				banqueService.retirer(banqueForm.getMontant(), banqueForm.getCode(), 1L);
			}
			else if(banqueForm.getTypeOperation().equals("VIR")){
				banqueService.virement(banqueForm.getMontant(), banqueForm.getCode(), banqueForm.getCode2(), 1L);
			}
			}
		} catch (Exception e) {
			banqueForm.setException(e.getMessage());
		}
			chargerCompte(banqueForm);
		
		return "banque";
	}
	
	public void chargerCompte(BanqueForm bf){
			try {
				Compte cp=banqueService.consulterCompte(bf.getCode());
				bf.setTypeCompte(cp.getClass().getSimpleName());
				bf.setCompte(cp);
				int pos=bf.getNbLignes()*bf.getPage();
				List<Operation> ops=banqueService.consulterOperations(bf.getCode(),pos,bf.getNbLignes());
				bf.setOperations(ops);
				long nbOp=banqueService.getNombteOperation(bf.getCode());
				bf.setNombrePages((int)(nbOp/bf.getNbLignes())+1);
			} catch (Exception e) {
				bf.setException(e.getMessage());
			}
	}
	

}
