package io.github.tomtom2211.tomsaddons.features;

import io.github.tomtom2211.tomsaddons.modconfig.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CommandAliases {
    public static void init(String command) {
        MinecraftClient client = MinecraftClient.getInstance();
        if(client.getNetworkHandler() != null && Config.config.commandAliases) {
            switch (command) {
                // Floors f0–f7
                case "f0":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_ENTRANCE");
                    break;
                case "f1":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_FLOOR_ONE");
                    break;
                case "f2":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_FLOOR_TWO");
                    break;
                case "f3":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_FLOOR_THREE");
                    break;
                case "f4":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_FLOOR_FOUR");
                    break;
                case "f5":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_FLOOR_FIVE");
                    break;
                case "f6":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_FLOOR_SIX");
                    break;
                case "f7":
                    client.getNetworkHandler().sendChatCommand("joindungeon CATACOMBS_FLOOR_SEVEN");
                    break;


                // Master Mode m1–m7
                case "m1":
                    client.getNetworkHandler().sendChatCommand("joindungeon MASTER_CATACOMBS_FLOOR_ONE");
                    break;
                case "m2":
                    client.getNetworkHandler().sendChatCommand("joindungeon MASTER_CATACOMBS_FLOOR_TWO");
                    break;
                case "m3":
                    client.getNetworkHandler().sendChatCommand("joindungeon MASTER_CATACOMBS_FLOOR_THREE");
                    break;
                case "m4":
                    client.getNetworkHandler().sendChatCommand("joindungeon MASTER_CATACOMBS_FLOOR_FOUR");
                    break;
                case "m5":
                    client.getNetworkHandler().sendChatCommand("joindungeon MASTER_CATACOMBS_FLOOR_FIVE");
                    break;
                case "m6":
                    client.getNetworkHandler().sendChatCommand("joindungeon MASTER_CATACOMBS_FLOOR_SIX");
                    break;
                case "m7":
                    client.getNetworkHandler().sendChatCommand("joindungeon MASTER_CATACOMBS_FLOOR_SEVEN");
                    break;
            }
        }
    }
    public static boolean supressUnknownCommand(Text msg){
        return !msg.getString().toLowerCase().contains("unknown command") || !Config.config.commandAliases;
    }
}
