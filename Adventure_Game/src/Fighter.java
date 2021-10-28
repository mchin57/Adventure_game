interface Fighter {
	double Health = 10;
	int dmg = 2;
	double defence = 1;
	double pAttack = 1;
	
	public double takedmg(double tdmg);
	
	public double attack();
	
	public void defend();
		
	public void newTurn();
		
	public double powerAttack();
		
	public double getHealth();
}

