package com.qouteall.immersive_portals.mixin;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = DimensionManager.class, remap = false)
public class MixinForgeDimensionManager {
    /**
     * @author qotueall
     * @reason
     */
    @Overwrite
    public static void unloadWorlds(MinecraftServer server, boolean checkLeaks) {
        //nothing
    }
}