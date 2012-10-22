package info.plugmania.ijmh.effects;

import java.util.HashMap;

import info.plugmania.ijmh.Util;
import info.plugmania.ijmh.ijmh;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class HeavyDuty {

	ijmh plugin;
	
	public HeavyDuty(ijmh instance){
		plugin = instance;
	}

	public void command(CommandSender sender, String[] args) {
		
		if(args.length==1) {
			HashMap<Integer, HashMap<String, String>> c = new HashMap<Integer, HashMap<String, String>>();
			c.put(0, plugin.util.cRow("skipworld", null, "list", null, null));
			c.put(1, plugin.util.cRow("walkspeed", null, "double", "0.1", null));
			c.put(2, plugin.util.cRow("flyspeed", null, "double", "0.2", null));
			c.put(3, plugin.util.cRow("modifier", null, "double", "1", "1-5"));
			c.put(4, plugin.util.cRow("text", null, null, ChatColor.GREEN + "* There are limits to speed, for both client and server performance:", null));
			c.put(5, plugin.util.cRow("text", null, null, ChatColor.GREEN + "** 1 is moving very fast", null));
			c.put(6, plugin.util.cRow("text", null, null, ChatColor.GREEN + "** 0 is not moving at all", null));
			c.put(7, plugin.util.cRow("text", null, null, ChatColor.GREEN + "** negative values make you move backwards", null));
			c.put(8, plugin.util.cRow("text", null, null, ChatColor.GREEN + "* modifier must be larger than 0 and lesser or eaqual to 1.0 where max armor means no movement.", null));
			c.put(9, plugin.util.cRow("text", null, null, ChatColor.RED + "! Walkspeed effect is broken atm due to the client itself not changing the value.", null));
			plugin.util.cSend(c, args, sender);
		}
	}
	
	public void main(Event e) {
		
		if(Util.config("heavy",null).getBoolean("active")){
			if(e.getEventName().equalsIgnoreCase("PlayerJoinEvent")) {
				PlayerJoinEvent event = (PlayerJoinEvent) e;
				Player player = (Player) event.getPlayer();
					
				this.extend(player);
			}
			else if(e.getEventName().equalsIgnoreCase("InventoryCloseEvent")) {
				InventoryCloseEvent event = (InventoryCloseEvent) e;
				Player player = (Player) event.getPlayer();
					
				this.extend(player);
			}
			else if(e.getEventName().equalsIgnoreCase("PlayerGameModeChangeEvent")) {
				PlayerGameModeChangeEvent event = (PlayerGameModeChangeEvent) e;
					Player player = (Player) event.getPlayer();
						
					this.extend(player);
			}
		}
	}
	
	public void extend(Player player) {
		
		if(!player.getGameMode().equals(GameMode.CREATIVE)) {
			if(!Util.config("heavy",null).getList("skip_world").contains(player.getWorld().getName())) {
				if(!player.hasPermission("ijmh.immunity.heavy")) {
											
					double WalkSpeed =  Util.config("heavy",null).getDouble("walkspeed");
					double FlySpeed = Util.config("heavy",null).getDouble("flyspeed");
			
					double curProt = Util.getPlayerArmorValue(player)*Util.config("heavy",null).getDouble("modifier");
			
					if(curProt>0 && !player.getGameMode().equals(GameMode.CREATIVE)) {
						player.setWalkSpeed((float) (WalkSpeed-((WalkSpeed*curProt)/20)));					
						player.setFlySpeed((float) (FlySpeed-((FlySpeed*curProt)/20)));
						Util.toLog("Current Protection is: " + curProt + " Walkspeed is " + player.getWalkSpeed() + ", Flyspeed is " + player.getFlySpeed(), true);
					} 
					else {
						player.setWalkSpeed((float) WalkSpeed);
						player.setFlySpeed((float) FlySpeed);
					}
				}
			}
		}
	}
}
