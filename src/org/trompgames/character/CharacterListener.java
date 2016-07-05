package org.trompgames.character;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class CharacterListener implements Listener{

	
	@EventHandler
	public void onInteract(PlayerInteractAtEntityEvent event){
		if(!event.getHand().equals(EquipmentSlot.HAND)) return;		
		if(!(event.getRightClicked() instanceof ArmorStand)) return;
		
		ArmorStand stand = (ArmorStand) event.getRightClicked();
		if(stand.getName() == null) return;
		
		GameCharacter gc = null;
		
		for(GameCharacter gcs : GameCharacter.getGameCharacters()){
			if(gcs.getEntity() != null && gcs.getEntity().equals(stand)) gc = gcs;
		}		
		if(gc == null) gc = GameCharacterType.createGameCharacter(stand, ChatColor.stripColor(stand.getName()));
		if(gc == null) return;
		
		Bukkit.broadcastMessage("Health " + gc.getHealth());
		
		
	}
	
	
}
