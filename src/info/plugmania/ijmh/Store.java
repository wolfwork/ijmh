package info.plugmania.ijmh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Store {
	public List<Player> desert = new ArrayList<Player>();
	public List<Player> riding = new ArrayList<Player>();
	
	public HashMap<Player, Block> drowning = new HashMap<Player, Block>();
	
	ijmh plugin;

	public Store(ijmh instance) {
		plugin = instance;
	}		
}

