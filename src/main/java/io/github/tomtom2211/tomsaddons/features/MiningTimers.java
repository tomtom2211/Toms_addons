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
    public static double cooldownReduction = 0;
    public static void init(Text msg) {
        cooldownReduction = 0;
        // Drill engine cooldown reduction
        switch(Config.drillEngine){
            case "None":
                break;
            case "Mithril-Infused":
                cooldownReduction += 0.02;
                break;
            case "Titanium-Infused":
                cooldownReduction += 0.04;
                break;
            case "Gemstone":
                cooldownReduction += 0.06;
                break;
            case "Perfectly-Cut":
                cooldownReduction += 0.1;
                break;
        }
        // Pickobulus pickaxe ability set timer
        if (msg.getString().toLowerCase().contains("you used your pickobulus pickaxe ability!")) {
            switch (Config.pickobulusLevel) {
                case 1:
                    if(Config.balLvl100 ){
                        pickobulusTimer = (long) (System.currentTimeMillis() + (54000*(1-cooldownReduction)));
                    }
                    else{
                    pickobulusTimer = (long) (System.currentTimeMillis() + (60000*(1-cooldownReduction)));
                    }
                    break;
                case 2:
                    if(Config.balLvl100) {
                        pickobulusTimer = (long) (System.currentTimeMillis() + (45000*(1-cooldownReduction)));
                    }
                    else{
                        pickobulusTimer = (long) (System.currentTimeMillis() + (50000*(1-cooldownReduction)));
                    }
                    break;
                case 3:
                    if(Config.balLvl100) {
                        pickobulusTimer = (long) (System.currentTimeMillis() + (36000*(1-cooldownReduction)));
                    }
                    else{
                        pickobulusTimer = (long) (System.currentTimeMillis() + (40000*(1-cooldownReduction)));
                    }
                    break;
            }
        }
        // Mining speed boost ability set timer
        else if (msg.getString().toLowerCase().contains("you used your mining speed boost pickaxe ability!")) {
            if(Config.balLvl100) {
                miningSpeedTimer = (long) (System.currentTimeMillis() + (108000*(1-cooldownReduction)));
            }
            else{
                miningSpeedTimer = (long) (System.currentTimeMillis() + (120000*(1-cooldownReduction)));
            }
        }
    }
    // Registering HUD
    public static void miningTimersHUD() {
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_4"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (MiningTimers.pickobulusTimer > System.currentTimeMillis() && Config.miningTimers) {
                        context.drawText(
                                client.textRenderer,
                                "Pickobulus!" + " (" + (((MiningTimers.pickobulusTimer - System.currentTimeMillis()) / 1000)+1) + "s)",
                                (client.getWindow().getScaledWidth() / 2) + 10,
                                client.getWindow().getScaledHeight() / 2,
                                Config.miningTimersColor.getRGB(),
                                true
                        );
                    }
                    else if (MiningTimers.miningSpeedTimer > System.currentTimeMillis() && Config.miningTimers) {
                        context.drawText(
                                client.textRenderer,
                                "Mining speed!" + " (" + (((MiningTimers.miningSpeedTimer - System.currentTimeMillis()) / 1000)+1) + "s)",
                                (client.getWindow().getScaledWidth() / 2) + 10,
                                client.getWindow().getScaledHeight() / 2,
                                Config.miningTimersColor.getRGB(),
                                true
                        );
                    }
                }
        );
    }
    // After lobby swap unload the timers
    public static void unload(){
        MiningTimers.pickobulusTimer = 0;
        MiningTimers.miningSpeedTimer = 0;
    }
}
