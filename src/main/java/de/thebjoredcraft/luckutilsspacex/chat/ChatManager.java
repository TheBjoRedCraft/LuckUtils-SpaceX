package de.thebjoredcraft.luckutilsspacex.chat;

import de.thebjoredcraft.luckutilsspacex.LuckUtilsX;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatManager implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();

        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player);
        String prefix = user.getCachedData().getMetaData().getPrefix();
        if(prefix != null) {

            boolean isregistered = player.hasPermission(LuckUtilsX.getInstance().getConfig().getString("Permisson", ""));
            String registered = isregistered ? LuckUtilsX.getInstance().getConfig().getString("RegFormatOn", "") : LuckUtilsX.getInstance().getConfig().getString("RegFormatOff", "");

            boolean isafk = player.hasPermission(LuckUtilsX.getInstance().getConfig().getString("AfkPermisson", ""));
            String afk = isafk ? LuckUtilsX.getInstance().getConfig().getString("TabFormaton", "") : LuckUtilsX.getInstance().getConfig().getString("TabFormatoff", "");

            String playerPrefix = prefix;
            String playerName = player.getName();

            String ChatFormat = LuckUtilsX.getInstance().getConfig().getString("ChatFormat", "").replace("%playername%", player.getName());

            String ChatFormat2 = ChatFormat.replace("%luckperms_prefix%", prefix);


            String ChatFormat3 = ChatFormat2.replace("%message%", event.getMessage());

            String ChatFormat4 = ChatFormat3.replace("%registered%", registered);

            String ChatFormat5 = ChatFormat4.replace("%afk%", afk);

            Bukkit.broadcastMessage(ChatFormat5);
        }else{
            player.sendMessage("[LuckUtils] Der Prefix ist null, bitte erstelle eine LuckPerms-Gruppe mit Prefix um LuckUtls benutzten zu können!");
        }
    }
}
