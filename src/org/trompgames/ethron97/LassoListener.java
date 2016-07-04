package org.trompgames.ethron97;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
 
public class LassoListener implements Listener {
	
    Plugin plugin;
    public LassoListener(Plugin plugin) {
        this.plugin = plugin;
    }
   
   
    @EventHandler
    public void snowballHitEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getDamager();
            if(snowball.getCustomName().toString().equals("Pokeball")) {
                Player shooter = (Player) snowball.getShooter();
                shooter.playSound(shooter.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 0.5f, 1f);
                ItemStack i = shooter.getInventory().getItemInMainHand();
                if(i.getType().equals(Material.LEASH)) {
                    if(i.getItemMeta().getLore().get(0).toString().equals("Mob Mover")) {
                        ItemMeta meta = i.getItemMeta();
                        String s = event.getEntity().getUniqueId().toString();
                        List<String> l = new ArrayList<String>();
                        l.add("Mob Mover");
                        l.add(s);
                        meta.setLore(l);
                        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        if(event.getEntity().getCustomName() != null) {
                            meta.setDisplayName(ChatColor.DARK_AQUA+""+ChatColor.MAGIC+"###"+org.bukkit.ChatColor.RESET+"   "+ChatColor.AQUA+
                                    event.getEntity().getCustomName()+ChatColor.RESET+"   "+ChatColor.DARK_AQUA+ChatColor.MAGIC+"###");
                        } else {
                            meta.setDisplayName(ChatColor.DARK_AQUA+""+ChatColor.MAGIC+"###"+org.bukkit.ChatColor.RESET+"   "+ChatColor.AQUA+
                                    event.getEntity().getType().getName().toString()+ChatColor.RESET+"   "+ChatColor.DARK_AQUA+ChatColor.MAGIC+"###");
                        }
                        i.setItemMeta(meta);
                        shooter.updateInventory();
                    }
                }
            }
        }
        if(event.getDamager() instanceof Arrow && event.getEntity() instanceof Player) {
            Arrow a = (Arrow) event.getEntity();
            if(a.getShooter() != null) {
                if(a.getShooter() instanceof Player) {
                    return;
                }
            }
        }
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if(player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HOE)) {
                if(event.getEntity() instanceof Player) {
                    return;
                }
                if(event.getEntity().getCustomName() != null) {player.sendMessage("Removed entity "+event.getEntity().getCustomName().toString());}
                else {player.sendMessage("Removed entity "+event.getEntity().getType().toString());}
                event.getEntity().remove();
            }
        }
        event.setCancelled(true);
    }
   
   
    @EventHandler
    public void projectileLaunch(ProjectileLaunchEvent event) {
        Player shooter = (Player) event.getEntity().getShooter();
        if(shooter.getInventory().getItemInMainHand().getType().equals(Material.LEASH) &&
                shooter.getInventory().getItemInMainHand().getItemMeta().getLore().get(0).toString().equals("Mob Mover")) {
            event.getEntity().setCustomName("Pokeball");
            event.getEntity().setVelocity(event.getEntity().getVelocity().multiply(3));
        }
    }
   
    @EventHandler(priority = EventPriority.HIGH)
    public void playerInteractEvent(PlayerInteractEvent event) {
        if(event.getHand().equals(EquipmentSlot.OFF_HAND)) {  return;  }
        Player player = event.getPlayer();
        Block block = null;
        if(event.getClickedBlock() != null) {block = event.getClickedBlock();}
        ItemStack inHand = player.getInventory().getItemInMainHand();
        if(inHand.getType().equals(Material.LEASH)) {event.setCancelled(lassoThings(player, event, event.getAction(), block));}
    }
   
    @EventHandler
    public void entityInteract(PlayerInteractEntityEvent event) {  
        if(event.getHand().equals(EquipmentSlot.OFF_HAND)) {  event.setCancelled(true);  }
        //event.setCancelled(lassoThings(event.getPlayer(), null, null));
    }
   
 
    @EventHandler
    public void armorStandInteract(PlayerArmorStandManipulateEvent event) {
        if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.LEASH)) {
            if(event.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
                lassoThings(event.getPlayer(), event, null, null);
            }
        }
        if(event.getRightClicked().getCustomName() != null) {
            event.setCancelled(true);
        }
    }
   
   
    @EventHandler
    public void interactAtEntity(PlayerInteractAtEntityEvent event) {
        if(event.getHand().equals(EquipmentSlot.OFF_HAND)) {return;}
        Player player = event.getPlayer();
        Entity e = event.getRightClicked();
        ItemStack i = player.getItemInHand();
        if(event.getRightClicked() instanceof Player) {return;}
        if(i.getType().equals(Material.LEASH)) {
            if(i.getItemMeta().getLore().get(0).toString().equals("Mob Mover")) {
                if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA+"Mob Mover")) {
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            ItemMeta meta = i.getItemMeta();
                            String s = e.getUniqueId().toString();
                            List<String> l = new ArrayList<String>();
                            l.add("Mob Mover");
                            l.add(s);
                            meta.setLore(l);
                            meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            if(e.getCustomName() != null) {
                                meta.setDisplayName(ChatColor.DARK_AQUA+""+ChatColor.MAGIC+"###"+org.bukkit.ChatColor.RESET+"   "+ChatColor.AQUA+
                                        e.getCustomName()+ChatColor.RESET+"   "+ChatColor.DARK_AQUA+ChatColor.MAGIC+"###");
                            } else {
                                meta.setDisplayName(ChatColor.DARK_AQUA+""+ChatColor.MAGIC+"###"+org.bukkit.ChatColor.RESET+"   "+ChatColor.AQUA+
                                        e.getType().getName().toString()+ChatColor.RESET+"   "+ChatColor.DARK_AQUA+ChatColor.MAGIC+"###");
                            }
                            i.setItemMeta(meta);
                            player.updateInventory();
                        }
                    }, 1L);
                }
            }
        }
        return;
    }
   
    public boolean lassoThings(Player player, Event event, Action action, Block block) {
        ItemStack inHand = (ItemStack) player.getInventory().getItemInMainHand();
        if(inHand.getItemMeta().hasLore() == false) {return false;}
        if(inHand.getType().equals(Material.LEASH) && inHand.getItemMeta().getLore().get(0).toString().equals("Mob Mover")) {
            List<String> l = new ArrayList<String>();
            l.add("Mob Mover");
            l.add("");
            ItemMeta meta = inHand.getItemMeta();
            if(event.getEventName().equals("PlayerArmorStandManipulateEvent")) {
                if(meta.getLore().get(1).equals("")) {
                    player.launchProjectile(Snowball.class);
                    return false;
                }
                if(inHand.getType().equals(Material.LEASH)) {
                    if(meta.hasLore() == false) {return false;}
                    if(meta.getLore().get(0).equals("Mob Mover")) {
                        if(meta.getLore().get(1) != null) {
                            String uuid = meta.getLore().get(1).toString();
                            for(Entity entity : Bukkit.getWorld("world").getEntities()) {
                                if(entity.getUniqueId().toString().equals(uuid)) {
                                    entity.teleport(player);
                                    entity.teleport(entity);
                                    entity.getLocation().setYaw(player.getLocation().getYaw());
                                    entity.getLocation().setPitch(player.getLocation().getPitch());
                                }
                            }
                        }
                    }
                }
            }
            if(action != null) {
                if(action.equals(Action.LEFT_CLICK_BLOCK) || action.equals(Action.LEFT_CLICK_AIR)) {
                    meta.setLore(l);
                    meta.removeEnchant(Enchantment.DAMAGE_ALL);
                    meta.setDisplayName(ChatColor.AQUA+"Mob Mover");
                    inHand.setItemMeta(meta);
                    player.updateInventory();
                    if(action != null) {
                        if(action.equals(Action.LEFT_CLICK_BLOCK)) {
                            if(!block.getType().equals(null)) {
                                return true;
                            }
                        }
                    }
                }
                if(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    if(meta.getLore().get(1).equals("")) {
                        player.launchProjectile(Snowball.class);
                        return false;
                    }
                    if(inHand.getType().equals(Material.LEASH)) {
                        if(meta.hasLore() == false) {return false;}
                        if(meta.getLore().get(0).equals("Mob Mover")) {
                            if(meta.getLore().get(1).equals("")) {return false;}
                            String uuid = meta.getLore().get(1).toString();
                            for(Entity entity : Bukkit.getWorld("world").getEntities()) {
                                if(entity.getUniqueId().toString().equals(uuid)) {
                                    entity.teleport(player);
                                    player.playSound(player.getLocation(), Sound.BLOCK_CLOTH_PLACE, 1f, 1f);
                                }
                            }
                        }
                    }
                }
            }
        }
       
        return false;
    }
}