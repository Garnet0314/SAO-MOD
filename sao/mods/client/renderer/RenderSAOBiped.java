package sao.mods.client.renderer;

import garnet.mods.client.renderer.RenderGarnetBiped;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;

import org.lwjgl.opengl.GL11;

import sao.mods.entity.ISAOMob;

public class RenderSAOBiped extends RenderGarnetBiped
{
    public RenderSAOBiped(ModelBiped par1ModelBiped, float par2)
    {
        super(par1ModelBiped, par2);
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