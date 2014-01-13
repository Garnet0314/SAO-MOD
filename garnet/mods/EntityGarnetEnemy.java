package garnet.mods;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public abstract class EntityGarnetEnemy extends EntityMob
{
    public EntityGarnetEnemy(World par1World)
    {
        super(par1World);
    }

    protected abstract String getTexture();

    public String getTextureMode()
    {
        return "textures/enemies/" + this.getTexture() + ".png";
    }

    public abstract String getTextureLocal();

    @Override
    protected void dropEquipment(boolean par1, int par2){}
}