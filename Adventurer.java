import java.util.Random;
public abstract class Adventurer{
    private String name;
    private int HP, DEX, STR, INT;

    public Adventurer(String n, int d, int s, int i){
	setName(n);
	setHP(20);
	setDEX(d);
	setSTR(s);
	setINT(i);	
    }
    public Adventurer(String n){
	this(n, 10, 10, 10);
    }
    public Adventurer(){
	this("Link", 10, 10, 10);
    }
    
    public void setName(String name){
	this.name = name;
    }
    public String getName(){
	return name;
    }
    public void setHP(int HP){
	this.HP = HP;
    }
    public int getHP(){
	return HP;
    }
    public int getOriginalHP(){
	return 20;
    }
    abstract void restoreResource();
    public void setDEX(int d){
	DEX = d;
    }
    public int getDEX(){
	return DEX;
    }
    public void setSTR(int s){
	STR = s;
    }
    public int getSTR(){
	return STR;
    }
    public void setINT(int i){
	INT = i;
    }
    public int getINT(){
	return INT;
    }

    public String toString(){
	return (getName());
    }

    public String getStats(){
	return this + ": " + getHP() + "HP\t" + getDEX() + "DEX\t" + getSTR() + "STR\t" + getINT() + "INT\t";
    }

    abstract boolean hit();
    abstract void attack(Adventurer other);
    abstract void specialAttack(Adventurer other);

}
