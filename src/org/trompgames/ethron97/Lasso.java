package org.trompgames.ethron97;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Lasso {

	public static void lassoCommand(Player player){
		ItemStack lasso = new ItemStack(Material.LEASH);
        ItemMeta meta = lasso.getItemMeta();
        meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS);
        
        meta.setDisplayName(ChatColor.AQUA + "Mob Mover");
        List<String> l = new ArrayList<String>();
        l.add("Mob Mover");
        l.add(null);
        meta.setLore(l);
        lasso.setItemMeta(meta);
        player.getInventory().addItem(lasso);
        player.sendMessage(ChatColor.GOLD + "You have been given a lasso! Right click a mob to select it, then right click somewhere else to teleport them!");
	}
	
}
