package com.gmail.louisdw1.louis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import com.gmail.louisdw1.upgrading;

public class ListenerBasic implements Listener, CommandExecutor{
	public static Map<java.lang.String, Integer> game;
	public static Map<java.lang.String, Boolean> canKill;
	//   player1.sendMessage(ChatColor.AQUA +"Your current tier is: "+tier.get(player));
//	SetInv.main(player1,tier.get(player),points.get(player1.getName()));
@EventHandler
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	if(cmd.getName().equalsIgnoreCase("leave")){ // If the player typed /basic then do the following...
		Player player = (Player) sender;
		
		game = upgrading.getGame();
		if(game.get(player.getName())==1)
		{
			canKill = upgrading.getCanKill();
			
			if(canKill.get(player.getName())==true)
			{
				player.sendMessage(ChatColor.RED+"You must have spawn protection!");
				return true;
			}
			player.getServer().getWorld("world").setSpawnLocation(220, 67, 256);
			upgrading.putGame(player, 0);

			choice(player);

			player.setHealth(0);
			return true;
		}
		upgrading.putGame(player, 0);

		choice(player);
return true;
	} //If this has happened the function will return true. 
        // If this hasn't happened the a value of false will be returned.
	return false; 
}
@EventHandler
public void onRespawn(PlayerRespawnEvent event)
{
	game = upgrading.getGame();
	if(game.get(event.getPlayer().getName())!=0)
	{
		return;
	}
	Player player = event.getPlayer();

	choice(player);
    player.teleport(new Location(Bukkit.getWorld("world"), 220, 67, 256));

}
	@EventHandler
