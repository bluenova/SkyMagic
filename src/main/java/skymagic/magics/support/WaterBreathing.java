/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skymagic.magics.support;

import bluenova.fairytailcraft.plugin.MagePluginEvent;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BlockIterator;

/**
 *
 * @author Sven
 */
public class WaterBreathing extends MagePluginEvent {

    @Override
    public boolean callPlayerInteractEvent(PlayerInteractEvent event, Integer level) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player pl = event.getPlayer();
            int minLevel = 19;
            int neededPerLevel = 7;
            int range = 40;
            int playerLevel = skymagic.SkyMagic.manager.getPlayerConfig(event.getPlayer()).getLevel();
            
            minLevel = minLevel + ((level - 1) * neededPerLevel);

            if (playerLevel < minLevel) {
                pl.sendMessage("You must at least be level " + minLevel + " for " + level + " of Magic!");
                return false;
            }
            Player ent = null;
            List<Entity> nearbyE = pl.getNearbyEntities(range,
                    range, range);
            ArrayList<Player> livingE = new ArrayList<Player>();

            for (Entity e : nearbyE) {
                if (e instanceof Player) {
                    livingE.add((Player) e);
                }
            }

            ent = null;
            BlockIterator bItr = new BlockIterator(pl, range);
            Block block;
            Location loc;
            int bx, by, bz;
            double ex, ey, ez;
            // loop through player's line of sight
            while (bItr.hasNext()) {
                block = bItr.next();
                bx = block.getX();
                by = block.getY();
                bz = block.getZ();
                // check for entities near this block in the line of sight
                for (Player e : livingE) {
                    loc = e.getLocation();
                    ex = loc.getX();
                    ey = loc.getY();
                    ez = loc.getZ();
                    if ((bx - .75 <= ex && ex <= bx + 1.75) && (bz - .75 <= ez && ez <= bz + 1.75) && (by - 1 <= ey && ey <= by + 2.5)) {
                        // entity is close enough, set target and stop
                        ent = e;
                        break;
                    }
                }
            }
            boolean self = false;
            if (ent == null) {
                ent = pl;
                self = true;
            }

            ent.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, level * 30 * 100, 0));
            if (self) {
                pl.sendMessage(ChatColor.GREEN + "You got Water Breathing !");
            } else {
                pl.sendMessage(ChatColor.GREEN + "You gave Water Breathing  to " + ent.getName());
                ent.sendMessage(ChatColor.GREEN + "You got Water Breathing  from " + pl.getName());
            }
            pl.sendMessage("Mana decreesed!");
            return true;
        }
        return false;
    }
}
