package sao.mods.entity.passive;

import garnet.mods.EntityGarnetAIAttackOnCollide;
import garnet.mods.EntityGarnetAIHurtByTarget;
import garnet.mods.EntityGarnetAINearestAttackableTarget;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import sao.mods.core.SAOWeapon;
import sao.mods.entity.ai.EntitySAOAIAttackSwordSkill;
import sao.mods.entity.projectile.EntitySwordSkill;

public class EntityKirito extends EntitySAOPlayer
{
    public EntityKirito(World par1World)
    {
        super(par1World);
        this.sword = new ItemStack(SAOWeapon.annealBlade);

        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntitySAOAIAttackSwordSkill(this, 1.5D, false, 25, 3.0F));
        this.tasks.addTask(3, new EntityGarnetAIAttackOnCollide(this, 1.5D, false, 7, 0.5F));
        this.tasks.addTask(4, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityGarnetAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityGarnetAINearestAttackableTarget(this, EntityMob.class, 4.0D, false));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.3D);
    }

    @Override
    protected String getTexture()
    {
        return "player/kirito";
    }

    @Override
    public float getSlashAttack()
    {
        return 20.0F;
    }

    /* ========== ソードスキルについて ========== */
    @Override
    public boolean shouldSwordSkill()
    {
        return this.rand.nextInt(5) == 0;
    }

    @Override
    public boolean doSwordSkill(int par1)
    {
        if(par1 == 0)
        {
        	if (this.rotationPitch < 5.0F && this.rotationPitch > -15.0F)
        	{
        		for (int var0 = 0; var0 < 27; var0++)
        		{
        			EntitySwordSkill var1 = new EntitySwordSkill(this.worldObj, this, 360.0F * var0 / 27.0F, 0.0F, 1.75D, 1.0F);
        			if (var0 % 2 == 0)
        			{
        				var1.setAcceleration(-0.3F);
        				var1.setElement(6);
        			}
        			else
        			{
        				var1.setPosition(var1.posX, var1.posY + 0.1F, var1.posZ);
        				var1.setAcceleration(-0.4F);
        				var1.setElement(5);
        				var1.setAngleZ(90.0F);
        			}
        			var1.setLimitSpeed(0.0F);
        			var1.setType(0);
        			var1.setDamage(10.0F);
        			var1.setElemetDamage(3.0F);
        			if (!this.worldObj.isRemote)
        			{
        				this.worldObj.spawnEntityInWorld(var1);
        			}
        		}
        		return true;
        	}
        	else
        	{
        		EntitySwordSkill var1 = new EntitySwordSkill(this.worldObj, this, 0.0F, 0.0F, 1.0D, 0.5F);
        		var1.setAcceleration(-0.2F);
        		var1.setLimitSpeed(0.0F);
        		var1.setType(1);
        		var1.setDamage(40.0F);
        		var1.setElement(6);
        		var1.setElemetDamage(10.0F);
        		if (!this.worldObj.isRemote)
        		{
        			this.worldObj.spawnEntityInWorld(var1);
        		}
        	}
        }
        return false;
    }
}