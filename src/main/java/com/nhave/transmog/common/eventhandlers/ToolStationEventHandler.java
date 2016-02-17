package com.nhave.transmog.common.eventhandlers;

import com.nhave.nhlib.events.ToolStationCraftingEvent;
import com.nhave.nhlib.events.ToolStationUpdateEvent;
import com.nhave.transmog.common.handlers.ItemHandler;
import com.nhave.transmog.common.helpers.TransmogHelper;

import buildcraft.api.tools.IToolWrench;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ToolStationEventHandler
{
	@SubscribeEvent
	public void handleToolStationEvent(ToolStationUpdateEvent evt)
	{
		if (evt.input == null || evt.mod == null)
		{
			return;
		}
		else if (evt.input.getItem() instanceof ItemArmor)
		{
			if (evt.mod.getItem() == ItemHandler.itemShaderCore)
			{
				ItemStack core = TransmogHelper.getItem(evt.input, "CORE");
				if (core == null)
				{
					ItemStack newItem = evt.input.copy();
					ItemStack newModItem = evt.mod.copy();
					newModItem.stackSize = 1;
					TransmogHelper.setItem(newItem, newModItem, "CORE");
					evt.materialCost=1;
					evt.output=newItem;
				}
			}
			else if (evt.mod.getItem() instanceof ItemArmor)
			{
				if (TransmogHelper.getItem(evt.mod, "CORE") != null || TransmogHelper.getItem(evt.mod, "ARMOR") != null) return;
				ItemStack core = TransmogHelper.getItem(evt.input, "CORE");
				ItemStack armor = TransmogHelper.getItem(evt.input, "ARMOR");
				if (core != null && armor == null)
				{
					ItemArmor newInput = (ItemArmor) evt.input.getItem();
					ItemArmor newMod = (ItemArmor) evt.mod.getItem();
					if (newInput.armorType == newMod.armorType)
					{
						ItemStack newItem = evt.input.copy();
						ItemStack newModItem = evt.mod.copy();
						newModItem.stackSize = 1;
						TransmogHelper.setItem(newItem, newModItem, "ARMOR");
						evt.materialCost=1;
						evt.output=newItem;
					}
				}
			}
			else if (evt.mod.getItem() == ItemHandler.itemCamo)
			{
				ItemStack core = TransmogHelper.getItem(evt.input, "CORE");
				ItemStack armor = TransmogHelper.getItem(evt.input, "ARMOR");
				if (core != null && armor == null)
				{
					ItemStack newItem = evt.input.copy();
					ItemStack newModItem = evt.mod.copy();
					newModItem.stackSize = 1;
					TransmogHelper.setItem(newItem, newModItem, "ARMOR");
					evt.materialCost=1;
					evt.output=newItem;
				}
			}
			else if (evt.mod.getItem() instanceof IToolWrench)
			{
				ItemStack core = TransmogHelper.getItem(evt.input, "CORE");
				ItemStack armor = TransmogHelper.getItem(evt.input, "ARMOR");
				if (core != null || armor != null)
				{
					ItemStack newItem = evt.input.copy();
					TransmogHelper.removeTransmogTags(newItem);
					evt.materialCost=0;
					evt.output=newItem;
				}
			}
		}
	}
	
	@SubscribeEvent
	public void handleToolStationCraftingEvent(ToolStationCraftingEvent evt)
	{
		if (evt.input == null || evt.mod == null || evt.output == null)
		{
			return;
		}
		if (evt.input.getItem() instanceof ItemArmor)
		{
			if (evt.mod.getItem() instanceof IToolWrench)
			{
				ItemStack core = TransmogHelper.getItem(evt.input, "CORE");
				ItemStack armor = TransmogHelper.getItem(evt.input, "ARMOR");
				if (core != null)
				{
					if (!evt.entityPlayer.inventory.addItemStackToInventory(core))
					{
						if (!evt.entityPlayer.worldObj.isRemote) evt.entityPlayer.entityDropItem(core, 1F);
					}
				}
				if (armor != null)
				{
					if (!evt.entityPlayer.inventory.addItemStackToInventory(armor))
					{
						if (!evt.entityPlayer.worldObj.isRemote) evt.entityPlayer.entityDropItem(armor, 1F);
					}
				}
			}
		}
	}
}