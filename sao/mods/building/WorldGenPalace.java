package sao.mods.building;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import sao.mods.core.ConfigBlock;

public class WorldGenPalace
{
    private World theWorld;
    private static int xPosition;
    private static int yPosition;
    private static int zPosition;
    //y,x,z座標の順
    private int[][][] createLayer = new int[2][][];

    public WorldGenPalace(World par1World, int par2, int par3, int par4)
    {
        this.theWorld = par1World;
        this.xPosition = par2;
        this.yPosition = par3;
        this.zPosition = par4;
        this.setCreateLayer();
    }

    public void CreatePalace()
    {
        int var1;
        int var2;
        int var3;
        int var4;

        //基準点から上へ100x100x100を空気へ変換
        for (var1 = 0; var1 < 100; var1++)
        {
        	for (var2 = 0; var2 < 100; var2++)
        	{
        		for (var3 = 0; var3 < 100; var3++)
        		{
        			var4 = this.theWorld.getBlockId(this.xPosition + var1, this.yPosition + var2, this.zPosition + var3);
        			if (var4 != 0 && var4 != Block.bedrock.blockID)
        			{
        				this.setBlock(this.xPosition + var1, this.yPosition + var2, this.zPosition + var3, 0);
        			}
        		}
        	}
        }

        //基準点から下へ100x32x100を壊せない石ブロックへ変換
        for (var1 = 0; var1 < 100; var1++)
        {
        	for (var2 = 0; var2 < 32; var2++)
        	{
        		for (var3 = 0; var3 < 100; var3++)
        		{
        			this.setBlock(this.xPosition + var1, this.yPosition - var2 - 1, this.zPosition + var3, ConfigBlock.blockUB + Block.stone.blockID);
        		}
        	}
        }

        //処理部分
        for (var2 = 0; var2 < this.createLayer.length; var2++)
        {
        	for (var1 = 0; var1 < this.createLayer[var2].length; var1++)
        	{
        		for (var3 = 0; var3 < this.createLayer[var2][var1].length; var3++)
        		{
        			var4 = this.getBlockFromLayer(this.createLayer[var2][var1][var3]);
        			if (var4 != 0)
        			{
        				this.setBlock(this.xPosition + var1, this.yPosition + var2, this.zPosition + var3, var4);
        			}
        		}
        	}
        }

        //万が一さとうきびなんかがアイテム化した場合削除
        if (!this.theWorld.loadedEntityList.isEmpty())
        {
        	for (var4 = 0; var4 < this.theWorld.loadedEntityList.size(); var4++)
        	{
        		Entity var5 = (Entity)this.theWorld.loadedEntityList.get(var4);
        		if (var5 instanceof EntityItem)
        		{
        			EntityItem var6 = (EntityItem)var5;
        			var6.setDead();
        		}
        	}
        }
    }

    private boolean setBlock(int par1, int par2, int par3, int par4)
    {
        return this.theWorld.setBlock(par1, par2, par3, par4);
    }

    private boolean setBlockWithMetadata(int par1, int par2, int par3, int par4, int par5)
    {
        return this.theWorld.setBlock(par1, par2, par3, par4, par5, 0x02);
    }

    private int getBlockFromLayer(int par1)
    {
        if (par1 == 0)
        {
        	return 0;
        }
        else if (par1 == 1)
        {
        	return ConfigBlock.blockUB + Block.stone.blockID;
        }
        return 0;
    }

    //各層の構造
    private void setCreateLayer()
    {
        this.createLayer[0] = new int[][]{
        		{0,0,0,0,0,0,0,0,0,0},
        		{0,1,1,1,1,1,1,1,1,0}
        };

        this.createLayer[1] = new int[][]{
        		{0,1,0,1,0,1,0,1,0,1},
        		{1,0,1,0,1,0,1,0,1,0}
        };
    }
}