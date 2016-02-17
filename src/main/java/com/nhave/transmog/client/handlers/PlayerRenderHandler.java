package com.nhave.transmog.client.handlers;

import java.util.Map;

import com.google.common.collect.Maps;
import com.nhave.transmog.common.handlers.ItemHandler;
import com.nhave.transmog.common.helpers.TransmogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PlayerRenderHandler
{
	private Map<String, ItemStack[]> armorCache = Maps.newHashMap();
	
	@SubscribeEvent
	public void renderPlayerEventPre(RenderPlayerEvent.Pre event)
	{
		ItemStack[] cache = new ItemStack[4];
		for (int i = 0; i < 4; i++)
		{
			ItemStack itemStack = event.entityPlayer.inventory.armorItemInSlot(3 - i);
			if (itemStack != null)
			{
				cache[i] = itemStack.copy();
			}
		}
		this.armorCache.put(event.entityPlayer.getCommandSenderName(), cache);
		
		for (int i = 0; i < 4; i++)
		{
			ItemStack slot = event.entityPlayer.inventory.armorItemInSlot(3 - i);
			if (slot != null)
			{
				ItemStack core = TransmogHelper.getItem(slot, "CORE");
				ItemStack armor = TransmogHelper.getItem(slot, "ARMOR");
				
				if (core != null && armor != null)
				{
					if ((armor.getItem() == ItemHandler.itemCamo))
					{
						event.entityPlayer.inventory.armorInventory[(3 - i)] = null;
					}
					else if ((armor.getItem() instanceof ItemArmor))
					{
						event.entityPlayer.inventory.armorInventory[(3 - i)] = armor;
					}
				}
			}
		}
  	}
	
	@SubscribeEvent
	public void renderPlayerEventPost(RenderPlayerEvent.Post event)
	{
		ItemStack[] cache = (ItemStack[])this.armorCache.get(event.entityPlayer.getCommandSenderName());
		for (int i = 0; i < 4; i++) 
		{
			event.entityPlayer.inventory.armorInventory[(3 - i)] = cache[i];
		}
	}
}
