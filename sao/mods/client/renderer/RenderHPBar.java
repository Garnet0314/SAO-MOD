package sao.mods.client.renderer;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sao.mods.core.EnumHPType;

public class RenderHPBar
{
    private static final int X_OFFSET = 15;
    private static final int Y_OFFSET = 10;
    private static final int MARGIN_NAME_UPPER = 1;
    private static final int MARGIN_BG_BAR_HEIGHT = 2;
    private static final int MARGIN_BG_BAR_LEFT = 1;
    private static final int MARGIN_BG_BAR_RIGHT = 13;
    private static final int MARGIN_BG_UPPER_BAR_UPPER = 3;
    private static final float BAR_WIDTH_UPPER = 150.0F;
    private static final float BAR_WIDTH_LOWER = 75.0F;
    private static final int BAR_HEIGHT_HALF = 3;
    private static final int SLOPE_MOVEMENT_BAR_X = 3;
    private static final int MARGIN_NAME_BAR = 2;
    private static final int MARGIN_HP_LEVEL = 6;
    private static final int MARGIN_BAR_HP_X = 2;
    private static final int MARGIN_BAR_HP_Y = 2;
    private static final int MARGIN_INFO_HEIGHT = 1;
    private static final int MARGIN_INFO_WIDTH = 2;
    private static final int STRING_HEIGHT = 9;
    private static final int INFO_BG_COLOR = 1090519039;
    private static final int INFO_NAME_COLOR = 1996488703;
    private static final int MARGIN_NAME_LEFT = 2;
    private static final int MARGIN_NAME_RIGHT = 1;
    private static final ResourceLocation bar = new ResourceLocation("sao-hpbar", "textures/hp_bg.png");
    private final int nameOffset;
    private int max;
    private int current;
    private float ratio;
    private EnumHPType colorType;

    public RenderHPBar(int nameOffset)
    {
        this.nameOffset = nameOffset;
    }

    public void update(Minecraft minecraft)
    {
        EntityClientPlayerMP player = minecraft.thePlayer;
        boolean updated = false;
        if (this.max != MathHelper.ceiling_float_int(player.getMaxHealth()))
        {
        	updated = true;
        	this.max = MathHelper.ceiling_float_int(player.getMaxHealth());
        }

        if (this.current != MathHelper.ceiling_float_int(player.getHealth()))
        {
        	updated = true;
        	this.current = MathHelper.ceiling_float_int(player.getHealth());
        }

        if (updated)
        {
        	this.ratio = (this.current / this.max);
        	if (this.ratio <= EnumHPType.GREEN.getColorBorder())
        	{
        		this.colorType = EnumHPType.GREEN;
        	}
        	if (this.ratio <= EnumHPType.YELLOW.getColorBorder())
        	{
        		this.colorType = EnumHPType.YELLOW;
        	}
        	if (this.ratio <= EnumHPType.RED.getColorBorder())
        	{
        		this.colorType = EnumHPType.RED;
        	}
        }
    }

    public void render(Minecraft minecraft)
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3008);
        GL11.glEnable(3553);
        minecraft.renderEngine.bindTexture(this.bar);
        this.renderBarBackGround();
        GL11.glEnable(3008);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        this.renderBar();

        renderInfo(minecraft.fontRenderer, minecraft.thePlayer.username, minecraft.thePlayer.experienceLevel);
        GL11.glPopMatrix();
    }

    private void renderInfo(FontRenderer fontRenderer, String name, int level)
    {
        String hp = this.current + "/" + this.max;
        int hp_w = fontRenderer.getStringWidth(hp);
        int hp_x = MathHelper.ceiling_float_int(15 + this.nameOffset + 2 + 75.0F + 2.0F);

        int hp_y = 18;

        int lv_x = MathHelper.ceiling_float_int(15 + this.nameOffset + 2 + 75.0F + 2.0F + hp_w + 6.0F);

        int lv_y = 18;
        String level_s = "LV:" + level;
        int lv_w = fontRenderer.getStringWidth(level_s);

        Gui.drawRect(hp_x - 2, hp_y - 1, hp_x + hp_w + 2, hp_y + 9 - 1, 1090519039);

        fontRenderer.drawString(hp, hp_x, hp_y, Color.WHITE.getRGB(), false);

        Gui.drawRect(lv_x - 2, lv_y - 1, lv_x + lv_w + 2, lv_y + 9 - 1, 1090519039);

        fontRenderer.drawString(level_s, lv_x, lv_y, Color.WHITE.getRGB(), false);

        Gui.drawRect(13, 8, 15 + this.nameOffset + 1, 18, 1996488703);

        fontRenderer.drawString(name, 15, 9, Color.WHITE.getRGB(), false);
    }

    private void renderBar()
    {
        Tessellator tessellator = Tessellator.instance;

        float width = 15 + this.nameOffset + 2 + 150.0F * this.ratio;
        GL11.glColor4f(this.colorType.getColorRed(), this.colorType.getColorGreen(), this.colorType.getColorBlue(), 1.0F);
        tessellator.startDrawingQuads();
        tessellator.addVertex(15 + this.nameOffset + 2, 13.0D, 0.0D);
        tessellator.addVertex(Math.max(width - 3.0F, 0.0F), 13.0D, 0.0D);
        tessellator.addVertex(Math.max(width, 0.0F), 10.0D, 0.0D);
        tessellator.addVertex(15 + this.nameOffset + 2, 10.0D, 0.0D);
        tessellator.draw();

        tessellator.startDrawingQuads();
        if (this.ratio >= 0.5F)
        {
        	tessellator.addVertex(15 + this.nameOffset + 2, 16.0D, 0.0D);
        	tessellator.addVertex(15 + this.nameOffset + 2 + 75.0F - 6.0F, 16.0D, 0.0D);
        	tessellator.addVertex(15 + this.nameOffset + 2 + 75.0F - 3.0F, 13.0D, 0.0D);
        	tessellator.addVertex(15 + this.nameOffset + 2, 13.0D, 0.0D);
        }
        else
        {
        	tessellator.addVertex(15 + this.nameOffset + 2, 16.0D, 0.0D);
        	tessellator.addVertex(Math.max(width - 6.0F, 0.0F), 16.0D, 0.0D);
        	tessellator.addVertex(Math.max(width - 3.0F, 0.0F), 13.0D, 0.0D);
        	tessellator.addVertex(15 + this.nameOffset + 2, 13.0D, 0.0D);
        }
        tessellator.draw();
    }

    private void renderBarBackGround()
    {
        Tessellator tessellator = Tessellator.instance;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(15 + this.nameOffset + 2 - 1, 18.0D, 0.0D, 0.0D, 0.0390625D);
        tessellator.addVertexWithUV(15 + this.nameOffset + 2 + 150.0F + 13.0F, 18.0D, 0.0D, 0.640625D, 0.0390625D);
        tessellator.addVertexWithUV(15 + this.nameOffset + 2 + 150.0F + 13.0F, 8.0D, 0.0D, 0.640625D, 0.0D);
        tessellator.addVertexWithUV(15 + this.nameOffset + 2 - 1, 8.0D, 0.0D, 0.0D, 0.0D);
        tessellator.draw();

        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(7.5D, 18.0D, 0.0D, 0.0D, 0.125D);
        tessellator.addVertexWithUV(12.0D, 18.0D, 0.0D, 0.03125D, 0.125D);
        tessellator.addVertexWithUV(12.0D, 8.0D, 0.0D, 0.03125D, 0.0625D);
        tessellator.addVertexWithUV(7.5D, 8.0D, 0.0D, 0.0D, 0.0625D);
        tessellator.draw();
    }
}