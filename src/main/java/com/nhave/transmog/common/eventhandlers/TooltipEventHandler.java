package com.nhave.transmog.common.eventhandlers;

import com.nhave.transmog.common.helpers.TransmogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipEventHandler
{
	@SubscribeEvent
	public void handleItemTooltipEvent(ItemTooltipEvent evt)
	{
		if (evt.itemStack == null)
		{
			return;
		}
		else if (evt.itemStack.getItem() instanceof ItemArmor)
		{
			ItemStack core = TransmogHelper.getItem(evt.itemStack, "CORE");
			ItemStack armor = TransmogHelper.getItem(evt.itemStack, "ARMOR");
			if (armor != null && core != null)
			{
				evt.toolTip.add(StatCollector.translateToLocal("tooltip.transmog.true") + ":");
				evt.toolTip.add("  " + "§e" + "§o" + armor.getDisplayName() + "§r");
			}
			else if (core != null && armor == null)
			{
				evt.toolTip.add(StatCollector.translateToLocal("tooltip.transmog.ready") + "!");
				evt.toolTip.add(StatCollector.translateToLocal("tooltip.transmog.howto"));
			}
		}
	}
}