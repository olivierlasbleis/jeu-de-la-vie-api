package ol.jeudelavie.entities.structures;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ol.jeudelavie.entities.IndexCase;

@Entity
public class Structure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	@OneToMany(cascade = CascadeType.ALL)
	private List<IndexCase> listePoints;
	private String urlPhoto;
	private String typeStructure;
	private int nbVotes;
	public void ajouterUnVote() {
		this.nbVotes = this.nbVotes + 1;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<IndexCase> getListePoints() {
		return listePoints;
	}
	public void setListePoints(List<IndexCase> listePoints) {
		this.listePoints = listePoints;
	}
	public String getUrlPhoto() {
		return urlPhoto;
	}
	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}
	public String getTypeStructure() {
		return typeStructure;
	}
	public void setTypeStructure(String typeStructure) {
		this.typeStructure = typeStructure;
	}
	public Structure(String nom, List<IndexCase> listePoints, String urlPhoto, String typeStructure) {
		super();
		this.nom = nom;
		this.listePoints = listePoints;
		this.urlPhoto = urlPhoto;
		this.typeStructure = typeStructure;
		this.nbVotes = 1;
	}
	public Structure() {
		super();
		this.nbVotes = 1;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getNbVotes() {
		return nbVotes;
	}
	public void setNbVotes(int nbVotes) {
		this.nbVotes = nbVotes;
	}

}
