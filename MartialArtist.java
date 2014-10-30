import java.util.Random;
public class MartialArtist extends Adventurer{
    private int qi;
    
    public MartialArtist(String n){
	super(n, 11, 8, 11);
	restoreResource();
    }
    public MartialArtist(){
	this("Po");
    }
    
    public void restoreResource(){
	setQi(10);
    }
    public void setQi(int q){
	qi = q;
    }
    public int getQi(){
	return qi;
    }

    public String getStats(){
	return super.getStats() + getQi() + "Qi";
    }

    public boolean hit(){
	Random r = new Random();
	int x = r.nextInt((getSTR() + getDEX() + getINT()) /3);
	return x > 0;
    }
    
    public void attack(Adventurer other){
	System.out.println(this + " karate chops " + other + "...");
	if (hit()){
	    Random r = new Random();
	    int x = r.nextInt(6) + 1;
	    other.setHP(other.getHP() - x);
	    if (other.getHP() < 0){
		other.setHP(0);
	    }
	    System.out.println("...and deals " + x + " point(s) of damage\n");
	}else{
	    System.out.println("...and misses\n");
        }
    }

    public void specialAttack(Adventurer other){
	if (this.getQi() >= 2){
	    this.setQi(this.getQi() - 2);
	    System.out.println(this + " focuses the energy of kung fu on " + other + "...");
	    if (hit()){
		Random r = new Random();
		int x = r.nextInt(9) + 1;
		other.setHP(other.getHP() - x);
		System.out.println("...and deals " + x + " point(s) of damage\n");
	    }else{
		System.out.println("...and misses\n");
	    }
	}else{
	    System.out.println(this + " doesn't have enough qi to use specialAttack");
	    attack(other);
	}
    }
}
