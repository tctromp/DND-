package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;
import org.trompgames.character.RangedAttack;

public class Bandit extends GameCharacter{

	public Bandit(Entity entity){
		super(entity, "Bandit", "2d8+2", 11, 9);
		this.addMeleeAttack(new MeleeAttack("Scimitar", 3, 1, "1d6+1"));
		this.addRangedAttack(new RangedAttack("Light Crossbow", 3, 24, 96, "1d8+1"));
	}
	
}
