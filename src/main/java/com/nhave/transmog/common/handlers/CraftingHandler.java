package com.nhave.transmog.common.handlers;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CraftingHandler
{
	public static void postInit()
	{
		GameRegistry.addRecipe(new ItemStack(ItemHandler.itemShaderCore),
			new Object[] {"XYX", "YZY", "XYX",
			'X', Items.iron_ingot,
			'Y', Blocks.glass_pane,
			'Z', Items.ender_pearl});
		GameRegistry.addRecipe(new ItemStack(ItemHandler.itemCamo),
			new Object[] {"XYX", "YZY", "XYX",
			'X', Items.iron_ingot,
			'Y', Blocks.glass_pane,
			'Z', Items.redstone});
	}
}