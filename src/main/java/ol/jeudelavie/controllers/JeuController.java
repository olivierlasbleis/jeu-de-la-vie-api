package ol.jeudelavie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ol.jeudelavie.entities.Case;
import ol.jeudelavie.entities.IndexCase;
import ol.jeudelavie.entities.TableauPost;
import ol.jeudelavie.services.JeuService;

@RestController
@RequestMapping("jeu")
public class JeuController {
	
	@Autowired
	JeuService jeuService;
	
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
		
		return jeuService.calculJeuDeLaVieIndex(situationInitiale);
	}

}
