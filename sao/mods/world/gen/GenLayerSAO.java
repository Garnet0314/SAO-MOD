package sao.mods.world.gen;

import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class GenLayerSAO
{
    private long worldGenSeed;
    protected GenLayerSAO parent;
    private long chunkSeed;
    private long baseSeed;

    public static GenLayerSAO[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
        GenLayerSAOIsland genlayerisland = new GenLayerSAOIsland(1L);
        GenLayerSAOFuzzyZoom genlayerfuzzyzoom = new GenLayerSAOFuzzyZoom(2000L, genlayerisland);
        GenLayerSAOAddIsland genlayeraddisland = new GenLayerSAOAddIsland(1L, genlayerfuzzyzoom);
        GenLayerSAOZoom genlayerzoom = new GenLayerSAOZoom(2001L, genlayeraddisland);
        genlayeraddisland = new GenLayerSAOAddIsland(2L, genlayerzoom);
        GenLayerSAOAddSnow genlayeraddsnow = new GenLayerSAOAddSnow(2L, genlayeraddisland);
        genlayerzoom = new GenLayerSAOZoom(2002L, genlayeraddsnow);
        genlayeraddisland = new GenLayerSAOAddIsland(3L, genlayerzoom);
        genlayerzoom = new GenLayerSAOZoom(2003L, genlayeraddisland);
        genlayeraddisland = new GenLayerSAOAddIsland(4L, genlayerzoom);
        GenLayerSAOAddMushroomIsland genlayeraddmushroomisland = new GenLayerSAOAddMushroomIsland(5L, genlayeraddisland);
        byte b0 = 4;

        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            b0 = 6;
        }
        b0 = getModdedBiomeSize(par2WorldType, b0);

        GenLayerSAO genlayer = GenLayerSAOZoom.magnify(1000L, genlayeraddmushroomisland, 0);
        GenLayerSAORiverInit genlayerriverinit = new GenLayerSAORiverInit(100L, genlayer);
        genlayer = GenLayerSAOZoom.magnify(1000L, genlayerriverinit, b0 + 2);
        GenLayerSAORiver genlayerriver = new GenLayerSAORiver(1L, genlayer);
        GenLayerSAOSmooth genlayersmooth = new GenLayerSAOSmooth(1000L, genlayerriver);
        GenLayerSAO genlayer1 = GenLayerSAOZoom.magnify(1000L, genlayeraddmushroomisland, 0);
        GenLayerSAOBiome genlayerbiome = new GenLayerSAOBiome(200L, genlayer1, par2WorldType);
        genlayer1 = GenLayerSAOZoom.magnify(1000L, genlayerbiome, 2);
        Object object = new GenLayerSAOHills(1000L, genlayer1);

        for (int j = 0; j < b0; ++j)
        {
            object = new GenLayerSAOZoom((long)(1000 + j), (GenLayerSAO)object);

            if (j == 0)
            {
                object = new GenLayerSAOAddIsland(3L, (GenLayerSAO)object);
            }

            if (j == 1)
            {
                object = new GenLayerSAOShore(1000L, (GenLayerSAO)object);
            }

            if (j == 1)
            {
                object = new GenLayerSAOSwampRivers(1000L, (GenLayerSAO)object);
            }
        }

        GenLayerSAOSmooth genlayersmooth1 = new GenLayerSAOSmooth(1000L, (GenLayerSAO)object);
        GenLayerSAORiverMix genlayerrivermix = new GenLayerSAORiverMix(100L, genlayersmooth1, genlayersmooth);
        GenLayerSAOVoronoiZoom genlayervoronoizoom = new GenLayerSAOVoronoiZoom(10L, genlayerrivermix);
        genlayerrivermix.initWorldGenSeed(par0);
        genlayervoronoizoom.initWorldGenSeed(par0);
        return new GenLayerSAO[] {genlayerrivermix, genlayervoronoizoom, genlayerrivermix};
    }

    public GenLayerSAO(long par1)
    {
        this.baseSeed = par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += par1;
    }

    public void initWorldGenSeed(long par1)
    {
        this.worldGenSeed = par1;

        if (this.parent != null)
        {
            this.parent.initWorldGenSeed(par1);
        }

        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
    }

    public void initChunkSeed(long par1, long par3)
    {
        this.chunkSeed = this.worldGenSeed;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
    }

    protected int nextInt(int par1)
    {
        int j = (int)((this.chunkSeed >> 24) % (long)par1);

        if (j < 0)
        {
            j += par1;
        }

        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.worldGenSeed;
        return j;
    }

    public abstract int[] getInts(int i, int j, int k, int l);

    public static byte getModdedBiomeSize(WorldType worldType, byte original)
    {
        WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.newSize;
    }
}