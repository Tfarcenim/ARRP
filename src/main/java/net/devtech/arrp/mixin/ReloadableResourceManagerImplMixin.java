package net.devtech.arrp.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import net.devtech.arrp.api.RRPEvent;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.util.IrremovableList;
import net.minecraft.resources.IAsyncReloader;
import net.minecraft.resources.IResourcePack;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.SimpleReloadableResourceManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mixin (SimpleReloadableResourceManager.class)
public abstract class ReloadableResourceManagerImplMixin {
	@Shadow @Final private static Logger LOGGER;

	@Shadow public abstract void addResourcePack(IResourcePack resourcePack);

	@Inject (method = "reloadResources", at = @At (value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
	private void registerARRPs(Executor prepareExecutor, Executor applyExecutor, CompletableFuture<Unit> initialStage, List<ResourcePack> packs,
	                           CallbackInfoReturnable<IAsyncReloader> cir) {
		LOGGER.info("ARRP register");
		IrremovableList<IResourcePack> newPacks = new IrremovableList<>(new ArrayList<>(), pack -> {
			if (pack instanceof RuntimeResourcePack) {
				((RuntimeResourcePack) pack).dump();
			}
		});
		RRPEvent event = new RRPEvent(newPacks);
		EVENT_BUS.post(event);
		newPacks.forEach(this::addResourcePack);
	}
}