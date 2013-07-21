package sao.mods.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import sao.mods.SAOMOD;
import sao.mods.core.ConfigBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrassUB extends Block
{
    @SideOnly(Side.CLIENT)
    private Icon iconGrassTop;
    @SideOnly(Side.CLIENT)
    private Icon iconSnowSide;
    @SideOnly(Side.CLIENT)
    private Icon iconGrassSideOverlay;

    public BlockGrassUB(int par1)
    {
        super(par1, Material.grass);
        this.setTickRandomly(true);
        this.setCreativeTab(SAOMOD.saoBlocks);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.iconGrassTop : (par1 == 0 ? Block.dirt.getBlockTextureFromSide(par1) : this.blockIcon);
    }

    @Override
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (!par1World.isRemote)
        {
        	if (par1World.getBlockLightValue(par2, par3 + 1, par4) < 4 && par1World.getBlockLightOpacity(par2, par3 + 1, par4) > 2)
        	{
        		par1World.setBlock(par2, par3, par4, ConfigBlock.blockUB + Block.dirt.blockID);
        	}
        	else if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9)
        	{
        		for (int l = 0; l < 4; ++l)
        		{
        			int i1 = par2 + par5Random.nextInt(3) - 1;
        			int j1 = par3 + par5Random.nextInt(5) - 3;
        			int k1 = par4 + par5Random.nextInt(3) - 1;
        			int l1 = par1World.getBlockId(i1, j1 + 1, k1);

        			if (par1World.getBlockId(i1, j1, k1) == ConfigBlock.blockUB + Block.dirt.blockID && par1World.getBlockLightValue(i1, j1 + 1, k1) >= 4 && par1World.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
        			{
        				par1World.setBlock(i1, j1, k1, ConfigBlock.blockUB + Block.grass.blockID);
        			}
        		}
        	}
        }
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Block.dirt.idDropped(0, par2Random, par3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        if (par5 == 1)
        {
        	return this.iconGrassTop;
        }
        else if (par5 == 0)
        {
        	return Block.dirt.getBlockTextureFromSide(par5);
        }
        else
        {
        	Material material = par1IBlockAccess.getBlockMaterial(par2, par3 + 1, par4);
        	return material != Material.snow && material != Material.craftedSnow ? this.blockIcon : this.iconSnowSide;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("grass_side");
        this.iconGrassTop = par1IconRegister.registerIcon("grass_top");
        this.iconSnowSide = par1IconRegister.registerIcon("snow_side");
        this.iconGrassSideOverlay = par1IconRegister.registerIcon("grass_side_overlay");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        double d0 = 0.5D;
        double d1 = 1.0D;
        return ColorizerGrass.getGrassColor(d0, d1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
        return this.getBlockColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        int l = 0;
        int i1 = 0;
        int j1 = 0;

        for (int k1 = -1; k1 <= 1; ++k1)
        {
        	for (int l1 = -1; l1 <= 1; ++l1)
        	{
        		int i2 = par1IBlockAccess.getBiomeGenForCoords(par2 + l1, par4 + k1).getBiomeGrassColor();
        		l += (i2 & 16711680) >> 16;
        		i1 += (i2 & 65280) >> 8;
        		j1 += i2 & 255;
        	}
        }

        return (l / 9 & 255) << 16 | (i1 / 9 & 255) << 8 | j1 / 9 & 255;
    }

    /*@SideOnly(Side.CLIENT)
    public static Icon getIconSideOverlay()
    {
        return BuildSAOBlock.grassUB.iconGrassSideOverlay;
    }*/
}
