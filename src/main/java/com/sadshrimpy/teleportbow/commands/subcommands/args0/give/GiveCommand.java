package com.sadshrimpy.teleportbow.commands.subcommands.args0.give;

import com.sadshrimpy.teleportbow.builders.MagicBow;
import com.sadshrimpy.teleportbow.commands.CommandSyntax;
import com.sadshrimpy.teleportbow.utils.sadlibrary.SadConfigurations;
import com.sadshrimpy.teleportbow.utils.sadlibrary.SadMessages;
import com.sadshrimpy.teleportbow.utils.sadlibrary.SadPlaceholders;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static com.sadshrimpy.teleportbow.TeleportBow.sadLibrary;

public class GiveCommand implements CommandSyntax {

    @Override
    public String getName() {
        return "give";
    }

    @Override
    public String getPermission(String[] args) {
        return sadLibrary.permissions().getGive();
    }

    @Override
    public boolean hasSubcommands() { return true; }

    @Override
    public void perform(CommandSender sender, String[] args) {
        // /teleportbow give <player/*>
        FileConfiguration config = sadLibrary.configurations().getConfigConfiguration();
        FileConfiguration msgConfig = sadLibrary.configurations().getMessagesConfiguration();
        SadMessages msg = sadLibrary.messages();
        SadPlaceholders place = sadLibrary.placeholders();

        if (args.length <= 1)
            for (String str : msgConfig.getStringList("help.generic"))
                sender.sendMessage(msg.viaChat(false, str));
        else {
            ItemStack magicBow = new MagicBow(
                    Material.BOW,
                    config.getString("magic-bow.name"),
                    config.getBoolean("magic-bow.unbreakable"),
                    config.getStringList("magic-bow.lore")).buildItem();

            // To all
            if (args[1].equals("*"))
                if (sender.hasPermission(sadLibrary.permissions().getGiveAll()))
                    Bukkit.getOnlinePlayers().forEach(player -> sendBow(sender, msgConfig, msg, place, magicBow, player));
            else {
                // Ooooooonly yooouuuuuuuuuuuuuuuuuuuu
                Player player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    sender.sendMessage(msg.viaChat(true, msgConfig.getString("player.generic.not-found")
                            .replace(place.getPlayerName(), getSenderName(sender))
                            .replace(place.getPlayerTarget(), args[1])));
                    return;
                }

                if (player.isOnline())
                    sendBow(sender, msgConfig, msg, place, magicBow, player);
            }
        }
    }

    private void sendBow(CommandSender sender, FileConfiguration msgConfig, SadMessages msg, SadPlaceholders place, ItemStack magicBow, Player player) {
        player.getInventory().addItem(magicBow);
        // Msg the TARGET
        player.sendMessage(msg.viaChat(true, msgConfig.getString("player.give.received")
                .replace(place.getPlayerName(), player.getName())
                .replace(place.getPlayerExecutor(), getSenderName(sender))));
        // Msg the SENDER
        sender.sendMessage(msg.viaChat(true, msgConfig.getString("player.give.executor")
                .replace(place.getPlayerName(), getSenderName(sender))
                .replace(place.getPlayerTarget(), player.getName())));
    }


    private String getSenderName(CommandSender sender) {
        if (sender instanceof ConsoleCommandSender)
            return "console";
        else
            return sender.getName();
    }
}
