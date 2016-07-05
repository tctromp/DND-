package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;
import org.trompgames.character.RangedAttack;

public class BugBear extends GameCharacter{

	public BugBear(Entity entity){
		super(entity, "BugBear", "5d8+5", 16, 9);
		this.addMeleeAttack(new MeleeAttack("Morning Star", 4, 1, "2d8+2"));
		//this.addRangedAttack(new RangedAttack("Light Crossbow", 3, 24, 96, "1d8+1"));
	}
	
	
	
	
}
