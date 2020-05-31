package Adventure_game;

public class Armor extends Item{
	private double protection;
	private int type = 1; //1-3 Helmet, chest, boots
	
	public Armor(int lvl, int type) { //set armor stats
		this.type = type;
		
		double mult = Math.random();
		if (mult > .7) {
			mult = mult-.3;
		}
		
		switch(type) {
		case 1: //for helmet
			this.protection = mult*(15*(-Math.pow(.9,lvl))+21.5);
			break;
		case 2://for chest
			this.protection = mult*(25*(-Math.pow(.9,lvl))+36);
			break;
		case 3://for boots
			this.protection = mult*(10*(-Math.pow(.9, lvl))+14.3);
			break;
		}
	} //end armor stats
	
	public Armor(int lvl, int tpe, int prot) {//constructor for base armor
//		System.out.println("Armor creation test");
		type = tpe;
		protection = prot;
	}
	public String armorType() {//tells armor type
		String tpe;
		switch(this.getType()) {
		case 1:
			tpe = "Helmet";
			break;
		case 2:
			tpe = "Chest plate";
			break;
		case 3:
			tpe = "Boots";
			break;
		default:
			tpe = "Error bad armor type";
		}
		return tpe;
	}//end Armor Type
	public String getProtection() {
		return("This armor piece deflects roughtly " + round(this.protection,2) + "% of damage");
	}
	public double getArmor() {
		return protection;
	}
	public int getType() {
		return this.type;
	}
	public double round(double value, int places) {
		double scale = Math.pow(10, places);
	    return Math.round(value * scale) / scale;
	}
}