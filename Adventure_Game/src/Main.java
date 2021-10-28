import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws NullPointerException {
		Scanner input = new Scanner(System.in);
//		input.nextLine();
//		Adventure_game.Rooms rm = new Rooms();
//		Adventure_game.Player p = new Player();
//		Adventure_game.Monster monster = null;
//		ArrayList<Item> rmInv = new ArrayList<Item>();
		Rooms rm = new Rooms();
		Player p = new Player();
		Monster monster = null;
		ArrayList<Item> rmInv = new ArrayList<Item>();
		boolean cmbTracker = false;//checks to see if the player has gone

		System.out.println("Welcome to the game.");
		System.out.println("Here is a list of commands you can try:");
		System.out.println("Attack - attacks with your currently equiped weapon. You currently deal "+ p.attack() + " damage");
		System.out.println("Defend - decreases the damage you take for one turn");
		System.out.println("PowerAttack - a powerful attack that also makes you more vulnerable for one turn");
		System.out.println("GetItem - adds an item from the room to your inventory");
		System.out.println("Inv - looks at your inventory");
		System.out.println("LookItems - Looks at what items you have equiped");
		System.out.println("Equip - changes your currently equiped item");
		System.out.println("Search - searches the room for items");
		System.out.println("NextRoom - goes to the next room");
		System.out.println("exit - exits the game");
		System.out.println("Help - replayes this message");
//		System.out.println(p.getHealth());
		
		int lvl = 0;
		boolean cont = true;//checks to see if the player is dead
		boolean rgen = false;//checks to see if the room has been generated yet
		do {//start game loop
						
			p.newTurn();//resets power attack and bonus defense
			cmbTracker = false;//checks to see if the player has taken a combat action yet
			String cmd = input.next();
			switch(cmd) {//player's turn start
				case "Attack":
					if (rgen&&!monster.dead()){
						monster.takedmg(p.attack());
						System.out.println("You dealt " + p.attack() + " damage.");
						System.out.println("The monster now has " + monster.getHealth() + " health remaining.");
						cmbTracker = true;
						break;
					}
					else if(rgen&&monster.dead()) {
						System.out.println("The monster is dead");
					}
					else {
						System.out.println("There's no monster");
					}
					break;
				case "Defend":
					if (rgen){
						p.defend();
						cmbTracker = true;
					}
					else if(rgen&&monster.dead()) {
						System.out.println("The monster is dead");
					}
					else {
						System.out.println("There's no monster");
					}
					break;
				case "PowerAttack":
					if (rgen&&!monster.dead()){
						double dmg = p.powerAttack();
						monster.takedmg(dmg);
						System.out.println("You dealt " + dmg + " damage.");
						System.out.println("The monster now has " + monster.getHealth() + " health remaining.");
						cmbTracker = true;
					}
					else if(rgen&&monster.dead()) {
						System.out.println("The monster is dead");
					}
					else {
						System.out.println("There's no monster");
					}
					break;
				case "GetItem":
					if(rmInv.size()==0) {//if the room is empty ends GetItem
						System.out.println("This room is empty");
						break;
					}else if(!monster.dead()) {//if the monster is dead you can't get an item
						System.out.println("There's a monster you should probably kill first.");
						break;
					}
					boolean c;//a variable to check for the do/while loop to repeat
					do{
						c = false;
						try{
						rm.searchRoom();
						System.out.println("What number item do you want to take?");
						int itm = input.nextInt();//recieves the index of the item the player wants
	//					input.nextLine();
						p.getItem(rmInv.get(itm));//gives the player an item from the room inventory based on the index they selected
						rmInv.remove(itm);
						}
						catch(IndexOutOfBoundsException e){
							System.out.println("Please enter a valid number");
							c = true;//if the player enters an out of bounds index the loop repeats
						}
					}while(c);
					break;
				case "Inv":
					p.printInv();
					break;
				case "LookItems":
//					System.out.println("Look items test");
					p.printEqp();
					break;
				case "Equip":
					if(p.returnInv().size()==0) {//if the room is empty ends GetItem
						System.out.println("There's nothing in your inventory");
						break;
					}
					boolean c1;//a variable to see if the do/while loop repeats
					do{
						c1 = false;
						try{
						p.printInv();
						System.out.println("What number item do you want to equip?");
						ArrayList<Item> pinv = p.returnInv();//copies the player's inventory
						int equip = input.nextInt();//puts selected item's index into temp variable
	//					input.nextLine();
						p.equip(pinv.get(equip));//equips based on selected item's index
						}catch(IndexOutOfBoundsException e) {
							System.out.println("Please enter a valid number");
							c1 = true;//if the player enters an out of bounds number the loop repeats
						}
					}while(c1);
					break;
				case "Search":
					if(rgen&&!monster.dead()) {
						System.out.println("Well there's definitly a monster in the room.");
					}else if(!rgen||(rmInv.size() == 0)) {//checks to see if the room is empty and has been generated
						System.out.println("The room is empty");
					}else
						rm.searchRoom();
					break;
				case "NextRoom":
					if(rgen&&!monster.dead()) {//start room generation
						System.out.println("There's a monster blocking your path");
					}
					else {
						lvl++;//increase lvl
						p.increaseHealth();//increase player health
						monster = rm.genMonster(lvl);//generates monster
						rmInv.clear();//clears previous room's inventory
						rmInv = rm.genItems(lvl);//generates current room's inventory
						rgen = true;//changes room state to exists
						System.out.println("Your health is now " +p.round(p.getHealth(),2));
						System.out.println("You bash down number " + lvl + " door and infront of you stands a hideous monster.");
						System.out.println("The monster has " + monster.getHealth() + " health.");
						if ((lvl%5)==0) {//gives player a small damage boost every 5 lvls
							p.setMod();
						}
					}//en room generation
					break;
				case "exit":
					System.exit(0);
					break;
				case "Help":
					System.out.println("Here is a list of commands you can try:");
					System.out.println("Attack - attacks with your currently equiped weapon. You currently deal "+ p.attack() + " damage");
					System.out.println("Defend - decreases the damage you take for one turn");
					System.out.println("PowerAttack - a powerful attack that also makes you more vulnerable for one turn");
					System.out.println("GetItem - adds an item from the room to your inventory");
					System.out.println("Inv - looks at your inventory");
					System.out.println("LookItems - Looks at what items you have equiped");
					System.out.println("Equip - changes your currently equiped item");
					System.out.println("Search - searches the room for items");
					System.out.println("NextRoom - goes to the next room");
					System.out.println("exit - exits the game");
					System.out.println("Help - replayes this message");
					break;
				default:
					System.out.println("I'm sorry I don't understand");
				
			}// player's turn end
			System.out.println("");
			if(cmbTracker&&!monster.dead()) {//begin monster's turn
				monster.newTurn();
				int choice = (int)(Math.random()*10);
//				System.out.println("Monster chose " + choice);
				switch(choice) {
					case 0: 
					case 1:
					case 2:
					case 3: 
						System.out.println("The monster attacked.");
						p.takedmg(monster.attack());
						break;
					case 4:
					case 5:
					case 6: 
						System.out.println("The Monster defended and is less vulnerable to attack for one turn.");
						monster.defend();
						break;
					case 7:
					case 8:
					case 9:
					case 10: System.out.println("The monster power attacked and is more vulnerable to attacks for one turn.");
						p.takedmg(monster.powerAttack());
						break;
					default: System.out.println("Monster broke roller in Main class");
					break;
				}
			}//end monster turn
			if (p.getHealth() <= 0){
				cont = false;
			}
		}while (cont); //end game loop
		System.out.println("Game over");
		input.close();
	}
}
