package SAO.mods.core;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;

public class SAOEventHookContainer {


	/*
	 * プレイヤーがベッドで寝ようとした時に発生
	 */
	@ForgeSubscribe
	public void  SleepInBedEvent(PlayerSleepInBedEvent event){

	       if (event.entityPlayer.isRiding() == false && event.entityPlayer instanceof EntityPlayerMP) {

				EntityPlayerMP thePlayer = (EntityPlayerMP) event.entityPlayer;

				//thePlayer.inventory.armorItemInSlot(3)でプレイヤーの装備品の取得
				ItemStack headItem = thePlayer.inventory.armorItemInSlot(3);

				//頭に何か装備しているか
				if(headItem != null){

					//装備している防具はチェーンヘルメットか(ナーヴギアが作れたらそちらに変更)
					if(headItem.itemID == Item.helmetChain.itemID){

						//移動するDimensionのID(Dimensionが作れたらそちらに変更)
						int DimensionID = -1;

						thePlayer.timeUntilPortal = 20;
						thePlayer.mcServer.getConfigurationManager().transferPlayerToDimension(thePlayer,DimensionID);

					}

				}

	       }

	}

}
