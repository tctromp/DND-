package org.trompgames.gamechars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.trompgames.character.Attack;
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
		

		
		List<GameCharacter> gameCharacters = new ArrayList<>();
		

		for(int i = 0; i < 100; i++){
			Bandit b = new Bandit(null);
			b.setName("Bandit(" + i + ")");
			gameCharacters.add(b);			
		}
		
		for(int i = 0; i < 5; i++){
			BugBear b = new BugBear(null);
			b.setName("BugBear(" + i + ")");
			gameCharacters.add(b);			
		}
		
		for(int i = 0; i < 5; i++){
			DireWolf b = new DireWolf(null);
			b.setName(b.getName() + "(" + i + ")");
			gameCharacters.add(b);			
		}
		
		for(int i = 0; i < 5; i++){
			Goblin b = new Goblin(null);
			b.setName(b.getName() + "(" + i + ")");
			gameCharacters.add(b);			
		}
		
		for(int i = 0; i < 5; i++){
			HalfOgre b = new HalfOgre(null);
			b.setName(b.getName() + "(" + i + ")");
			gameCharacters.add(b);			
		}
		
		for(int i = 0; i < 5; i++){
			Orc b = new Orc(null);
			b.setName(b.getName() + "(" + i + ")");
			gameCharacters.add(b);			
		}
		
		for(int i = 0; i < 1; i++){
			Orog b = new Orog(null);
			b.setName(b.getName() + "(" + i + ")");
			gameCharacters.add(b);			
		}
		

		int round = 1;
		int i = 0;
		while(true){
			if(i >= gameCharacters.size()) i = 0;
			
			if(getAliveChars(gameCharacters) <= 1) break;
			if(oneTypeAlive(gameCharacters)) break;
			
			GameCharacter character = gameCharacters.get(i);
			if(character.getHealth() <= 0){				
				i++;
				continue;
			}
			
			System.out.println("---[Round: " + round + "]" + "---");
			
			GameCharacter target = getTarget(gameCharacters, character);
			
			
			attack(character, target);	
			
			
			i++;
			round++;			
		}	
		
		System.out.println();
		System.out.println("Result: ");
		System.out.println("Round: " + round);
		
		for(GameCharacter gameChar : gameCharacters){
			if(gameChar.getHealth() > 0) System.out.println(gameChar.getName() + " won with " + gameChar.getHealth() + "hp");
			//System.out.println(gameChar.getName() + " Health: " + gameChar.getHealth());			
		}
		
		
		//System.out.println(b1.getName() + " missed: " + b1Misses + " times");
		//System.out.println(b2.getName() + " missed: " + b2Misses + " times");

		
	}
	
	
	
	public static Random rand = new Random();
	
	public static GameCharacter getTarget(List<GameCharacter> gameChars, GameCharacter curChar){
		GameCharacter end = null;
		while(end == null || end.equals(curChar)){
			end = gameChars.get(rand.nextInt(gameChars.size()));
			if(end.getHealth() <= 0 || (end.getClass().equals(curChar.getClass()))) end = null;
			
		}
		return end;	
	}
	
	public static int getAliveChars(List<GameCharacter> gameChars){
		int i = 0;
		for(GameCharacter gameChar : gameChars){
			if(gameChar.getHealth() > 0) i++;
		}
		return i;
	}
	
	public static boolean oneTypeAlive(List<GameCharacter> gameChars){
		GameCharacter gc = null;
		for(GameCharacter charr : gameChars){
			if(charr.getHealth() <= 0) continue;
			if(gc == null){
				gc = charr;
				continue;
			}
			if(!gc.getClass().equals(charr.getClass())) return false;
		}
		return true;		
	}
	
	public static boolean attack(GameCharacter attacker, GameCharacter defender){
		
		Attack att = attacker.getMeleeAttacks().get(0);
		
		int attRoll = att.getAttackRoll();
		
		
		System.out.println(attacker.getName() + " rolled " + attRoll + " attk");	
		
		if(attRoll >= defender.getArmorClass()){
			int damRoll = att.getDamageRoll();
			defender.setHealth(defender.getHealth()-damRoll);
			
			System.out.println(attacker.getName() + " dealt " + damRoll + " damage");
			System.out.println(defender.getName() + " has " + defender.getHealth());
			if(defender.getHealth() <= 0) System.out.println(defender.getName() + " has died!");
			return true;
		}else{
			System.out.println(attacker.getName() + " missed his attack!");
			return false;
		}
	}
	
	
	
	
}
