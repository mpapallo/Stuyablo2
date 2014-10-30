import java.util.Scanner;
import java.util.Random;
public class Game{
    
    public static Adventurer userGeneratePlayer(){
	String name = userChooseName();
	Adventurer player = userCustomizePlayer(name);
	return player;
    }

    public static String userChooseName(){
	Scanner s = new Scanner(System.in);
	System.out.println("\nType a name for your character:");
	String n = s.nextLine();
	return n;
    }

    public static Adventurer userCustomizePlayer(String name){
	Adventurer player;
	Scanner s = new Scanner(System.in);
	boolean done;
	do{
	    done = true;
	    System.out.println("\nType the letter of your desired class:\n");
	    System.out.println("A. Warrior\nB. Wizard\nC. Rogue\nD. Martial Artist");
	    String input = s.nextLine();
	    if (input.toLowerCase().equals("a")){
		player = new Warrior();
	    }else if (input.toLowerCase().equals("b")){
		player = new Wizard();
	    }else if (input.toLowerCase().equals("c")){
		player = new Rogue();
	    }else if (input.toLowerCase().equals("d")){
		player = new MartialArtist();
	    }else{
		player = new Warrior();
		done = false;
	    }
	}while(!done);
	
	player.setName(name);
	userAllocateStats(player, 27);
	
	return player;
    }

    public static Adventurer userAllocateStats(Adventurer player, int points){
	player.setSTR(1);
	player.setDEX(1);
	player.setINT(1);
	System.out.println("\nYour current stats are:\n" + player.getStats());
	System.out.println("\nYou have " + points + " points to split among strength, dexterity, and intelligence");
	Scanner s = new Scanner(System.in);
	boolean done;
	do{
	    done = true;
	    System.out.println("How much STR?");
	    int S = s.nextInt();
	    System.out.println("How much DEX?");
	    int D = s.nextInt();
	    System.out.println("How much INT?");
	    int I = s.nextInt();
	    if (!(S + D + I == points)){
		System.out.println("\nCheck your math...");
		done = false;
	    }else{
		player.setSTR(player.getSTR() + S);
		player.setDEX(player.getDEX() + D);
		player.setINT(player.getINT() + I);
	    }
	}while(!done);
	return player;
    }

    public static void combat(Adventurer [] players, Adventurer opponent){
	System.out.println("\nThe battle begins!\n");
        do{
	    printStats(players, opponent);

	    Scanner s = new Scanner(System.in);
	    for (int i = 0; i < players.length; i ++){
		if (players[i].getHP() > 0){
		    if (opponent.getHP() > 0){
			boolean done;
			do{
			    done = true;
			    System.out.println("Chose an action for " + players[i] + ":\n");
			    System.out.println("A: attack\nS: special attack\n");
			    String action = s.nextLine();
			    if (action.toLowerCase().equals("a")){
				players[i].attack(opponent);
			    }else if (action.toLowerCase().equals("s")){
				players[i].specialAttack(opponent);
			    }else{
				done = false;
			    }
			}while(!done);
		    }else{
			System.out.println(players[i] + ": No need to attack a dead opponent...\n");
		    }
		}else{
		    System.out.println(players[i] + " is too dead to attack...\n");
		}
	    }
	    if (opponent.getHP() > 0){
		Random rand = new Random();
		int z = rand.nextInt(10);
		if (z < 7){
		    opponent.attack(opponentChooseVictim(players));
		}else{
		    opponent.specialAttack(opponentChooseVictim(players));
		}
	    }else{
		System.out.println("Opponent is too dead to attack...\n");
	    }
	}while(opponent.getHP() > 0 && !allDead(players));
	
	printStats(players, opponent);

	if (opponent.getHP() <= 0){
	    System.out.println("Your party wins!");
	}else{
	    System.out.println("The opponent wins!");
	}
    }
    
    public static Adventurer opponentChooseVictim(Adventurer [] players){
	Random r = new Random();
	int p = r.nextInt(3);
	while (players[p].getHP() <= 0){
	    p = r.nextInt(3);
	}
	return players[p];
    }
    
    public static boolean allDead(Adventurer [] players){
	boolean allDead = true;
	for (int i = 0; i < players.length; i ++){
	    if ((players[i].getHP() > 0)){
		allDead = false;
	    }
	}
	return allDead;
    }

    public static void printStats(Adventurer [] players, Adventurer opponent){
	System.out.println("Your party");
	for (int i = 0; i < players.length; i ++){
	    System.out.println(players[i].getStats());
	}
	System.out.println("\nYour opponent\n" + opponent.getStats());
	System.out.println();
    }

    public static Adventurer assignRandomClass(){
	Adventurer p; 
	Random r = new Random();
	int x = r.nextInt(4);
	if (x == 0){
	    p = new Warrior();
	}else if (x == 1){
	    p = new Wizard();
	}else if (x == 2){
	    p = new Rogue();
	}else{
	    p = new MartialArtist();
	}
	return p;
    }

    public static void commenceBattle(Adventurer [] players){
	Adventurer opponent = assignRandomClass();
	opponent.setHP(60);
	
	combat(players, opponent);

	Scanner s = new Scanner(System.in);
	boolean done;
	do{
	    done = true;
	    System.out.println("Choose an option:\nA. New Battle (same party, different opponent)\nB. End Game");
	    String option = s.nextLine();
	    if (option.toLowerCase().equals("a")){
		healParty(players);
		commenceBattle(players);
	    }else if (option.toLowerCase().equals("b")){
		System.out.println("The End!");
	    }else{
		done = false;
	    }
	}while(!done);
	
    }
    
    public static void healParty(Adventurer [] players){
	for (int i = 0; i < players.length; i ++){
	    players[i].setHP(players[i].getOriginalHP());
	    players[i].restoreResource();
	}
    }

    public static void main(String[]args){
	
	System.out.println("\nStuyablo:");
	System.out.println("You will create a team of 3 characters to fight a random opponent.\n");
	
	Adventurer [] players = new Adventurer[3];
	Scanner s = new Scanner(System.in);
	boolean done;
	do{
	    done = true;
	    System.out.println("Choose an option:\nA. Default Party\nB. Custom Party\n");
	    String option = s.nextLine();
	    if (option.toLowerCase().equals("a")){
		for (int i = 0; i < players.length; i ++){
		    Adventurer player = assignRandomClass();
		    player.setName("Player" + (i + 1));
		    players[i] = player;
		}
	    }else if (option.toLowerCase().equals("b")){
		for (int i = 0; i < players.length; i ++){
		    System.out.println("\nCharacter " + (i + 1) + ":");
		    Adventurer player = userGeneratePlayer();
		    players[i] = player;
		}
	    }else{
		done = false;
	    }
	}while(!done);
	
	commenceBattle(players);
    }

}  

