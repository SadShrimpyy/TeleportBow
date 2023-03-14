package com.sadshrimpy.teleportbow.commands.subcommands.args0.take;

import com.sadshrimpy.teleportbow.commands.CommandSyntax;
import org.bukkit.command.CommandSender;

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

    }
}
