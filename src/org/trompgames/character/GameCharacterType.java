package org.trompgames.character;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.trompgames.gamechars.Bandit;
import org.trompgames.gamechars.BugBear;
import org.trompgames.gamechars.DireWolf;
import org.trompgames.gamechars.Goblin;
import org.trompgames.gamechars.HalfOgre;
import org.trompgames.gamechars.Orc;
import org.trompgames.gamechars.Orog;

public enum GameCharacterType {

	
	BANDIT("Bandit", Bandit.class),
	BUGBEAR("BugBear", BugBear.class),
	DIREWOLF("DireWolf", DireWolf.class),
	GOBLIN("Goblin", Goblin.class),
	HALFOGRE("HalfOgre", HalfOgre.class),
	ORC("Orc", Orc.class),
	OROG("Orog", Orog.class);
	
	private String name;
	private Class type;
	
	private GameCharacterType(String name, Class type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName(){
		return name;
	}
	
	public static GameCharacterType getGameCharacterType(String name){
		for(GameCharacterType gct : GameCharacterType.values()){
			if(gct.getName().contains(name)) return gct;
		}
		return null;
	}
	
	public static GameCharacter createGameCharacter(Entity e, String name){
		GameCharacterType gct = getGameCharacterType(name);
		if(gct == null) return null;
		GameCharacter gc = null;
		try {
			gc = (GameCharacter) gct.type.asSubclass(gct.type).getConstructor(Entity.class).newInstance(e);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block			e1.printStackTrace();
		}
		return gc;
	}
	
	
	
	
	
	
}
