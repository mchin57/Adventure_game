import java.lang.Math;

class Monster implements Fighter{
	private double Health;
	private int dmg;
	private double defence = 1;
	private double pAttack = 1;
	
	public Monster(int lvl) {//setting the monster stats
		double multiplier = Math.random()*2;
		dmg = (int)(1+(1.12*lvl*(2-multiplier)));
		Health = (int)(2+(multiplier*1.7*lvl));
		this.newTurn();
	}
	public double getHealth() {
		return Health;
	}
	public boolean dead() {
		if(Health <=0 ) {
			return true;
		}
		else
			return false;
	}
	public void setHealth(int a) {
		Health = a;
	}
	public double takedmg(double tdmg) {
		Health = Health - (int)(defence*pAttack*tdmg);
		return(this.Health);
		}
	public double attack() {
		return (dmg*pAttack);
	}
	public void defend() {
		defence = .5;
	}
	public void newTurn() {
		defence = 1;
		pAttack = 1;
	}
	public double powerAttack() {
		pAttack = 1.5;
		return this.attack();
	}
}