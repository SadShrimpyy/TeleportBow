package com.sadshrimpy.teleportbow.commands.subcommands.args0;

import com.sadshrimpy.teleportbow.commands.CommandSyntax;
import org.bukkit.command.CommandSender;

import static com.sadshrimpy.teleportbow.TeleportBow.sadLibrary;

public class ReloadCommand implements CommandSyntax {

    @Override
    public String getName() { return "reload"; }

    @Override
    public String getPermission(String[] args) { return sadLibrary.permissions().getReload(); }

    @Override
    public boolean hasSubcommands() { return false; }

    @Override
    public void perform(CommandSender sender, String[] args) {
        sadLibrary.buildFiles();
        if (sadLibrary.configurations().reloadFiles())
            sender.sendMessage(sadLibrary.messages().viaChat(true, sadLibrary.configurations().getMessagesConfiguration().getString("reloaded.correctly")));
        else
            sender.sendMessage(sadLibrary.messages().viaChat(true, sadLibrary.configurations().getMessagesConfiguration().getString("reloaded.incorrectly")));

    }
}
