package info.plugmania.ijmh;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Util{
	static ijmh plugin;
	
	public Util(ijmh instance) {
		plugin = instance;
	}
	
	public void checkVersion(boolean isCmd, Player player,CommandSender sender){
		
		try {	
			URL url = new URL("http://dev.bukkit.org/server-mods/" + plugin.getDescription().getName() + "/files.rss");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
						
			String str;
			Boolean endCheck = false;
			while (((str = in.readLine()) != null) && !endCheck) {				
				String pattern = "(\\d)\\.(\\d)\\.(\\d)";
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(str);
				
				if(m.find()) {
					String chkVersion = m.group(0).replace(".", "");
					int chkVal = Integer.parseInt(chkVersion);
					String curVersion = (plugin.getDescription().getVersion()).replace(".", "");
					int curVal = Integer.parseInt(curVersion);
					if(curVal<chkVal){
						String msg = ChatColor.AQUA + "[ijmh] New version " + ChatColor.GOLD + "v" + m.group(0) + ChatColor.AQUA + " is out! You are running " + ChatColor.GRAY + "v" + plugin.getDescription().getVersion() + ChatColor.AQUA + ". Get the new version is at " + ChatColor.GOLD + "http://dev.bukkit.org/server-mods/" + plugin.getDescription().getName();
						if(player!=null) player.sendMessage(msg);
						else if(sender!=null) sender.sendMessage(msg);
					} else if(isCmd){
						sender.sendMessage(ChatColor.AQUA + "[ijmh] Newest version " + ChatColor.GOLD + plugin.getDescription().getVersion() + ChatColor.AQUA + " already running.");	
					}
					endCheck = true;
				}
			}
			in.close();
		 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static int sec2tic(int seconds){
		int ticks = seconds * 20;
		return ticks;
	}
	
	public static boolean pctChance(double d){
		boolean result = false;
		int rNum = (int) (100*Math.random());
		if(rNum<=d) result = true;
		return result;
	}
}