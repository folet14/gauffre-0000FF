package Model;

public class Tuple {
    public final int player;
    public final int nbcoup;
    public final int first ;
    public final int second;
    public final int eaten;
    public Tuple(int p, int nb, int f, int s, int e){
        this.player = p;
        this.nbcoup = nb;
        this.first = f;
        this.second = s;
        this.eaten = e;
    }
}
