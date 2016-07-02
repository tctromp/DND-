package org.trompgames.character;

public class MeleeAttack extends Attack{

	private int reach;
	
	public MeleeAttack(String name, int attackModifier, int reach, String damageRoll) {
		super(name, attackModifier, damageRoll);
		this.reach = reach;		
	}
	
	public int getReach(){
		return reach;
	}
	
	

}
