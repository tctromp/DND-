package org.trompgames.character;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.trompgames.roll.Roll;

public abstract class GameCharacter {

	private String name;
	private String healthRoll;
	private int health;
	private int armorClass;
	private int movement;
	private List<MeleeAttack> meleeAttacks = new ArrayList<>();
	private List<RangedAttack> rangedAttacks = new ArrayList<>();
	private Entity entity;
	
	public GameCharacter(Entity entity, String name, String healthRoll, int armorClass, int movement){
		this.entity = entity;
		this.name = name;
		this.healthRoll = healthRoll;
		this.armorClass = armorClass;
		this.movement = movement;
		calculateHealth();
	}
	
	public Entity getEntity(){
		return entity;
	}
	
	private void calculateHealth(){
		this.health = new Roll(healthRoll).getResult();
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setHealth(int health){
		this.health = health;
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
	
	public List<MeleeAttack> getMeleeAttacks(){
		return meleeAttacks;
	}
	
	public List<RangedAttack> getRangedAttacks(){
		return rangedAttacks;
	}
	
	

}
