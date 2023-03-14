package com.sadshrimpy.teleportbow.commands.subcommands.args0.give;

import com.sadshrimpy.teleportbow.commands.CommandSyntax;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

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
    }

    private String getSenderName(CommandSender sender) {
        if (sender instanceof ConsoleCommandSender)
            return "console";
        else
            return sender.getName();
    }
}
