package SAO.mods.core;

import net.minecraft.item.Item;
import SAO.mods.item.EnumSAOWeapon;
import SAO.mods.item.ItemSAOWeapon;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BuildSAOWeapon
{
    public static Item smallSword;

    public static void build(ConfigWeapon par1)
    {
        //SmallSword
        smallSword = (new ItemSAOWeapon(par1.smallSwordID - 256, EnumSAOWeapon.SMALLSWORD)).setUnlocalizedName("smallSword");
        LanguageRegistry.addName(smallSword, "Small Sword");
        LanguageRegistry.instance().addNameForObject(smallSword, "ja_JP", "スモールソード");
    }
}