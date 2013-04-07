package SAO.mods.core;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import SAO.mods.SAOMOD;
import SAO.mods.item.ItemSAOMoney;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BuildSAOItem
{
    public static Item nerveGear;
    public static Item money100;
    public static Item money500;
    public static Item money1000;

    public static void build(ConfigItem par1)
    {
        //NerveGear
        nerveGear = (new ItemArmor(par1.saoIemID + 0, EnumArmorMaterial.CHAIN, SAOMOD.proxy.addArmor("NerveGear"), 0)).setUnlocalizedName("SAO:item.NerveGear").setCreativeTab(SAOMOD.saoTabs);
        LanguageRegistry.addName(nerveGear, "Nerve Gear");
        LanguageRegistry.instance().addNameForObject(nerveGear, "ja_JP", "ナーブギア");

        //100
        money100 = (new ItemSAOMoney(par1.money100ID - 256)).setUnlocalizedName("100Col");
        LanguageRegistry.addName(money100, "100Col");
        LanguageRegistry.instance().addNameForObject(money100, "ja_JP", "100コル");
        //500
        money500 = (new ItemSAOMoney(par1.money500ID - 256)).setUnlocalizedName("500Col");
        LanguageRegistry.addName(money500, "500Col");
        LanguageRegistry.instance().addNameForObject(money500, "ja_JP", "500コル");
        //1000
        money1000 = (new ItemSAOMoney(par1.money1000ID - 256)).setUnlocalizedName("1000Col");
        LanguageRegistry.addName(money1000, "1000Col");
        LanguageRegistry.instance().addNameForObject(money1000, "ja_JP", "1000コル");
    }

    public static void addRecipe()
    {
        //500
        GameRegistry.addShapelessRecipe(new ItemStack(money500, 1), new Object[]{
        	new ItemStack(money100, 1), new ItemStack(money100, 1), new ItemStack(money100, 1), new ItemStack(money100, 1), new ItemStack(money100, 1)});
        //1000
        GameRegistry.addShapelessRecipe(new ItemStack(money1000, 1), new Object[]{
        	new ItemStack(money500, 1), new ItemStack(money500, 1)});
    }
}