package de.thebjoredcraft.luckutilsspacex.tab;

import de.thebjoredcraft.luckutilsspacex.LuckUtilsX;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class TabListManager {
    public static void updateTablist(){
        for (Player player : Bukkit.getOnlinePlayers()){
            MiniMessage mHeader = MiniMessage.miniMessage();
            MiniMessage mFooter = MiniMessage.miniMessage();

            Component header = mHeader.deserialize("<bold><gradient:#ff8400:#fbff00>SpaceX Community Server SMP </gradient>");
            Component footer = mFooter.deserialize("<color:#5b0187><color:#7102a8><color:#8e02d4><color:#ab03ff>twitch.tv/spacemitx</color></color></color></color> <color:#303030><></color> <color:#e00000>youtube.com/@spacemitx</color>");
            String prefix = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(player).getCachedData().getMetaData().getPrefix();

            boolean isregistered = player.hasPermission(LuckUtilsX.getInstance().getConfig().getString("RegPermisson", ""));
            String registered = isregistered ? LuckUtilsX.getInstance().getConfig().getString("RegFormatOn", "") : LuckUtilsX.getInstance().getConfig().getString("RegFormatOff", "");

            boolean isafk = player.hasPermission(LuckUtilsX.getInstance().getConfig().getString("AfkPermisson", ""));
            String afk = isafk ? LuckUtilsX.getInstance().getConfig().getString("TabFormaton", "") : LuckUtilsX.getInstance().getConfig().getString("TabFormatoff", "");

            if(prefix != null) {
                String PlayerListNameFormat1 = LuckUtilsX.getInstance().getConfig().getString("PlayerListNameFormat", "").replace("%playername%", player.getName());
                //                player.setPlayerListHeader(PlayerListHeader2);
                //                player.setPlayerListFooter(PlayerListFooter2);

                String PlayerListNameFormat2 = PlayerListNameFormat1.replace("%luckperms_prefix%", prefix);
                String PlayerListNameFormat3 = PlayerListNameFormat2.replace("%registered%", registered);
                String PlayerListNameFormat4 = PlayerListNameFormat3.replace("%afk%", afk);
                //                player.setPlayerListHeader(PlayerListHeader2);
                //                player.setPlayerListFooter(PlayerListFooter2);

                player.setPlayerListName(PlayerListNameFormat4);
                //                player.setPlayerListHeader(PlayerListHeader2);
                //                player.setPlayerListFooter(PlayerListFooter2);
            }else{
                player.sendMessage("[LuckUtils] Der Prefix ist null, bitte erstelle eine LuckPerms-Gruppe mit Prefix um LuckUtls benutzten zu können!");
            }
        }
    }
    private static BukkitRunnable runnable;

    public static void startTabupdate(){
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                updateTablist();
            }
        };
        BukkitTask bukkitTask = runnable.runTaskTimer(LuckUtilsX.getInstance(), 0, 20);
    }
    public static void stopTabListUpdate(){
        try {
            if(!runnable.isCancelled()) {
                runnable.cancel();
            }
        } catch(Exception exception) {
            Bukkit.getConsoleSender().sendMessage("ERROR in stopTabUpdate");
        }
    }
}
