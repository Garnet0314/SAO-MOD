package garnet.mods;

import garnet.mods.item.ItemGarnetGun;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
    {
        if (player instanceof EntityPlayerMP)
        {
        	EntityPlayerMP var1 = (EntityPlayerMP)player;
        	ItemStack var2 = var1.getCurrentEquippedItem();
        	if (var2 != null && var2.getItem() instanceof ItemGarnetGun)
        	{
        		ItemGarnetGun lifn = (ItemGarnetGun)var2.getItem();
        		lifn.setDoFullAuto(var2, packet.data[0]);
        	}
        }
    }
}