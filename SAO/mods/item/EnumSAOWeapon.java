package SAO.mods.item;

import net.minecraft.item.Item;

public enum EnumSAOWeapon
{
    //耐久、斬撃、刺突、打撃、貫通、地水火風光闇、必要スキルレベル、必要スキル
    SMALLSWORD(100, 4, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, "Novice");

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