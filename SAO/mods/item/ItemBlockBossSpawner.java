package SAO.mods.item;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import SAO.mods.block.TileEntityBossSpawner;
import SAO.mods.core.ConfigBlock;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemBlockBossSpawner extends ItemBlock {

	public ItemBlockBossSpawner(int par1) {
		super(par1);
	}

	@Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {
		if(stack.hasTagCompound() == false){
			return false;
		}
		NBTTagCompound tag = stack.getTagCompound();
		if(tag.hasKey("MobNBT") == false){
			return false;
		}

		boolean result = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ,metadata);
		if(result){
			TileEntity tiletmp = world.getBlockTileEntity(x, y, z);
			if(tiletmp != null && tiletmp instanceof TileEntityBossSpawner){
				TileEntityBossSpawner tile = (TileEntityBossSpawner)tiletmp;

				tile.mobNBT = tag.getCompoundTag("MobNBT");
			}
		}
		return result;
    }

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player,
			World world, int x, int y, int z, int side, float hitX, float hitY,
			float hitZ) {
		if(stack.hasTagCompound() == false){
			return false;
		}

		NBTTagCompound tag = stack.getTagCompound();
		if(tag.hasKey("MobNBT") == false){
			return false;
		}

		return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
	}

	@Override
	public boolean getShareTag() {
		return true;
	}

	@Override
	public void addInformation(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, List par3List, boolean par4) {

		super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);

    	if (par1ItemStack.hasTagCompound())
        {
            NBTTagCompound tag = par1ItemStack.getTagCompound();
            if(tag.hasKey("MobNBT") ){
            	tag = tag.getCompoundTag("MobNBT");

            	String id = tag.getString("id");
            	if(id != null && id.length() > 0){
            		par3List.add(id);
            	}
            }
        }
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack) {
		return par1ItemStack.hasTagCompound() && par1ItemStack.getTagCompound().hasKey("MobNBT");
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack par1ItemStack,
			EntityLiving par2EntityLiving) {

		NBTTagCompound tag = new NBTTagCompound();

		par2EntityLiving.addEntityID(tag);

		NBTTagCompound itemTag;
		if(par1ItemStack.hasTagCompound()){
			itemTag = par1ItemStack.getTagCompound();
		}else{
			itemTag = new NBTTagCompound();
			par1ItemStack.setTagCompound(itemTag);
		}
		itemTag.setCompoundTag("MobNBT", tag);

		if(!par2EntityLiving.worldObj.isRemote)
		{
			File logDir = new File(Minecraft.getMinecraftDir(), ConfigBlock.bossSpawnerDir);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			String t = sdf.format(new java.util.Date());
	        File logFile = new File(logDir, tag.getString("id") + "_" + t + ".dat");
	        logDir.mkdir();
	        try
	        {
	        	logFile.createNewFile();
                FileOutputStream dat = new FileOutputStream(logFile);
				CompressedStreamTools.writeCompressed(tag, dat);
//				CompressedStreamTools.read(logFile);

	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        }
		}

		return true;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,
			Entity entity) {

		if(entity instanceof EntityLiving)
			itemInteractionForEntity(stack, (EntityLiving)entity);

		return true;
	}
}
