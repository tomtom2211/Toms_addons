package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import io.github.tomtom2211.tomsaddons.utils.LocationUtils;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class KuudraPredict {
    public static String side ="" ;
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static boolean phase4 = false;

    public static void kuudraPredictHUD(){
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_10"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (!side.isEmpty() && Config.kuudraPrediction) {
                        context.drawText(
                                client.textRenderer,
                                side,
                                (client.getWindow().getScaledWidth() / 2)+Config.kuudraPredictionX,
                                (client.getWindow().getScaledHeight() / 2)-Config.kuudraPredictionY,
                                Config.kuudraPreHUDColor.getRGB(),
                                true
                        );
                    }
                }
        );
    }
    public static void init(){
        if (Config.forceKuudraPredictionHUD){
            side = "FORCED";
            return;
        }
        else if(phase4 && client.world != null && client.player != null && Config.kuudraPrediction && LocationUtils.inKuudra) {
            for (Entity entity : client.world.getEntities()) {
                if(!Config.kuudraCheckY || client.player.getBlockPos().getY() < 25) {
                    if (entity instanceof MagmaCubeEntity magmaCube && magmaCube.getWidth() > 14) {
                        if (magmaCube.getBlockPos().getX() < -128 && !side.equals("RIGHT")) {
                            side = "RIGHT";
                        } else if (magmaCube.getBlockPos().getX() > -72 && !side.equals("LEFT")) {
                            side = "LEFT";
                        } else if (magmaCube.getBlockPos().getZ() > -84 && !side.equals("FRONT")) {
                            side = "FRONT";
                        } else if (magmaCube.getBlockPos().getZ() < -132 && !side.equals("BACK")) {
                            side = "BACK";
                        }
                        return;
                    }
                }
            }
        }
            side = "";
    }

    public static void checkPhase(Text msg){
        if(msg.getString().toLowerCase().contains("i knew you could do it!")){
            phase4 = true;
        }
    }

    public static void unload(){
        phase4 = false;
        side = "";
    }
}
