package SAO.mods.core;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SAOCreativeTab extends CreativeTabs
{
    public SAOCreativeTab(String par1Str)
    {
        super(par1Str);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getTabIconItemIndex()
    {
        return BuildSAOWeapon.smallSword.itemID;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel()
    {
        return "SAO";
    }
}