package sao.mods.world.gen;

import net.minecraft.world.gen.layer.IntCache;
import sao.mods.world.BiomeGenSAOBase;

public class GenLayerSAOSwampRivers extends GenLayerSAO
{
    public GenLayerSAOSwampRivers(long par1, GenLayerSAO par3GenLayer)
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

                if ((k1 != BiomeGenSAOBase.swampland.biomeID || this.nextInt(6) != 0) && (k1 != BiomeGenSAOBase.jungle.biomeID && k1 != BiomeGenSAOBase.jungleHills.biomeID || this.nextInt(8) != 0))
                {
                    aint1[j1 + i1 * par3] = k1;
                }
                else
                {
                    aint1[j1 + i1 * par3] = BiomeGenSAOBase.river.biomeID;
                }
            }
        }

        return aint1;
    }
}