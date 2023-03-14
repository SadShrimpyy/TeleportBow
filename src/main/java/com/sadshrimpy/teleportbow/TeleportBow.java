package com.sadshrimpy.teleportbow;

import com.sadshrimpy.teleportbow.commands.CommandManager;
import com.sadshrimpy.teleportbow.commands.TabCompleterManager;
import com.sadshrimpy.teleportbow.events.EventsManager;
import com.sadshrimpy.teleportbow.utils.sadlibrary.SadLibrary;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportBow extends JavaPlugin {

    public static SadLibrary sadLibrary = new SadLibrary();

    @Override
    public void onEnable() {
        // Plugin startup logic
        sadLibrary.initialize();
        PluginCommand tpBowCmd = getCommand("teleportbow");
        PluginManager pm = getServer().getPluginManager();


        // Command & Tabcomplayer related
        assert tpBowCmd != null;
        tpBowCmd.setExecutor(new CommandManager());
        tpBowCmd.setTabCompleter(new TabCompleterManager());

        // Events
        pm.registerEvents(new EventsManager(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
