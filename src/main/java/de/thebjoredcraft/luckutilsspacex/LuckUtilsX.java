package de.thebjoredcraft.luckutilsspacex;

import de.thebjoredcraft.luckutilsspacex.chat.ChatManager;
import de.thebjoredcraft.luckutilsspacex.tab.TabListManager;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuckUtilsX extends JavaPlugin {
    private static LuckUtilsX instance;
    private LuckPerms luckPerms;

    @Override
    public void onLoad() {
        instance = this;
    }
    public static de.thebjoredcraft.luckutilsspacex.LuckUtilsX getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GREEN + "(LU) LuckUtils wird geladen!");

        getServer().getPluginManager().registerEvents(new ChatManager(), this);

        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            getLogger().info("(LU) LuckPerms Plugin gefunden!");
        } else {
            getLogger().warning("(LU) LuckPerms Plugin nicht gefunden! Plugin wird deaktiviert.");
            getServer().getPluginManager().disablePlugin(this);
        }
        // Plugin startup logic
        saveDefaultConfig();
        if(getInstance().getConfig().getBoolean("UpdateTabList")){
            TabListManager.startTabupdate();
        }else{
            getLogger().info("(LU) §eAutoTablistUpdate is not enabled!");
        }
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        if(getInstance().getConfig().getBoolean("UpdateTabList")){
            TabListManager.stopTabListUpdate();
        }
        getLogger().info(ChatColor.RED + "(LU) LuckUtils wird gestoppt!");
        getLogger().info(ChatColor.GREEN + "(LU) Plugin wird gesaved!");
        // Plugin shutdown logic
    }

    public static void setPermission(Player player, String permission, Boolean trueorfalse) {
        PermissionAttachment attachment = player.addAttachment(LuckUtilsX.getInstance());
        attachment.setPermission(permission, trueorfalse);

        player.recalculatePermissions();
    }
}
