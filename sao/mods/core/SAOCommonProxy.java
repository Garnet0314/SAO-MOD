package sao.mods.core;

import net.minecraft.entity.player.EntityPlayer;

public interface SAOCommonProxy
{
    public int addArmor(String par1Str);

    public void registerRenderers();

    public void registerKeyBinds();

    public void openGui(EntityPlayer par1EntityPlayer);
}