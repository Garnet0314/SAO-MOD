package sao.mods.core;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.DimensionManager;
import sao.mods.world.BiomeGenSAOBase;
import sao.mods.world.WorldProviderSAO;

public class SAOWorld
{
    public static void load(ConfigWorld cfg)
    {
        DimensionManager.registerProviderType(cfg.aincradDimensionID, WorldProviderSAO.class, false);
        DimensionManager.registerDimension(cfg.aincradDimensionID, cfg.aincradDimensionID);
        for (BiomeGenSAOBase biome: BiomeGenSAOBase.saoBiomeList)
        {
        	if (biome != null)
        	{
        		BiomeDictionary.registerBiomeType(biome, cfg.BIOME_TYPE_SAO);
        	}
        }
    }
}