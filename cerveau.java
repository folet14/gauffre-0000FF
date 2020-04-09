/*modele*/

import java.util.ArrayList;
import java.util.Collections;

public class cerveau{
	ArrayList<ArrayList<Boolean>> gauffre;
	int largeur;
	int hauteur;
	ArrayList<tuple> historique;
	int coup;
	int nbplayers;

	public void cuisiner(int width, int height, int guests){
		this.largeur = width;
		this.hauteur = height;
		this.gauffre = new ArrayList<>();
		for (int i = 0; i < this.hauteur; i++){
			this.gauffre.add(new ArrayList<>(Collections.nCopies(this.largeur, true)));
		}
		this.historique = new ArrayList<>();
		this.coup = 0;
		this.nbplayers = guests;
	}

	public void recuisiner(){ /*reset grid*/
		this.gauffre = new ArrayList<>();
		for (int i = 0; i < this.hauteur; i++){
			this.gauffre.add(new ArrayList<>(Collections.nCopies(this.largeur, true)));
		}
	}

	public int whosturn(){
		return ((this.coup)%this.nbplayers)+1;
	}

	public int whojustplayed(){
		return ((this.coup-1)%this.nbplayers)+1;
	}

	public int howmanyturn(){
		return this.coup/2;
	}

	public int manger(int player, int coordX, int coordY) {/* a normal turn*/
		int eaten = 0;
		if (!(coordX == 0 && coordY == 0)) {
			for (int i = coordY; i < this.hauteur; i++) {
				for (int j = coordX; j < this.largeur; j++) {
					if (this.gauffre.get(i).get(j)) {
						this.gauffre.get(i).set(j, false);
						eaten++;
					}
				}
			}
			if (eaten != 0) {
				coup++;
				if (this.historique.size() < coup) {
					this.historique.add(new tuple(player, this.coup, coordX, coordY, eaten));
				} else {
					this.historique.add(this.coup - 1, new tuple(player, this.coup, coordX, coordY, eaten));
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
				tuple couptuple = this.historique.get(i);
				manger(couptuple.player,couptuple.first,couptuple.second);
			}
		}
	}

	public void avant(){ /* go forward*/
		if (this.coup < this.historique.size()){
			tuple coupjoue = this.historique.get(coup);
			manger(coupjoue.player,coupjoue.first,coupjoue.second);
		}
	}

	public int whowin(){
		int sumbool = 0;
		for (int i = 0; i < this.hauteur; i++){
			for (int j = 0; j < this.largeur; j++){
					if (this.gauffre.get(i).get(j)){
						sumbool++;
				}
			}
		}
		if (this.gauffre.get(0).get(0) && (sumbool == 1)){
			return whojustplayed();
		}
		else{
			return 0;
		}
	}
}