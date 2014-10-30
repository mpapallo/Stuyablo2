import java.util.Random;
public class Warrior extends Adventurer{
    private int rage;

    public Warrior(String n){
	super(n, 8, 15, 7);
        setHP(30);
	setRage(10);
    }
    public Warrior(){
	this("Spartacus");
    }

    public int getOriginalHP(){
	return 30;
    }
    public void restoreResource(){
	setRage(10);
    }
    public void setRage(int r){
	rage = r;
    }
    public int getRage(){
	return rage;
    }
    
    public String getStats(){
	return super.getStats() + getRage() + "Rage";
    }
    
    public boolean hit(){
	Random r = new Random();
	int x = r.nextInt(getSTR() / 2);
	return x > 0;
    }

    public void attack(Adventurer other){
	System.out.println(this + " brutally assaults " + other + "...");
	if (hit()){
	    Random r = new Random();
	    int x = r.nextInt(7) + 1;
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
	if (this.getRage() >= 2){
	    this.setRage(this.getRage() - 2);
	    System.out.println(this + " summons the rage of his ancestors on " + other + "...");
	    if (hit()){
		Random r = new Random();
		int x = r.nextInt(10) + 1;
		other.setHP(other.getHP() - x);
		System.out.println("...and deals " + x + " point(s) of damage\n");
	    }else{
		System.out.println("...and misses\n");
	    }
	}else{
	    System.out.println(this + " doesn't have enough rage to use specialAttack");
	    attack(other);
	}
    }

}
