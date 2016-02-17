package com.nhave.transmog.common.helpers;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TransmogHelper
{
	public static ItemStack getItem(ItemStack stack, String key)
	{
		String compound = "TRANSMOG";
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound == null)
        {
            return null;
        }
        else
        {
        	NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag(compound);

            if (nbttagcompound1 == null)
            {
                return null;
            }
            else
            {
                NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompoundTag(key);
                return nbttagcompound2 == null ? null : (nbttagcompound1.hasKey(key) ? stack.loadItemStackFromNBT(nbttagcompound2) : null);
            }
        }
	}
	
	public static void setItem(ItemStack main, ItemStack sub, String key)
	{
		String compound = "TRANSMOG";
        NBTTagCompound nbttagcompound = main.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            main.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag(compound);

        if (!nbttagcompound.hasKey(compound))
        {
            nbttagcompound.setTag(compound, nbttagcompound1);
        }

        NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompoundTag(key);

        if (!nbttagcompound1.hasKey(key))
        {
        	nbttagcompound1.setTag(key, nbttagcompound2);
        }
        
        sub.writeToNBT(nbttagcompound2);
    }
	
	public static void removeTransmogTags(ItemStack stack)
	{
		if (stack.stackTagCompound != null) stack.stackTagCompound.removeTag("TRANSMOG");
	}
}