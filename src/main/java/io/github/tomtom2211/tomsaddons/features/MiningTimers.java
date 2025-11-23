package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MiningTimers {
    public static long pickobulusTimer = 0;
    public static long miningSpeedTimer = 0;

    public static void init(Text msg) {
        if (msg.getString().toLowerCase().contains("you used your pickobulus pickaxe ability!")) {
            switch (Config.config.pickobulusLevel) {
                case 1:
                    if(Config.config.balLvl100){
                        pickobulusTimer = System.currentTimeMillis() + 54000;
                    }
                    else{
                    pickobulusTimer = System.currentTimeMillis() + 60000;
                    }
                    break;
                case 2:
                    if(Config.config.balLvl100) {
                        pickobulusTimer = System.currentTimeMillis() + 45000;
                    }
                    else{
                        pickobulusTimer = System.currentTimeMillis() + 50000;
                    }
                    break;
                case 3:
                    if(Config.config.balLvl100) {
                        pickobulusTimer = System.currentTimeMillis() + 36000;
                    }
                    else{
                        pickobulusTimer = System.currentTimeMillis() + 40000;
                    }
                    break;
            }
        } else if (msg.getString().toLowerCase().contains("you used your mining speed boost pickaxe ability!")) {
            if(Config.config.balLvl100) {
                miningSpeedTimer = System.currentTimeMillis() + 162000;
            }
            else{
                miningSpeedTimer = System.currentTimeMillis() + 180000;
            }
        }
    }

    public static void miningTimersHUD() {
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_4"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (MiningTimers.pickobulusTimer > System.currentTimeMillis() && Config.config.miningTimers) {
                        context.drawText(
                                client.textRenderer,
                                "Pickobulus!" + " (" + (((MiningTimers.pickobulusTimer - System.currentTimeMillis()) / 1000)+1) + "s)",
                                (client.getWindow().getScaledWidth() / 2) + 10,
                                client.getWindow().getScaledHeight() / 2,
                                0xFF00FFFF,
                                true
                        );
                    }
                    else if (MiningTimers.miningSpeedTimer > System.currentTimeMillis() && Config.config.miningTimers) {
                        context.drawText(
                                client.textRenderer,
                                "Mining speed!" + " (" + (((MiningTimers.miningSpeedTimer - System.currentTimeMillis()) / 1000)+1) + "s)",
                                (client.getWindow().getScaledWidth() / 2) + 10,
                                client.getWindow().getScaledHeight() / 2,
                                0xFF00FFFF,
                                true
                        );
                    }
                }
        );
    }
    public static void unload(){
        MiningTimers.pickobulusTimer = 0;
        MiningTimers.miningSpeedTimer = 0;
    }
}
