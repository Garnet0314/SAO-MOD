package sao.mods.world;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import sao.mods.core.ConfigWorld;

public class WorldProviderSAO extends WorldProvider
{
    @Override
    public void registerWorldChunkManager()
    {
        this.worldChunkMgr = new WorldChunkManagerSAO(this.worldObj, BiomeGenSAOBase.saoPlains);
        this.dimensionId = ConfigWorld.aincradDimensionID;
    }

    @Override
    public IChunkProvider createChunkGenerator()
    {
        return new ChunkProviderSAO(this.worldObj, this.worldObj.getSeed());
    }

    @Override
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        int k = this.worldObj.getFirstUncoveredBlock(par1, par2);
        return k == 0 ? false : Block.blocksList[k].blockMaterial.blocksMovement();
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public boolean canRespawnHere()
    {
        return false;
    }

    @Override
    public ChunkCoordinates getEntrancePortalLocation()
    {
        return new ChunkCoordinates(100, 50, 0);
    }

    @Override
    public String getDimensionName()
    {
        return "1st";
    }
}