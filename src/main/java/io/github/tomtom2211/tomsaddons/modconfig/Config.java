package io.github.tomtom2211.tomsaddons.modconfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.tomtom2211.tomsaddons.TomsAddons;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    private static final File FILE = new File("config/tomsaddons.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Config options
    public static Config config = new Config();
    public boolean starredMobESP = false;
    public double starredMobESPScale = 1;
    public int starredMobESPColor = 0xff0000;
    public boolean immunityTimers = false;
    public boolean bonzoMask = true;
    public boolean spiritMask = true;
    public boolean phoenixPet = true;
    public int cataLVL = 50;
    public boolean instantRequeue = false;
    public boolean commandAliases = false;
    public boolean miningTimers = false;
    public int pickobulusLevel = 1;
    public boolean balLvl100 = false;
    public String drillEngine = "None";
    public boolean kuudraPrediction = false;
    public int kuudraPredictionX = 0;
    public int kuudraPredictionY = 0;
    public boolean forceKuudraPredictionHUD = false;
    public int kuudraPreHUDColor = 0x00ffff;
    public boolean highlightShurikenMobs = false;
    public double shurikenMobESPScale = 1;
    public int shurikenMobESPColor = 0x00ffff;

    public static void load(){
        // Check if the config file already exists and create one if it doesn't
        if(!FILE.exists()){
            save();
        }
        // Try to create a FileReader
        try (FileReader reader = new FileReader(FILE)){
            config = GSON.fromJson(reader, Config.class);
            if (config == null){
                config = new Config();
            }
        }
        catch(IOException e){
            TomsAddons.LOGGER.error("Failed to load config file!", e);
        }

    }
    public static void save(){
        try (FileWriter writer = new FileWriter(FILE)){
            GSON.toJson(config, writer);
        }
        catch(IOException e){
            TomsAddons.LOGGER.error("Failed to save config file!", e);
        }

    }
}
