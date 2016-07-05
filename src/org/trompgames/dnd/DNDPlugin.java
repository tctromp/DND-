package org.trompgames.dnd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.trompgames.character.CharacterListener;
import org.trompgames.ethron97.Lasso;
import org.trompgames.ethron97.LassoListener;

import net.md_5.bungee.api.ChatColor;

public class DNDPlugin extends JavaPlugin{

	@Override
	public void onEnable(){
		
		this.getServer().getPluginManager().registerEvents(new LassoListener(this), this);
		this.getServer().getPluginManager().registerEvents(new CharacterListener(), this);

		Bukkit.broadcastMessage(ChatColor.GOLD + "DNDPlugin Enabled");
	}
	
	@Override
	public void onDisable(){
		
	}
	
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(!(sender instanceof Player)) return false;
		Player player = (Player) sender;		
		
		if(cmd.getName().equalsIgnoreCase("lasso")){
			Lasso.lassoCommand(player);
			return true;
		}		
		return false;
	}
	
}
