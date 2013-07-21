package sao.mods.block;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import sao.mods.entity.EntitySAOBoss;

public class TileEntityBossSpawner extends TileEntity {

	public NBTTagCompound mobNBT = null;

	public boolean spawned = false;
	private int requiredPlayerRange = 16;
	public double yaw;
	public double yaw2 = 0.0D;
	int delay = 0;

	public TileEntityBossSpawner() {
		delay = 20;
	}

	public boolean anyPlayerInRange() {
		EntityPlayer ep = this.worldObj.getClosestPlayer((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D, (double) this.requiredPlayerRange);
		return ep != null && !ep.capabilities.isCreativeMode;
	}

	public void updateEntity() {
		if (this.worldObj.isRemote) {
			if (this.delay > 0) {
				--this.delay;
			} else {
				this.delay = 20;
			}

			this.yaw2 = this.yaw;
			this.yaw = (this.yaw + (double) (1000.0F / ((float) this.delay + 200.0F))) % 360.0D;
			return;
		}
		if (!this.worldObj.isRemote && 0 < this.worldObj.difficultySetting && this.anyPlayerInRange() && mobNBT != null) {
			if(spawned == false) {
				Entity var13 = EntityList.createEntityFromNBT(this.mobNBT, this.worldObj);

				if (var13 == null) {
					return;
				} else {
					var13.setLocationAndAngles(this.xCoord, this.yCoord + 2, this.zCoord, this.worldObj.rand.nextFloat() * 360.0F, 0.0F);
					if(var13 instanceof EntitySAOBoss) {
						((EntitySAOBoss)var13).setSpawnerPosition(this.xCoord, this.yCoord, this.zCoord);
					}

					this.worldObj.spawnEntityInWorld(var13);
					this.worldObj.playAuxSFX(2004, this.xCoord, this.yCoord, this.zCoord, 0);

					if (var13 instanceof EntityLiving) {
						((EntityLiving) var13).spawnExplosionParticle();
					}
					spawned = true;
				}
			}
		}
		super.updateEntity();
	}


	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);

		this.spawned = par1NBTTagCompound.getBoolean("Spawned");
		this.requiredPlayerRange = par1NBTTagCompound.getShort("RequiredPlayerRange");
		this.mobNBT = par1NBTTagCompound.getCompoundTag("MobNBT");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("Spawned", this.spawned);
		par1NBTTagCompound.setShort("RequiredPlayerRange", (short) this.requiredPlayerRange);
		par1NBTTagCompound.setCompoundTag("MobNBT", this.mobNBT);
	}

	public Entity getMobEntity() {
		if (this.mobNBT == null) {
			return null;
		}
		return EntityList.createEntityFromNBT(this.mobNBT, null);
	}

}
