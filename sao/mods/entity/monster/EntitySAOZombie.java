package sao.mods.entity.monster;

import garnet.mods.EntityGarnetAIAttackOnCollide;
import garnet.mods.EntityGarnetAIHurtByTarget;
import garnet.mods.EntityGarnetAINearestAttackableTarget;
import garnet.mods.entity.passive.EntityGarnetBase;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntitySAOZombie extends EntitySAOEnemy
{
    public EntitySAOZombie(World par1World)
    {
        super(par1World);
        this.getNavigator().setBreakDoors(true);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
        this.tasks.addTask(2, new EntityGarnetAIAttackOnCollide(this, 1.0D, false));
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityGarnetAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityGarnetAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityGarnetAINearestAttackableTarget(this, EntityGarnetBase.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(25.0D);
        if (this.getEntityType() == 4)
        {
        	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.475D);
        }
        else if (this.getEntityType() == 5)
        {
        	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.345D);
        }
        else
        {
        	this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23D);
        }
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
    }

    @Override
    public String getTexture()
    {
        if (this.getEntityType() == 0)
        {
        	return "zombie/normal";
        }
        else if (this.getEntityType() == 1)
        {
        	return "zombie/earth";
        }
        else if (this.getEntityType() == 2)
        {
        	return "zombie/water";
        }
        else if (this.getEntityType() == 3)
        {
        	return "zombie/fire";
        }
        else if (this.getEntityType() == 4)
        {
        	return "zombie/wind";
        }
        else if (this.getEntityType() == 5)
        {
        	return "zombie/light";
        }
        else if (this.getEntityType() == 6)
        {
        	return "zombie/dark";
        }
        return "zombie/normal";
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
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(17, Byte.valueOf((byte)0));
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
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
    {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);

        if (this.worldObj.provider instanceof WorldProviderHell)
        {
        	this.setEntityType(3);
        }
        else
        {
        	int var1 = this.getRNG().nextInt(7);
        	this.setEntityType(var1);
        }

        return par1EntityLivingData;
    }

    private int getEntityType()
    {
        return this.dataWatcher.getWatchableObjectByte(17);
    }

    private void setEntityType(int par1)
    {
        this.dataWatcher.updateObject(17, Byte.valueOf((byte)par1));
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
    public int getThrustResist()
    {
        return 20;
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