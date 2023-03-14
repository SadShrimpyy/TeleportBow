package com.sadshrimpy.teleportbow.builders;

import com.sadshrimpy.teleportbow.utils.sadlibrary.SadMessages;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static com.sadshrimpy.teleportbow.TeleportBow.sadLibrary;

public class MagicBow {

    // NOT NEEDED
    ItemStack magicBow;
    ItemMeta meta;
    ArrayList<String> lore = new ArrayList<>();

    public MagicBow(Material material, String displayNameParam, boolean unbrekeableParam, List<String> loreParam) {
        SadMessages msg = sadLibrary.messages();
        this.magicBow = new ItemStack(material);
        // META RELATED
            this.meta = this.magicBow.getItemMeta();
            this.meta.setDisplayName(msg.translateColors(displayNameParam));
            this.meta.setUnbreakable(unbrekeableParam);
            // Lore
            loreParam.forEach(str -> {
                this.lore.add(msg.translateColors(str));
            });
            this.meta.setLore(this.lore);
            this.magicBow.setItemMeta(this.meta);
    }

    public ItemStack buildItem() {
        return this.magicBow;
    }

}
