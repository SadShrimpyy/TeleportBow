package com.sadshrimpy.teleportbow.commands.subcommands.args0.take;

import com.sadshrimpy.teleportbow.builders.MagicBow;
import com.sadshrimpy.teleportbow.commands.CommandSyntax;
import com.sadshrimpy.teleportbow.utils.sadlibrary.SadMessages;
import com.sadshrimpy.teleportbow.utils.sadlibrary.SadPlaceholders;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.BufferedWriter;
import java.io.Console;

import static com.sadshrimpy.teleportbow.TeleportBow.sadLibrary;

public class TakeCommand implements CommandSyntax {

    @Override
    public String getName() {
        return "take";
    }

    @Override
    public String getPermission(String[] args) {
        return sadLibrary.permissions().getTake();
    }

    @Override
    public boolean hasSubcommands() {
        return false;
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        // /teleportbow take
        FileConfiguration config = sadLibrary.configurations().getConfigConfiguration();
        FileConfiguration msgConfig = sadLibrary.configurations().getMessagesConfiguration();
        SadMessages msg = sadLibrary.messages();
        SadPlaceholders place = sadLibrary.placeholders();

        if (args.length == 0)
            for (String str : msgConfig.getStringList("help.generic"))
                sender.sendMessage(msg.viaChat(false, str));
        else {
            // Check the sender
            if (sender instanceof ConsoleCommandSender) {
                sender.sendMessage(msg.viaChat(true, msgConfig.getString("player.take.console")
                        .replace(place.getPlayerName(), getSenderName(sender))
                        .replace(place.getPlayerTarget(), args[0])));
                return;
            }

            Player player = Bukkit.getPlayer(sender.getName());

            // Check the target
            if (player == null) {
                sender.sendMessage(msg.viaChat(true, msgConfig.getString("player.generic.not-found")
                        .replace(place.getPlayerName(), getSenderName(sender))
                        .replace(place.getPlayerTarget(), args[0])));
                return;
            }

            // Is on? o.o
            if (player.isOnline()) {
                // Item
                player.getInventory().addItem(new MagicBow(
                        Material.BOW,
                        config.getString("magic-bow.name"),
                        config.getBoolean("magic-bow.unbreakable"),
                        config.getStringList("magic-bow.lore")).buildItem());

                // Msg
                player.sendMessage(msg.viaChat(true, msgConfig.getString("player.take.bow")
                        .replace(place.getPlayerName(), getSenderName(sender))));
            }
        }
    }

    private String getSenderName(CommandSender sender) {
        if (sender instanceof ConsoleCommandSender)
            return "console";
        else
            return sender.getName();
    }

}