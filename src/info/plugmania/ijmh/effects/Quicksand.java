package info.plugmania.ijmh.effects;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import info.plugmania.ijmh.Util;
import info.plugmania.ijmh.ijmh;

public class Quicksand {

	ijmh plugin;
	public HashMap<Integer, HashMap<String, String>> c = new HashMap<Integer, HashMap<String, String>>();
	public HashMap<Player, Integer> quicksand = new HashMap<Player, Integer>();
	public long timer = 0;
	
	public Quicksand(ijmh instance){
		plugin = instance;
	}
	
	public void init() {
		plugin.feature.put("Quicksand", "quicksand");
		c.put(0, plugin.util.cRow("skipworld", null, "list", null, null));
		c.put(1, plugin.util.cRow("message", null, "boolean", "true", "true/false/*"));
		c.put(2, plugin.util.cRow("chance", null, "integer", "10", "1-100"));
		c.put(3, plugin.util.cRow("chancemod", null, "integer", "1", "1-?"));
		c.put(4, plugin.util.cRow("cooldown", null, "integer", "5", "1-? seconds"));
		c.put(5, plugin.util.cRow("jumps", null, "integer", "5", "1-?"));
		c.put(6, plugin.util.cRow("text", null, null, ChatColor.GREEN + "* Cooldown is the duration before sinking 1 block deeper", null));
	}	
	
	public boolean command(CommandSender sender, String[] args) {
		if(args.length==1) {
			plugin.util.cSend(c, args, sender);
		} else {
			Util.cmdExecute(sender, args);
		} 
		return true;
	}
	
	public void main(Event e) {
		
		if(Util.config("quicksand",null).getBoolean("active")){
			
			if(e.getEventName().equalsIgnoreCase("PlayerMoveEvent")) {
				PlayerMoveEvent event = (PlayerMoveEvent) e;
				Player player = event.getPlayer();
				Location pUnder = player.getLocation().add(0, -1, 0);
				Date curDate = new Date();
				long curTime = curDate.getTime();
				
				if(!player.getGameMode().equals(GameMode.CREATIVE) && !Util.config("quicksand",null).getList("skipworld").contains(player.getWorld().getName())) {
					if(!player.hasPermission("ijmh.immunity.quicksand")) {
						if(
								(pUnder.getBlock().getType().equals(Material.SAND) || pUnder.getBlock().getType().equals(Material.SANDSTONE)) &&
								!player.isInsideVehicle() &&
								!event.getTo().getBlock().isLiquid()
								) {
							if(!plugin.quicksand.quicksand.containsKey(player) && Util.pctChance(Util.config("quicksand",null).getInt("chance"),Util.config("quicksand",null).getInt("chancemod"))) {
								plugin.quicksand.quicksand.put(player, 0);
								
								player.teleport(pUnder);
								timer = curTime + (Util.config("quicksand",null).getInt("cooldown") * 1000);
								
								if(Util.config("quicksand",null).getBoolean("message")) player.sendMessage(ChatColor.GOLD + Util.chatColorText(Util.language.getString("lan_21")));
							} else if(event.getFrom().getY() < event.getTo().getY() && plugin.quicksand.quicksand.containsKey(player)) {
								plugin.quicksand.quicksand.put(player, plugin.quicksand.quicksand.get(player)+1);
								Util.toLog("Jumps: " + plugin.quicksand.quicksand.get(player), true);
								player.teleport(event.getFrom());
								if(plugin.quicksand.quicksand.get(player)>=Util.config("quicksand",null).getInt("jumps")) {
									player.teleport(player.getLocation().add(new Vector(0,1,0)));
									if(player.getLocation().getBlock().getType().equals(Material.AIR)) {
										Util.toLog("Removed by AIR", true);
										plugin.quicksand.quicksand.remove(player);
										if(Util.config("quicksand",null).getBoolean("message")) player.sendMessage(ChatColor.GOLD + Util.chatColorText(Util.language.getString("lan_22")));
									}
								} 
								else if(curTime>timer) {
									Util.toLog("Get back", true);
									player.teleport(pUnder);
									timer = curTime + (Util.config("quicksand",null).getInt("cooldown") * 1000);
								}
							} else if(curTime>timer && plugin.quicksand.quicksand.containsKey(player)) {
								player.teleport(pUnder);
								Util.toLog("Get back", true);
								timer = curTime + (Util.config("quicksand",null).getInt("cooldown") * 1000);
							}
							
						} 
						else if(
								plugin.quicksand.quicksand.containsKey(player) &&
								!pUnder.getBlock().getType().equals(Material.SAND) &&
								!pUnder.getBlock().getType().equals(Material.SANDSTONE) &&
								!pUnder.getBlock().getType().equals(Material.AIR) 
								){
							plugin.quicksand.quicksand.remove(player);
							Util.toLog("Removed by pUnder being: "+pUnder.getBlock().getType(), true);
						}
					}
				}
				
			} 
			else if(e.getEventName().equalsIgnoreCase("BlockBreakEvent")) {
				BlockBreakEvent event = (BlockBreakEvent) e;
				Player player = event.getPlayer();
				
				// PREVENT BREAKOUT 
				if(!player.getGameMode().equals(GameMode.CREATIVE) && !Util.config("quicksand",null).getList("skipworld").contains(player.getWorld().getName())) {
					if(plugin.quicksand.quicksand.containsKey(player)) {
						event.setCancelled(true);
					}
				}
			} 
			else if(e.getEventName().equalsIgnoreCase("PlayerCommandPreprocessEvent")) {
				PlayerCommandPreprocessEvent event = (PlayerCommandPreprocessEvent) e;
				Player player = event.getPlayer();
				
				// PREVENT COMMANDS 
				if(plugin.quicksand.quicksand.containsKey(player)) {
					event.setCancelled(true);
				}				
			} 
			else if(e.getEventName().equalsIgnoreCase("AsyncPlayerChatEvent")) {
				AsyncPlayerChatEvent event = (AsyncPlayerChatEvent) e;
				Player player = event.getPlayer();
				
				// PREVENT COMMANDS POSTED IN CHAT
				if(plugin.quicksand.quicksand.containsKey(player)) {
					if(event.getMessage().substring(0, 1)=="/") event.setCancelled(true);
					Util.toLog(event.getMessage().substring(0, 1), true);
				}
			}
			else if(e.getEventName().equalsIgnoreCase("EntityDamageEvent")) {
				EntityDamageEvent event = (EntityDamageEvent) e;
				
				if(event.getEntity() instanceof Player) {
					Player player = (Player) event.getEntity();
					if(!player.getGameMode().equals(GameMode.CREATIVE)) {
						if(event.isCancelled() && plugin.quicksand.quicksand.containsKey(player)) {
							if(event.getCause().equals(DamageCause.SUFFOCATION)) {
								player.damage(event.getDamage());
							}
						}
					}
				}
			}
			else if(e.getEventName().equalsIgnoreCase("PlayerDeathEvent")) {
				PlayerDeathEvent event = (PlayerDeathEvent) e;
				Player player = event.getEntity();
				
				// REMOVE FROM LIST WHEN DEAD
				if(plugin.quicksand.quicksand.containsKey(player)) {
					plugin.quicksand.quicksand.remove(player);
					event.setDeathMessage(player.getName() + " " + Util.chatColorText(Util.language.getString("lan_23")));
				}
			}
		}
	}
}
