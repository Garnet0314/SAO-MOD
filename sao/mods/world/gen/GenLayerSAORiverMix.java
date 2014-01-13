package sao.mods.world.gen;

import net.minecraft.world.gen.layer.IntCache;
import sao.mods.world.BiomeGenSAOBase;

public class GenLayerSAORiverMix extends GenLayerSAO
{
    private GenLayerSAO biomePatternGeneratorChain;
    private GenLayerSAO riverPatternGeneratorChain;

    public GenLayerSAORiverMix(long par1, GenLayerSAO par3GenLayer, GenLayerSAO par4GenLayer)
    {
        super(par1);
        this.biomePatternGeneratorChain = par3GenLayer;
        this.riverPatternGeneratorChain = par4GenLayer;
    }

    @Override
    public void initWorldGenSeed(long par1)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(par1);
        this.riverPatternGeneratorChain.initWorldGenSeed(par1);
        super.initWorldGenSeed(par1);
    }

    @Override
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int[] aint = this.biomePatternGeneratorChain.getInts(par1, par2, par3, par4);
        int[] aint1 = this.riverPatternGeneratorChain.getInts(par1, par2, par3, par4);
        int[] aint2 = IntCache.getIntCache(par3 * par4);

        for (int i1 = 0; i1 < par3 * par4; ++i1)
        {
            if (aint[i1] == BiomeGenSAOBase.ocean.biomeID)
            {
                aint2[i1] = aint[i1];
            }
            else if (aint1[i1] >= 0)
            {
                if (aint[i1] == BiomeGenSAOBase.icePlains.biomeID)
                {
                    aint2[i1] = BiomeGenSAOBase.frozenRiver.biomeID;
                }
                else if (aint[i1] != BiomeGenSAOBase.mushroomIsland.biomeID && aint[i1] != BiomeGenSAOBase.mushroomIslandShore.biomeID)
                {
                    aint2[i1] = aint1[i1];
                }
                else
                {
                    aint2[i1] = BiomeGenSAOBase.mushroomIslandShore.biomeID;
                }
            }
            else
            {
                aint2[i1] = aint[i1];
            }
        }

        return aint2;
    }
}