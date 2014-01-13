package sao.mods.world.gen;

import net.minecraft.world.gen.layer.IntCache;
import sao.mods.world.BiomeGenSAOBase;

public class GenLayerSAOShore extends GenLayerSAO
{
    public GenLayerSAOShore(long par1, GenLayerSAO par3GenLayer)
    {
        super(par1);
        this.parent = par3GenLayer;
    }

    @Override
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
        int[] aint1 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par4; ++i1)
        {
            for (int j1 = 0; j1 < par3; ++j1)
            {
                this.initChunkSeed((long)(j1 + par1), (long)(i1 + par2));
                int k1 = aint[j1 + 1 + (i1 + 1) * (par3 + 2)];
                int l1;
                int i2;
                int j2;
                int k2;

                if (k1 == BiomeGenSAOBase.mushroomIsland.biomeID)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (l1 != BiomeGenSAOBase.ocean.biomeID && i2 != BiomeGenSAOBase.ocean.biomeID && j2 != BiomeGenSAOBase.ocean.biomeID && k2 != BiomeGenSAOBase.ocean.biomeID)
                    {
                        aint1[j1 + i1 * par3] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BiomeGenSAOBase.mushroomIslandShore.biomeID;
                    }
                }
                else if (k1 != BiomeGenSAOBase.ocean.biomeID && k1 != BiomeGenSAOBase.river.biomeID && k1 != BiomeGenSAOBase.swampland.biomeID && k1 != BiomeGenSAOBase.extremeHills.biomeID)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (l1 != BiomeGenSAOBase.ocean.biomeID && i2 != BiomeGenSAOBase.ocean.biomeID && j2 != BiomeGenSAOBase.ocean.biomeID && k2 != BiomeGenSAOBase.ocean.biomeID)
                    {
                        aint1[j1 + i1 * par3] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BiomeGenSAOBase.beach.biomeID;
                    }
                }
                else if (k1 == BiomeGenSAOBase.extremeHills.biomeID)
                {
                    l1 = aint[j1 + 1 + (i1 + 1 - 1) * (par3 + 2)];
                    i2 = aint[j1 + 1 + 1 + (i1 + 1) * (par3 + 2)];
                    j2 = aint[j1 + 1 - 1 + (i1 + 1) * (par3 + 2)];
                    k2 = aint[j1 + 1 + (i1 + 1 + 1) * (par3 + 2)];

                    if (l1 == BiomeGenSAOBase.extremeHills.biomeID && i2 == BiomeGenSAOBase.extremeHills.biomeID && j2 == BiomeGenSAOBase.extremeHills.biomeID && k2 == BiomeGenSAOBase.extremeHills.biomeID)
                    {
                        aint1[j1 + i1 * par3] = k1;
                    }
                    else
                    {
                        aint1[j1 + i1 * par3] = BiomeGenSAOBase.extremeHillsEdge.biomeID;
                    }
                }
                else
                {
                    aint1[j1 + i1 * par3] = k1;
                }
            }
        }

        return aint1;
    }
}