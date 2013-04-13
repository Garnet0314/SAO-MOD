package SAO.mods.core;

import net.minecraft.item.Item;
import SAO.mods.item.EnumSAOWeapon;
import SAO.mods.item.ItemSAOWeapon;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BuildSAOWeapon
{
    //片手剣
    public static Item stoneSword;
    public static Item stoneSword2;
    public static Item annealBlade;

    //両手剣
    public static Item stoneLongSword;
    public static Item stoneLongSword2;

    //細剣
    public static Item stoneRapier;
    public static Item stoneRapier2;
    public static Item windFleuret;

    //曲剣
    public static Item stoneCurvedSword;
    public static Item stoneCurvedSword2;

    //槍
    public static Item stoneSpear;
    public static Item stoneSpear2;

    //斧
    public static Item stoneAxe;
    public static Item stoneAxe2;
    public static Item stoneBattleAxe;
    public static Item stoneBattleAxe2;

    //短剣
    public static Item stoneShortSword;
    public static Item stoneShortSword2;

    //戦槌

    //棍
    public static Item stoneClub;
    public static Item stoneClub2;

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

        //両手剣
        //StoneLongSword
        stoneLongSword = (new ItemSAOWeapon(par1.longSwordID + 0, EnumSAOWeapon.StoneLongSword)).setUnlocalizedName("StoneLongSword");
        LanguageRegistry.addName(stoneLongSword, "Stone Long Sword");
        LanguageRegistry.instance().addNameForObject(stoneLongSword, "ja_JP", "石の両手剣");
        //StoneLongSword2
        stoneLongSword2 = (new ItemSAOWeapon(par1.longSwordID + 1, EnumSAOWeapon.StoneLongSword2)).setUnlocalizedName("StoneLongSwordBreak");
        LanguageRegistry.addName(stoneLongSword2, "Custom Stone Long Sword");
        LanguageRegistry.instance().addNameForObject(stoneLongSword2, "ja_JP", "石の両手剣・改");

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

        //曲剣
        //StoneCurvedSword
        stoneCurvedSword = (new ItemSAOWeapon(par1.curvedSwordID + 0, EnumSAOWeapon.StoneCurvedSword)).setUnlocalizedName("StoneScimitar");
        LanguageRegistry.addName(stoneCurvedSword, "Stone Scimitar");
        LanguageRegistry.instance().addNameForObject(stoneCurvedSword, "ja_JP", "石の曲剣");
        //StoneCurvedSword2
        stoneCurvedSword2 = (new ItemSAOWeapon(par1.curvedSwordID + 1, EnumSAOWeapon.StoneCurvedSword2)).setUnlocalizedName("StoneScimitarBreak");
        LanguageRegistry.addName(stoneCurvedSword2, "Custom Stone Scimitar");
        LanguageRegistry.instance().addNameForObject(stoneCurvedSword2, "ja_JP", "石の曲剣・改");

        //槍
        //StoneSpear
        stoneSpear = (new ItemSAOWeapon(par1.spearID + 0, EnumSAOWeapon.StoneSpear)).setUnlocalizedName("StoneSpear");
        LanguageRegistry.addName(stoneSpear, "Stone Spear");
        LanguageRegistry.instance().addNameForObject(stoneSpear, "ja_JP", "石の槍");
        //StoneSpear
        stoneSpear2 = (new ItemSAOWeapon(par1.spearID + 1, EnumSAOWeapon.StoneSpear2)).setUnlocalizedName("StoneSpearBreak");
        LanguageRegistry.addName(stoneSpear2, "Custom Stone Spear");
        LanguageRegistry.instance().addNameForObject(stoneSpear2, "ja_JP", "石の槍・改");

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

        //短剣
        //StoneShortSword
        stoneShortSword = (new ItemSAOWeapon(par1.shortSwordID + 0, EnumSAOWeapon.StoneShortSword)).setUnlocalizedName("StoneDagger");
        LanguageRegistry.addName(stoneShortSword, "Stone Dagger");
        LanguageRegistry.instance().addNameForObject(stoneShortSword, "ja_JP", "石の短剣");
        //StoneShortSword2
        stoneShortSword2 = (new ItemSAOWeapon(par1.shortSwordID + 1, EnumSAOWeapon.StoneShortSword2)).setUnlocalizedName("StoneDaggerBreak");
        LanguageRegistry.addName(stoneShortSword2, "Custom Stone Dagger");
        LanguageRegistry.instance().addNameForObject(stoneShortSword2, "ja_JP", "石の短剣・改");

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
    }
}