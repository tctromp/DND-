package org.trompgames.character;

import org.trompgames.roll.Roll;

public abstract class Attack {

	private String name;
	
	private int attackModifier;
	private String damageRoll;
	
	public Attack(String name, int attackModifier, String damageRoll){
		this.name = name;
		this.attackModifier = attackModifier;
		this.damageRoll = damageRoll;
	}
	
	
	
	public int getAttackModifier(){
		return attackModifier;
	}
	
	public int attack(){
		return new Roll(damageRoll).getResult() + attackModifier;		
	}
	
	public String getName(){
		return name;
	}
	
}
