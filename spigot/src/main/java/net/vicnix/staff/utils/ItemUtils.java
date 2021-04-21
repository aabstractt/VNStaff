package net.vicnix.staff.utils;

import net.vicnix.staff.Staff;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class ItemUtils {

    @SuppressWarnings("deprecation")
    public static ItemStack mapToItem(Map<String, Object> itemData) {
        if (!itemData.containsKey("id") || !itemData.containsKey("amount")) {
            return new ItemStack(Material.AIR);
        }

        ItemStack itemStack = new ItemStack((Integer)itemData.get("id"), (Integer)itemData.get("amount"));

        if (itemData.containsKey("damage")) {
            itemStack.setDurability(((Integer)itemData.get("damage")) == 10 ? (byte)10 : (byte)1);
        }

        if (itemData.containsKey("displayName")) {
            ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', (String) itemData.get("displayName")));

            itemMeta.spigot().setUnbreakable(true);

            itemStack.setItemMeta(itemMeta);
        }

        return itemStack;
    }

    @SuppressWarnings("unchecked")
    public static Map<Integer, ItemStack> getStaffContents(Boolean vanished) {
        Map<Integer, ItemStack> contents = new HashMap<>();

        for (Map<?, ?> itemData : Staff.getInstance().getConfig().getMapList("staff-items")) {
            if (itemData.get("itemName").equals("vanish-" + (vanished ? "disable" : "enable"))) continue;

            contents.put((Integer) itemData.get("slot"), mapToItem((Map<String, Object>) itemData));
        }

        return contents;
    }

    public static Map<?, ?> getStaffContent(Integer slot) {
        for (Map<?, ?> itemData : Staff.getInstance().getConfig().getMapList("staff-items")) {
            if (itemData.get("slot") != slot) continue;

            return itemData;
        }

        return null;
    }
}