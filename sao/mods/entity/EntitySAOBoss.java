package sao.mods.entity;

import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import sao.mods.block.TileEntityBossSpawner;

public abstract class EntitySAOBoss extends EntityMob implements ISAOMob, IBossDisplayData
{
	int xCoord;
	int yCoord;
	int zCoord;

	public EntitySAOBoss(World par1World)
    {
        super(par1World);
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Integer(0));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        if (!this.worldObj.isRemote)
        {
        	if (this.getAttackTarget() == null)
        	{
        		this.addTargetTimer();
        	}
        	else
        	{
        		this.resetTargetTimer();
        	}
        }

        if (!this.isTargetExist())
        {
        	this.setDead();
        }
    }

    @Override
    public void onDeath(DamageSource par1DamageSource)
    {
        super.onDeath(par1DamageSource);
        //死んだときにディメンション開放
    }

    private void addTargetTimer()
    {
        this.dataWatcher.updateObject(16, this.dataWatcher.getWatchableObjectInt(16) + 1);
    }

    private void resetTargetTimer()
    {
        this.dataWatcher.updateObject(16, 0);
    }

    private boolean isTargetExist()
    {
        return this.dataWatcher.getWatchableObjectInt(16) < 500;
    }

    @Override
    public float getSize()
    {
        return 1.0F;
    }

    @Override
    public int getSlashResist()
    {
        return 0;
    }

    @Override
    public int getThrustResist()
    {
        return 0;
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
        return 0;
    }

    @Override
    public int getWaterResist()
    {
        return 0;
    }

    @Override
    public int getFireResist()
    {
        return 0;
    }

    @Override
    public int getAeroResist()
    {
        return 0;
    }

    @Override
    public int getShineResist()
    {
        return 0;
    }

    @Override
    public int getDarkResist()
    {
        return 0;
    }

	@Override
	protected boolean canDespawn() {
		return true;
	}

	@Override
	public void setDead() {
		if(this.func_110143_aJ() <= 0.0F) {
		}
		else {
			TileEntity tile = this.worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
			if(tile instanceof TileEntityBossSpawner) {
				((TileEntityBossSpawner)tile).spawned = false;
			}
		}
		super.setDead();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		super.writeEntityToNBT(par1nbtTagCompound);
		par1nbtTagCompound.setIntArray("SpawnerPosition", new int[]{xCoord, yCoord, zCoord});
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		super.readEntityFromNBT(par1nbtTagCompound);
		int[] pos = par1nbtTagCompound.getIntArray("SpawnerPosition");
		if(pos != null) {
			xCoord = pos[0];
			yCoord = pos[1];
			zCoord = pos[2];
		}
	}

	public void setSpawnerPosition(int xCoord, int yCoord, int zCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;

	}
}