package ol.jeudelavie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import ol.jeudelavie.entities.IndexCase;
import ol.jeudelavie.entities.structures.Structure;
import ol.jeudelavie.repositories.StructureRepository;



/**
 * Cette Classe initialise la bdd. Au demmarage de l'application, elle fait
 * appel à la methode init().
 * 
 */
@Component
public class StartupDataInit {


	@Value("${data.init}")
	private Boolean isDataInit;
	
	@Autowired
	StructureRepository structureRepository;

	@Autowired
	 private Environment env;

		
		  
	
	/**
	 * 
	 */
	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() throws Exception {
		if(isDataInit) {
			String structuresString = env.getProperty("structures.stable").toString();
			  System.out.println(structuresString);
			Gson g = new Gson(); 
			
			Structure[] p = g.fromJson(structuresString, Structure[].class);
		
				structureRepository.saveAll(Arrays.asList(p));
			
			  System.out.println(p[0].getNom());
			//structureRepository.save(new Structure("bloc", {new IndexCase(0,0),new IndexCase(0,0)}, "https://upload.wikimedia.org/wikipedia/commons/b/ba/JdlV_bloc_4.4.gif", "stable"))
		}
	}

}
