package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;

public class Goblin extends GameCharacter{

	 public Goblin(Entity entity){
         super(entity, "Goblin", "2d6", 15, 9);
         this.addMeleeAttack(new MeleeAttack("Scimitar", 4, 1, "1d6+2"));
      }
	
}
