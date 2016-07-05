package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;

public class HalfOgre extends GameCharacter{
	 public HalfOgre(Entity entity){
         super(entity, "HalfOgre", "4d10+8", 12, 9);
         this.addMeleeAttack(new MeleeAttack("Battleaxe", 5, 1, "2d10+3"));
     }
}
