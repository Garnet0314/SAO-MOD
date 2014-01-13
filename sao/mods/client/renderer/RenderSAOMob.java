package sao.mods.client.renderer;

import garnet.mods.client.renderer.RenderGarnetEnemy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sao.mods.entity.ISAOMob;

public class RenderSAOMob extends RenderGarnetEnemy
{
    public RenderSAOMob(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2, 1.0F);
    }

    protected void preRenderScale(ISAOMob par1ISAOMob, float par2)
    {
        GL11.glScalef(par1ISAOMob.getSize(), par1ISAOMob.getSize(), par1ISAOMob.getSize());
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((ISAOMob)par1EntityLivingBase, par2);
    }
}