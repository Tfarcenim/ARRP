package net.devtech.arrp.api;

import net.minecraft.resources.IResourcePack;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class RRPEvent extends Event {

	public final List<IResourcePack> packs;

	public RRPEvent(List<IResourcePack> pack) {
		this.packs = pack;
	}

	public void insert(List<IResourcePack> resources) {
		packs.addAll(resources);
	}
}
