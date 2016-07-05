package org.trompgames.gamechars;

import org.bukkit.entity.Entity;
import org.trompgames.character.GameCharacter;
import org.trompgames.character.MeleeAttack;

public class Orog extends GameCharacter{
	public Orog(Entity entity){
        super(entity, "Orog", "5d8+20", 18, 9);
        this.addMeleeAttack(new MeleeAttack("Greataxe", 6, 1, "1d12+4"));
    }
}
