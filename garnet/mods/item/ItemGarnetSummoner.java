package garnet.mods.item;

import garnet.mods.entity.passive.EntityGarnetBase;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.world.World;

public abstract class ItemGarnetSummoner extends Item
{
    public ItemGarnetSummoner(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
        	return true;
        }
        else
        {
        	int var11 = par3World.getBlockId(par4, par5, par6);
        	par4 += Facing.offsetsXForSide[par7];
        	par5 += Facing.offsetsYForSide[par7];
        	par6 += Facing.offsetsZForSide[par7];
        	double var12 = 0.0D;

        	if (par7 == 1 && var11 == Block.fence.blockID || var11 == Block.netherFence.blockID)
        	{
        		var12 = 0.5D;
        	}

        	if (this.spawnGarnetBase(par1ItemStack, par2EntityPlayer, par3World, (double)par4 + 0.5D, (double)par5 + var12, (double)par6 + 0.5D) != null && !par2EntityPlayer.capabilities.isCreativeMode)
        	{
        		--par1ItemStack.stackSize;
        	}

        	return true;
        }
    }

    public abstract EntityGarnetBase spawnGarnetBase(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, double par4, double par5, double par6);
}