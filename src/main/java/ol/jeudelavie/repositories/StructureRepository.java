package ol.jeudelavie.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ol.jeudelavie.entities.IndexCase;
import ol.jeudelavie.entities.structures.Structure;

public interface StructureRepository extends JpaRepository<Structure, Integer>{

	List<Structure> findByTypeStructure(String typeStructure);

	@Query(value = "SELECT m.*                    -- get the row that contains the max value\r\n" + 
			"FROM structure m                 -- \"m\" from \"max\"\r\n" + 
			"    LEFT JOIN structure b        -- \"b\" from \"bigger\"\r\n" + 
			"        ON m.NOM= b.NOM-- match \"max\" row with \"bigger\" row by `home`\r\n" + 
			"        AND m.NB_VOTES< b.NB_VOTES-- want \"bigger\" than \"max\"\r\n" + 
			"WHERE b.NB_VOTES IS NULL  -- keep only if there is no bigger than max\r\n" + 
			"AND m.TYPE_STRUCTURE= :typeStructure "
			+ "ORDER BY m.NOM", 
	  nativeQuery = true)
	List<Structure> findByTypeStructureAndMaxVotes(@Param("typeStructure") String typeStructure);
	
	@Query(value = "SELECT DISTINCT(TYPE_STRUCTURE) FROM STRUCTURE", 
			  nativeQuery = true)
	List<String> getAllTypeStructure();


	Optional<List<Structure>> findByNom(String nom);

	


}
