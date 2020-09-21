package ol.jeudelavie.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ol.jeudelavie.entities.IndexCase;
import ol.jeudelavie.entities.structures.Structure;
import ol.jeudelavie.repositories.StructureRepository;

@Service
public class StructureService {

	@Autowired
	StructureRepository structureRepository;
	
	public List<Structure> getAllByTypeStructure(String typeStructure){
		
		return structureRepository.findByTypeStructureAndMaxVotes(typeStructure);
	}

	public List<String> getAllTypeStructure() {
		// TODO Auto-generated method stub
		return structureRepository.getAllTypeStructure();
	}

	public Structure updateStructure(Structure structure) {
		Integer jMinimum = structure.getListePoints().stream().map(p -> p.getJ()).min(Integer::compare).get();
		Integer iMinimum = structure.getListePoints().stream().map(p -> p.getI()).min(Integer::compare).get();
		structure.getListePoints().stream().forEach(p -> {p.setI(p.getI() - iMinimum);p.setJ(p.getJ() - jMinimum); });
		if(structureRepository.findByNom(structure.getNom()).isPresent()) {
			final Structure struc = structure;
			List<Structure> listeStructures = structureRepository.findByNom(structure.getNom()).get();
			List<Structure> listeStructureSimilaire = listeStructures.stream().filter(
					s -> isSameListeIndexCase(struc.getListePoints(),s.getListePoints())).collect(Collectors.toList());
			if(listeStructureSimilaire.size()>0) {
				structure = listeStructureSimilaire.get(0);
				structure.ajouterUnVote();
			}else {
				structure.setId(null);
			}
		}
		return structureRepository.save(structure);
	}
	
	public boolean isSameListeIndexCase(List<IndexCase> listeA, List<IndexCase> listeB) {
		for (IndexCase a : listeA) {
			if(!listeB.stream().filter(b -> b.getI()==a.getI() && b.getJ()==a.getJ()).findAny().isPresent()) {
				return false;
			}
		}
		return true;
	}
}
