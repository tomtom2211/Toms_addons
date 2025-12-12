package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.util.Identifier;

public class KuudraPredict {
    public static String side;
    public static final MinecraftClient client = MinecraftClient.getInstance();

    public static void kuudraPredictHUD(){
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_10"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (side != null && Config.config.kuudraPrediction) {
                        context.drawText(
                                client.textRenderer,
                                side,
                                (client.getWindow().getScaledWidth() / 2)+Config.config.kuudraPredictionX,
                                (client.getWindow().getScaledHeight() / 2)+Config.config.kuudraPredictionY,
                                0xffff0000,
                                true
                        );
                    }
                }
        );
    }
    public static void init(){
        if(client.world != null && Config.config.kuudraPrediction) {
            for (Entity entity : client.world.getEntities()) {
                if (entity instanceof MagmaCubeEntity magmaCube && magmaCube.getPos().y<35 && magmaCube.getSize() > 20){
                    if(magmaCube.getPos().x < -128){
                        side = "RIGHT";
                    }
                    else if(magmaCube.getPos().x > -72){
                        side = "LEFT";
                    }
                    else if(magmaCube.getPos().z > -84){
                        side = "FRONT";
                    }
                    else if(magmaCube.getPos().z < -132){
                        side = "BACK";
                    }
                    else{
                        side = null;
                    }
                }

            }
        }
    }
    public static void unload(){
        side = null;
    }
}
