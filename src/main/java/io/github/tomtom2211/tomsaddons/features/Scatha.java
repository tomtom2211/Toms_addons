package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Scatha {
    public static MinecraftClient client = MinecraftClient.getInstance();
    private static long lastWorm = 0;
    public static void init(){
        if(client.world != null){
            for(Entity entity : client.world.getEntities()) {
                if (entity instanceof ArmorStandEntity armorStand && client.player != null) {
                    String name = armorStand.getName().getString().toLowerCase();
                    if (name.contains("scatha") && lastWorm < System.currentTimeMillis()) {
                        lastWorm = System.currentTimeMillis()+30000;
                        if(Config.AnnounceScathas) {
                            client.player.sendMessage(Text.literal("§6§lScatha §fhas spawned!"), false);
                            client.player.playSound(
                                    SoundEvents.ENTITY_ARROW_HIT_PLAYER,
                                    1.0f,
                                    1.2f
                            );
                        }
                    } else if (name.contains("worm") && lastWorm < System.currentTimeMillis()) {
                        lastWorm = System.currentTimeMillis()+30000;
                        if(Config.AnnounceScathas) {
                            client.player.sendMessage(Text.literal("§c§lWorm §fhas spawned!"), false);
                            client.player.playSound(
                                    SoundEvents.BLOCK_ANVIL_PLACE,
                                    1.0f,
                                    1.2f
                            );
                        }
                    }
                }
            }
        }
    }
    public static void scathaHUD(){
        HudElementRegistry.attachElementAfter(
                VanillaHudElements.HOTBAR, // layer to attach after
                Identifier.of("tomsaddons", "after_hotbar_0"),
                (context, tickCounter) -> {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (lastWorm > System.currentTimeMillis() && Config.WormCooldown) {
                        context.drawText(
                                client.textRenderer,
                                "On cooldown!" + " (" + (lastWorm - System.currentTimeMillis())/1000 + "s)",
                                (client.getWindow().getScaledWidth() / 2)+10,
                                (client.getWindow().getScaledHeight() / 2)-10,
                                Config.WormCooldownColor.getRGB(),
                                true
                        );
                    }
                }
        );
    }
}