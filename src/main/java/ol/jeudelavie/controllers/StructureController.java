package ol.jeudelavie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ol.jeudelavie.entities.IndexCase;
import ol.jeudelavie.entities.TableauPost;
import ol.jeudelavie.entities.structures.Structure;
import ol.jeudelavie.services.StructureService;

@RestController
@RequestMapping("structure")
public class StructureController {

	@Autowired
	StructureService structureService;
	
	@GetMapping("{typeStructure}")
	public List<Structure> getStructures(@PathVariable(value="typeStructure") String typeStructure) {
		return structureService.getAllByTypeStructure(typeStructure);
	}
	
	@GetMapping("/allType")
	public List<String> getAllType() {
		return structureService.getAllTypeStructure();
	}
	
	@GetMapping("/all")
	public List<Structure> getAll() {
		return structureService.getAllStructure();
	}
	
	@PostMapping("/addStructure")
	public Structure addStructure(@RequestBody Structure structure){
		
		return structureService.save(structure);
	}
	
	@PostMapping("/new")
	public Structure getCalculJeuDeLaVieIndex(@RequestBody Structure structure){
		
		return structureService.updateStructure(structure);
	}
}
