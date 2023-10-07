package de.thebjoredcraft.luckutilsspacex.utils;

import de.thebjoredcraft.luckutilsspacex.tab.TabListManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionHandler implements Listener {
    @EventHandler
    public void onJoin (PlayerJoinEvent event){
        TabListManager.updateTablist();
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent event){
        TabListManager.updateTablist();
    }
}
