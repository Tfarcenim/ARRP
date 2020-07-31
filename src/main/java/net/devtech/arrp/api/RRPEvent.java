package net.devtech.arrp.api;

import net.minecraft.resources.IResourcePack;
import net.minecraftforge.eventbus.api.Event;

import java.util.Arrays;
import java.util.List;

public class RRPEvent extends Event {

	private final List<IResourcePack> runTimeResourcePacks;

	public RRPEvent(List<IResourcePack> pack) {
		this.runTimeResourcePacks = pack;
	}

	public void addPack(IResourcePack pack) {
		runTimeResourcePacks.add(pack);
	}

	public void addPacks(IResourcePack... packs) {
		runTimeResourcePacks.addAll(Arrays.asList(packs));
	}

	public void addPacks(List<IResourcePack> packs) {
		runTimeResourcePacks.addAll(packs);
	}

}
