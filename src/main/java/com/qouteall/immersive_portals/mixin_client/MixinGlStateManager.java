package com.qouteall.immersive_portals.mixin_client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.qouteall.immersive_portals.Global;
import com.qouteall.immersive_portals.render.context_management.RenderStates;
import com.qouteall.immersive_portals.render.lag_spike_fix.GlBufferCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlStateManager.class)
public abstract class MixinGlStateManager {
    @Shadow
    public static void disableCull() {
        throw new IllegalStateException();
    }
    
    @Inject(
        method = "Lcom/mojang/blaze3d/platform/GlStateManager;enableCull()V",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void onEnableCull(CallbackInfo ci) {
        if (RenderStates.shouldForceDisableCull) {
            disableCull();
            ci.cancel();
        }
    }
    
    @Inject(
        method = "Lcom/mojang/blaze3d/platform/GlStateManager;genBuffers()I",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void onGenBuffers(CallbackInfoReturnable<Integer> cir) {
        if (Global.cacheGlBuffer) {
            cir.setReturnValue(GlBufferCache.getNewBufferId());
            cir.cancel();
        }
    }
    
}
