package sao.mods.core;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.EnumHelper;
import sao.mods.world.BiomeGenSAOBase;
import sao.mods.world.WorldProviderSAO;

public class ConfigWorld
{
    public static int aincradDimensionID;
    public static int saoBiomeID;
    public static BiomeDictionary.Type BIOME_TYPE_SAO = EnumHelper.addEnum(BiomeDictionary.Type.class, "SAO", new Class[0], new Object[0]);

    public static boolean PalaceSpawning;
    public static int xLocation;
    public static int yLocation;
    public static int zLocation;

    public void preLoad(Configuration cfg)
    {
        this.aincradDimensionID = cfg.get("World", "aincradDimensionID", 100).getInt();
        this.saoBiomeID = cfg.get("World", "saoBiomeID", 100).getInt();

        this.PalaceSpawning = cfg.get("World", "Palace", false).getBoolean(false);
        this.xLocation = cfg.get("World", "PalaceXPosition", 0).getInt();
        this.yLocation = cfg.get("World", "PalaceYPosition", 0).getInt();
        this.zLocation = cfg.get("World", "PalaceZPosition", 0).getInt();
    }
}