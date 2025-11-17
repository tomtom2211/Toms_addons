package io.github.tomtom2211.tomsaddons.features;

import net.minecraft.text.Text;


public class ImmunityTimers {
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

}
