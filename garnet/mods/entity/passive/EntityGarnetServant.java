package garnet.mods.entity.passive;

import garnet.mods.entity.IServantGarnet;
import garnet.mods.entity.ISummonerGarnet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class EntityGarnetServant extends EntityGarnetBase implements IServantGarnet
{
    private EntityLivingBase theMaster;

    public EntityGarnetServant(World par1World)
    {
        super(par1World);
    }

    @Override
    protected boolean canDespawn()
    {
        return this.theMaster == null || !this.theMaster.isEntityAlive();
    }

    @Override
    public void onLivingUpdate()
    {
        if (!this.worldObj.isRemote)
        {
        	if (this.theMaster == null)
        	{
        		this.setDead();
        	}
        	else if (!this.theMaster.isEntityAlive())
        	{
        		this.setDead();
        	}
        }
        super.onLivingUpdate();
    }

    @Override
    public void setDead()
    {
        if (this.getSummoner() != null && this.theMaster.isEntityAlive())
        {
        	this.getSummoner().readySummon(true);
        }
        super.setDead();
    }

    protected void setMaster(EntityLivingBase par1EntityLivingBase)
    {
        this.theMaster = par1EntityLivingBase;
    }

    public EntityLivingBase getMaster()
    {
        return this.theMaster;
    }

    public ISummonerGarnet getSummoner()
    {
        if (this.theMaster instanceof ISummonerGarnet)
        {
        	return (ISummonerGarnet)this.theMaster;
        }
        return null;
    }

    @Override
    public String getOwnerName()
    {
        if (this.theMaster != null && this.theMaster instanceof EntityGarnetTameable)
        {
        	EntityGarnetTameable var1 = (EntityGarnetTameable)this.theMaster;
        	return var1.getOwnerName();
        }
        return "";
    }

    @Override
    public EntityPlayer getOwner()
    {
        if (this.theMaster != null && this.theMaster instanceof EntityGarnetTameable)
        {
        	EntityGarnetTameable var1 = (EntityGarnetTameable)this.theMaster;
        	return var1.getOwner();
        }
        return null;
    }
}