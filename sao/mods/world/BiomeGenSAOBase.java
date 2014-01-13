package sao.mods.world;

import net.minecraft.world.biome.BiomeGenBase;
import sao.mods.core.ConfigWorld;

public class BiomeGenSAOBase extends BiomeGenBase
{
    public static final BiomeGenSAOBase[] saoBiomeList = new BiomeGenSAOBase[64];
    public static final BiomeGenSAOBase saoPlains = (BiomeGenSAOBase)(new BiomeGenSAOPlains(1, ConfigWorld.saoBiomeID + 1)).setColor(9286496).setBiomeName("SAO_Plains").setTemperatureRainfall(0.8F, 0.4F);

    public BiomeGenSAOBase(int par1, int par2)
    {
        super(par1);
    }
}