package garnet.mods.entity.passive;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityGarnetTameable extends EntityGarnetBase
{
    private String lastMessage;

    public EntityGarnetTameable(World par1World)
    {
        super(par1World);
        this.lastMessage = "";
    }

    @Override
    public void onLivingUpdate()
    {
        if ((this.isInWater() || !this.onGround) && this.isStandbyMode() && this.isEnable())
        {
        	this.setFollowMode();
        }
        super.onLivingUpdate();
    }

    @Override
    protected boolean canDespawn()
    {
        return !this.isEnable();
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
    {
        Entity var3 = par1DamageSource.getEntity();
        if (var3 instanceof EntityPlayer)
        {
        	EntityPlayer var4 = (EntityPlayer)var3;
        	ItemStack var5 = var4.inventory.getCurrentItem();
        	if (var5 != null && var5.itemID == this.getControlItemID())
        	{
        		par2 = 100.0F;
        	}
        }
        return super.attackEntityFrom(par1DamageSource, par2);
    }

    @Override
    protected void damageEntity(DamageSource par1DamageSource, float par2)
    {
        super.damageEntity(par1DamageSource, par2);
        if (this.getHealth() > 0.0F)
        {
        	this.chatMessage("HP = " + this.getHealth() * 0.5);
        }
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(15, Byte.valueOf((byte)0));
        this.dataWatcher.addObject(16, Byte.valueOf((byte)-1));
        this.dataWatcher.addObject(17, "");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        if (this.getOwnerName() == null)
        {
        	par1NBTTagCompound.setString("Owner", "");
        }
        else
        {
        	par1NBTTagCompound.setString("Owner", this.getOwnerName());
        }
        par1NBTTagCompound.setByte("Mode", this.dataWatcher.getWatchableObjectByte(16));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        String var1 = par1NBTTagCompound.getString("Owner");
        if (var1.length() > 0)
        {
        	this.setOwner(var1);
        	this.setEnable();
        	this.dataWatcher.updateObject(16, par1NBTTagCompound.getByte("Mode"));
        }
    }

    @Override
    public int getVerticalFaceSpeed()
    {
        return this.isStandbyMode() ? 20 : super.getVerticalFaceSpeed();
    }

    @Override
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
        ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();
        if (var2 != null && var2.itemID == this.getControlItemID())
        {
        	if (!this.worldObj.isRemote)
        	{
        		if (this.isEnable() && par1EntityPlayer == this.getOwner())
        		{
        			this.changeMode();
        		}
        		else
        		{
        			this.setEnable();
        			this.setPathToEntity(null);
        			this.setAttackTarget(null);
        			this.setOwner(par1EntityPlayer.username);
        			this.chatMessage(this.getEnableMessage());
        			this.setStandbyMode();
        		}
        	}
        	return true;
        }
        return super.interact(par1EntityPlayer);
    }

    protected abstract int getControlItemID();

    @Override
    public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)-1));
        this.chatMessage(this.getLogoutMessage());
    }

    @Override
    public void chatMessage(String par1Str)
    {
        EntityPlayer var1EntityPlayer = this.getOwner();
        if (var1EntityPlayer != null && !this.worldObj.isRemote)
        {
        	if (!this.lastMessage.equalsIgnoreCase(par1Str))
        	{
        		var1EntityPlayer.addChatMessage(" " + this.getEntityString() + " : " + par1Str);
        		this.lastMessage = par1Str;
        	}
        	System.out.println(" " + this.getEntityString() + " : " + par1Str);
        }
    }

    protected String getEnableMessage()
    {
        return "Awaken!";
    }

    protected String getLogoutMessage()
    {
        return "logout";
    }

    public boolean isEnable()
    {
        return this.dataWatcher.getWatchableObjectByte(15) == 1;
    }

    public void setEnable()
    {
        this.dataWatcher.updateObject(15, Byte.valueOf((byte)1));
    }

    public boolean isStandbyMode()
    {
        return this.dataWatcher.getWatchableObjectByte(16) == 0;
    }

    protected void setStandbyMode()
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)0));
        this.mountEntity(null);
        this.setPathToEntity(null);
        this.chatMessage("Standby mode");
        this.worldObj.playSoundEffect(this.posX, this.posY +(double)this.getEyeHeight(), this.posZ, "random.click", 0.3F, 0.6F);
    }

    public boolean isFreeMode()
    {
        return this.dataWatcher.getWatchableObjectByte(16) == 1;
    }

    protected void setFreeMode()
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)1));
        this.mountEntity(null);
        this.chatMessage("Free mode");
        this.worldObj.playSoundEffect(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, "random.click", 0.3F, 0.6F);
    }

    public boolean isFollowMode()
    {
        return this.dataWatcher.getWatchableObjectByte(16) == 2;
    }

    protected void setFollowMode()
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)2));
        this.mountEntity(null);
        this.chatMessage("Follow mode");
        this.worldObj.playSoundEffect(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, "random.click", 0.3F, 0.6F);
    }

    public boolean isSatelliteMode()
    {
        return this.dataWatcher.getWatchableObjectByte(16) == 3;
    }

    protected void setSatelliteMode()
    {
        this.dataWatcher.updateObject(16, Byte.valueOf((byte)3));
        this.mountEntity(null);
        this.chatMessage("Satellite mode");
        this.worldObj.playSoundEffect(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, "random.click", 0.3F, 0.6F);
    }

    protected abstract boolean canSatelliteMode();

    protected void changeMode()
    {
        String var1;
        if (this.isStandbyMode())
        {
        	if (this.canSatelliteMode())
        	{
        		this.setSatelliteMode();
        		var1 = "happyVillager";
        	}
        	else
        	{
        		this.setFreeMode();
        		var1 = "note";
        	}
        }
        else if (this.isSatelliteMode())
        {
        	this.setFreeMode();
        	var1 = "note";
        }
        else if (this.isFreeMode())
        {
        	this.setFollowMode();
        	var1 = "heart";
        }
        else
        {
        	this.setStandbyMode();
        	var1 = "spell";
        }

        double var0 = rand.nextGaussian() * 0.02D;
        double var2 = rand.nextGaussian() * 0.02D;
        double var4 = rand.nextGaussian() * 0.02D;
        this.worldObj.spawnParticle(var1, this.posX + (double)(rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(rand.nextFloat() * this.height), this.posZ + (double)(rand.nextFloat() * this.width * 2.0F) - (double)this.width, var0, var2, var4);
    }

    @Override
    public String getOwnerName()
    {
        return this.dataWatcher.getWatchableObjectString(17);
    }

    public void setOwner(String par1Str)
    {
        this.dataWatcher.updateObject(17, par1Str);
    }

    @Override
    public EntityPlayer getOwner()
    {
        return this.worldObj.getPlayerEntityByName(this.getOwnerName());
    }
}