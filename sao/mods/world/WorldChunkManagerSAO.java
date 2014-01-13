package sao.mods.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import sao.mods.world.gen.GenLayerSAO;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldChunkManagerSAO extends WorldChunkManager
{
    public static ArrayList<BiomeGenSAOBase> saoBiomes = new ArrayList<BiomeGenSAOBase>(Arrays.asList(BiomeGenSAOBase.saoPlains));
    private GenLayerSAO genBiomes;
    private GenLayerSAO biomeIndexLayer;
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;
    private BiomeGenSAOBase biomeToUse;

    protected WorldChunkManagerSAO()
    {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList();
        this.biomesToSpawnIn.addAll(this.saoBiomes);
    }

    public WorldChunkManagerSAO(long par1, WorldType par3WorldType)
    {
        this();
        GenLayerSAO[] agenlayer = GenLayerSAO.initializeAllBiomeGenerators(par1, par3WorldType);
        agenlayer = this.getModdedBiomeGeneratorsSAO(par3WorldType, par1, agenlayer);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }

    public WorldChunkManagerSAO(World par1World, BiomeGenSAOBase par2BiomeGenSAOBase)
    {
        this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
        this.biomeToUse = par2BiomeGenSAOBase;
    }

    @Override
    public List getBiomesToSpawnIn()
    {
        return this.biomesToSpawnIn;
    }

    @Override
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return this.biomeToUse;//this.biomeCache.getBiomeGenAt(par1, par2);
    }

    @Override
    public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
        	par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1)
        {
        	float f = BiomeGenSAOBase.biomeList[aint[i1]].getIntRainfall() / 65536.0F;

        	if (f > 1.0F)
        	{
        		f = 1.0F;
        	}

        	par1ArrayOfFloat[i1] = f;
        }

        return par1ArrayOfFloat;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }

    @Override
    public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1)
        {
            float f = BiomeGenSAOBase.biomeList[aint[i1]].getIntTemperature() / 65536.0F;

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            par1ArrayOfFloat[i1] = f;
        }

        return par1ArrayOfFloat;
    }

    @Override
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

        for (int i1 = 0; i1 < par4 * par5; ++i1)
        {
            par1ArrayOfBiomeGenBase[i1] = BiomeGenSAOBase.biomeList[aint[i1]];
        }

        //TODO
        Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.biomeToUse);
        return par1ArrayOfBiomeGenBase;
    }

    @Override
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5)
    {
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        Arrays.fill(par1ArrayOfBiomeGenBase, 0, par4 * par5, this.biomeToUse);
        return par1ArrayOfBiomeGenBase;
        //TODO
        //return this.getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }

    @Override
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0)
        {
            BiomeGenBase[] aBiomeGenBase1 = this.biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(aBiomeGenBase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }
        else
        {
            int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);

            for (int i1 = 0; i1 < par4 * par5; ++i1)
            {
                par1ArrayOfBiomeGenBase[i1] = BiomeGenSAOBase.biomeList[aint[i1]];
            }

            return par1ArrayOfBiomeGenBase;
        }
    }

    @Override
    public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
        return par4List.contains(this.biomeToUse);
        //TODO
        /*
        IntCache.resetIntCache();
        int l = par1 - par3 >> 2;
        int i1 = par2 - par3 >> 2;
        int j1 = par1 + par3 >> 2;
        int k1 = par2 + par3 >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);

        for (int j2 = 0; j2 < l1 * i2; ++j2)
        {
            BiomeGenBase biomeGenTofuBase = BiomeGenSAOBase.biomeList[aint[j2]];

            if (!par4List.contains(biomeGenTofuBase))
            {
                return false;
            }
        }

        return true;*/
    }

    @Override
    public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
    {
        IntCache.resetIntCache();
        int l = par1 - par3 >> 2;
        int i1 = par2 - par3 >> 2;
        int j1 = par1 + par3 >> 2;
        int k1 = par2 + par3 >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
        ChunkPosition chunkposition = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2)
        {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomeGenTofuBase = BiomeGenSAOBase.biomeList[aint[k2]];

            if (par4List.contains(biomeGenTofuBase) && (chunkposition == null || par5Random.nextInt(j2 + 1) == 0))
            {
                chunkposition = new ChunkPosition(l2, 0, i3);
                ++j2;
            }
        }

        //TODO
        //return chunkposition;
        return par4List.contains(this.biomeToUse) ? new ChunkPosition(par1 - par3 + par5Random.nextInt(par3 * 2 + 1), 0, par2 - par3 + par5Random.nextInt(par3 * 2 + 1)) : null;
    }

    @Override
    public void cleanupCache()
    {
        this.biomeCache.cleanupCache();
    }

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
        return original;
    }

    public GenLayerSAO[] getModdedBiomeGeneratorsSAO(WorldType worldType, long seed, GenLayerSAO[] original)
    {
        return original;
    }
}