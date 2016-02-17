package com.nhave.transmog.common.handlers;

import com.nhave.transmog.common.core.Reference;
import com.nhave.transmog.common.core.Transmog;
import com.nhave.transmog.common.items.ItemBase;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemHandler
{
	public static Item itemShaderCore;
	public static Item itemCamo;
	
	public static void preInit()
	{
		itemShaderCore = new ItemBase(1, "shader", "tooltip.shadercore").setCreativeTab(Transmog.creativeTab).setTextureName("transmog:ShaderCore").setUnlocalizedName("transmog.itemShaderCore");
		itemCamo = new ItemBase(1, "shader", "tooltip.camo").setCreativeTab(Transmog.creativeTab).setTextureName("transmog:Camo").setUnlocalizedName("transmog.itemCamo");
		
		registerItem(itemShaderCore);
		registerItem(itemCamo);
	}

	public static void postInit() {}
	
	private static void registerItem(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName(), Reference.MODID);
	}
}