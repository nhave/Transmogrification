package com.nhave.transmog.common.core;

import java.io.File;

public class CommonProxy
{
	public void setupConfig(File configFile)
	{
		//FMLCommonHandler.instance().bus().register(new ConfigHandler(false));
		//ConfigHandler.init(configFile);
	}
	
	public void registerEventHandlers() {}

	public void registerRenderers() {}
}