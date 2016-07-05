package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;

public class DireWolf extends GameCharacter{
	public DireWolf(Entity entity) {
        super(entity, "Dire Wolf", "5d10+10", 14, 15);
        this.addMeleeAttack(new MeleeAttack("Bite", 5, 1, "2d6+3"));
     }
}
