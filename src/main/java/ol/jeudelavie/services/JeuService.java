package ol.jeudelavie.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import ol.jeudelavie.entities.Case;
import ol.jeudelavie.entities.IndexCase;
import ol.jeudelavie.entities.TableauPost;

@Service
public class JeuService {
	
	private static int[][] configPossibles = new int[][]{{-1,-1},{-1,0},{-1,+1},{0,-1},{0,+1},{+1,-1},{+1,0},{+1,+1}};

	@Autowired
	Environment env;
	
	@Autowired
	AllService allService;
	
	public String[] getListStructures(){
		return env.getProperty("structures.stable").split(",");
	}
	
	public Case[][] calculJeuDeLaVieCase(Case[][] situationInitiale){
		Case[][] situationFinale = new Case[situationInitiale.length][situationInitiale.length];
		for (int i = 1; i < situationInitiale.length-1; i++) {
			for (int j = 1; j < situationInitiale[i].length-1; j++) {
				int somme = situationInitiale[i-1][j].getValue()
						+ situationInitiale[i][j-1].getValue()
						+ situationInitiale[i-1][j-1].getValue()
						+ situationInitiale[i+1][j-1].getValue()
						+ situationInitiale[i-1][j+1].getValue()
						+ situationInitiale[i+1][j].getValue()
						+ situationInitiale[i][j+1].getValue()
						+ situationInitiale[i+1][j+1].getValue();
			if (situationInitiale[i][j].getValue() == 1 && (somme == 2 || somme == 3)) {
						situationFinale[i][j].setValue(1);
			}else if(situationInitiale[i][j].getValue() == 0 && somme == 3) {
				situationFinale[i][j].setValue(1);
			}else {
				situationFinale[i][j].setValue(0);
			}
			}
		}
		return situationFinale;
	}
	
	public List<IndexCase> calculJeuDeLaVieIndex(TableauPost tableauPost){
		List<Case> listeCasesAVerifier = new ArrayList<>();
		List<IndexCase> listeCasesVivantesVerif = new ArrayList<>(tableauPost.getListeIndex());
		for (IndexCase indexCase : tableauPost.getListeIndex()) {
			listeCasesAVerifier.add(new Case(
					new IndexCase(indexCase.getI() ,
									indexCase.getJ() ),1));
			
			for (int i = 0; i < configPossibles.length; i++) {
				final int index = i;
				if (!tableauPost.getListeIndex().stream().anyMatch(c -> c.getI()== indexCase.getI() + configPossibles[index][0]
						&& c.getJ()== indexCase.getJ() + configPossibles[index][1])
				&& !listeCasesAVerifier.stream().anyMatch(c -> c.getIndexCase().getI()== indexCase.getI() + configPossibles[index][0]
								&& c.getIndexCase().getJ()== indexCase.getJ() + configPossibles[index][1])) {
					listeCasesAVerifier.add(new Case(
							new IndexCase(indexCase.getI() + configPossibles[i][0],
											indexCase.getJ() + configPossibles[i][1]),0));
				}
			}
		}
		
		for (Case caseCourante : listeCasesAVerifier) {
			
			List<IndexCase> listeIndexCasesVivantesCourante = new ArrayList<>(tableauPost.getListeIndex());
			long somme = listeIndexCasesVivantesCourante.stream().filter(
				  c -> c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[0][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[0][1])
					|| c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[1][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[1][1])
					|| c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[2][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[2][1])
					|| c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[3][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[3][1])
					|| c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[4][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[4][1])
					|| c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[5][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[5][1])
					|| c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[6][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[6][1])
					|| c.getI()==(caseCourante.getIndexCase().getI() + configPossibles[7][0])
					&& c.getJ()==(caseCourante.getIndexCase().getJ() + configPossibles[7][1]))
			.count();
			if (caseCourante.getValue() == 1 && (somme == 2 || somme == 3)) {
				caseCourante.setValue(1);
			}else if(caseCourante.getValue() == 0 && somme == 3) {
				caseCourante.setValue(1);
			}else {
				caseCourante.setValue(0);
			}
		}
		return listeCasesAVerifier.stream().filter(c -> c.getValue() == 1 
				&& c.getIndexCase().getI() >= 0 
				&& c.getIndexCase().getJ() >= 0 
				&& c.getIndexCase().getI() < tableauPost.getTailleTableau().getX()
				&& c.getIndexCase().getJ() < tableauPost.getTailleTableau().getY()).map(c -> c.getIndexCase()).collect(Collectors.toList());
		
	}
		
	
	
	
}
