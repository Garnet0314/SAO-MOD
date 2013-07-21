package sao.mods.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.common.FMLCommonHandler;

public class SAOSaveFile
{
    public static int createdPalace;

    public static void writeData()
    {
        try
        {
        	//TODO いずれSAO用ディメンションへ
        	File var1File = new File(new StringBuilder().append(FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(-1).getChunkSaveLocation()).append("\\").append("SAOModSave.dat").toString());
        	if(!var1File.exists())
        	{
        		var1File.createNewFile();
        	}
        	FileOutputStream var2FileOut = new FileOutputStream(var1File);
        	NBTTagCompound var3NBTTag = new NBTTagCompound();

        	var3NBTTag.setInteger("WorldGenPalace", createdPalace);

        	CompressedStreamTools.writeCompressed(var3NBTTag, var2FileOut);
        	var2FileOut.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }

    public static void readData()
    {
        try
        {
        	//TODO いずれSAO用ディメンションへ
        	File var1File = new File(new StringBuilder().append(FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(-1).getChunkSaveLocation()).append("\\").append("SAOModSave.dat").toString());
        	if(!var1File.exists())
        	{
        		return;
        	}

        	FileInputStream var2FileInput = new FileInputStream(var1File);
        	NBTTagCompound var3NBTTag = CompressedStreamTools.readCompressed(var2FileInput);

        	if(var3NBTTag.hasKey("WorldGenPalace"))
        	{
        		createdPalace = var3NBTTag.getInteger("WorldGenPalace");
        	}

        	var2FileInput.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
    }
}