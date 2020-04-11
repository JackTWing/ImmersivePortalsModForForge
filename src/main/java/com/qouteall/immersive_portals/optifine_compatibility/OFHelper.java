package com.qouteall.immersive_portals.optifine_compatibility;

import com.qouteall.immersive_portals.CHelper;
import com.qouteall.immersive_portals.Global;
import com.qouteall.immersive_portals.Helper;
import net.minecraft.client.shader.Framebuffer;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_NO_ERROR;

public class OFHelper {
    
    
    public static void copyFromShaderFbTo(Framebuffer destFb, int copyComponent) {
        GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, OFGlobal.getDfb.get());
        GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, destFb.framebufferObject);
    
        GL30.glBlitFramebuffer(
            0, 0, Shaders.renderWidth, Shaders.renderHeight,
            0, 0, destFb.framebufferTextureWidth, destFb.framebufferTextureHeight,
            copyComponent, GL_NEAREST
        );
    
        int errorCode = GL11.glGetError();
        if (errorCode != GL_NO_ERROR) {
            String message = "Detected Video Card's Incapability of Depth Format Conversion." +
                "Switch to Compatibility Renderer";
            Helper.err("OpenGL Error" + errorCode);
            Helper.log(message);
            CHelper.printChat(message);
            
            Global.renderMode = Global.RenderMode.compatibility;
        }
    
        OFGlobal.bindToShaderFrameBuffer.run();
    }
    
    public static boolean isChocapicShader() {
        String name = OFGlobal.getCurrentShaderpack.get().getName();
        return name.toLowerCase().indexOf("chocapic") != -1;
    }
}
