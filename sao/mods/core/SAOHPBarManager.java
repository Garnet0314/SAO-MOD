package sao.mods.core;

import net.minecraft.client.Minecraft;
import sao.mods.client.renderer.RenderHPBar;

public class SAOHPBarManager
{
    private RenderHPBar barobj;

    public void checkUpdate(Minecraft minecraft)
    {
        this.barobj.update(minecraft);
    }

    public void render(Minecraft minecraft)
    {
        this.barobj.render(minecraft);
    }

    public void setBar(RenderHPBar barobj)
    {
        this.barobj = barobj;
    }
}