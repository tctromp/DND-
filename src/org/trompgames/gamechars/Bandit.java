package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;

public class Bandit extends GameCharacter{

	public Bandit(Entity entity){
		super(entity, "Bandit", "2d8+2", 11, 9);
		this.addMeleeAttack(new MeleeAttack());
	}
	
}
