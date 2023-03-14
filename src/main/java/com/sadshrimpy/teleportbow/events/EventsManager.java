package com.sadshrimpy.teleportbow.events;

import com.sadshrimpy.teleportbow.utils.sadlibrary.SadPlaceholders;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;

import static com.sadshrimpy.teleportbow.TeleportBow.sadLibrary;

public class EventsManager implements Listener {

    @EventHandler
    public void onHitEvent(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Player player = (Player) event.getEntity().getShooter();
            Location newLoc = event.getEntity().getLocation();
            SadPlaceholders place = sadLibrary.placeholders();

            player.teleport(newLoc);
            player.sendMessage(sadLibrary.messages().viaChat(true, sadLibrary.configurations().getMessagesConfiguration().getString("player.arrow.land")
                    .replace(place.getX(), Integer.toString(newLoc.getBlockX()))
                    .replace(place.getY(), Integer.toString(newLoc.getBlockY()))
                    .replace(place.getZ(), Integer.toString(newLoc.getBlockZ()))));
        }
    }

    @EventHandler
    public void onLaunchEvent(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Arrow) {
            if (event.getEntity().getShooter() instanceof Player) {
                Player player = (Player) event.getEntity().getShooter();
                player.sendMessage(sadLibrary.messages().viaChat(true, sadLibrary.configurations().getMessagesConfiguration().getString("player.arrow.launched")));
            }
        }
    }
}
