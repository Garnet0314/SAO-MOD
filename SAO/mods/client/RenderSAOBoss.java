package SAO.mods.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.BossStatus;
import SAO.mods.entity.EntitySAOBoss;

public class RenderSAOBoss extends RenderSAOMob
{
    public RenderSAOBoss(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    @Override
    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderBoss((EntitySAOBoss)par1EntityLiving, par2, par4, par6, par8, par9);
        super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void renderBoss(EntitySAOBoss par1EntitySAOBoss, double par2, double par4, double par6, float par8, float par9)
    {
        BossStatus.func_82824_a(par1EntitySAOBoss, true);
    }
}