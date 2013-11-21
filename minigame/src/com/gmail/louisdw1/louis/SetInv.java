package com.gmail.louisdw1.louis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SetInv {

	public static void main(Player player,int tier,int points)
	{
		player.setLevel(tier);
		ScoreboardManager manager = Bukkit.getScoreboardManager();
    	Scoreboard board = manager.getNewScoreboard();
    	 
    	Objective objective = board.registerNewObjective("tier", "dummy");
    	objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    	objective.setDisplayName("Points until next tier: ");
    	 
    	  Score score = objective.getScore(player);
    	  score.setScore(points(tier) - points); //Example
    
    	 
    	 player.setScoreboard(board);

		PlayerInventory inv= player.getInventory();
		inv.clear();
		inv.setHelmet(new ItemStack(Material.AIR));
		inv.setChestplate(new ItemStack(Material.AIR));
		inv.setLeggings(new ItemStack(Material.AIR));
		inv.setBoots(new ItemStack(Material.AIR));
		if(tier>=8)
		{
			 ItemStack WOOD_SWORD11111 = new ItemStack(Material.DIAMOND_SWORD);
			  ItemMeta meta11111 = WOOD_SWORD11111.getItemMeta();
			  meta11111.addEnchant(Enchantment.DURABILITY, 50, true);
			  WOOD_SWORD11111.setItemMeta(meta11111);
			  player.getInventory().addItem(new ItemStack(WOOD_SWORD11111));
			  ItemStack WOOD_SWORD111111 = new ItemStack(Material.BOW);
			  ItemMeta meta111111 = WOOD_SWORD111111.getItemMeta();
			  meta111111.addEnchant(Enchantment.DURABILITY, 50, true);
			  meta111111.addEnchant(Enchantment.ARROW_INFINITE, 1, true);

			  WOOD_SWORD111111.setItemMeta(meta111111);
			  player.getInventory().addItem(new ItemStack(WOOD_SWORD111111));
			  ItemStack WOOD_SWORD1111111 = new ItemStack(Material.ARROW);
			  ItemMeta meta1111111 = WOOD_SWORD1111111.getItemMeta();
			  

			  WOOD_SWORD1111111.setItemMeta(meta1111111);
			  player.getInventory().addItem(new ItemStack(WOOD_SWORD1111111));
		}
		switch (tier)
		{ 
		  case 0:
			  ItemStack WOOD_SWORD = new ItemStack(Material.WOOD_AXE);
			  ItemMeta meta = WOOD_SWORD.getItemMeta();
			  meta.addEnchant(Enchantment.DURABILITY, 50, true);
			  WOOD_SWORD.setItemMeta(meta);
			  player.getInventory().addItem(new ItemStack(WOOD_SWORD));

		        break;
		   case 1:
			   ItemStack WOOD_SWORD1 = new ItemStack(Material.WOOD_SWORD);
				  ItemMeta meta1 = WOOD_SWORD1.getItemMeta();
				  meta1.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD1.setItemMeta(meta1);
				  player.getInventory().addItem(new ItemStack(WOOD_SWORD1));
				  break;
		   case 2:
			   ItemStack WOOD_SWORD11 = new ItemStack(Material.STONE_AXE);
				  ItemMeta meta11 = WOOD_SWORD11.getItemMeta();
				  meta11.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD11.setItemMeta(meta11);
				  player.getInventory().addItem(new ItemStack(WOOD_SWORD11));
				  break;
		   case 3:
			   ItemStack WOOD_SWORD111 = new ItemStack(Material.STONE_SWORD);
				  ItemMeta meta111 = WOOD_SWORD111.getItemMeta();
				  meta111.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD111.setItemMeta(meta111);
				  player.getInventory().addItem(new ItemStack(WOOD_SWORD111));
				  break;
		   case 4:
			   ItemStack WOOD_SWORD1111 = new ItemStack(Material.IRON_AXE);
				  ItemMeta meta1111 = WOOD_SWORD1111.getItemMeta();
				  meta1111.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD1111.setItemMeta(meta1111);
				  player.getInventory().addItem(new ItemStack(WOOD_SWORD1111));
				  break;
		   case 5:
			   ItemStack WOOD_SWORD11111 = new ItemStack(Material.IRON_SWORD);
				  ItemMeta meta11111 = WOOD_SWORD11111.getItemMeta();
				  meta11111.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD11111.setItemMeta(meta11111);
				  player.getInventory().addItem(new ItemStack(WOOD_SWORD11111));
				  break;
		   case 6:
			   ItemStack WOOD_SWORD111111 = new ItemStack(Material.DIAMOND_AXE);
				  ItemMeta meta111111 = WOOD_SWORD111111.getItemMeta();
				  meta111111.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD111111.setItemMeta(meta111111);
				  player.getInventory().addItem(new ItemStack(WOOD_SWORD111111));
				  break;
		   case 7:
			   ItemStack WOOD_SWORD1111111 = new ItemStack(Material.DIAMOND_SWORD);
				  ItemMeta meta1111111 = WOOD_SWORD1111111.getItemMeta();
				  meta1111111.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD1111111.setItemMeta(meta1111111);
				  player.getInventory().addItem(new ItemStack(WOOD_SWORD1111111));
				  break; 
		   case 8:
			   break;
		   case 9:
			   ItemStack WOOD_SWORD11111111 = new ItemStack(Material.LEATHER_HELMET);
				  ItemMeta meta11111111 = WOOD_SWORD11111111.getItemMeta();
				  meta11111111.addEnchant(Enchantment.DURABILITY, 50, true);
				  WOOD_SWORD11111111.setItemMeta(meta11111111);
				  player.getInventory().setHelmet( new ItemStack(WOOD_SWORD11111111));
				  break; 
				  default:
					  ItemStack WOOD_SWORD111111111 = new ItemStack(Material.LEATHER_HELMET);
					  ItemMeta meta111111111 = WOOD_SWORD111111111.getItemMeta();
					  meta111111111.addEnchant(Enchantment.DURABILITY, 50, true);
					  WOOD_SWORD111111111.setItemMeta(meta111111111);
					  player.getInventory().setHelmet( new ItemStack(WOOD_SWORD111111111));
					  break; 
		   
		}	}

	public static int points(int tier)
	{
		int pointstotier = ((tier*10)+20);
		//Bukkit.broadcastMessage("Points to tier: "+pointstotier+" Tier: "+tier);
		return pointstotier;
	}
		
		
		
}

