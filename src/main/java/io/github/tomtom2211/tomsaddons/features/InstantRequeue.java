package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;

public class InstantRequeue {
    public static boolean toggle = true;
    public static void init(Text msg){
        MinecraftClient client = MinecraftClient.getInstance();

        if(msg.getString().toLowerCase().contains("to re-queue") && Config.config.instantRequeue && toggle && client.getNetworkHandler() != null && client.player != null){
            client.getNetworkHandler().sendChatCommand("instancerequeue");
            client.player.playSound(
                    SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(),
                    0.5f,
                    1.2f
            );
        }

        if(msg.getString().toLowerCase().contains("entered") && msg.getString().toLowerCase().contains("catacombs, floor") && client.player != null && Config.config.instantRequeue){
            toggle = true;
            client.player.playSound(
                    SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(),
                    0.5f,
                    1.2f
            );
        }

        if(msg.getString().toLowerCase().contains("!dt") && client.player != null && Config.config.instantRequeue){
            toggle = false;
            client.player.playSound(
                    SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(),
                    0.5f,
                    1.2f
            );
        }
        if((msg.getString().toLowerCase().contains("has left the party.") || msg.getString().toLowerCase().contains("has been removed from the party.")) && client.player != null && Config.config.instantRequeue){
            toggle = false;
            client.player.playSound(
                    SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(),
                    0.5f,
                    1.2f
            );
        }


    }
}
