package ru.ender.aitemss.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.ender.aitemss.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemsConfig {

    private static final FileConfiguration config = Main.getInstance.getConfig();
    private static final ArrayList<String> tab = new ArrayList<>();

    public static List<String> getNames(){
        tab.addAll(config.getConfigurationSection("items").getKeys(false));
        return tab;
    }

    public static ItemStack getItem(){
        String items = "items.";
        ItemStack item = null;
        for (String select : config.getConfigurationSection("items").getKeys(false)){
            Material material = Material.getMaterial(config.getString(items + select + ".material"));
            String name = config.getString(items + select + ".name");
            List<String> lore = config.getStringList(items + select + ".lore");

            item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(name);
            meta.setLore(Color.format(lore));

            for (String enchants : config.getStringList(items + select + ".enchants")){
                String[] enchant = enchants.split(":");
                int level = Integer.parseInt(enchant[1]);
                NamespacedKey key = NamespacedKey.minecraft(enchant[0].toLowerCase());
                meta.addEnchant(Enchantment.getByKey(key), level, true);
            }

            for (String attributes : config.getStringList(items + select + ".attribute")){
                String[] attribute = attributes.split(":");
                int multiply = Integer.parseInt(attribute[1]);
                AttributeModifier at = new AttributeModifier(UUID.randomUUID(), Attribute.valueOf(attribute[0]).name(), multiply, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
                meta.addAttributeModifier(Attribute.valueOf(attribute[0]), at);
            }

            if (config.getBoolean(items + select + ".hide_enchants") || config.getBoolean(items + select + ".hide_attributes")) {
                meta.addItemFlags(config.getBoolean(items + select + ".hide_enchants") ? ItemFlag.HIDE_ENCHANTS : ItemFlag.HIDE_ATTRIBUTES);
            }

            item.setItemMeta(meta);
        }
        return item;
    }
}
