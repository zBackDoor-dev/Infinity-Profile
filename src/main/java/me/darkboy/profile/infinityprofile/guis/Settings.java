package me.darkboy.profile.infinityprofile.guis;

import me.darkboy.profile.infinityprofile.InfinityProfile;
import me.darkboy.profile.infinityprofile.api.inventory.ClickableItem;
import me.darkboy.profile.infinityprofile.api.inventory.InfinityInventory;
import me.darkboy.profile.infinityprofile.api.inventory.content.InventoryContents;
import me.darkboy.profile.infinityprofile.api.inventory.content.InventoryProvider;
import me.darkboy.profile.infinityprofile.api.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashSet;

public class Settings implements InventoryProvider, Listener {

    public static InfinityInventory inv = InfinityInventory.builder()
            .id("settingsGUI")
            .provider(new Settings())
            .size(6, 9)
            .title("§bSettings")
            .build();

    private HashSet<Player> hide = new HashSet<>();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillBorders(ClickableItem.empty(new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§7✦ §b§lDream§c§lWars §7✦").build()));

        if (!hide.contains(player)) {
            hide.add(player);
        } else {
            hide.remove(player);
        }
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        contents.set(1, 2, ClickableItem.empty(new ItemBuilder(Material.WATCH).setDisplayName("§aPlayer visibility").build()));
        if(!hide.contains(player)) {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.INK_SACK, 1, (byte) 8).setDisplayName("§aVisibility Disabled").build(), e -> {
                hide.add(player);

                Bukkit.getOnlinePlayers().forEach(p -> player.hidePlayer(InfinityProfile.getInstance(), p));
            }));
        } else {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setDisplayName("§aVisibility Enabled").build(), e -> {
                hide.remove(player);

                Bukkit.getOnlinePlayers().forEach(p -> player.showPlayer(InfinityProfile.getInstance(), p));
            }));
        }



/*        contents.set(1, 2, ClickableItem.empty(new ItemBuilder(Material.FEATHER).setDisplayName("§aFly").build()));
        if(player.getAllowFlight()) {
            contents.set()
        }*/
    }

}
