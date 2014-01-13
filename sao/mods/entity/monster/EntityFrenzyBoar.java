package sao.mods.entity.monster;

import garnet.mods.EntityGarnetAIAttackOnCollide;
import garnet.mods.EntityGarnetAIHurtByTarget;
import garnet.mods.EntityGarnetAINearestAttackableTarget;
import garnet.mods.entity.passive.EntityGarnetBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityFrenzyBoar extends EntitySAOEnemy
{
    public EntityFrenzyBoar(World par1World)
    {
        super(par1World);
        this.setSize(1.6F, 1.0F);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityGarnetAIAttackOnCollide(this, 2.0D, false));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityGarnetAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityGarnetAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityGarnetAINearestAttackableTarget(this, EntityGarnetBase.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.21D);
    }

    @Override
    public String getTexture()
    {
        if (this.getHealth() < this.getMaxHealth() * 0.5F)
        {
        	return "frenzyBoar/angry";
        }
        return "frenzyBoar/normal";
    }

    @Override
    public float getStrikeAttack()
    {
        return 2.0F;
    }
}