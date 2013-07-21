package sao.mods.core;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class SAOEventHookContainer
{
    /*
     * プレイヤーがベッドで寝ようとした時に発生
     */
    @ForgeSubscribe
    public void  SleepInBedEvent(PlayerSleepInBedEvent event)
    {
        if (event.entityPlayer.isRiding() == false && event.entityPlayer instanceof EntityPlayerMP)
        {
        	EntityPlayerMP thePlayer = (EntityPlayerMP) event.entityPlayer;

        	//thePlayer.inventory.armorItemInSlot(3)でプレイヤーの装備品の取得
        	ItemStack headItem = thePlayer.inventory.armorItemInSlot(3);

        	//頭に何か装備しているか
        	if (headItem != null)
        	{
        		//装備している防具はチェーンヘルメットか
        		if (headItem.itemID == SAOItem.nerveGear.itemID)
        		{
        			//TODO 移動するDimensionのID(Dimensionが作れたらそちらに変更)
        			int DimensionID = -1;

        			thePlayer.timeUntilPortal = 20;
        			thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer,DimensionID);
        		}
        	}
        }
    }
}