package Model;/*modele*/

import java.util.ArrayList;
import java.util.Collections;

public class Cerveau {
	public ArrayList<ArrayList<Boolean>> gaufre;
	public int largeur;
	public int hauteur;
	public ArrayList<Tuple> historique;
	public int coup;
	public int nbplayers;

	public void cuisiner(int width, int height, int guests){
		this.largeur = width;
		this.hauteur = height;
		this.gaufre = new ArrayList<>();
		for (int i = 0; i < this.hauteur; i++){
			this.gaufre.add(new ArrayList<>(Collections.nCopies(this.largeur, true)));
		}
		this.historique = new ArrayList<>();
		this.coup = 0;
		this.nbplayers = guests;
	}

	public void recuisiner(){ /*reset grid*/
		this.gaufre = new ArrayList<>();
		for (int i = 0; i < this.hauteur; i++){
			this.gaufre.add(new ArrayList<>(Collections.nCopies(this.largeur, true)));
		}
	}

	public int whosturn(){
		return ((this.coup)%this.nbplayers)+1;
	}

	public int whojustplayed(){
		return ((this.coup-1)%this.nbplayers)+1;
	}

	public int howmanyturn(){
		return this.coup;
	}

	public int jouerCoup(int player, int coordX, int coordY) {/* a normal turn*/
		int eaten = 0;
		
		if (!(coordX == 0 && coordY == 0)) {
			eaten = manger(coordX, coordY);
			
			if (eaten != 0) {
				this.historique.add(this.coup, new Tuple(player, this.coup, coordX, coordY, eaten));
				this.coup++;
			}
		}
		
		return eaten;
	}
	
	public int manger(int coordX, int coordY) {
		int eaten = 0;
		
		for (int i = coordY; i < this.hauteur; i++) {
			for (int j = coordX; j < this.largeur; j++) {
				if (this.gaufre.get(i).get(j)) {
					this.gaufre.get(i).set(j, false);
					eaten++;
				}
			}
		}
		
		return eaten;
	}

	public void arriere(){ /*go backward*/
		if (this.coup > 0) {
			this.coup--;
			recuisiner();
			for (int i = 0; i < this.coup; i++){
				Tuple couptuple = this.historique.get(i);
				manger(couptuple.first, couptuple.second);
			}
		}
	}

	public void avant(){ /* go forward*/
		if (this.coup < this.historique.size()){
			Tuple coupjoue = this.historique.get(coup);
			manger(coupjoue.first, coupjoue.second);
			this.coup++;
		}
	}

	public int whowin(){
		int sumbool = 0;
		for (int i = 0; i < this.hauteur; i++){
			for (int j = 0; j < this.largeur; j++){
					if (this.gaufre.get(i).get(j)){
						sumbool++;
				}
			}
		}
		if (this.gaufre.get(0).get(0) && (sumbool == 1)){
			return whojustplayed();
		}
		else{
			return 0;
		}
	}
}