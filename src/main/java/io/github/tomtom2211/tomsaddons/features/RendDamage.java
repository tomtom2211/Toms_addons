package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import io.github.tomtom2211.tomsaddons.utils.Formatting;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import java.util.ArrayList;
import java.util.List;

public class RendDamage {

    private static float lastKuudraHp = 24999f;
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static List<Long> rendDamageArray = new ArrayList<>();
    public static void init() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null || !Config.rendDamage) return;
        for (Entity entity : client.world.getEntities()) {
            if (entity instanceof MagmaCubeEntity magmaCube && magmaCube.getPos().y < 35 && magmaCube.getSize() > 20) {
                float currentHp = magmaCube.getHealth();
                if (currentHp <= 25000) {
                    float damage = lastKuudraHp - currentHp;
                    if (damage > 1666) {
                        long scaledDamage = (long) (damage * 10000);
                        rendDamageArray.add(scaledDamage);
                        String color = getDamageColor((int) damage);
                        String formattedDamage = Formatting.shortFormat(scaledDamage);
                        client.player.sendMessage(Text.literal("§d[REND] §fSomeone pulled for " + color + formattedDamage + " §fdamage."), true);
                        client.player.playSound(
                                SoundEvents.ENTITY_ARROW_HIT_PLAYER,
                                1.0f,
                                1.2f
                        );
                    }
                }
                lastKuudraHp = currentHp;
                break;
            }
        }
    }

    private static String getDamageColor(int dmg) {
        if (dmg > 7000){
            return "§a";
        }
        else if (dmg >= 4000){
            return "§e";
        }
        else{
            return "§c";
        }
    }

    public static void sendRendDamageMap(Text msg){
        if(!rendDamageArray.isEmpty() && client.player != null && msg.getString().toLowerCase().contains("kuudra down")) {
            for(Long damage : rendDamageArray){
                client.player.sendMessage(Text.literal("§d[Rend]§f" + damage),false);
            }
        }
    }

    public static void unload() {
        lastKuudraHp = 24999f;
        if(!rendDamageArray.isEmpty()) {
            rendDamageArray.clear();
        }
    }

}