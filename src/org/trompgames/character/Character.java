package org.trompgames.character;

import java.util.ArrayList;
import java.util.List;

import org.trompgames.roll.Roll;

public class Character {

	private String name;
	private String healthRoll;
	private int health;
	private int armorClass;
	private int movement;
	private List<MeleeAttack> meleeAttacks = new ArrayList<>();
	private List<RangedAttack> rangedAttacks = new ArrayList<>();
	
	
	public Character(String name, String healthRoll, int armorClass, int movement, MeleeAttack meleeAttack1){
		this.name = name;
		this.healthRoll = healthRoll;
		this.armorClass = armorClass;
		this.movement = movement;
		calculateHealth();
	}
	
	private void calculateHealth(){
		this.health = new Roll(healthRoll).getResult();
	}

	public String getName(){
		return name;
	}
	
	public int getHealth(){
		return health;		
	}
	
	public int getArmorClass(){
		return armorClass;
	}
	
	public int getMovement(){
		return movement;
	}
	
	public void addMeleeAttack(MeleeAttack attack){
		meleeAttacks.add(attack);
	}
	
	public void addRangedAttack(RangedAttack attack){
		rangedAttacks.add(attack);
	}
	

}
