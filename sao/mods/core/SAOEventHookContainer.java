package sao.mods.core;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import sao.mods.world.TeleporterSAO;

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
        	//移動するDimensionのID
        	int DimensionID = ConfigWorld.aincradDimensionID;

        	//現実世界かどうか
        	if (thePlayer.worldObj.provider.dimensionId == 0)
        	{
        		//thePlayer.inventory.armorItemInSlot(3)でプレイヤーの装備品の取得
        		ItemStack headItem = thePlayer.inventory.armorItemInSlot(3);
        		//頭の防具はナーブギアか
        		if (headItem != null && headItem.itemID == SAOItem.nerveGear.itemID)
        		{
        			thePlayer.timeUntilPortal = 20;
        			thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer, DimensionID, new TeleporterSAO(thePlayer.mcServer.worldServerForDimension(DimensionID)));
        		}
        	}
        }
    }
}