package net.devtech.arrp;

import java.util.logging.Logger;

import net.devtech.arrp.impl.RuntimeResourcePackImpl;
import net.minecraftforge.fml.common.Mod;

@Mod("arrp")
public class ARRP {
	private static final Logger LOGGER = Logger.getLogger("ARRP");

	public ARRP() {
		LOGGER.severe("I used the json to destroy the json");
		RuntimeResourcePackImpl.EXECUTOR_SERVICE.submit(this::pregen);
	}

	public void pregen() {
	}
}
