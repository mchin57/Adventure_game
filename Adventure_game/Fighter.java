package Adventure_game;

interface Fighter {
	int Health = 10;
	int dmg = 2;
	double defence = 1;
	double pAttack = 1;
	
	public int takedmg(int tdmg);
	
	public int attack();
	
	public void defend();
		
	public void newTurn();
		
	public int powerAttack();
		
	public int getHealth();
}

