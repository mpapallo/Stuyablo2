import java.util.Random;
public class Rogue extends Adventurer{
    private int stamina;

    public Rogue(String n){
	super(n, 15, 6, 9);
	restoreResource();
    }
    public Rogue(){
	this("Styx");
    }

    public void restoreResource(){
	setStamina(10);
    }
    public void setStamina(int s){
	stamina = s;
    }
    public int getStamina(){
	return stamina;
    }

    public String getStats(){
	return super.getStats() + getStamina() + "Stamina";
    }

    public boolean hit(){
	Random r = new Random();
	int x = r.nextInt(getDEX() / 2);
	return x > 0;
    }

    public void attack(Adventurer other){
	System.out.println(this + " backstabs " + other + "...");
	if (hit() || hit()){
	    Random r = new Random();
	    int x = r.nextInt(5) + 1;
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
	if (this.getStamina() >=3){
	    this.setStamina(this.getStamina() - 3);
	    System.out.println(this + " uses his ninja skills on " + other + "...");
	    if (hit() || hit()){
		Random r = new Random();
		int x = r.nextInt(8) + 1;
		other.setHP(other.getHP() - x);
		System.out.println("...and deals " + x + " point(s) of damage\n");
	    }else{
		System.out.println("...and misses\n");
	    }
	}else{
	    System.out.println(this + " doesn't have enough stamina to use specialAttack");
	    attack(other);
	}
    }

}
