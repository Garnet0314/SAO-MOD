package sao.mods.entity.passive;

import garnet.mods.EntityGarnetAIAttackOnCollide;
import garnet.mods.EntityGarnetAIHurtByTarget;
import garnet.mods.EntityGarnetAINearestAttackableTarget;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityKirito extends EntitySAOPlayer
{
    public EntityKirito(World par1World)
    {
        super(par1World);

        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityGarnetAIAttackOnCollide(this, 1.5D, false, 7, 0.5F));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(3, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityGarnetAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityGarnetAINearestAttackableTarget(this, EntityMob.class, 4.0D, false));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setAttribute(1.0D);
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
}