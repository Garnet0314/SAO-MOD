package sao.mods.core;

public enum EnumHPType
{
    GREEN(0.0F, 1.0F, 0.0F, 1.0F),
    YELLOW(1.0F, 1.0F, 0.0F, 0.5F),
    RED(1.0F, 0.0F, 0.0F, 0.2F);

    private final float red;
    private final float green;
    private final float blue;
    private final float border;

    private EnumHPType(float red, float green, float blue, float border)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.border = border;
    }

    public float getColorRed()
    {
        return this.red;
    }

    public float getColorGreen()
    {
        return this.green;
    }

    public float getColorBlue()
    {
        return this.blue;
    }

    public float getColorBorder()
    {
        return this.border;
    }
}