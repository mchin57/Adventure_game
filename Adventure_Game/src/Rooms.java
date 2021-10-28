import java.util.ArrayList;
import java.lang.Math;

public class Rooms {
	private ArrayList<Item> WAndM = new ArrayList<>();
	private Monster lem;
	
	public boolean CheckMonster() {
		return(!lem.dead());
	}
	
	public ArrayList<Item> genItems(int lvl) {
		int Itms = (int) (Math.random()*10);		
		switch(Itms) {//deciding what loot is in the room
		case 1://new weapon
			WAndM.add(new Weapon(lvl));
			break;
		case 2: //new helmet
			WAndM.add(new Armor(lvl,1));
			break;
		case 3://new Chest
			WAndM.add(new Armor(lvl,2));
			break;
		case 4://new boots
			WAndM.add(new Armor(lvl,3));
			break;
		case 5://two random armor
			int pcs = ((int)(Math.random()*10));//randomly deciding what the armor piece is
			if (pcs > 7) {
				pcs = pcs-7;
			}
			else if(pcs > 3) {
				pcs = pcs - 3;
				if(pcs==4) {
					pcs=1;
				}
			}
			WAndM.add(new Armor(lvl,pcs));
			int pcs2 = ((int)(Math.random()*10));//randomly deciding what the second armor is
			if (pcs2 > 7) {
				pcs2 = pcs2-7;
			}
			else if(pcs > 3) {
				pcs2 = pcs2 - 3;
				if(pcs2==4) {
					pcs2=1;
				}
			}
			WAndM.add(new Armor(lvl,pcs2));
			break;
		case 6:
		case 7://random armor and weapon
			int pcs3 = ((int)(Math.random()*10));
			if (pcs3 > 7) {
				pcs3 = pcs3-7;
			}
			else if(pcs3 > 3) {
				pcs3 = pcs3 - 3;
				if(pcs3==4) {
					pcs3=1;
				}
			}
			WAndM.add(new Armor(lvl,pcs3));
			WAndM.add(new Weapon(lvl));
			break;
		default:
		}//end item generation
		return WAndM;
	}//end item generation
	public Monster genMonster(int lvl) {
		Monster lem = new Monster(lvl);
		return lem;
	}
	public void searchRoom() {
		for (int i=0; i < WAndM.size(); i++) {
			if (WAndM.get(i) instanceof Armor){
				System.out.println((i)+" "+((Armor) WAndM.get(i)).armorType());
				System.out.println(((Armor) WAndM.get(i)).getProtection());
				System.out.println("");
			}
			else if(WAndM.get(i) instanceof Weapon){
				System.out.println((i) +" Sword");
				System.out.println("This sword deals "+((Weapon) WAndM.get(i)).getDmg()+" damage");
				System.out.println("");
			}
			else
				System.out.println("The room is empty");
		}
	}	
	public Monster getMonster() {
		System.out.println("Test Getmonster");
		return lem;
	}
	public Item getItem(int pos) {
		Item temp = WAndM.get(pos+1);
		WAndM.remove(pos+1);
		return temp;
	}
}
