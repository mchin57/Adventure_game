package Adventure_game;

import java.lang.Math;

class Monster implements Fighter{
	private int Health;
	private int dmg;
	private double defence = 1;
	private double pAttack = 1;
	
	public Monster(int lvl) {//setting the monster stats
		double multiplier = Math.random()*2;
		dmg = (int)(1+(1.25*lvl*(2-multiplier)));
		Health = (int)(lvl+(multiplier*2.5*lvl));
	}
	public int getHealth() {
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
	public int takedmg(int tdmg) {
		Health = Health - (int)(defence*pAttack*tdmg);
		return(this.Health);
		}
	public int attack() {
		return (int)(dmg*pAttack);
	}
	public void defend() {
		defence = .5;
	}
	public void newTurn() {
		defence = 1;
		pAttack = 1;
	}
	public int powerAttack() {
		pAttack = 1.5;
		return this.attack();
	}
}