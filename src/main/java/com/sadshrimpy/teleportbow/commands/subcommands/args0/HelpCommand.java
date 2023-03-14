package com.sadshrimpy.teleportbow.commands.subcommands.args0;

import com.sadshrimpy.teleportbow.commands.CommandSyntax;
import org.bukkit.command.CommandSender;

import static com.sadshrimpy.teleportbow.TeleportBow.sadLibrary;

public class HelpCommand implements CommandSyntax {

    @Override
    public String getName() { return "help"; }

    @Override
    public String getPermission(String[] args) { return sadLibrary.permissions().getHelp(); }

    @Override
    public boolean hasSubcommands() { return false; }

    @Override
    public void perform(CommandSender sender, String[] args) {
        for (String str : sadLibrary.configurations().getMessagesConfiguration().getStringList("help.generic"))
            sender.sendMessage(sadLibrary.messages().viaChat(false, str));
    }
}
