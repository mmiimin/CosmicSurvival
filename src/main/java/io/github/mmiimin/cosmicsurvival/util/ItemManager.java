package io.github.mmiimin.cosmicsurvival.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemManager {

    public ItemManager() {

    }

    public ItemStack createSkull(String minecraftURL) {
        final ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        assert meta != null;
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", minecraftURL));
        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta(meta);
        return skull;
    }

    public ItemStack createItem(final ItemStack item, final String name, final String... lore) {
        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));

        List<Component> componentLore = new ArrayList<>(); //Component ArrayList to Component Array
        for(String s : lore) componentLore.add(Component.text(s));
        Component[] array = new Component[componentLore.size()];
        int size=0;
        for(Component temp : componentLore) array[size++] = temp;
        meta.lore(List.of(array));

        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text(name));

        List<Component> componentLore = new ArrayList<>(); //Component ArrayList to Component Array
        for(String s : lore) componentLore.add(Component.text(s));
        Component[] array = new Component[componentLore.size()];
        int size=0;
        for(Component temp : componentLore) array[size++] = temp;
        meta.lore(List.of(array));

        item.setItemMeta(meta);
        return item;
    }

}
