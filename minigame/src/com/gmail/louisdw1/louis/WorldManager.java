package com.gmail.louisdw1.louis;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class WorldManager implements Listener {
	public static void sendPlayer(Player player,String worldName,GameMode gamemode)
	{
		PlayerInventory inv= player.getInventory();
		inv.clear();
		inv.setHelmet(new ItemStack(Material.AIR));
		inv.setChestplate(new ItemStack(Material.AIR));
		inv.setLeggings(new ItemStack(Material.AIR));
		inv.setBoots(new ItemStack(Material.AIR));		 
		World creative = Bukkit.getWorld("world_"+worldName);
		if(creative == null)
		{
		
			
			WorldCreator Creative = new WorldCreator("world_"+worldName).environment(Environment.NORMAL).generateStructures(false).seed(0);
			
			creative = Creative.createWorld();
			creative.setWaterAnimalSpawnLimit(0);
			creative.setAmbientSpawnLimit(0);
			creative.setAnimalSpawnLimit(0);

		}
		if(player.isOp())
		{
			gamemode = GameMode.CREATIVE;
		}
		player.teleport(creative.getSpawnLocation());

		player.closeInventory();
		player.setGameMode(gamemode);
	}
	
}
