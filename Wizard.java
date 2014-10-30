import java.util.Random;
public class Wizard extends Adventurer{
    private int mana;

    public Wizard(String n){
	super(n, 9, 6, 15);
	restoreResource();
    }
    public Wizard(){
	this("Harry");
    }

    public void restoreResource(){
	setMana(10);
    }
    public void setMana(int mana){
	this.mana = mana;
    }
    public int getMana(){
	return mana;
    }
    
    public String getStats(){
	return super.getStats() + getMana() + "Mana";
    }

    public boolean hit(){
	Random r = new Random();
	int x = r.nextInt(getINT() / 2);
	return x > 0;
    }

    public void attack(Adventurer other){
	System.out.println(this + " waves a stick at " + other + "...");
	if (hit()){
	    Random r = new Random();
	    int x = r.nextInt(3) + 1;
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
	if (this.getMana() >=3){
	    this.setMana(this.getMana() - 3);
	    System.out.println(this + " hurls the elements at " + other + "...");
	    if (hit()){
		Random r = new Random();
		int x = r.nextInt(12) + 1;
		other.setHP(other.getHP() - x);
		System.out.println("...and deals " + x + " point(s) of damage\n");
	    }else{
		System.out.println("...and misses\n");
	    }
	}else{
	    System.out.println(this + " doesn't have enough mana to use specialAttack");
	    attack(other);
	}
    }

}
