package ol.jeudelavie.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ol.jeudelavie.entities.Case;
import ol.jeudelavie.entities.IndexCase;
import ol.jeudelavie.entities.TableauPost;
import ol.jeudelavie.entities.structures.Structure;
import ol.jeudelavie.services.AllService;
import ol.jeudelavie.services.JeuService;

@RestController
@RequestMapping("jeu")
public class JeuController {
	
	@Autowired
	JeuService jeuService;
	
	@Autowired
	AllService allService;
	
	@GetMapping
	public String getTest() {
		return "ok";
	}
	
	@PostMapping("/cases")
	public Case[][] getCalculJeuDeLaVieCase(@RequestBody Case[][] situationInitiale){
		
		return jeuService.calculJeuDeLaVieCase(situationInitiale);
	}
	
	@PostMapping("/index")
	public List<IndexCase> getCalculJeuDeLaVieIndex(@RequestBody TableauPost situationInitiale){
		Set set = new HashSet();
		set.addAll(situationInitiale.getListeIndex());
		List<IndexCase> listeIndexSansDoublons= new ArrayList(set);
		situationInitiale.setListeIndex(listeIndexSansDoublons);
		List<IndexCase> listeIndexCasesRetour = jeuService.calculJeuDeLaVieIndex(situationInitiale);
		if(!listeIndexCasesRetour.equals(situationInitiale.getListeIndex())){
			return listeIndexCasesRetour;
		}else {
			listeIndexCasesRetour.add(0,new IndexCase(1000,1000));
			return listeIndexCasesRetour;
		}
		
		
	}

}
