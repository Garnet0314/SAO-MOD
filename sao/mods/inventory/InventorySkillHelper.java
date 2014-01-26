package sao.mods.inventory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.relauncher.FMLInjectionData;

public class InventorySkillHelper
{
    private static Map<String,InventorySkill> InvList = new HashMap();
    private boolean inventoryChanged = false;

    public static IInventory getInventory(String par1Str)
    {
        if (InvList.containsKey(par1Str))
        {
        	return InvList.get(par1Str);
        }
        else
        {
        	InventorySkill var1 = load(par1Str);
        	InvList.put(par1Str, var1);
        	return var1;
        }
    }

    public static void unloadCount()
    {
        for (Iterator var1 = InvList.entrySet().iterator(); var1.hasNext();)
        {
        	Map.Entry<String, InventorySkill> var2 = (Map.Entry)var1.next();
        	String var3 = var2.getKey();
        	InventorySkill var4 = var2.getValue();
        	if(var4.unloadTick++ > 20)
        	{
        		unload(var3,var4);
        		var1.remove();
        	}
        }
    }

    //本来ならファイルを扱う際は例外処理を適切にしてやる必要がありますがここでは省略します
    //ファイルのパスは.minecraftを基点とした相対パスで指定しています
    //ファイル操作はJavaの解説サイトに詳しく載っていますのでここでは説明しません
    public static InventorySkill load(String par1Str)
    {
        //ファイル無かったりおかしかったした場合は空のインベントリを返します
        InventorySkill var1 = new InventorySkill();
        try
        {
        	//ファイルを取得
        	File var2 = (File)FMLInjectionData.data()[6];
        	File var3 = new File(var2, "IDChestInventory");
        	if (!var3.exists())
        	{
        		var3.mkdirs();
        	}
        	File var4 = new File(var3, par1Str + ".nbt");
        	//入力ストリームを作成
        	DataInputStream var5;
        	var5 = new DataInputStream(new FileInputStream(var4));
        	NBTTagList var6;
        	//入力ストリームよりNBTデータを取得
        	var6 = (NBTTagList)NBTBase.readNamedTag(var5);
        	//ストリームを閉じる
        	var5.close();
        	//インベントリにNBTを読み込ませる
        	var1.loadNBTTag(var6);
        	return var1;
        }
        catch (Exception e)
        {
        	//ここでは例外処理は適当
        	return var1;
        }
    }

    public static void unload(String par1Str, InventorySkill par2InventorySkill)
    {
        //インベントリをファイルに書き込みます
        //この実装では例外が発生した場合は何もしないのでインベントリは消滅します
        try
        {
        	//ファイルを取得
        	File var1 = (File)FMLInjectionData.data()[6];
        	File var2 = new File(var1, "IDChestInventory");
        	if (!var2.exists())
        	{
        		var2.mkdirs();
        	}
        	File var3 = new File(var2, par1Str + ".nbt");
        	//無ければ作る
        	var3.createNewFile();
        	//元からディレクトリが存在している場合などは何もせずに戻る
        	if (!var3.isFile())
        	{
        		return;
        	}
        	//出力ストリームを作成
        	DataOutputStream var4 = new DataOutputStream(new FileOutputStream(var3));
        	//出力ストリームにインベントリよりNBTデータを取得して書き込み
        	NBTBase.writeNamedTag(par2InventorySkill.getNBT(), var4);
        	//ストリームを閉じる
        	var4.close();
        }
        catch (Exception e) {}
    }

    //読み込まれている全データの読み込みを解除
    public static void unloadAll()
    {
        for (Iterator var1 = InvList.entrySet().iterator(); var1.hasNext();)
        {
        	Map.Entry<String, InventorySkill> var2 = (Map.Entry)var1.next();
        	String var3 = var2.getKey();
        	InventorySkill var4 = var2.getValue();
        	unload(var3,var4);
        }
        InvList.clear();
    }
}