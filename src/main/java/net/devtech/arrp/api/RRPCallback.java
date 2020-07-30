package net.devtech.arrp.api;

import java.util.List;

import net.devtech.arrp.util.IrremovableList;


import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.resources.ResourcePack;

public interface RRPCallback {
	Event<RRPCallback> EVENT = EventFactory.createArrayBacked(RRPCallback.class, r -> {
		return rs -> {
			IrremovableList<ResourcePack> packs = new IrremovableList<>(rs, pack -> {
				if (pack instanceof RuntimeResourcePack) {
					((RuntimeResourcePack) pack).dump();
				}
			});
			for (RRPCallback callback : r) {
				callback.insert(packs);
			}
		};
	});

	/**
	 * you can only add resource packs to this list, you may not remove them
	 */
	void insert(List<ResourcePack> resources);
}
