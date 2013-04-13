package SAO.mods.item;

public enum EnumSAOWeapon
{
    //耐久、斬撃、刺突、打撃、貫通、地水火風光闇、必要スキルレベル、必要スキル
    //片手剣、基本：耐久普通・斬撃メイン
    StoneSword(150, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Sword"),
    StoneSword2(135, 5, 2, 0, 0, 0, 0, 0, 0, 0, 0, 1, "Sword"),
    AnneealBlade(200, 6, 2, 0, 1, 0, 0, 0, 0, 0, 0, 2, "Sword"),

    //両手剣、基本：耐久高め・斬撃メイン高め・盾持ち不可
    StoneLongSword(160, 5, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, "LongSword"),
    StoneLongSword2(144, 6, 1, 0, 2, 0, 0, 0, 0, 0, 0, 1, "LongSword"),

    //細剣、基本：耐久低め・刺突メイン
    StoneRapier(130, 0, 5, 0, 2, 0, 0, 0, 0, 0, 0, 0, "Rapier"),
    StoneRapier2(117, 0, 6, 0, 2, 0, 0, 0, 0, 0, 0, 1, "Rapier"),
    WindFleuret(120, 0, 5, 0, 2, 0, 0, 0, 2, 0, 0, 2, "Rapier"),

    //曲剣、基本：耐久低め・斬撃メイン高め
    StoneCurvedSword(130, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "CurvedSword"),
    StoneCurvedSword2(117, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, "CurvedSword"),

    //槍、基本：耐久高め・貫通メイン
    StoneSpear(160, 1, 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, "Spear"),
    StoneSpear2(144, 1, 3, 0, 5, 0, 0, 0, 0, 0, 0, 1, "Spear"),

    //斧、基本：耐久普通
    StoneAxe(150, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Axe"),
    StoneAxe2(135, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, "Axe"),
    StoneBattleAxe(170, 6, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, "Axe"),
    StoneBattleAxe2(153, 7, 0, 2, 0, 0, 0, 0, 0, 0, 0, 3, "Axe"),

    //短剣、基本：耐久普通・斬撃メイン低め・属性付が多い
    StoneShortSword(150, 3, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, "ShortSword"),
    StoneShortSword2(135, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, "ShortSword"),

    //戦槌、基本：耐久普通・打撃メイン高め・盾持ち不可

    //棍、基本：耐久普通、打撃メイン普通
    StoneClub(150, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, "Club"),
    StoneClub2(135, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 1, "Club");
    //耐久、斬撃、刺突、打撃、貫通、地水火風光闇、必要スキルレベル、必要スキル

    private final int maxUses;

    private final int slashElement;
    private final int thrustElement;
    private final int strikeElement;
    private final int penetrateElement;

    private final int quakeElement;
    private final int waterElement;
    private final int fireElement;
    private final int aeroElement;
    private final int shineElement;
    private final int darkElement;

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

    public int getSlashElement()
    {
        return this.slashElement;
    }

    public int getThrustElement()
    {
        return this.thrustElement;
    }

    public int getStrikeElement()
    {
        return this.strikeElement;
    }

    public int getPenetrateElement()
    {
        return this.penetrateElement;
    }

    public int getQuakeElement()
    {
        return this.quakeElement;
    }

    public int getWaterElement()
    {
        return this.waterElement;
    }

    public int getFireElement()
    {
        return this.fireElement;
    }

    public int getAeroElement()
    {
        return this.aeroElement;
    }

    public int getShineElement()
    {
        return this.shineElement;
    }

    public int getDarkElement()
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