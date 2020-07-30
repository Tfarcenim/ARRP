package net.devtech.arrp.mixin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RRPEvent;
import net.minecraft.resources.IAsyncReloader;
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

@Mixin (SimpleReloadableResourceManager.class)
public abstract class ReloadableResourceManagerImplMixin {
	@Shadow @Final private static Logger LOGGER;

	@Inject (method = "reloadResources", at = @At (value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
	private void registerARRPs(Executor prepareExecutor, Executor applyExecutor, CompletableFuture<Unit> initialStage, List<ResourcePack> packs,
	                           CallbackInfoReturnable<IAsyncReloader> cir) {
		LOGGER.info("ARRP register");
		List<ResourcePack> pack = new ArrayList<>();
		RRPEvent event = new RRPEvent(pack);
		pack.forEach(this::addPack);
	}

	@Shadow
	public abstract void addPack(ResourcePack resourcePack);
}