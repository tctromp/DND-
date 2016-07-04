package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;
import org.trompgames.character.RangedAttack;

public class Bandit extends GameCharacter{

	public Bandit(Entity entity){
		super(entity, "Bandit", "2d8+2", 12, 9);
		this.addMeleeAttack(new MeleeAttack("Scimitar", 3, 1, "1d6+1"));
		this.addRangedAttack(new RangedAttack("Light Crossbow", 3, 24, 96, "1d8+1"));
	}
	
	//Normal ranged: roll 1d20 + attk modifier
	//Disadvantaged: roll 2 1d20 + attk modifier and get the lower
	//NOT USED ATM: Advanted: roll 2 1d20 + attk modifier and get the higher
	
	public static void main(String[] args) {
		
		Bandit b = new Bandit(null);

		System.out.println("Name: " + b.getName());
		System.out.println("Health: " + b.getHealth());
		System.out.println("Armor Class: " + b.getArmorClass());
		System.out.println("Movement: " + b.getMovement());
	
		RangedAttack att = b.getRangedAttacks().get(0);
		
		for(int i = 0; i < 10; i++){
			
			int dam = att.attack();
			System.out.println("Attack: " + dam + " damage");
			
		}
		
	}
	
}
