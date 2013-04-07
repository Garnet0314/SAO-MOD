package SAO.mods.entity;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import SAO.mods.entity.ai.EntitySAOAINearestAttackableTarget;

public class EntitySAOZombie extends EntityMob implements ISAOMob
{
    public EntitySAOZombie(World par1World)
    {
        super(par1World);
        this.texture = "/mob/zombie.png";
        this.moveSpeed = 0.23F;
        this.getNavigator().setBreakDoors(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, this.moveSpeed, false));
        this.tasks.addTask(6, new EntityAIWander(this, this.moveSpeed));
        this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntitySAOAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0.0F, true));
    }

    @Override
    public String getTexture()
    {
        if (this.getEntityType() == 0)
        {
        	return "/mob/zombie.png";
        }
        return "/mob/zombie.png";
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    public float getSpeedModifier()
    {
        if (this.getEntityType() == 4)
        {
        	return super.getSpeedModifier() * 2.5F;
        }
        else if (this.getEntityType() == 5)
        {
        	return super.getSpeedModifier() * 1.5F;
        }
        return super.getSpeedModifier();
    }

    @Override
    public int getTotalArmorValue()
    {
        if (this.getEntityType() == 0)
        {
        	return 0;
        }
        else if (this.getEntityType() == 1)
        {
        	return 2;
        }
        else if (this.getEntityType() == 2)
        {
        	return 7;
        }
        else if (this.getEntityType() == 3)
        {
        	return 4;
        }
        else if (this.getEntityType() == 4)
        {
        	return 12;
        }
        else if (this.getEntityType() == 5)
        {
        	return 15;
        }
        else
        {
        	return 1;
        }
    }

    @Override
    public int getMaxHealth()
    {
        return 25;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(15, Byte.valueOf((byte)0));
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        if (par1NBTTagCompound.hasKey("EntityType"))
        {
            byte var1 = par1NBTTagCompound.getByte("EntityType");
            this.setEntityType(var1);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("EntityType", (byte)this.getEntityType());
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        if (this.getEntityType() == 5)
        {
        	return super.getCreatureAttribute();
        }
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected String getLivingSound()
    {
        return "mob.zombie.say";
    }

    @Override
    protected String getHurtSound()
    {
        return "mob.zombie.hurt";
    }

    @Override
    protected String getDeathSound()
    {
        return "mob.zombie.death";
    }

    @Override
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.zombie.step", 0.15F, 1.0F);
    }

    @Override
    public void initCreature()
    {
        if (this.worldObj.provider instanceof WorldProviderHell)
        {
        	this.setEntityType(3);
        }
    }

    private int getEntityType()
    {
        return this.dataWatcher.getWatchableObjectByte(15);
    }

    private void setEntityType(int par1)
    {
        this.dataWatcher.updateObject(15, Byte.valueOf((byte)par1));
        this.isImmuneToFire = par1 == 3;
    }

    @Override
    public float getSize()
    {
        if (this.getEntityType() == 3)
        {
        	return 1.5F;
        }
        else if (this.getEntityType() == 5)
        {
        	return 0.8F;
        }
        return 1.2F;
    }

    @Override
    public int getSlashResist()
    {
        return 0;
    }

    @Override
    public int getThrustResist()
    {
        return 20;
    }

    @Override
    public int getStrikeResist()
    {
        return 0;
    }

    @Override
    public int getPenetrateResist()
    {
        return 0;
    }

    @Override
    public int getQuakeResist()
    {
        if (this.getEntityType() == 1)
        {
        	return 70;
        }
        return 10;
    }

    @Override
    public int getWaterResist()
    {
        if (this.getEntityType() == 2)
        {
        	return 200;
        }
        return 0;
    }

    @Override
    public int getFireResist()
    {
        if (this.getEntityType() == 3)
        {
        	return 150;
        }
        return -20;
    }

    @Override
    public int getAeroResist()
    {
        if (this.getEntityType() == 4)
        {
        	return 90;
        }
        return 0;
    }

    @Override
    public int getShineResist()
    {
        if (this.getEntityType() == 5)
        {
        	return 100;
        }
        return -50;
    }

    @Override
    public int getDarkResist()
    {
        if (this.getEntityType() == 6)
        {
        	return 100;
        }
        return 30;
    }
}