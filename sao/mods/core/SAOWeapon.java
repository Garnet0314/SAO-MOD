package sao.mods.core;

import net.minecraft.item.Item;
import sao.mods.item.EnumSAOWeapon;
import sao.mods.item.ItemSAOWeapon;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class SAOWeapon
{
    //片手剣
    public static Item stoneSword;
    public static Item stoneSword2;
    public static Item ironSword;
    public static Item ironSword2;
    public static Item ironSword3;
    public static Item ironSwordMud;
    public static Item ironSwordWater;
    public static Item ironSwordFire;
    public static Item ironSwordBreeze;
    public static Item annealBlade;

    //両手剣
    public static Item stoneLongSword;
    public static Item stoneLongSword2;
    public static Item ironLongSword;
    public static Item ironLongSword2;
    public static Item ironLongSword3;
    public static Item ironLongSwordMud;
    public static Item ironLongSwordWater;
    public static Item ironLongSwordFire;
    public static Item ironLongSwordBreeze;

    //細剣
    public static Item stoneRapier;
    public static Item stoneRapier2;
    public static Item windFleuret;
    public static Item ironRapier;
    public static Item ironRapier2;
    public static Item ironRapier3;
    public static Item ironRapierMud;
    public static Item ironRapierWater;
    public static Item ironRapierFire;
    public static Item ironRapierBreeze;

    //曲剣
    public static Item stoneCurvedSword;
    public static Item stoneCurvedSword2;
    public static Item ironCurvedSword;
    public static Item ironCurvedSword2;
    public static Item ironCurvedSword3;
    public static Item ironCurvedSwordMud;
    public static Item ironCurvedSwordWater;
    public static Item ironCurvedSwordFire;
    public static Item ironCurvedSwordBreeze;

    //槍
    public static Item stoneSpear;
    public static Item stoneSpear2;
    public static Item ironSpear;
    public static Item ironSpear2;
    public static Item ironSpear3;
    public static Item ironSpearMud;
    public static Item ironSpearWater;
    public static Item ironSpearFire;
    public static Item ironSpearBreeze;

    //斧
    public static Item stoneAxe;
    public static Item stoneAxe2;
    public static Item stoneBattleAxe;
    public static Item stoneBattleAxe2;
    public static Item ironAxe;
    public static Item ironAxe2;
    public static Item ironAxe3;
    public static Item ironAxeMud;
    public static Item ironAxeWater;
    public static Item ironAxeFire;
    public static Item ironAxeBreeze;
    public static Item ironBattleAxe;
    public static Item ironBattleAxe2;
    public static Item ironBattleAxe3;
    public static Item ironBattleAxeMud;
    public static Item ironBattleAxeWater;
    public static Item ironBattleAxeFire;
    public static Item ironBattleAxeBreeze;

    //短剣
    public static Item stoneShortSword;
    public static Item stoneShortSword2;
    public static Item ironShortSword;
    public static Item ironShortSword2;
    public static Item ironShortSword3;
    public static Item ironShortSwordMud;
    public static Item ironShortSwordWater;
    public static Item ironShortSwordFire;
    public static Item ironShortSwordBreeze;

    //戦槌

    //棍
    public static Item stoneClub;
    public static Item stoneClub2;
    public static Item ironClub;
    public static Item ironClub2;
    public static Item ironClub3;
    public static Item ironClubMud;
    public static Item ironClubWater;
    public static Item ironClubFire;
    public static Item ironClubBreeze;

    public static void build(ConfigWeapon par1)
    {
        //片手剣
        //StoneSword
        stoneSword = (new ItemSAOWeapon(par1.swordID + 0, EnumSAOWeapon.StoneSword)).setUnlocalizedName("StoneSword");
        LanguageRegistry.addName(stoneSword, "Stone Sword");
        LanguageRegistry.instance().addNameForObject(stoneSword, "ja_JP", "石の片手剣");
        //StoneSword2
        stoneSword2 = (new ItemSAOWeapon(par1.swordID + 1, EnumSAOWeapon.StoneSword2)).setUnlocalizedName("StoneSwordBreak");
        LanguageRegistry.addName(stoneSword2, "Custom Stone Sword");
        LanguageRegistry.instance().addNameForObject(stoneSword2, "ja_JP", "石の片手剣・改");
        //AnnealBlade
        annealBlade = (new ItemSAOWeapon(par1.swordID + 2, EnumSAOWeapon.AnneealBlade)).setUnlocalizedName("AnnealBlade");
        LanguageRegistry.addName(annealBlade, "Anneal Blade");
        LanguageRegistry.instance().addNameForObject(annealBlade, "ja_JP", "アニールブレード");
        //IronSword
        ironSword = (new ItemSAOWeapon(par1.swordID + 3, EnumSAOWeapon.IronSword)).setUnlocalizedName("IronSword");
        LanguageRegistry.addName(ironSword, "Iron Sword");
        LanguageRegistry.instance().addNameForObject(ironSword, "ja_JP", "鉄の片手剣");
        //IronSword2
        ironSword2 = (new ItemSAOWeapon(par1.swordID + 4, EnumSAOWeapon.IronSword2)).setUnlocalizedName("IronSwordBreak");
        LanguageRegistry.addName(ironSword2, "Custom Iron Sword");
        LanguageRegistry.instance().addNameForObject(ironSword2, "ja_JP", "鉄の片手剣・改");
        //IronSword3
        ironSword3 = (new ItemSAOWeapon(par1.swordID + 5, EnumSAOWeapon.IronSword3)).setUnlocalizedName("IronSwordNeo");
        LanguageRegistry.addName(ironSword3, "Iron Sword Neo");
        LanguageRegistry.instance().addNameForObject(ironSword3, "ja_JP", "真・鉄の片手剣");
        //IronSwordMud
        ironSwordMud = (new ItemSAOWeapon(par1.swordID + 6, EnumSAOWeapon.IronSwordMud)).setUnlocalizedName("IronSwordMud");
        LanguageRegistry.addName(ironSwordMud, "Mud Sword");
        LanguageRegistry.instance().addNameForObject(ironSwordMud, "ja_JP", "泥の片手剣");
        //IronSwordWater
        ironSwordWater = (new ItemSAOWeapon(par1.swordID + 7, EnumSAOWeapon.IronSwordWater)).setUnlocalizedName("IronSwordWater");
        LanguageRegistry.addName(ironSwordWater, "Water Sword");
        LanguageRegistry.instance().addNameForObject(ironSwordWater, "ja_JP", "水の片手剣");
        //IronSwordFire
        ironSwordFire = (new ItemSAOWeapon(par1.swordID + 8, EnumSAOWeapon.IronSwordFire)).setUnlocalizedName("IronSwordFire");
        LanguageRegistry.addName(ironSwordFire, "Fire Sword");
        LanguageRegistry.instance().addNameForObject(ironSwordFire, "ja_JP", "火の片手剣");
        //IronSwordBreeze
        ironSwordBreeze = (new ItemSAOWeapon(par1.swordID + 9, EnumSAOWeapon.IronSwordBreeze)).setUnlocalizedName("IronSwordBreeze");
        LanguageRegistry.addName(ironSwordBreeze, "Breeze Sword");
        LanguageRegistry.instance().addNameForObject(ironSwordBreeze, "ja_JP", "そよ風の片手剣");

        //両手剣
        //StoneLongSword
        stoneLongSword = (new ItemSAOWeapon(par1.longSwordID + 0, EnumSAOWeapon.StoneLongSword)).setUnlocalizedName("StoneLongSword");
        LanguageRegistry.addName(stoneLongSword, "Stone Long Sword");
        LanguageRegistry.instance().addNameForObject(stoneLongSword, "ja_JP", "石の両手剣");
        //StoneLongSword2
        stoneLongSword2 = (new ItemSAOWeapon(par1.longSwordID + 1, EnumSAOWeapon.StoneLongSword2)).setUnlocalizedName("StoneLongSwordBreak");
        LanguageRegistry.addName(stoneLongSword2, "Custom Stone Long Sword");
        LanguageRegistry.instance().addNameForObject(stoneLongSword2, "ja_JP", "石の両手剣・改");
        //IronLongSword
        ironLongSword = (new ItemSAOWeapon(par1.longSwordID + 2, EnumSAOWeapon.IronLongSword)).setUnlocalizedName("IronLongSword");
        LanguageRegistry.addName(ironLongSword, "Iron Long Sword");
        LanguageRegistry.instance().addNameForObject(ironLongSword, "ja_JP", "鉄の両手剣");
        //IronLongSword2
        ironLongSword2 = (new ItemSAOWeapon(par1.longSwordID + 3, EnumSAOWeapon.IronLongSword2)).setUnlocalizedName("IronLongSwordBreak");
        LanguageRegistry.addName(ironLongSword2, "Custom Iron Long Sword");
        LanguageRegistry.instance().addNameForObject(ironLongSword2, "ja_JP", "鉄の両手剣・改");
        //IronLongSword
        ironLongSword3 = (new ItemSAOWeapon(par1.longSwordID + 4, EnumSAOWeapon.IronLongSword3)).setUnlocalizedName("IronLongSwordNeo");
        LanguageRegistry.addName(ironLongSword3, "Iron Long Sword Neo");
        LanguageRegistry.instance().addNameForObject(ironLongSword3, "ja_JP", "真・鉄の両手剣");
        //IronLongSwordMud
        ironLongSwordMud = (new ItemSAOWeapon(par1.longSwordID + 5, EnumSAOWeapon.IronLongSwordMud)).setUnlocalizedName("IronLongSwordMud");
        LanguageRegistry.addName(ironLongSwordMud, "Mud Long Sword");
        LanguageRegistry.instance().addNameForObject(ironLongSwordMud, "ja_JP", "泥の両手剣");
        //IronLongSwordWater
        ironLongSwordWater = (new ItemSAOWeapon(par1.longSwordID + 6, EnumSAOWeapon.IronLongSwordWater)).setUnlocalizedName("IronLongSwordWater");
        LanguageRegistry.addName(ironLongSwordWater, "Water Long Sword");
        LanguageRegistry.instance().addNameForObject(ironLongSwordWater, "ja_JP", "水の両手剣");
        //IronLongSwordFire
        ironLongSwordFire = (new ItemSAOWeapon(par1.longSwordID + 7, EnumSAOWeapon.IronLongSwordFire)).setUnlocalizedName("IronLongSwordFire");
        LanguageRegistry.addName(ironLongSwordFire, "Fire Long Sword");
        LanguageRegistry.instance().addNameForObject(ironLongSwordFire, "ja_JP", "火の両手剣");
        //IronLongSwordBreeze
        ironLongSwordBreeze = (new ItemSAOWeapon(par1.longSwordID + 8, EnumSAOWeapon.IronLongSwordBreeze)).setUnlocalizedName("IronLongSwordBreeze");
        LanguageRegistry.addName(ironLongSwordBreeze, "Breeze Long Sword");
        LanguageRegistry.instance().addNameForObject(ironLongSwordBreeze, "ja_JP", "そよ風の両手剣");

        //細剣
        //StoneRapier
        stoneRapier = (new ItemSAOWeapon(par1.rapierID + 0, EnumSAOWeapon.StoneRapier)).setUnlocalizedName("StoneFineSword");
        LanguageRegistry.addName(stoneRapier, "Stone Fine Sword");
        LanguageRegistry.instance().addNameForObject(stoneRapier, "ja_JP", "石の細剣");
        //StoneRapier2
        stoneRapier2 = (new ItemSAOWeapon(par1.rapierID + 1, EnumSAOWeapon.StoneRapier2)).setUnlocalizedName("StoneFineSwordBreak");
        LanguageRegistry.addName(stoneRapier2, "Custom Stone Fine Sword");
        LanguageRegistry.instance().addNameForObject(stoneRapier2, "ja_JP", "石の細剣・改");
        //WindFleuret
        windFleuret = (new ItemSAOWeapon(par1.rapierID + 2, EnumSAOWeapon.WindFleuret)).setUnlocalizedName("WindFleuret");
        LanguageRegistry.addName(windFleuret, "Wind Fleuret");
        LanguageRegistry.instance().addNameForObject(windFleuret, "ja_JP", "ウインドフルーレ");
        //IronRapier
        ironRapier = (new ItemSAOWeapon(par1.rapierID + 3, EnumSAOWeapon.IronRapier)).setUnlocalizedName("IronFineSword");
        LanguageRegistry.addName(ironRapier, "Iron Fine Sword");
        LanguageRegistry.instance().addNameForObject(ironRapier, "ja_JP", "鉄の細剣");
        //IronRapier2
        ironRapier2 = (new ItemSAOWeapon(par1.rapierID + 4, EnumSAOWeapon.IronRapier2)).setUnlocalizedName("IronFineSwordBreak");
        LanguageRegistry.addName(ironRapier2, "Custom Iron Fine Sword");
        LanguageRegistry.instance().addNameForObject(ironRapier2, "ja_JP", "鉄の細剣・改");
        //IronRapier
        ironRapier3 = (new ItemSAOWeapon(par1.rapierID + 5, EnumSAOWeapon.IronRapier3)).setUnlocalizedName("IronFineSwordNeo");
        LanguageRegistry.addName(ironRapier3, "Iron Fine Sword Neo");
        LanguageRegistry.instance().addNameForObject(ironRapier3, "ja_JP", "真・鉄の細剣");
        //IronRapierMud
        ironRapierMud = (new ItemSAOWeapon(par1.rapierID + 6, EnumSAOWeapon.IronRapierMud)).setUnlocalizedName("IronFineSwordMud");
        LanguageRegistry.addName(ironRapierMud, "Mud Fine Sword");
        LanguageRegistry.instance().addNameForObject(ironRapierMud, "ja_JP", "泥の細剣");
        //IronRapierWater
        ironRapierWater = (new ItemSAOWeapon(par1.rapierID + 7, EnumSAOWeapon.IronRapierWater)).setUnlocalizedName("IronFineSwordWater");
        LanguageRegistry.addName(ironRapierWater, "Water Fine Sword");
        LanguageRegistry.instance().addNameForObject(ironRapierWater, "ja_JP", "水の細剣");
        //IronRapierFire
        ironRapierFire = (new ItemSAOWeapon(par1.rapierID + 8, EnumSAOWeapon.IronRapierFire)).setUnlocalizedName("IronFineSwordFire");
        LanguageRegistry.addName(ironRapierFire, "Fire Fine Sword");
        LanguageRegistry.instance().addNameForObject(ironRapierFire, "ja_JP", "火の細剣");
        //IronRapierBreeze
        ironRapierBreeze = (new ItemSAOWeapon(par1.rapierID + 9, EnumSAOWeapon.IronRapierBreeze)).setUnlocalizedName("IronFineSwordBreeze");
        LanguageRegistry.addName(ironRapierBreeze, "Breeze Fine Sword");
        LanguageRegistry.instance().addNameForObject(ironRapierBreeze, "ja_JP", "そよ風の細剣");

        //曲剣
        //StoneCurvedSword
        stoneCurvedSword = (new ItemSAOWeapon(par1.curvedSwordID + 0, EnumSAOWeapon.StoneCurvedSword)).setUnlocalizedName("StoneScimitar");
        LanguageRegistry.addName(stoneCurvedSword, "Stone Scimitar");
        LanguageRegistry.instance().addNameForObject(stoneCurvedSword, "ja_JP", "石の曲剣");
        //StoneCurvedSword2
        stoneCurvedSword2 = (new ItemSAOWeapon(par1.curvedSwordID + 1, EnumSAOWeapon.StoneCurvedSword2)).setUnlocalizedName("StoneScimitarBreak");
        LanguageRegistry.addName(stoneCurvedSword2, "Custom Stone Scimitar");
        LanguageRegistry.instance().addNameForObject(stoneCurvedSword2, "ja_JP", "石の曲剣・改");
        //IronCurvedSword
        ironCurvedSword = (new ItemSAOWeapon(par1.curvedSwordID + 2, EnumSAOWeapon.IronCurvedSword)).setUnlocalizedName("IronScimitar");
        LanguageRegistry.addName(ironCurvedSword, "Iron Scimitar");
        LanguageRegistry.instance().addNameForObject(ironCurvedSword, "ja_JP", "鉄の曲剣");
        //IronCurvedSword2
        ironCurvedSword2 = (new ItemSAOWeapon(par1.curvedSwordID + 3, EnumSAOWeapon.IronCurvedSword2)).setUnlocalizedName("IronScimitarBreak");
        LanguageRegistry.addName(ironCurvedSword2, "Custom Iron Scimitar");
        LanguageRegistry.instance().addNameForObject(ironCurvedSword2, "ja_JP", "鉄の曲剣・改");
        //IronCurvedSword3
        ironCurvedSword3 = (new ItemSAOWeapon(par1.curvedSwordID + 4, EnumSAOWeapon.IronCurvedSword3)).setUnlocalizedName("IronScimitarNeo");
        LanguageRegistry.addName(ironCurvedSword3, "Iron Scimitar Neo");
        LanguageRegistry.instance().addNameForObject(ironCurvedSword3, "ja_JP", "真・鉄の曲剣");
        //IronCurvedSwordMud
        ironCurvedSwordMud = (new ItemSAOWeapon(par1.curvedSwordID + 5, EnumSAOWeapon.IronCurvedSwordMud)).setUnlocalizedName("IronScimitarMud");
        LanguageRegistry.addName(ironCurvedSwordMud, "Mud Scimitar");
        LanguageRegistry.instance().addNameForObject(ironCurvedSwordMud, "ja_JP", "泥の曲剣");
        //IronCurvedSwordWater
        ironCurvedSwordWater = (new ItemSAOWeapon(par1.curvedSwordID + 6, EnumSAOWeapon.IronCurvedSwordWater)).setUnlocalizedName("IronScimitarWater");
        LanguageRegistry.addName(ironCurvedSwordWater, "Water Scimitar");
        LanguageRegistry.instance().addNameForObject(ironCurvedSwordWater, "ja_JP", "水の曲剣");
        //IronCurvedSwordFire
        ironCurvedSwordFire = (new ItemSAOWeapon(par1.curvedSwordID + 7, EnumSAOWeapon.IronCurvedSwordFire)).setUnlocalizedName("IronScimitarFire");
        LanguageRegistry.addName(ironCurvedSwordFire, "Fire Scimitar");
        LanguageRegistry.instance().addNameForObject(ironCurvedSwordFire, "ja_JP", "火の曲剣");
        //IronCurvedSwordBreeze
        ironCurvedSwordBreeze = (new ItemSAOWeapon(par1.curvedSwordID + 8, EnumSAOWeapon.IronCurvedSwordBreeze)).setUnlocalizedName("IronScimitarBreeze");
        LanguageRegistry.addName(ironCurvedSwordBreeze, "Breeze Scimitar");
        LanguageRegistry.instance().addNameForObject(ironCurvedSwordBreeze, "ja_JP", "風の曲剣");

        //槍
        //StoneSpear
        stoneSpear = (new ItemSAOWeapon(par1.spearID + 0, EnumSAOWeapon.StoneSpear)).setUnlocalizedName("StoneSpear");
        LanguageRegistry.addName(stoneSpear, "Stone Spear");
        LanguageRegistry.instance().addNameForObject(stoneSpear, "ja_JP", "石の槍");
        //StoneSpear
        stoneSpear2 = (new ItemSAOWeapon(par1.spearID + 1, EnumSAOWeapon.StoneSpear2)).setUnlocalizedName("StoneSpearBreak");
        LanguageRegistry.addName(stoneSpear2, "Custom Stone Spear");
        LanguageRegistry.instance().addNameForObject(stoneSpear2, "ja_JP", "石の槍・改");
        //IronSpear
        ironSpear = (new ItemSAOWeapon(par1.spearID + 2, EnumSAOWeapon.IronSpear)).setUnlocalizedName("IronSpear");
        LanguageRegistry.addName(ironSpear, "Iron Spear");
        LanguageRegistry.instance().addNameForObject(ironSpear, "ja_JP", "鉄の槍");
        //IronSpear2
        ironSpear2 = (new ItemSAOWeapon(par1.spearID + 3, EnumSAOWeapon.IronSpear2)).setUnlocalizedName("IronSpearBreak");
        LanguageRegistry.addName(ironSpear2, "Custom Iron Spear");
        LanguageRegistry.instance().addNameForObject(ironSpear2, "ja_JP", "鉄の槍・改");
        //IronSpear3
        ironSpear3 = (new ItemSAOWeapon(par1.spearID + 4, EnumSAOWeapon.IronSpear3)).setUnlocalizedName("IronSpearNeo");
        LanguageRegistry.addName(ironSpear3, "Iron Spear Neo");
        LanguageRegistry.instance().addNameForObject(ironSpear3, "ja_JP", "真・鉄の槍");
        //IronSpearMud
        ironSpearMud = (new ItemSAOWeapon(par1.spearID + 5, EnumSAOWeapon.IronSpearMud)).setUnlocalizedName("IronSpearMud");
        LanguageRegistry.addName(ironSpearMud, "Mud Spear");
        LanguageRegistry.instance().addNameForObject(ironSpearMud, "ja_JP", "泥の槍");
        //IronSpearWater
        ironSpearWater = (new ItemSAOWeapon(par1.spearID + 6, EnumSAOWeapon.IronSpearWater)).setUnlocalizedName("IronSpearWater");
        LanguageRegistry.addName(ironSpearWater, "Water Spear");
        LanguageRegistry.instance().addNameForObject(ironSpearWater, "ja_JP", "水の槍");
        //IronSpearFire
        ironSpearFire = (new ItemSAOWeapon(par1.spearID + 7, EnumSAOWeapon.IronSpearFire)).setUnlocalizedName("IronSpearFire");
        LanguageRegistry.addName(ironSpearFire, "Fire Spear");
        LanguageRegistry.instance().addNameForObject(ironSpearFire, "ja_JP", "火の槍");
        //IronSpearBreeze
        ironSpearBreeze = (new ItemSAOWeapon(par1.spearID + 8, EnumSAOWeapon.IronSpearBreeze)).setUnlocalizedName("IronSpearBreeze");
        LanguageRegistry.addName(ironSpearBreeze, "Breeze Spear");
        LanguageRegistry.instance().addNameForObject(ironSpearBreeze, "ja_JP", "そよ風の槍");

        //斧
        //StoneAxe
        stoneAxe = (new ItemSAOWeapon(par1.axeID + 0, EnumSAOWeapon.StoneAxe)).setUnlocalizedName("StoneAxe");
        LanguageRegistry.addName(stoneAxe, "Stone Axe");
        LanguageRegistry.instance().addNameForObject(stoneAxe, "ja_JP", "石の斧");
        //StoneAxe2
        stoneAxe2 = (new ItemSAOWeapon(par1.axeID + 1, EnumSAOWeapon.StoneAxe2)).setUnlocalizedName("StoneAxeBreak");
        LanguageRegistry.addName(stoneAxe2, "Custom Stone Axe");
        LanguageRegistry.instance().addNameForObject(stoneAxe2, "ja_JP", "石の斧・改");
        //StoneBattleAxe
        stoneBattleAxe = (new ItemSAOWeapon(par1.axeID + 2, EnumSAOWeapon.StoneBattleAxe)).setUnlocalizedName("StoneBattleAxe");
        LanguageRegistry.addName(stoneBattleAxe, "Stone Battle Axe");
        LanguageRegistry.instance().addNameForObject(stoneBattleAxe, "ja_JP", "石の戦斧");
        //StoneBattleAxe2
        stoneBattleAxe2 = (new ItemSAOWeapon(par1.axeID + 3, EnumSAOWeapon.StoneBattleAxe2)).setUnlocalizedName("StoneBattleAxeBreak");
        LanguageRegistry.addName(stoneBattleAxe2, "Custom Stone Battle Axe");
        LanguageRegistry.instance().addNameForObject(stoneBattleAxe2, "ja_JP", "石の戦斧・改");
        //IronAxe
        ironAxe = (new ItemSAOWeapon(par1.axeID + 4, EnumSAOWeapon.IronAxe)).setUnlocalizedName("IronAxe");
        LanguageRegistry.addName(ironAxe, "Iron Axe");
        LanguageRegistry.instance().addNameForObject(ironAxe, "ja_JP", "鉄の斧");
        //IronAxe2
        ironAxe2 = (new ItemSAOWeapon(par1.axeID + 5, EnumSAOWeapon.IronAxe2)).setUnlocalizedName("IronAxeBreak");
        LanguageRegistry.addName(ironAxe, "Custom Iron Axe");
        LanguageRegistry.instance().addNameForObject(ironAxe, "ja_JP", "鉄の斧・改");
        //IronAxe3
        ironAxe3 = (new ItemSAOWeapon(par1.axeID + 6, EnumSAOWeapon.IronAxe3)).setUnlocalizedName("IronAxeNeo");
        LanguageRegistry.addName(ironAxe3, "Iron Axe Neo");
        LanguageRegistry.instance().addNameForObject(ironAxe3, "ja_JP", "真・鉄の斧");
        //IronAxeMud
        ironAxeMud = (new ItemSAOWeapon(par1.axeID + 7, EnumSAOWeapon.IronAxeMud)).setUnlocalizedName("IronAxeMud");
        LanguageRegistry.addName(ironAxeMud, "Mud Axe");
        LanguageRegistry.instance().addNameForObject(ironAxeMud, "ja_JP", "泥の斧");
        //IronAxeWater
        ironAxeWater = (new ItemSAOWeapon(par1.axeID + 8, EnumSAOWeapon.IronAxeWater)).setUnlocalizedName("IronAxeWater");
        LanguageRegistry.addName(ironAxeWater, "Water Axe");
        LanguageRegistry.instance().addNameForObject(ironAxeWater, "ja_JP", "水の斧");
        //IronAxeFire
        ironAxeFire = (new ItemSAOWeapon(par1.axeID + 9, EnumSAOWeapon.IronAxeFire)).setUnlocalizedName("IronAxeFire");
        LanguageRegistry.addName(ironAxeFire, "Fire Axe");
        LanguageRegistry.instance().addNameForObject(ironAxeFire, "ja_JP", "火の斧");
        //IronAxeBreeze
        ironAxeBreeze = (new ItemSAOWeapon(par1.axeID + 10, EnumSAOWeapon.IronAxeBreeze)).setUnlocalizedName("IronAxeBreeze");
        LanguageRegistry.addName(ironAxeBreeze, "Breeze Axe");
        LanguageRegistry.instance().addNameForObject(ironAxeBreeze, "ja_JP", "そよ風の斧");
        //IronBattleAxe
        ironBattleAxe = (new ItemSAOWeapon(par1.axeID + 11, EnumSAOWeapon.IronBattleAxe)).setUnlocalizedName("IronBattleAxe");
        LanguageRegistry.addName(ironBattleAxe, "Iron Battle Axe");
        LanguageRegistry.instance().addNameForObject(ironBattleAxe, "ja_JP", "鉄の戦斧");
        //IronBattleAxe2
        ironBattleAxe2 = (new ItemSAOWeapon(par1.axeID + 12, EnumSAOWeapon.IronBattleAxe2)).setUnlocalizedName("IronBattleAxeBreak");
        LanguageRegistry.addName(ironBattleAxe2, "Custom Iron Battle Axe");
        LanguageRegistry.instance().addNameForObject(ironBattleAxe2, "ja_JP", "鉄の戦斧・改");
        //IronBattleAxe3
        ironBattleAxe3 = (new ItemSAOWeapon(par1.axeID + 13, EnumSAOWeapon.IronBattleAxe3)).setUnlocalizedName("IronBattleAxeNeo");
        LanguageRegistry.addName(ironBattleAxe3, "Iron Battle Axe Neo");
        LanguageRegistry.instance().addNameForObject(ironBattleAxe3, "ja_JP", "真・鉄の戦斧");
        //IronBattleAxeMud
        ironBattleAxeMud = (new ItemSAOWeapon(par1.axeID + 14, EnumSAOWeapon.IronBattleAxeMud)).setUnlocalizedName("IronBattleAxeMud");
        LanguageRegistry.addName(ironBattleAxeMud, "Mud Battle Axe");
        LanguageRegistry.instance().addNameForObject(ironBattleAxeMud, "ja_JP", "泥の戦斧");
        //IronBattleAxeWater
        ironBattleAxeWater = (new ItemSAOWeapon(par1.axeID + 15, EnumSAOWeapon.IronBattleAxeWater)).setUnlocalizedName("IronBattleAxeWater");
        LanguageRegistry.addName(ironBattleAxeWater, "Water Battle Axe");
        LanguageRegistry.instance().addNameForObject(ironBattleAxeWater, "ja_JP", "水の戦斧");
        //IronBattleAxeFire
        ironBattleAxeFire = (new ItemSAOWeapon(par1.axeID + 16, EnumSAOWeapon.IronBattleAxeFire)).setUnlocalizedName("IronBattleAxeFire");
        LanguageRegistry.addName(ironBattleAxeFire, "Fire Battle Axe");
        LanguageRegistry.instance().addNameForObject(ironBattleAxeFire, "ja_JP", "火の戦斧");
        //IronBattleAxeBreeze
        ironBattleAxeBreeze = (new ItemSAOWeapon(par1.axeID + 17, EnumSAOWeapon.IronBattleAxeBreeze)).setUnlocalizedName("IronBattleAxeBreeze");
        LanguageRegistry.addName(ironBattleAxeBreeze, "Breeze Battle Axe");
        LanguageRegistry.instance().addNameForObject(ironBattleAxeBreeze, "ja_JP", "そよ風の戦斧");

        //短剣
        //StoneShortSword
        stoneShortSword = (new ItemSAOWeapon(par1.shortSwordID + 0, EnumSAOWeapon.StoneShortSword)).setUnlocalizedName("StoneDagger");
        LanguageRegistry.addName(stoneShortSword, "Stone Dagger");
        LanguageRegistry.instance().addNameForObject(stoneShortSword, "ja_JP", "石の短剣");
        //StoneShortSword2
        stoneShortSword2 = (new ItemSAOWeapon(par1.shortSwordID + 1, EnumSAOWeapon.StoneShortSword2)).setUnlocalizedName("StoneDaggerBreak");
        LanguageRegistry.addName(stoneShortSword2, "Custom Stone Dagger");
        LanguageRegistry.instance().addNameForObject(stoneShortSword2, "ja_JP", "石の短剣・改");
        //IronShortSword
        ironShortSword = (new ItemSAOWeapon(par1.shortSwordID + 2, EnumSAOWeapon.IronShortSword)).setUnlocalizedName("IronDagger");
        LanguageRegistry.addName(ironShortSword, "Iron Short Sword");
        LanguageRegistry.instance().addNameForObject(ironShortSword, "ja_JP", "鉄の短剣");
        //IronShortSword2
        ironShortSword2 = (new ItemSAOWeapon(par1.shortSwordID + 3, EnumSAOWeapon.IronShortSword2)).setUnlocalizedName("IronDaggerBreak");
        LanguageRegistry.addName(ironShortSword2, "Custom Iron Short Sword");
        LanguageRegistry.instance().addNameForObject(ironShortSword2, "ja_JP", "鉄の短剣・改");
        //IronShortSword3
        ironShortSword3 = (new ItemSAOWeapon(par1.shortSwordID + 4, EnumSAOWeapon.IronShortSword3)).setUnlocalizedName("IronDaggerNeo");
        LanguageRegistry.addName(ironShortSword3, "Iron Short Sword Neo");
        LanguageRegistry.instance().addNameForObject(ironShortSword3, "ja_JP", "真・鉄の短剣");
        //IronShortSwordMud
        ironShortSwordMud = (new ItemSAOWeapon(par1.shortSwordID + 5, EnumSAOWeapon.IronShortSwordMud)).setUnlocalizedName("IronDaggerMud");
        LanguageRegistry.addName(ironShortSwordMud, "Mud Short Sword");
        LanguageRegistry.instance().addNameForObject(ironShortSwordMud, "ja_JP", "泥の短剣");
        //IronShortSwordWater
        ironShortSwordWater = (new ItemSAOWeapon(par1.shortSwordID + 6, EnumSAOWeapon.IronShortSwordWater)).setUnlocalizedName("IronDaggerWater");
        LanguageRegistry.addName(ironShortSwordWater, "Water Short Sword");
        LanguageRegistry.instance().addNameForObject(ironShortSwordWater, "ja_JP", "水の短剣");
        //IronShortSwordFire
        ironShortSwordFire = (new ItemSAOWeapon(par1.shortSwordID + 7, EnumSAOWeapon.IronShortSwordFire)).setUnlocalizedName("IronDaggerFire");
        LanguageRegistry.addName(ironShortSwordFire, "Fire Short Sword");
        LanguageRegistry.instance().addNameForObject(ironShortSword, "ja_JP", "火の短剣");
        //IronShortSwordBreeze
        ironShortSwordBreeze = (new ItemSAOWeapon(par1.shortSwordID + 8, EnumSAOWeapon.IronShortSwordBreeze)).setUnlocalizedName("IronDaggerBreeze");
        LanguageRegistry.addName(ironShortSwordBreeze, "Iron Short Sword Breeze");
        LanguageRegistry.instance().addNameForObject(ironShortSwordBreeze, "ja_JP", "そよ風の短剣");

        //戦槌

        //棍
        //StoneClub
        stoneClub = (new ItemSAOWeapon(par1.clubID + 0, EnumSAOWeapon.StoneClub)).setUnlocalizedName("StoneClub");
        LanguageRegistry.addName(stoneClub, "Stone Club");
        LanguageRegistry.instance().addNameForObject(stoneClub, "ja_JP", "石の棍");
        //StoneClub2
        stoneClub2 = (new ItemSAOWeapon(par1.clubID + 1, EnumSAOWeapon.StoneClub2)).setUnlocalizedName("StoneClubBreak");
        LanguageRegistry.addName(stoneClub2, "Custom Stone Club");
        LanguageRegistry.instance().addNameForObject(stoneClub2, "ja_JP", "石の棍・改");
        //IronClub
        ironClub = (new ItemSAOWeapon(par1.clubID + 2, EnumSAOWeapon.IronClub)).setUnlocalizedName("IronClub");
        LanguageRegistry.addName(ironClub, "Iron Club");
        LanguageRegistry.instance().addNameForObject(ironClub, "ja_JP", "鉄の棍");
        //IronClub2
        ironClub2 = (new ItemSAOWeapon(par1.clubID + 3, EnumSAOWeapon.IronClub2)).setUnlocalizedName("IronClubBreak");
        LanguageRegistry.addName(ironClub2, "Custom Iron Club");
        LanguageRegistry.instance().addNameForObject(ironClub2, "ja_JP", "鉄の棍・改");
        //IronClub3
        ironClub3 = (new ItemSAOWeapon(par1.clubID + 4, EnumSAOWeapon.IronClub3)).setUnlocalizedName("IronClubNeo");
        LanguageRegistry.addName(ironClub3, "Iron Club Neo");
        LanguageRegistry.instance().addNameForObject(ironClub3, "ja_JP", "真・鉄の棍");
        //IronClubMud
        ironClubMud = (new ItemSAOWeapon(par1.clubID + 5, EnumSAOWeapon.IronClubMud)).setUnlocalizedName("IronClubMud");
        LanguageRegistry.addName(ironClubMud, "Mud Club");
        LanguageRegistry.instance().addNameForObject(ironClubMud, "ja_JP", "泥の棍");
        //IronClubWater
        ironClubWater = (new ItemSAOWeapon(par1.clubID + 6, EnumSAOWeapon.IronClubWater)).setUnlocalizedName("IronClubWater");
        LanguageRegistry.addName(ironClubWater, "Water Club");
        LanguageRegistry.instance().addNameForObject(ironClubWater, "ja_JP", "水の棍");
        //IronClubFire
        ironClubFire = (new ItemSAOWeapon(par1.clubID + 7, EnumSAOWeapon.IronClubFire)).setUnlocalizedName("IronClubFire");
        LanguageRegistry.addName(ironClubFire, "Fire Club");
        LanguageRegistry.instance().addNameForObject(ironClubFire, "ja_JP", "火の棍");
        //IronClubBreeze
        ironClubBreeze = (new ItemSAOWeapon(par1.clubID + 8, EnumSAOWeapon.IronClubBreeze)).setUnlocalizedName("IronClubBreeze");
        LanguageRegistry.addName(ironClubBreeze, "Iron Club");
        LanguageRegistry.instance().addNameForObject(ironClubBreeze, "ja_JP", "そよ風の棍");
    }
}