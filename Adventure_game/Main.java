package Adventure_game;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws NullPointerException {
		Scanner input = new Scanner(System.in);
//		input.nextLine();
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
		System.out.println("Equip - changes your currently equiped item");
		System.out.println("Search - searches the room for items");
		System.out.println("NextRoom - goes to the next room");
		System.out.println("Help - replayes this message");
//		System.out.println(p.getHealth());
		
		int lvl = 0;
		boolean cont = true;//checks to see if the player is dead
		boolean rgen = false;//checks to see if the room has been generated yet
		do {//start game loop
						
			p.newTurn();//resets power attack and bonus defense
			cmbTracker = false;
			String cmd = input.next();
//			input.nextLine();
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
					if (rgen){
						int dmg = p.powerAttack();
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
					System.out.println("What number item do you want to take?");
					int itm = input.nextInt();
//					input.nextLine();
					p.getItem(rmInv.get(itm));
					break;
				case "Inv":
					p.printInv();
					break;
				case "Equip":
					p.printInv();
					System.out.println("What number item do you want to equip?");
					ArrayList<Item> pinv = p.returnInv();
					int equip = input.nextInt();
//					input.nextLine();
					p.equip(pinv.get(equip));
					break;
				case "Search":
					if(!monster.dead()) {
						System.out.println("Well there's definitly a monster in the room.");
					}else if(rmInv.size() == 0) {
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
						System.out.println("Your health is now " +p.getHealth());
						System.out.println("You bash down number " + lvl + " door and infront of you stands a hideous monster.");
						System.out.println("The monster has " + monster.getHealth() + " health.");
					}//en room generation
					break;
				case "Help":
					System.out.println("Here is a list of commands you can try:");
					System.out.println("Attack - attacks with your currently equiped weapon");
					System.out.println("Defend - decreases the damage you take for one turn");
					System.out.println("PowerAttack - a powerful attack that also makes you more vulnerable for one turn");
					System.out.println("GetItem - adds an item from the room to your inventory");
					System.out.println("Inv - looks at your inventory");
					System.out.println("Equip - changes your currently equiped item");
					System.out.println("Search - searches the room for items");
					System.out.println("NextRoom - goes to the next room");
					System.out.println("Help - replayes this message");
					break;
				default:
					System.out.println("I'm sorry I don't understand");
				
			}// player's turn end
			System.out.println("");
			if(cmbTracker&&!monster.dead()) {//begin monster's turn
				monster.newTurn();
				int choice = (int)(Math.random()*10);
				System.out.println("Monster chose " + choice);
				switch(choice) {
					case 0: 
					case 1:
					case 2:
					case 3: 
						p.takedmg(monster.attack());
						System.out.println("The monster attacked.");
						break;
					case 4:
					case 5:
					case 6:
					case 7: monster.defend();
						System.out.println("The Monster defended and is less vulnerable to attack for one turn.");
						break;
					case 8:
					case 9:
					case 10: p.takedmg(monster.powerAttack());
						System.out.println("The monster power attacked and is more vulnerable to attacks for one turn.");
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