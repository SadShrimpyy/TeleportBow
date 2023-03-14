package com.sadshrimpy.teleportbow.builders;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MagicBow {

    // NOT NEEDED
    ItemStack magicBow;
    ItemMeta meta;
    ArrayList<String> lore = new ArrayList<>();

    public MagicBow(Material material, String displayNameParam, boolean unbrekeableParam, ArrayList<String> loreParam) {
        this.magicBow = new ItemStack(material);
        // META RELATED
            this.meta = this.magicBow.getItemMeta();
            this.meta.setDisplayName(displayNameParam);
            this.meta.setUnbreakable(unbrekeableParam);
            // Lore
            this.lore.addAll(loreParam);
            this.meta.setLore(this.lore);
            this.magicBow.setItemMeta(this.meta);
    }

    public ItemStack buildItem() {
        return this.magicBow;
    }

}
