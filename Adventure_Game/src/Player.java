import java.util.ArrayList;

class Player implements Fighter{
	private double Health;
	private int mxHealth;
	private int dmg;
	private int mod=0;
	private double defence = 1;//whether the player is defending or not
	private double resistance = 1;//damage reduction provided by armor
	private double pAttack = 1;
	private Weapon eWeapon = new Weapon(0,2);
	private Armor eHat = new Armor(0,1,0);
	private Armor eChest = new Armor(0,2,0);
	private Armor eBoot = new Armor(0,3,0);
	private ArrayList<Item> inv = new ArrayList<>();

	public Player() {
		mxHealth = 10;
		Health = 10;
		this.setDefense();
		dmg = eWeapon.getDmg();
	}
	public double attack() {
		return (this.getDmg()*pAttack);
	}
	public int getDmg() {
		return dmg+mod;
	}
	
	public double takedmg(double dmg) {
		Health = (Health - resistance*pAttack*defence*dmg);
		System.out.println("You took " + round((double)(resistance*pAttack*defence*dmg),2) + " damage and now you have " + round(Health,2) + " Health remaining.");
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
				System.out.println(i+" "+((Armor) inv.get(i)).armorType());
				System.out.println(((Armor) inv.get(i)).getProtection());
				System.out.println("");
			}
			else {
				System.out.println(i+" Sword");
				System.out.println("This sword deals "+((Weapon) inv.get(i)).getDmg()+" damage");
				System.out.println("");
			}
		}
	}//end get inv
	public void printEqp() {
		System.out.println("Equiped items:");
		System.out.println("This helmet deflects roughtly " + round(eHat.getArmor(),2) + "% of damage.");
		System.out.println("This chest piece deflects roughly " + round(eChest.getArmor(),2) + "% of damage.");
		System.out.println("These boots deflect roughly " + round(eBoot.getArmor(),2) + "% of damage.");
		System.out.println("This sword deals " + eWeapon.getDmg() + " damage");
	}
	public ArrayList<Item> returnInv() {
		return inv;
	}
	public void equip(Item item) {
		if(item instanceof Weapon) {
			inv.add(eWeapon);//adds currently equiped item to the inventory
			eWeapon = (Weapon) item;
			dmg = eWeapon.getDmg();
			System.out.println("You have equiped a new sword.");
		}
		else if(item instanceof Armor){
			switch(((Armor) item).getType()) {
			case 1:
				inv.add(eHat);//adds currently equiped hat to the inventory
				eHat = (Armor)item;
				System.out.println("You have put on a new helmet.");
				break;
			case 2:
				inv.add(eChest);
				eChest = (Armor)item;
				System.out.println("You have put on a new chestplate.");
				break;
			case 3:
				inv.add(eBoot);
				eBoot = (Armor)item;
				System.out.println("You have put on a new pair of boots.");
				break;
			}
			this.setDefense();
		}
		else {
			System.out.println("player.equip broke");
		}
		inv.remove(item);//removes selected item from player's inventory
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
	public double getHealth() {
		return Health;
	}
	@Override
	public void newTurn() {
		defence = 1;
		pAttack = 1;
	}
	@Override
	public double powerAttack() {
		pAttack = 1.5;
		return this.attack();
	}
	public double getDefense() {
		return resistance;
	}
	public void setMod() {
		mod++;
	}
	public double round(double value, int places) {
		double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
}