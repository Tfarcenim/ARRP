package net.devtech.arrp.api;

import net.minecraft.resources.ResourcePack;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class RRPEvent extends Event {

	public final List<ResourcePack> packs;

	public RRPEvent(List<ResourcePack> pack) {
		this.packs = pack;
	}

	public void insert(List<ResourcePack> resources) {
		packs.addAll(resources);
	}
}
