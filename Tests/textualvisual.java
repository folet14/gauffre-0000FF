package Tests;

import Model.cerveau;

import java.util.Scanner;

public class textualvisual {
    private static void affichage(cerveau jeu){
        for (int i = 0; i < jeu.hauteur; i++){
            StringBuilder line = new StringBuilder("| ");
            for (int j = 0; j < jeu.largeur; j++){
                if(jeu.gaufre.get(i).get(j)){
                    line.append("1 | ");
                }
                else{
                    line.append("0 | ");
                }
            }
            System.out.println(line.toString());
        }
    }

    public static void main(String[] args){
       cerveau jeu = new cerveau();
       jeu.cuisiner(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]));
       Scanner scanner = new Scanner(System.in);
        while(jeu.whowin() == 0){
            System.out.println("joueur " + jeu.whojustplayed() + " vient de jouer");
            System.out.println("nb tour = " + jeu.howmanyturn());
            System.out.println("nb coups = " + jeu.coup);
            System.out.println("au tour de joueur " + jeu.whosturn());


            textualvisual.affichage(jeu);
            System.out.println("avant f, arriere b, rien n");
            String line = scanner.nextLine();
            switch (line){
                case "f":
                case "F":
                case "avant":
                    jeu.avant();
                    break;
                case "b":
                case "B":
                case "arriere":
                    jeu.arriere();
                    break;
                default:
                    System.out.println("x? = ");
                    int x = scanner.nextInt() - 1;
                    scanner.nextLine();
                    System.out.println("y? = ");
                    int y = scanner.nextInt() - 1;
                    scanner.nextLine();
                    System.out.println("le joueur a manger " +jeu.manger(jeu.whosturn(), x, y)+ " cases");
                    break;
                }
            }
        System.out.println("le joueur " + jeu.whowin() + " à gagné!!!");
    }
}
