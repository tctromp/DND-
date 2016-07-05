package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;

public class Orc extends GameCharacter{

	 public Orc(Entity entity){
         super(entity, "Orc", "2d8+6", 13, 9);
         this.addMeleeAttack(new MeleeAttack("Greataxe", 5, 1, "1d12+3"));
     }
	
}
