package sao.mods.inventory;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventorySkill extends InventoryBasic
{
    //アンロードカウント
    public int unloadTick = 0;

    public InventorySkill()
    {
        super("container.skill", false, 5);
    }

    //バニラのチェストのNBT読み込みメソッドをNBTTagListを直接渡せるように改変しただけ
    public void loadNBTTag(NBTTagList par1NBTagList)
    {
        for (int var3 = 0; var3 < par1NBTagList.tagCount(); ++var3)
        {
        	NBTTagCompound var4 = (NBTTagCompound)par1NBTagList.tagAt(var3);
        	int var5 = var4.getByte("Slot") & 255;

        	if (var5 >= 0 && var5 < this.getSizeInventory())
        	{
        		this.setInventorySlotContents(var5, ItemStack.loadItemStackFromNBT(var4));
        	}
        }
    }

    //バニラのチェストのNBT書き込みメソッドをNBTTagListを直接投げるように改変しただけ
    public NBTTagList getNBT()
    {
        NBTTagList tag = new NBTTagList();

        for (int var3 = 0; var3 < this.getSizeInventory(); ++var3)
        {
        	if (this.getStackInSlot(var3) != null)
        	{
        		NBTTagCompound var4 = new NBTTagCompound();
        		var4.setByte("Slot", (byte)var3);
        		this.getStackInSlot(var3).writeToNBT(var4);
        		tag.appendTag(var4);
        	}
        }

        return tag;
    }

    //IInventoryのメソッド
    //インベントリに変更があった時に呼ばれる
    //アンロードカウントをリセット
    @Override
    public void onInventoryChanged()
    {
        this.unloadTick = 0;
    }
}