package skymagic;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import bluenova.fairytailcraft.event.MageEventType;
import bluenova.fairytailcraft.plugin.MagePlugin;
import bluenova.fairytailcraft.plugin.MagePluginManager;
import skymagic.magics.fight.Slow;
import skymagic.magics.fight.Wind;
import skymagic.magics.heal.BigHeal;
import skymagic.magics.heal.MediumHeal;
import skymagic.magics.heal.SmallHeal;
import skymagic.magics.support.Damage;
import skymagic.magics.support.FireResistence;
import skymagic.magics.support.Speed;

/**
 *
 * @author Sven
 */
public class SkyMagic implements MagePlugin {

    private String magicName = "SkyMagic";
    public static MagePluginManager manager;

    public void setPluginManager(MagePluginManager manager) {
        SkyMagic.manager = manager;
    }

    public void loadPlugin() {
        SkyMagic.manager.registerMagic("smallheal", magicName, 1, 10, new SmallHeal(), MageEventType.INTERACT, false);
        SkyMagic.manager.registerMagic("incresedamage", magicName, 8, 13, new Damage(), MageEventType.INTERACT, false);
        SkyMagic.manager.registerMagic("mediumheal", magicName, 10, 15, new MediumHeal(), MageEventType.INTERACT, false);
        SkyMagic.manager.registerMagic("fireresistence", magicName, 16, 18, new FireResistence(), MageEventType.INTERACT, false);
        SkyMagic.manager.registerMagic("slow", magicName, 18, 23, new Slow(), MageEventType.INTERACT, false);
        SkyMagic.manager.registerMagic("incresespeed", magicName, 19, 24, new Speed(), MageEventType.INTERACT, false);
        SkyMagic.manager.registerMagic("bigheal", magicName, 20, 25, new BigHeal(), MageEventType.INTERACT, false);
        SkyMagic.manager.registerMagic("wind", magicName, 22, 30, new Wind(), MageEventType.INTERACT, false);
        System.out.println("SkyMagic Successfully Load!");
    }

    public void unloadPlugin() {
        System.out.println("SkyMagic Successfully Unload!");
    }

    public String getMagicName() {
        return magicName;
    }
}
