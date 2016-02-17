package com.nhave.transmog.client.core;

import java.io.File;

import com.nhave.transmog.client.handlers.PlayerRenderHandler;
import com.nhave.transmog.common.core.CommonProxy;

import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy
{
	@Override
	public void setupConfig(File configFile)
	{
		//FMLCommonHandler.instance().bus().register(new ConfigHandler(true));
		//ConfigHandler.init(configFile);
	}

	@Override
	public void registerEventHandlers() {}
	
	@Override
	public void registerRenderers() 
	{
	    MinecraftForge.EVENT_BUS.register(new PlayerRenderHandler());
	}
}
