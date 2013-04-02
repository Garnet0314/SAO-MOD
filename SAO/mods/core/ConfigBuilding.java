package SAO.mods.core;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigBuilding
{
    public static boolean PalaceSpawning;
    public static int xLocation;
    public static int yLocation;
    public static int zLocation;

    public void load(Configuration cfg)
    {
        this.PalaceSpawning = cfg.get("Building", "Palace", false).getBoolean(false);
        this.xLocation = cfg.get("Building", "PalaceXPosition", 0).getInt();
        this.yLocation = cfg.get("Building", "PalaceYPosition", 0).getInt();
        this.zLocation = cfg.get("Building", "PalaceZPosition", 0).getInt();
    }
}