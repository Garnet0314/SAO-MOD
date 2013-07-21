package sao.mods.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import sao.mods.SAOMOD;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSAOMaterial extends Item
{
    //Normal,Earth,Water,Fire,Wind,Light,Dark
    private String propertyName;
    private int propertyLevel;

    public ItemSAOMaterial(int par1)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setCreativeTab(SAOMOD.saoTabs);
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
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("sao:" + this.getUnlocalizedName());
    }
}