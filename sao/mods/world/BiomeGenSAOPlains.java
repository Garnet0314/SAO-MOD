package sao.mods.world;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.biome.SpawnListEntry;

public class BiomeGenSAOPlains extends BiomeGenSAOBase
{
    protected BiomeGenSAOPlains(int par1, int par2)
    {
        super(par1, par2);
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.flowersPerChunk = 4;
        this.theBiomeDecorator.grassPerChunk = 10;
    }
}