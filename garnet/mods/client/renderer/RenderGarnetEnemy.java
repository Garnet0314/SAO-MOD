package garnet.mods.client.renderer;

import garnet.mods.EntityGarnetEnemy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderGarnetEnemy extends RenderLiving
{
    private float scale;

    public RenderGarnetEnemy(ModelBase par1ModelBase, float par2, float par3)
    {
        super(par1ModelBase, par2 * par3);
        this.scale = par3;
    }

    protected void preRenderScale(EntityGarnetEnemy par1EntityMob, float par2)
    {
        GL11.glScalef(this.scale, this.scale, this.scale);
    }

    @Override
    protected void preRenderCallback(EntityLivingBase par1EntityLivingBase, float par2)
    {
        this.preRenderScale((EntityGarnetEnemy)par1EntityLivingBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getThePath((EntityGarnetEnemy)par1Entity);
    }

    protected ResourceLocation getThePath(EntityGarnetEnemy par1EntityGarnetEnemy)
    {
        return new ResourceLocation(par1EntityGarnetEnemy.getTextureLocal(), par1EntityGarnetEnemy.getTextureMode());
    }
}