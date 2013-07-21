package sao.mods.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSkill extends Container
{
    private IInventory theInventory;

    public ContainerSkill(IInventory par1IInventoryPlayer, IInventory par2IInventory)
    {
        this.theInventory = par2IInventory;
        par2IInventory.openChest();
        int var1;
        int var2;

        for (var1 = 0; var1 < par2IInventory.getSizeInventory(); var1++)
        {
        	this.addSlotToContainer(new Slot(par2IInventory, var1, 44 + var1 * 18, 20));
        }

        for (var1 = 0; var1 < 3; ++var1)
        {
        	for (var2 = 0; var2 < 9; ++var2)
        	{
        		this.addSlotToContainer(new Slot(par1IInventoryPlayer, var2 + var1 * 9 + 9, 8 + var2 * 18, var1 * 18 + 51));
        	}
        }

        for (var1 = 0; var1 < 9; ++var1)
        {
        	this.addSlotToContainer(new Slot(par1IInventoryPlayer, var1, 8 + var1 * 18, 109));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.theInventory.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var1 = null;
        Slot var2 = (Slot)this.inventorySlots.get(par2);

        if (var2 != null && var2.getHasStack())
        {
        	ItemStack var3 = var2.getStack();
        	var1 = var3.copy();

        	if (par2 < this.theInventory.getSizeInventory())
        	{
        		if (!this.mergeItemStack(var3, this.theInventory.getSizeInventory(), this.inventorySlots.size(), true))
        		{
        			return null;
        		}
        	}
        	else if (!this.mergeItemStack(var3, 0, this.theInventory.getSizeInventory(), false))
        	{
        		return null;
        	}

        	if (var3.stackSize == 0)
        	{
        		var2.putStack((ItemStack)null);
        	}
        	else
        	{
        		var2.onSlotChanged();
        	}
        }

        return var1;
    }

    @Override
    public void onContainerClosed(EntityPlayer par1EntityPlayer)
    {
        super.onContainerClosed(par1EntityPlayer);
        this.theInventory.closeChest();
    }
}