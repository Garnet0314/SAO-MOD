package sao.mods.item;

public enum EnumSAOWeapon
{
    //耐久、斬撃、刺突、打撃、貫通、地水火風光闇、必要スキルレベル、必要スキル
    //片手剣、基本：耐久普通・斬撃メイン
    StoneSword(150, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Sword"),
    StoneSword2(120, 6, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, "Sword"),
    AnneealBlade(200, 10, 6, 0, 4, 1, 0, 1, 0, 0, 0, 2, "Sword"),
    IronSword(400, 6, 4, 0, 0, 0, 0, 0, 0, 0, 0, 2, "Sword"),
    IronSword2(320, 8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 3, "Sword"),
    IronSword3(420, 9, 5, 0, 0, 0, 0, 0, 0, 0, 0, 4, "Sword"),
    IronSwordMud(400, 6, 4, 1, 0, 2, 0, 0, 0, 0, 0, 4, "Sword"),
    IronSwordWater(400, 6, 4, 0, 1, 0, 2, 0, 0, 0, 0, 4, "Sword"),
    IronSwordFire(400, 6, 5, 0, 0, 0, 0, 2, 0, 0, 0, 4, "Sword"),
    IronSwordBreeze(400, 7, 4, 0, 0, 0, 0, 0, 2, 0, 0, 4, "Sword"),

    //両手剣、基本：耐久高め・斬撃メイン高め・盾持ち不可・属性低め
    StoneLongSword(160, 5, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, "LongSword"),
    StoneLongSword2(128, 7, 1, 0, 2, 0, 0, 0, 0, 0, 0, 1, "LongSword"),
    IronLongSword(420, 9, 3, 0, 4, 0, 0, 0, 0, 0, 0, 2, "LongSword"),
    IronLongSword2(336, 11, 3, 0, 4, 0, 0, 0, 0, 0, 0, 3, "LongSword"),
    IronLongSword3(441, 12, 4, 1, 5, 0, 0, 0, 0, 0, 0, 4, "LongSword"),
    IronLongSwordMud(420, 10, 3, 1, 4, 1, 0, 0, 0, 0, 0, 4, "LongSword"),
    IronLongSwordWater(420, 10, 3, 0, 5, 0, 1, 0, 0, 0, 0, 4, "LongSword"),
    IronLongSwordFire(420, 10, 4, 0, 4, 0, 0, 1, 0, 0, 0, 4, "LongSword"),
    IronLongSwordBreeze(420, 11, 3, 0, 4, 0, 0, 0, 1, 0, 0, 4, "LongSword"),

    //細剣、基本：耐久低め・刺突メイン
    StoneRapier(130, 0, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    StoneRapier2(104, 0, 6, 0, 2, 0, 0, 0, 0, 0, 0, 1, "Rapier"),
    WindFleuret(120, 0, 5, 0, 2, 0, 0, 0, 2, 0, 0, 2, "Rapier"),
    IronRapier(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    IronRapier2(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    IronRapier3(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    IronRapierMud(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    IronRapierWater(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    IronRapierFire(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    IronRapierBreeze(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Rapier"),

    //曲剣、基本：耐久低め・斬撃メイン高め
    StoneCurvedSword(130, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    StoneCurvedSword2(104, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, "CurvedSword"),
    IronCurvedSword(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    IronCurvedSword2(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    IronCurvedSword3(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    IronCurvedSwordMud(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    IronCurvedSwordWater(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    IronCurvedSwordFire(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    IronCurvedSwordBreeze(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),

    //槍、基本：耐久高め・貫通メイン
    StoneSpear(160, 1, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    StoneSpear2(128, 1, 3, 0, 5, 0, 0, 0, 0, 0, 0, 1, "Spear"),
    IronSpear(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    IronSpear2(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    IronSpear3(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    IronSpearMud(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    IronSpearWater(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    IronSpearFire(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    IronSpearBreeze(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Spear"),

    //斧、基本：耐久普通
    StoneAxe(150, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    StoneAxe2(120, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, "Axe"),
    StoneBattleAxe(170, 6, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, "Axe"),
    StoneBattleAxe2(136, 7, 0, 2, 0, 0, 0, 0, 0, 0, 0, 3, "Axe"),
    IronAxe(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronAxe2(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronAxe3(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronAxeMud(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronAxeWater(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronAxeFire(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronAxeBreeze(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronBattleAxe(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronBattleAxe2(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronBattleAxe3(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronBattleAxeMud(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronBattleAxeWater(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronBattleAxeFire(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    IronBattleAxeBreeze(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),

    //短剣、基本：耐久普通・斬撃メイン低め・属性が高い
    StoneShortSword(150, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    StoneShortSword2(120, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, "ShortSword"),
    IronShortSword(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    IronShortSword2(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    IronShortSword3(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    IronShortSwordMud(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    IronShortSwordWater(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    IronShortSwordFire(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    IronShortSwordBreeze(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),

    //戦槌、基本：耐久普通・打撃メイン高め・盾持ち不可

    //棍、基本：耐久普通、打撃メイン普通
    StoneClub(150, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    StoneClub2(120, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 1, "Club"),
    IronClub(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    IronClub2(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    IronClub3(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    IronClubMud(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    IronClubWater(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    IronClubFire(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    IronClubBreeze(100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Club");
    //耐久、斬撃、刺突、打撃、貫通、地水火風光闇、必要スキルレベル、必要スキル

    private final int maxUses;

    private final float slashElement;
    private final float thrustElement;
    private final float strikeElement;
    private final float penetrateElement;

    private final float quakeElement;
    private final float waterElement;
    private final float fireElement;
    private final float aeroElement;
    private final float shineElement;
    private final float darkElement;

    private final int skillLevel;
    private final String skillType;

    private EnumSAOWeapon(int par1, int par2, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, int par11, int par12, String par13Str)
    {
        this.maxUses = par1;

        this.slashElement = par2;
        this.thrustElement = par3;
        this.strikeElement = par4;
        this.penetrateElement = par5;

        this.quakeElement = par6;
        this.waterElement = par7;
        this.fireElement = par8;
        this.aeroElement = par9;
        this.shineElement = par10;
        this.darkElement = par11;

        this.skillLevel = par12;
        this.skillType = par13Str;
    }

    public int getMaxUses()
    {
        return this.maxUses;
    }

    public float getSlashElement()
    {
        return this.slashElement;
    }

    public float getThrustElement()
    {
        return this.thrustElement;
    }

    public float getStrikeElement()
    {
        return this.strikeElement;
    }

    public float getPenetrateElement()
    {
        return this.penetrateElement;
    }

    public float getQuakeElement()
    {
        return this.quakeElement;
    }

    public float getWaterElement()
    {
        return this.waterElement;
    }

    public float getFireElement()
    {
        return this.fireElement;
    }

    public float getAeroElement()
    {
        return this.aeroElement;
    }

    public float getShineElement()
    {
        return this.shineElement;
    }

    public float getDarkElement()
    {
        return this.darkElement;
    }

    public int getSkillLevel()
    {
        return this.skillLevel;
    }

    public String getSkillType()
    {
        return this.skillType;
    }
}