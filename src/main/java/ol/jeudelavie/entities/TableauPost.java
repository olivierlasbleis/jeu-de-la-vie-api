package ol.jeudelavie.entities;

import java.util.List;

public class TableauPost {
	
	private List<IndexCase> listeIndex;
	private TailleTableau tailleTableau;
	
	public TailleTableau getTailleTableau() {
		return tailleTableau;
	}
	public void setTailleTableau(TailleTableau tailleTableau) {
		this.tailleTableau = tailleTableau;
	}
	public List<IndexCase> getListeIndex() {
		return listeIndex;
	}
	public void setListeIndex(List<IndexCase> listeIndexCasesVivantes) {
		this.listeIndex = listeIndexCasesVivantes;
	}
	public TableauPost(TailleTableau tailleTableau, List<IndexCase> listeIndex) {
		super();
		this.tailleTableau = tailleTableau;
		this.listeIndex = listeIndex;
	}
	public TableauPost() {
		super();
	}

}
