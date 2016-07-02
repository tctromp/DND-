package org.trompgames.character;

import org.trompgames.roll.Roll;

public abstract class Attack {

	private String name;
	
	private int attackModifier;
	private int damage;
	private String damageRoll;
	
	public Attack(String name, int attackModifier, String damageRoll){
		this.name = name;
		this.attackModifier = attackModifier;
		this.damageRoll = damageRoll;
		calculateDamage();
	}
	
	public void calculateDamage(){
		this.damage = new Roll(damageRoll).getResult();
	}
	
	public int getAttackModifier(){
		return attackModifier;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public String getName(){
		return name;
	}
	
}
