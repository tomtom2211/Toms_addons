package io.github.tomtom2211.tomsaddons.utils;

import net.hypixel.modapi.HypixelModAPI;
import net.hypixel.modapi.packet.impl.clientbound.event.ClientboundLocationPacket;


public class LocationUtils {
    public static boolean inDungeon = false;
    public static boolean inKuudra = false;
    public static void init() {
        HypixelModAPI.getInstance().createHandler(ClientboundLocationPacket.class, packet -> {
            inDungeon = false;
            inKuudra = false;
            String map = packet.getMap().toString().toLowerCase();
            if(map.contains("dungeon") && !map.contains("hub")) {
                inDungeon = true;
            }
            else if(map.contains("kuudra")) {
                inKuudra = true;
            }
        });
    }
}
