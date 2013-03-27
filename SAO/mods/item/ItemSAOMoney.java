package SAO.mods.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSAOMoney extends Item
{
    public ItemSAOMoney(int par1)
    {
        super(par1);
        this.maxStackSize = 64;
        this.setCreativeTab(CreativeTabs.tabMisc);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("SAO:" + this.getUnlocalizedName());
    }
}