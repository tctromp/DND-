package org.trompgames.character;

public class RangedAttack extends Attack{

	private int reachNormal;
	private int reachDisanvantaged;
	
	
	public RangedAttack(String name, int attackModifier, int reachNormal, int reachDisanvantaged, String damageRoll) {
		super(name, attackModifier, damageRoll);
		this.reachDisanvantaged = reachDisanvantaged;
		this.reachNormal = reachNormal;
		// TODO Auto-generated constructor stub
	}


	public int getReachNormal() {
		return reachNormal;
	}


	public int getReachDisanvantaged() {
		return reachDisanvantaged;
	}
	
	
	
	



}
