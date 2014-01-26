package sao.mods.client.renderer;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class RenderHPBarHelper
{
    private static final int FONT_HEIGHT = 12;

    public static final void renderStringWithBackGround(FontRenderer fontRenderer, String string, int x, int y, int font_color, int bg_color)
    {
        int string_w = fontRenderer.getStringWidth(string);

        Gui.drawRect(x, y, x + string_w, y + 12, bg_color);

        fontRenderer.drawString(string, x, y, font_color, false);
    }
}