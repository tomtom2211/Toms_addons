package io.github.tomtom2211.tomsaddons.modconfig;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import java.awt.*;


public class Config {
    // Config options
    public static ConfigClassHandler<Config> HANDLER = ConfigClassHandler.createBuilder(Config.class)
            .id(Identifier.of("tomsaddons", "tomsaddons"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("tomsaddons.json5"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting) // not needed, pretty print by default
                    .setJson5(true)
                    .build())
            .build();

    // General
    @SerialEntry
    public static boolean shurikenMobESP = false;
    @SerialEntry
    public static double shurikenMobESPScale = 1;
    @SerialEntry
    public static Color shurikenMobESPColor = Color.cyan;

    // Dungeons
    @SerialEntry
    public static boolean starredMobESP = false;
    @SerialEntry
    public static double starredMobESPScale = 1;
    @SerialEntry
    public static Color starredMobESPColor = Color.red;
    @SerialEntry
    public static boolean immunityTimers = false;
    @SerialEntry
    public static boolean bonzoMask = true;
    @SerialEntry
    public static boolean spiritMask = true;
    @SerialEntry
    public static boolean phoenixPet = true;
    @SerialEntry
    public static int cataLVL = 50;
    @SerialEntry
    public static boolean instantRequeue = false;
    @SerialEntry
    public static boolean commandAliases = false;

    // Kuudra
    @SerialEntry
    public static boolean kuudraPrediction = false;
    @SerialEntry
    public static int kuudraPredictionX = 10;
    @SerialEntry
    public static int kuudraPredictionY = 0;
    @SerialEntry
    public static boolean forceKuudraPredictionHUD = false;
    @SerialEntry
    public static Color kuudraPreHUDColor = Color.CYAN;
    @SerialEntry
    public static boolean rendDamage = false;

    // Mining
    @SerialEntry
    public static boolean miningTimers = false;
    @SerialEntry
    public static int pickobulusLevel = 1;
    @SerialEntry
    public static boolean balLvl100 = false;
    @SerialEntry
    public static String drillEngine = "None";
    @SerialEntry
    public static Color miningTimersColor = Color.GREEN;
}
