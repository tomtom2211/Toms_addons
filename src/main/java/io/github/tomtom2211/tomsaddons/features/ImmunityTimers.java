package io.github.tomtom2211.tomsaddons.features;
import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ImmunityTimers{
    public static long phoenixTimer = 0;
    public static long bonzoTimer = 0;
    public static long spiritTimer = 0;
    public static long serverTimer = 0;
    public static void init(Text msg){
        if(msg.getString().toLowerCase().contains("phoenix pet saved you")){
            phoenixTimer = System.currentTimeMillis() + 60000;
            serverTimer = System.currentTimeMillis() + 3000;
        }
        else if(msg.getString().toLowerCase().contains("your bonzo's mask")){
            bonzoTimer = System.currentTimeMillis() + (360000 - (Config.config.cataLVL * 3600L));
            serverTimer = System.currentTimeMillis() + 3000;
        }
        else if(msg.getString().toLowerCase().contains("your spirit mask")){
            spiritTimer = System.currentTimeMillis() + 30000;
            serverTimer = System.currentTimeMillis() + 3000;
        }
    }
    public static void immunityTimersHUD(){
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_1"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.bonzoTimer > System.currentTimeMillis() && Config.config.immunityTimers && Config.config.bonzoMask) {
                        context.drawText(
                                client.textRenderer,
                                "Bonzo!" + " (" + (ImmunityTimers.bonzoTimer-System.currentTimeMillis())/1000 + "s)",
                                (client.getWindow().getScaledWidth() / 2)+10,
                                client.getWindow().getScaledHeight() / 2-10,
                                0xffff0000,
                                true
                        );
                    }
                }
        );
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_2"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.phoenixTimer > System.currentTimeMillis() && Config.config.immunityTimers && Config.config.phoenixPet) {
                        context.drawText(
                                client.textRenderer,
                                "Phoenix!" + " (" + (ImmunityTimers.phoenixTimer-System.currentTimeMillis())/1000 + "s)",
                                (client.getWindow().getScaledWidth() / 2)+10,
                                client.getWindow().getScaledHeight() / 2,
                                0xffff0000,
                                true
                        );
                    }
                }
        );

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_3"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.spiritTimer > System.currentTimeMillis() && Config.config.immunityTimers && Config.config.spiritMask) {
                        context.drawText(
                                client.textRenderer,
                                "Spirit!" + " (" + (ImmunityTimers.spiritTimer-System.currentTimeMillis())/1000 + "s)",
                                (client.getWindow().getScaledWidth() / 2)+10,
                                (client.getWindow().getScaledHeight() / 2)-20,
                                0xffff0000,
                                true
                        );
                    }
                }
        );

        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_5"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (ImmunityTimers.serverTimer > System.currentTimeMillis() && Config.config.immunityTimers) {
                        double timer = (ImmunityTimers.serverTimer-System.currentTimeMillis())/1000.0;
                        context.drawText(
                                client.textRenderer,
                                String.format("%.2f", timer),
                                (client.getWindow().getScaledWidth() / 2) - 25,
                                client.getWindow().getScaledHeight() / 2,
                                0xFF00FFFF,
                                true
                        );
                    }
                }
        );
    }
    public static void unload(){
        ImmunityTimers.phoenixTimer = 0;
        ImmunityTimers.bonzoTimer = 0;
        ImmunityTimers.spiritTimer = 0;
        ImmunityTimers.serverTimer = 0;
    }
}
