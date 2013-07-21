package sao.mods.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import sao.mods.SAOMOD;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSAOMoney extends Item
{
    public ItemSAOMoney(int par1)
    {
        super(par1);
        this.maxStackSize = 64;
        this.setCreativeTab(SAOMOD.saoTabs);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("sao:" + this.getUnlocalizedName());
    }
}