package com.gmail.louisdw1.louis;

import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.gmail.louisdw1.upgrading;

public class Listener2 implements Listener{
	public static Map<java.lang.String, Integer> game;
	public static Map<java.lang.String, Boolean> fallDam; 

	@EventHandler
public void move(PlayerMoveEvent event)
{
		game = upgrading.getGame();

		
	Player player = event.getPlayer();
	World currentWorld = player.getWorld();
	Location loc = player.getLocation();
    Location nloc = new Location(currentWorld, loc.getX(), loc.getY()-1, loc.getZ());

	if((nloc.getBlock()).getType().equals(Material.MELON_BLOCK))
	{
		if(game.get((event.getPlayer()).getName()) == null || game.get((event.getPlayer()).getName())!=1)
		{
			return;
		}
		upgrading.candamageTrue(player);
	}

	if((nloc.getBlock()).getType().equals(Material.PISTON_BASE))
	{
		if(game.get((event.getPlayer()).getName()) != null || game.get((event.getPlayer()).getName())==1 || game.get((event.getPlayer()).getName())==4)
		{
			
		 Vector dir = player.getLocation().getDirection();
		 Vector vec;
		 if(game.get((event.getPlayer()).getName())==4)
		 {
			 nloc.setY(nloc.getY()-1.0);
			 if((nloc.getBlock()).getType().equals(Material.COBBLESTONE))
			 {
		         vec = new Vector(dir.getX(), 1D, dir.getZ());

			 }
			 else 
			 {
	         vec = new Vector(dir.getX() * 1.0D, 1D, dir.getZ() * 1.0D);
			 }
		 }else 
		 {
         vec = new Vector(dir.getX() * 2.0D, 2.5D, dir.getZ() * 2.0D);
		 }
         player.setVelocity(vec);
			upgrading.putFall(player, false);
         // Bukkit.broadcastMessage("Velocity : "+vec);
		//player.setVelocity(player.getVelocity().multiply(player.getVelocity()));
		}
	}
	Location loc2 = new Location(currentWorld,loc.getX(), loc.getY()-0.125, loc.getZ());

	//Bukkit.broadcastMessage("loc2 : "+loc2+" Block : "+loc2.getBlock().getType());
	//Location loc2 = new Location(currentWorld,loc.getX(), loc.getY()-0.125, loc.getZ());
	if(loc2.getBlock().getType().equals(Material.DIODE_BLOCK_OFF))
	{
		if(game.get((event.getPlayer()).getName()) == null || game.get((event.getPlayer()).getName())!=1)
		{
			return;
		}
		//Block diode = loc2.getBlock();
		 Vector dir = player.getLocation().getDirection();
         Vector vec = new Vector(dir.getX() * 5.0D, dir.getY()+0.2D, dir.getZ() * 5.0D);
         player.setVelocity(vec);
		//player.setVelocity(player.getVelocity().multiply(player.getVelocity()));
	//Bukkit.broadcastMessage(" Direction : "diode.);
	}
}
	
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		game = upgrading.getGame();

		Player player = event.getPlayer();
		int game1 = game.get(player.getName());
	if(game1!=1&&game1!=0)
	{
		return;
	}
		  GameMode gamemode = player.getGameMode();
		  
	        if((gamemode==GameMode.CREATIVE))
	        {
	        	return;
	        }
	        event.setCancelled(true);
	}
	@EventHandler
	public void onDamage(EntityDamageEvent event)
	{
		if(!(event.getEntity() instanceof Player))
		{
			return;
		}
		Player player = (Player) event.getEntity();
		if(event.getCause()==DamageCause.FALL)
		{
			fallDam = upgrading.getFall();
			//Bukkit.broadcastMessage("Value : "+fallDam.get(player.getName()));

			if(fallDam.containsKey(player.getName()) && fallDam.get(player.getName())==false)
			{
				event.setCancelled(true);
				upgrading.putFall(player, true);
			}
		}
		//Bukkit.broadcastMessage("Cause : "+event.getCause());
	}
	}
