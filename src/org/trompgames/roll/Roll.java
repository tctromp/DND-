package org.trompgames.roll;

import java.util.Random;

public class Roll {
	private String string;
	
	private int dice = -1;
	private int sides = -1;
	private int add = -1;
	private boolean goodParse = true;		
	
	private int[] rolls;
	
	private static Random rand = new Random();
	
	public Roll(String string){
		this.string = string;
		parseString();
		
		if(goodParse) calculateRolls();
	}
	
	public void calculateRolls(){
		rolls = new int[dice];
		for(int i = 0; i < dice; i++){
			rolls[i] = roll(sides);
		}
	}
	
	public int getResult(){
		int tot = add;
		for(int i = 0; i < dice; i++){
			tot += rolls[i];
		}
		return tot;
	}
	
	public int[] getRolls(){
		return rolls;
	}
	
	private void parseString(){
		String s = "";			
		try{			
			for(int i = 0; i < string.length(); i++){
				char c = string.charAt(i);
				if(dice == -1){
					if(c == 'd'){
						dice = Integer.parseInt(s);
						s = "";
					}else s += c;						
				}else if(sides == -1){
					if(c == '+'){
						sides = Integer.parseInt(s);
						s = "";
					}else s += c;
				}else{
					s += c;
				}				
			}				
			if(sides == -1){
				if(add == -1) add = 0;
				sides = Integer.parseInt(s);
			}else{
				add = Integer.parseInt(s);
			}
		}catch(Exception e){
			goodParse = false;
		}		
	}
	
	public int getDice(){
		return dice;
	}
	
	public int getSided(){
		return sides;
	}
	
	public int getAdd(){
		return add;
	}
	
	public boolean isGoodParse(){
		return goodParse;
	}
	
	public int roll(int sides){
		return rand.nextInt(sides) + 1;
	}
	
	
	
	
	@Override
	public String toString(){
		if(add != 0)
			return "" + dice + "d" + sides + "+" + add;
		return "" + dice + "d" + sides;

	}
	
	
}

