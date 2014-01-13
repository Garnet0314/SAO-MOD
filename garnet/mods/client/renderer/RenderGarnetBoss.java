package garnet.mods.client.renderer;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;

public class RenderGarnetBoss extends RenderGarnetEnemy
{
    public RenderGarnetBoss(ModelBase par1ModelBase, float par2, float par3)
    {
        super(par1ModelBase, par2, par3);
    }

    @Override
    public void renderPlayer(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBoss(par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRenderLiving(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBoss(par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBoss((EntityLivingBase)par1Entity, par2, par4, par6, par8, par9);
    }

    public void renderBoss(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9)
    {
        if (par1EntityLivingBase instanceof IBossDisplayData)
        {
        	BossStatus.setBossStatus((IBossDisplayData)par1EntityLivingBase, true);
        }
        super.doRenderLiving(par1EntityLivingBase, par2, par4, par6, par8, par9);
    }
}