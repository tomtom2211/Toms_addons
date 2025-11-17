package io.github.tomtom2211.tomsaddons.features;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ImmunityTimers{
    public static long phoenixTimer = 0;
    public static long bonzoTimer = 0;
    public static void init(Text msg){
        if(msg.getString().toLowerCase().contains("phoenix pet saved you")){
            phoenixTimer = System.currentTimeMillis() + 60000;
        }
        else if(msg.getString().toLowerCase().contains("your bonzo's mask")){
            bonzoTimer = System.currentTimeMillis() + 180000;
        }
    }
    public static void immunityTimersHUD(){
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach before
                Identifier.of("tomsaddons", "after_hotbar_1"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.phoenixTimer > System.currentTimeMillis()) {
                        context.drawText(
                                client.textRenderer,
                                (ImmunityTimers.phoenixTimer-System.currentTimeMillis())/1000 + "s Phoenix!",
                                (client.getWindow().getScaledWidth() / 2)+25,
                                client.getWindow().getScaledHeight() / 2,
                                0xFF00FF00,
                                true
                        );
                    }
                }
        );
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach before
                Identifier.of("tomsaddons", "after_hotbar_2"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.bonzoTimer > System.currentTimeMillis()) {
                        context.drawText(
                                client.textRenderer,
                                (ImmunityTimers.bonzoTimer-System.currentTimeMillis())/1000+"s Bonzo!",
                                (client.getWindow().getScaledWidth() / 2)+25,
                                client.getWindow().getScaledHeight() / 2-10,
                                0xFF00FF00,
                                true
                        );
                    }
                }
        );
    }
    public static void unload(){
        ImmunityTimers.phoenixTimer = 0;
        ImmunityTimers.bonzoTimer = 0;
    }
}
