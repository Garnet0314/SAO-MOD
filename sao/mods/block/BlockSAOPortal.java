package sao.mods.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * BlockPortalのコピペ
 */
public class BlockSAOPortal extends BlockBreakable
{
    public BlockSAOPortal(int par1)
    {
        super(par1, "sao:saoPortal", Material.portal, false);
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting)
        {
        	int l;

        	for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par2, l, par4) && l > 0; --l)
        	{
        		;
        	}

        	if (l > 0 && !par1World.isBlockNormalCube(par2, l + 1, par4))
        	{
        		Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57, (double)par2 + 0.5D, (double)l + 1.1D, (double)par4 + 0.5D);

        		if (entity != null)
        		{
        			entity.timeUntilPortal = entity.getPortalCooldown();
        		}
        	}
        }
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        float var1;
        float var2;

        if (par1IBlockAccess.getBlockId(par2 - 1, par3, par4) != this.blockID && par1IBlockAccess.getBlockId(par2 + 1, par3, par4) != this.blockID)
        {
            var1 = 0.125F;
            var2 = 0.5F;
            this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var2, 0.5F + var1, 1.0F, 0.5F + var2);
        }
        else
        {
            var1 = 0.5F;
            var2 = 0.125F;
            this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var2, 0.5F + var1, 1.0F, 0.5F + var2);
        }
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
    {
        //TODO
        return false;
    }

    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        byte var1 = 0;
        byte var2 = 1;

        if (par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID)
        {
        	var1 = 1;
        	var2 = 0;
        }

        int i1;

        for (i1 = par3; par1World.getBlockId(par2, i1 - 1, par4) == this.blockID; --i1)
        {
        	;
        }

        if (par1World.getBlockId(par2, i1 - 1, par4) != Block.obsidian.blockID)
        {
        	par1World.setBlockToAir(par2, par3, par4);
        }
        else
        {
        	int var3;

        	for (var3 = 1; var3 < 4 && par1World.getBlockId(par2, i1 + var3, par4) == this.blockID; ++var3)
        	{
        		;
        	}

        	if (var3 == 3 && par1World.getBlockId(par2, i1 + var3, par4) == Block.obsidian.blockID)
        	{
        		boolean flag = par1World.getBlockId(par2 - 1, par3, par4) == this.blockID || par1World.getBlockId(par2 + 1, par3, par4) == this.blockID;
        		boolean flag1 = par1World.getBlockId(par2, par3, par4 - 1) == this.blockID || par1World.getBlockId(par2, par3, par4 + 1) == this.blockID;

        		if (flag && flag1)
        		{
        			par1World.setBlockToAir(par2, par3, par4);
        		}
        		else
        		{
        			if ((par1World.getBlockId(par2 + var1, par3, par4 + var2) != Block.obsidian.blockID || par1World.getBlockId(par2 - var1, par3, par4 - var2) != this.blockID) && (par1World.getBlockId(par2 - var1, par3, par4 - var2) != Block.obsidian.blockID || par1World.getBlockId(par2 + var1, par3, par4 + var2) != this.blockID))
        			{
        				par1World.setBlockToAir(par2, par3, par4);
        			}
        		}
        	}
        	else
        	{
        		par1World.setBlockToAir(par2, par3, par4);
        	}
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par1IBlockAccess.getBlockId(par2, par3, par4) == this.blockID)
        {
            return false;
        }
        else
        {
            boolean var6 = par1IBlockAccess.getBlockId(par2 - 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 - 2, par3, par4) != this.blockID;
            boolean var7 = par1IBlockAccess.getBlockId(par2 + 1, par3, par4) == this.blockID && par1IBlockAccess.getBlockId(par2 + 2, par3, par4) != this.blockID;
            boolean var8 = par1IBlockAccess.getBlockId(par2, par3, par4 - 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 - 2) != this.blockID;
            boolean var9 = par1IBlockAccess.getBlockId(par2, par3, par4 + 1) == this.blockID && par1IBlockAccess.getBlockId(par2, par3, par4 + 2) != this.blockID;
            boolean var10 = var6 || var7;
            boolean var11 = var8 || var9;
            return var10 && par5 == 4 ? true : (var10 && par5 == 5 ? true : (var11 && par5 == 2 ? true : var11 && par5 == 3));
        }
    }

    @Override
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }

    @Override
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        if (par5Entity.ridingEntity == null && par5Entity.riddenByEntity == null)
        {
        	par5Entity.setInPortal();
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par5Random.nextInt(100) == 0)
        {
        	par1World.playSound((double)par2 + 0.5D, (double)par3 + 0.5D, (double)par4 + 0.5D, "portal.portal", 0.5F, par5Random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int var1 = 0; var1 < 4; ++var1)
        {
        	double var2 = (double)((float)par2 + par5Random.nextFloat());
        	double var3 = (double)((float)par3 + par5Random.nextFloat());
        	double var4 = (double)((float)par4 + par5Random.nextFloat());
        	double var5 = 0.0D;
        	double var6 = 0.0D;
        	double var7 = 0.0D;
        	int var8 = par5Random.nextInt(2) * 2 - 1;
        	var5 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
        	var6 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;
        	var7 = ((double)par5Random.nextFloat() - 0.5D) * 0.5D;

        	if (par1World.getBlockId(par2 - 1, par3, par4) != this.blockID && par1World.getBlockId(par2 + 1, par3, par4) != this.blockID)
        	{
        		var2 = (double)par2 + 0.5D + 0.25D * (double)var8;
        		var5 = (double)(par5Random.nextFloat() * 2.0F * (float)var8);
        	}
        	else
        	{
        		var4 = (double)par4 + 0.5D + 0.25D * (double)var8;
        		var7 = (double)(par5Random.nextFloat() * 2.0F * (float)var8);
        	}

        	par1World.spawnParticle("portal", var2, var3, var4, var5, var6, var7);
        }
    }

    @SideOnly(Side.CLIENT)
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return 0;
    }
}