public class Weapon extends Item{
	private int dmg;
	
	public Weapon(int lvl){
		dmg = (int)((Math.random()*5)+(.7*lvl)*Math.random());
	}
	public Weapon(int lvl, int damg) {
		dmg = damg;
	}
	
	public int getDmg() {
		return this.dmg;
	}
}

