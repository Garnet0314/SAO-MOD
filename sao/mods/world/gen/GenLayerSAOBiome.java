package sao.mods.world.gen;

import java.util.Arrays;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.IntCache;
import sao.mods.world.BiomeGenSAOBase;

public class GenLayerSAOBiome extends GenLayerSAO
{
    private BiomeGenSAOBase[] allowedBiomes = new BiomeGenSAOBase[] {BiomeGenSAOBase.saoPlains};

    public GenLayerSAOBiome(long par1, GenLayerSAO par3GenLayer, WorldType par4WorldType)
    {
        super(par1);
        this.parent = par3GenLayer;
    }

    @Override
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.parent.getInts(par1, par2, par3, par4);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; ++i1)
        {
            for (int j1 = 0; j1 < par3; ++j1)
            {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + i1 * par3];

                if (k1 == 0)
                {
                    aint1[j1 + i1 * par3] = 0;
                }
                else if (k1 == BiomeGenSAOBase.mushroomIsland.biomeID)
                {
                    aint1[j1 + i1 * par3] = k1;
                }
                else if (k1 == 1)
                {
                    aint1[j1 + i1 * par3] = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;
                }
                else
                {
                    int l1 = this.allowedBiomes[this.nextInt(this.allowedBiomes.length)].biomeID;

                    if (l1 == BiomeGenSAOBase.taiga.biomeID)
                    {
                        aint1[j1 + i1 * par3] = l1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BiomeGenSAOBase.icePlains.biomeID;
                    }
                }
            }
        }

        return aint1;
    }
}