package com.nhave.transmog.common.core;

import org.apache.logging.log4j.Logger;

import com.nhave.transmog.common.eventhandlers.ToolStationEventHandler;
import com.nhave.transmog.common.eventhandlers.TooltipEventHandler;
import com.nhave.transmog.common.handlers.CraftingHandler;
import com.nhave.transmog.common.handlers.ItemHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MODID, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MCVERSIONS, dependencies = Reference.DEPENDENCIES)
public class Transmog
{
    public static Logger logger;
    
	public static String MC_CONFIG_DIR;
	
	public static final CreativeTabs creativeTab = new CreativeTabs("transmog")
	{
		@Override
		public Item getTabIconItem()
		{
			return ItemHandler.itemShaderCore;
		}
	};
    
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
	public static CommonProxy proxy;
    
    @Mod.Instance(Reference.MODID)
	public static Transmog instance = new Transmog();
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	logger = event.getModLog();
		
    	ItemHandler.preInit();
    	proxy.registerRenderers();
    }
    
    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {}
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	ItemHandler.postInit();
    	CraftingHandler.postInit();
    	MinecraftForge.EVENT_BUS.register(new ToolStationEventHandler());
    	MinecraftForge.EVENT_BUS.register(new TooltipEventHandler());
    }
}