package com.nhave.transmog.common.items;

import java.util.List;

import com.nhave.nhlib.helpers.TooltipHelper;
import com.nhave.nhlib.util.StringUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class ItemBase extends Item
{
	private String[] rarityNames = new String[] {"basic", "rare", "legendary", "exotic", "creative"};
	private String[] rarityColors = new String[] {StringUtils.WHITE, StringUtils.LIGHT_BLUE, StringUtils.PURPLE, StringUtils.ORANGE, StringUtils.PINK};
	private int rarity;
	private String type;
	private String tooltip;
	
	public ItemBase(int rarity, String type, String tooltip)
	{
		if (rarity > 4 || rarity < 0) rarity = 0;
		this.rarity = rarity;
		this.type = type;
		this.tooltip = tooltip;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		list.add(StringUtils.BOLD + StatCollector.translateToLocal("tooltip.item." + type) + "     ");
		list.add(rarityColors[rarity] + StatCollector.translateToLocal("tooltip.shader.rarity." + rarityNames[rarity]));
		
		if (tooltip.length() > 0)
		{
			if (StringUtils.isShiftKeyDown())
			{
				TooltipHelper.addHiddenTooltip(list, tooltip, ";");
			}
			else  list.add(StringUtils.shiftForInfo);
		}
	}
}