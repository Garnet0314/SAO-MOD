package garnet.mods.client;

import garnet.mods.CommonProxy;
import garnet.mods.client.renderer.RenderGarnetBullet;
import garnet.mods.entity.projectile.EntityGarnetBullet;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class GarnetClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityGarnetBullet.class, new RenderGarnetBullet());
    }
}