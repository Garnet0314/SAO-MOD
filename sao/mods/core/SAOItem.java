package sao.mods.core;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import sao.mods.SAOMOD;
import sao.mods.item.ItemSAOArmor;
import sao.mods.item.ItemSAOMoney;
import sao.mods.item.ItemSAOSkill;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SAOItem
{
    public static Item nerveGear;

    public static Item skillSword;
    public static Item skillLongSword;
    public static Item skillRpier;
    public static Item skillCurvedSword;
    public static Item skillSpear;
    public static Item skillAxe;
    public static Item skillShortSword;
    public static Item skillMace;
    public static Item skillClub;

    public static Item money100;
    public static Item money500;
    public static Item money1000;

    public static void build(ConfigItem par1)
    {
        //nerveGear
        nerveGear = (new ItemSAOArmor(par1.saoIemID + 0, EnumArmorMaterial.CHAIN, SAOMOD.proxy.addArmor("NerveGear"), 0)).setUnlocalizedName("nerveGear").setTextureName("sao:item.NerveGear").setCreativeTab(SAOMOD.saoTabs);
        LanguageRegistry.addName(nerveGear, "Nerve Gear");
        LanguageRegistry.instance().addNameForObject(nerveGear, "ja_JP", "ナーブギア");

        //SkillSword
        skillSword = (new ItemSAOSkill(par1.saoIemID + 1, "Sword")).setUnlocalizedName("SkillSword");
        LanguageRegistry.addName(skillSword, "Skill : Sword");
        LanguageRegistry.instance().addNameForObject(skillSword, "ja_JP", "片手剣スキル");

        //SkillLongSword
        skillLongSword = (new ItemSAOSkill(par1.saoIemID + 2, "LongSword")).setUnlocalizedName("SkillLongSword");
        LanguageRegistry.addName(skillLongSword, "Skill : Long Sword");
        LanguageRegistry.instance().addNameForObject(skillLongSword, "ja_JP", "両手剣スキル");

        //SkillRapier
        skillRpier = (new ItemSAOSkill(par1.saoIemID + 3, "Rapier")).setUnlocalizedName("SkillRapier");
        LanguageRegistry.addName(skillRpier, "Skill : Rapier");
        LanguageRegistry.instance().addNameForObject(skillRpier, "ja_JP", "細剣スキル");

        //SkillCurvedSword
        skillCurvedSword = (new ItemSAOSkill(par1.saoIemID + 4, "CurvedSword")).setUnlocalizedName("SkillCurvedSword");
        LanguageRegistry.addName(skillCurvedSword, "Skill : Curved Sword");
        LanguageRegistry.instance().addNameForObject(skillCurvedSword, "ja_JP", "曲剣スキル");

        //SkillSpear
        skillSpear = (new ItemSAOSkill(par1.saoIemID + 5, "Spear")).setUnlocalizedName("SkillSpear");
        LanguageRegistry.addName(skillSpear, "Skill : Spear");
        LanguageRegistry.instance().addNameForObject(skillSpear, "ja_JP", "槍スキル");

        //SkillAxe
        skillAxe = (new ItemSAOSkill(par1.saoIemID + 6, "Axe")).setUnlocalizedName("SkillAxe");
        LanguageRegistry.addName(skillAxe, "Skill : Axe");
        LanguageRegistry.instance().addNameForObject(skillAxe, "ja_JP", "斧スキル");

        //SkillShortSword
        skillShortSword = (new ItemSAOSkill(par1.saoIemID + 7, "ShortSword")).setUnlocalizedName("SkillShortSword");
        LanguageRegistry.addName(skillShortSword, "Skill : Short Sword");
        LanguageRegistry.instance().addNameForObject(skillShortSword, "ja_JP", "短剣スキル");

        //SkillMace
        skillMace = (new ItemSAOSkill(par1.saoIemID + 8, "Mace")).setUnlocalizedName("SkillMace");
        LanguageRegistry.addName(skillMace, "Skill : Mace");
        LanguageRegistry.instance().addNameForObject(skillMace, "ja_JP", "戦槌スキル");

        //SkillClub
        skillClub = (new ItemSAOSkill(par1.saoIemID + 9, "Club")).setUnlocalizedName("SkillClub");
        LanguageRegistry.addName(skillClub, "Skill : Club");
        LanguageRegistry.instance().addNameForObject(skillClub, "ja_JP", "棍スキル");

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