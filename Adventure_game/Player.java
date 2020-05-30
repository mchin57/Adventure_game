package Adventure_game;

import java.util.ArrayList;

class Player implements Fighter{
	private int Health;
	private int mxHealth;
	private int dmg = 2;
	private double defence = 1;//whether the player is defending or not
	private double resistance = 1;//damage reduction provided by armor
	private double pAttack = 1;
	private Weapon eWeapon;
	private Armor eHat = new Armor(0,1,0);
	private Armor eChest = new Armor(0,2,0);
	private Armor eBoot = new Armor(0,3,0);
	private ArrayList<Item> inv = new ArrayList<>();

	public Player() {
		mxHealth = 9;
		Health = 9;
		eWeapon = new Weapon(0,2);
	}
	public int attack() {
		return (int)(this.getDmg()*pAttack);
	}
	public int getDmg() {
		return dmg;
	}
	
	public int takedmg(int dmg) {
		Health = (int)(Health - resistance*pAttack*defence*dmg);
		System.out.println("You took " + (double)(resistance*pAttack*defence*dmg) + " damage and now you have " + Health + " Health remaining.");
		return(this.Health);
		}
	public void defend() {
		defence = .5;
	}	
	public void getItem(Item itm) {//start get item
		inv.add(itm);
		if(itm instanceof Armor) {
			System.out.println("One " + ((Armor) itm).armorType() + " has been added to your inventory.");
			System.out.println("");
		}
		else {
			System.out.println("One Sword has been added to your inventory.");
			System.out.println("");
		}
	}//end get item
	public void printInv() {//start get inv
		System.out.println("Inventory:");
		for (int i=0; i < inv.size(); i++) {
			if (inv.get(i) instanceof Armor){
				System.out.println(i+((Armor) inv.get(i)).armorType());
				System.out.println(((Armor) inv.get(i)).getProtection());
				System.out.println("");
			}
			else {
				System.out.println(i+"Sword");
				System.out.println("This sword deals "+((Weapon) inv.get(i)).getDmg()+" damage");
				System.out.println("");
			}
		}
	}//end get inv
	public ArrayList<Item> returnInv() {
		return inv;
	}
	public void equip(Item item) {
		if(item instanceof Weapon) {
			eWeapon = (Weapon) item;
			dmg = eWeapon.getDmg();
		}
		else if(item instanceof Armor){
			switch(((Armor) item).getType()) {
			case 1:
				eHat = (Armor)item;
				System.out.println("You have put on a new helmet.");
				break;
			case 2:
				eChest = (Armor)item;
				System.out.println("You have put on a new chestplate.");
				break;
			case 3:
				eBoot = (Armor)item;
				System.out.println("You have put on a new pair of boots.");
				break;
			}
			this.setDefense();
		}
		else
			System.out.println("player.equip broke");	
	}
	public void setDefense() {
		double d = eHat.getArmor() + eChest.getArmor() + eBoot.getArmor();
		resistance = 1-(d/100);
	}
	public void heal() {
		Health = mxHealth;
	}
	public void increaseHealth() {
		mxHealth++;
		Health = mxHealth;
	}
	public int getHealth() {
		return Health;
	}
	@Override
	public void newTurn() {
		defence = 1;
		pAttack = 1;
	}
	@Override
	public int powerAttack() {
		pAttack = 1.5;
		return this.attack();
	}
	public double getDefense() {
		return resistance;
	}
}