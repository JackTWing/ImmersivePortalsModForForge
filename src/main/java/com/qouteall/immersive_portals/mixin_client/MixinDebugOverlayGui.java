package com.qouteall.immersive_portals.mixin_client;

import com.qouteall.immersive_portals.render.MyRenderHelper;
import net.minecraft.client.gui.overlay.DebugOverlayGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(DebugOverlayGui.class)
public class MixinDebugOverlayGui {
    @Inject(method = "getDebugInfoRight", at = @At("RETURN"), cancellable = true)
    private void onGetRightText(CallbackInfoReturnable<List<String>> cir) {
        cir.getReturnValue().add("Rendered Portal Num: " + MyRenderHelper.lastPortalRenderInfos.size());
    }
}