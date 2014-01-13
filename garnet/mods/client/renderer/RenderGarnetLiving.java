package garnet.mods.client.renderer;

import org.lwjgl.opengl.GL11;

import garnet.mods.EntityGarnetEnemy;
import garnet.mods.entity.passive.EntityGarnetBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderGarnetLiving extends RenderLiving
{
    private float scale;

    public RenderGarnetLiving(ModelBase par1ModelBase, float par2)
    {
        this(par1ModelBase, par2, 1.0F);
    }

    public RenderGarnetLiving(ModelBase par1ModelBase, float par2, float par3)
    {
        super(par1ModelBase, par2);
        this.scale = par3;
    }

    protected void preRenderScale(EntityGarnetBase par1EntityGarnetBase, float par2)
    {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((EntityGarnetBase)par1EntityLivingBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getThePath((EntityGarnetBase)par1Entity);
    }

    protected ResourceLocation getThePath(EntityGarnetBase par1EntityGarnetBase)
    {
        return new ResourceLocation(par1EntityGarnetBase.getTextureLocal(), par1EntityGarnetBase.getTextureMode());
    }
}