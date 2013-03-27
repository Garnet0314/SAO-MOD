package SAO.mods.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSkill extends Container
{
    private IInventory skill;
    private IInventory unUsed;

    public ContainerSkill(IInventory par1IInventory, IInventory par2IInventory)
    {
        this.skill = par2IInventory;
        this.unUsed = par1IInventory;
        par2IInventory.openChest();
        int var1;
        int var2;
        for (var1 = 0; var1 < 3; var1++)
        {
        	for (var2 = 0; var2 < 9; var2++)
        	{
        		this.addSlotToContainer(new Slot(par2IInventory, var2 + var1 * 9, 8 + var2 * 18, 18 + var1 * 18));
        	}
        }

        for (var1 = 0; var1 < 3; ++var1)
        {
        	for (var2 = 0; var2 < 9; ++var2)
        	{
        		this.addSlotToContainer(new Slot(par1IInventory, var2 + var1 * 9 + 9, 8 + var2 * 18, 85 + var1 * 18));
        	}
        }

        for (var1 = 0; var1 < 9; ++var1)
        {
        	this.addSlotToContainer(new Slot(par1IInventory, var1, 8 + var1 * 18, 143));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.skill.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 < 3 * 9)
            {
                if (!this.mergeItemStack(itemstack1, 3 * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 3 * 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
        super.onCraftGuiClosed(par1EntityPlayer);
        this.skill.closeChest();
    }

    public IInventory getLowerChestInventory()
    {
        return this.skill;
    }
}