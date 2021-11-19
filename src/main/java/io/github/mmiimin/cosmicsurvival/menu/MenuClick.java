package io.github.mmiimin.cosmicsurvival.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuClick implements Listener
{
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {

        if (!(e.getView().getTitle().contains("§0"))) return;
        e.setCancelled(true);
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;
        final Player player = (Player) e.getWhoClicked();
        final int slot = e.getRawSlot();
        if (e.getView().getTitle().equals("§0프로필")){
            switch (slot){
                case 40:
                    player.closeInventory();
                    break;
            }
        }
        else if (e.getView().getTitle().equals("§0설정")){
            switch (slot){

            }
        }




    }
}