public void onjoin(PlayerJoinEvent event)
{
		event.setJoinMessage(""); 
		
		game = upgrading.getGame();
		Player player = event.getPlayer();
	if(game.get(player.getName()) == null || game.get(player.getName()) == 0)
	{

		 if(game.containsKey(player.getName())==false)
		    {
		    	Bukkit.broadcastMessage(ChatColor.YELLOW +"Welcome "+player.getName()+" to the server! Who has joined for the first Time! " );
		  
	
		    }
	    	choice(player);

	}
}
	public static void choice(Player player)
	{
		PlayerInventory inv= player.getInventory();
		inv.clear();
		inv.setHelmet(new ItemStack(Material.AIR));
		inv.setChestplate(new ItemStack(Material.AIR));
		inv.setLeggings(new ItemStack(Material.AIR));
		inv.setBoots(new ItemStack(Material.AIR));		 
		upgrading.putGame(player, 0);
		    ItemStack upgrading = new ItemStack(Material.RECORD_11);
			  ItemMeta meta = upgrading.getItemMeta();
			  meta.setDisplayName(ChatColor.BOLD+""+ChatColor.GOLD+"Right Click Me!");
			  upgrading.setItemMeta(meta);
			  inv.addItem(new ItemStack(upgrading));
		        player.teleport(new Location(Bukkit.getWorld("world"), 220, 67, 256));
		        player.setGameMode(GameMode.SURVIVAL);
		        player.setHealth(20.0);
		        player.setFoodLevel(20);
	}
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){
		game = upgrading.getGame();
		
	    Player player = event.getPlayer();
    	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Welcome "+player.getName()+"TESTEST "+game.get(player.getName()) );

		if(game.get(player.getName()) == 0)
		{
	    	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Welcome "+player.getName()+"TESTEST "+game.get(player.getName())+"   "+player.getItemInHand() );

	    if((player.getItemInHand()).getType().equals(Material.RECORD_11)){
	    	//Bukkit.broadcastMessage(ChatColor.YELLOW +"Welcome "+player.getName()+"TESTEST "+game.get(player.getName()) );

	    	  Inventory inventory = Bukkit.createInventory(player, 27, "Choose game: ");
	    	  ItemStack upgrading = new ItemStack(Material.DIAMOND_SWORD);
			  ItemMeta meta = upgrading.getItemMeta();
			  meta.setDisplayName("Upgrading");
			  List<String> loreList = new ArrayList<String>();

			  loreList.add(ChatColor.RED+"FastPaced battles");
			  loreList.add(ChatColor.RED+"encouraging progression");
			  loreList.add(ChatColor.RED+"through tiers.");

			  meta.setLore(loreList);
			  upgrading.setItemMeta(meta);
			  inventory.addItem(new ItemStack(upgrading));	 
			  if(player.isOp())
			  {
				  ItemStack creativeTest = new ItemStack(Material.BEDROCK);
				  ItemMeta meta1 = upgrading.getItemMeta();
				  meta1.setDisplayName("Creative Test World");
				  List<String> loreList1 = new ArrayList<String>();

				  loreList1.add(ChatColor.RED+"A Creative Test World For OPS!");
				 

				  meta1.setLore(loreList1);
				  creativeTest.setItemMeta(meta1);
				  inventory.addItem(new ItemStack(creativeTest));	
			  }
			  if(player.isOp())
			  {
				  ItemStack trench = new ItemStack(Material.WOOD_SPADE);
				  ItemMeta meta12 = upgrading.getItemMeta();
				  meta12.setDisplayName("Trench");
				  List<String> loreList12 = new ArrayList<String>();

				  loreList12.add(ChatColor.RED+"Battle in the trenches and");
				  loreList12.add(ChatColor.RED+"fight for King and country! APLHA");


				  meta12.setLore(loreList12);
				  trench.setItemMeta(meta12);
				  inventory.addItem(new ItemStack(trench));	
			  }
			  if(player.isOp())
			  {
				  ItemStack trench = new ItemStack(Material.FISHING_ROD);
				  ItemMeta meta12 = upgrading.getItemMeta();
				  meta12.setDisplayName("Capture game");
				  List<String> loreList12 = new ArrayList<String>();

				  loreList12.add(ChatColor.RED+"Capture the spawnpoints and");
				  loreList12.add(ChatColor.RED+"defeat the enimies! APLHA");


				  meta12.setLore(loreList12);
				  trench.setItemMeta(meta12);
				  inventory.addItem(new ItemStack(trench));	
			  }
			  if(player.isOp())
			  {
				  ItemStack trench = new ItemStack(Material.LEATHER_BOOTS);
				  ItemMeta meta12 = upgrading.getItemMeta();
				  meta12.setDisplayName("Parkour");
				  List<String> loreList12 = new ArrayList<String>();

				  loreList12.add(ChatColor.RED+"Complete the complicated");
				  loreList12.add(ChatColor.RED+"parcour map! APLHA");


				  meta12.setLore(loreList12);
				  trench.setItemMeta(meta12);
				  inventory.addItem(new ItemStack(trench));	
			  }
			  player.openInventory(inventory);
	    }
	    
		}
		}


	@EventHandler
    public void inventoryclick(InventoryClickEvent e){
        if (e.getInventory().getName().equalsIgnoreCase("Choose game: ")){

            if (e.getRawSlot() < 54 && e.getRawSlot() > -1){ // the top inv rawslots are numbered 0 to 53 starting top left, -999 is returned if u click outside the inv view screen
                ItemStack itemclicked = e.getCurrentItem();
                ItemStack cursor = e.getCursor();
                e.setCancelled(true);
                if (cursor.getType() == Material.AIR){ //if player has no item on the cursor
                 ItemMeta meta = itemclicked.getItemMeta();
                 String display = meta.getDisplayName();
                 if(display=="Upgrading")
                 {
                	 Player player = (Player) e.getWhoClicked();
                	upgrading.putGame(player, 1);
                    player.getServer().getWorld("world")
                    .setSpawnLocation(272, 120, 38);
            		player.setHealth(0);
                 }
                 if(display=="Creative Test World")
                 {

                	 Player player = (Player) e.getWhoClicked();
                  	upgrading.putGame(player, 2);

               	 WorldManager.sendPlayer(player,"creative",GameMode.SURVIVAL);
                 }
                 if(display=="Trench")
                 {
                	 Player player = (Player) e.getWhoClicked();
                  	upgrading.putGame(player, 3);

               	 WorldManager.sendPlayer(player,"trench",GameMode.SURVIVAL);
                 }
                 if(display=="Capture game")
                 {
                	 Player player = (Player) e.getWhoClicked();
                  	upgrading.putGame(player, 4);
                	 WorldManager.sendPlayer(player,"capture",GameMode.SURVIVAL);
                 }
                 if(display=="Parkour")
                 {
                	 Player player = (Player) e.getWhoClicked();
                  	upgrading.putGame(player, 4);
                	 WorldManager.sendPlayer(player,"parkour",GameMode.SURVIVAL);
                 }
                }
            }
        }
    }
}
