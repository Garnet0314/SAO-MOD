package SAO.mods.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemSAOMaterial extends Item
{
    //Normal,Earth,Water,Fire,Wind,Light,Dark
    private String propertyName;
    private int propertyLevel;

    public ItemSAOMaterial(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.tabMisc);
        this.propertyName = "Normal";
        this.propertyLevel = 0;
    }

    public void setPropertyName(String par1Str, int par2)
    {
        this.propertyName = par1Str;
        this.propertyLevel = par2;
    }

    public String getPropertyName()
    {
        return this.propertyName;
    }

    public int getPropertyLevel()
    {
        return this.propertyLevel;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateIcons(IconRegister par1IconRegister)
    {
        this.iconIndex = par1IconRegister.registerIcon("SAO:" + this.getUnlocalizedName());
    }
}