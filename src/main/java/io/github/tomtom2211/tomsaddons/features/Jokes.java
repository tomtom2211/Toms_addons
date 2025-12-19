package io.github.tomtom2211.tomsaddons.features;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.tomtom2211.tomsaddons.TomsAddons;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Jokes {
    private static final Random rand = new Random(); // For random numbers
    public static Long buttonPressTime = System.currentTimeMillis(); // Used for delays
    private static final MinecraftClient mcClient = MinecraftClient.getInstance(); // Minecraft client instance
    private static final File FILE = new File("config/jokes.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static Jokes jokes = new Jokes();
    public static void load(){
        // Check if the jokes file already exists and create one if it doesn't
        if(!FILE.exists()){
            save();
        }
        // Try to create a FileReader
        try (FileReader reader = new FileReader(FILE)){
            jokes = GSON.fromJson(reader, Jokes.class);
            if (jokes == null){
                jokes = new Jokes();
            }
        }
        catch(IOException e){
            TomsAddons.LOGGER.error("Failed to load config file!", e);
        }

    }
    public static void save(){
        try (FileWriter writer = new FileWriter(FILE)){
            GSON.toJson(jokes, writer);
        }
        catch(IOException e){
            TomsAddons.LOGGER.error("Failed to save config file!", e);
        }

    }

    public String[] jokesList = {
            "Why did the Healer bring a pickaxe to dungeons? Because it was the only way they'd ever get carried.",
            "What’s a Healer’s favorite weapon? The Party Finder 'kick' button.",
            "How do you make a Healer feel useful? Pretend you actually need him.",
            "Why don’t Healers ever die? Because they’re already dead inside after 500 runs of Master Mode Floor 7.",
            "What’s a Healer’s ultimate ability? Convincing the party that they actually matter.",
            "How do you spot a Healer in a dungeon run? They’re the one with the lowest damage and the highest ego.",
            "What do you call a Healer with high DPS? A liar.",
            "What’s the difference between a Healer and a Spirit Leprechaun? One drops loot, the other just drops performance.",
            "Why don’t Healers argue? They don’t have the DPS to back it up.",
            "Why do Tanks and Healers get along? Because misery loves company.",
            "What’s a Healer’s favorite dungeon role? The Spectator.",
            "What’s a Healer’s favorite boss phase? The one where everyone else does the work.",
            "What’s the Healer’s biggest fear? Someone finding out they’re replaceable.",
            "What’s the difference between a Healer and a spirit leap? The leap is faster.",
            "What’s a Healer’s biggest achievement? Not getting kicked.",
            "What’s the Healer’s favorite activity? AFKing, just like in dungeons.",
            "Why don’t parties argue with Healers? They just kick them instead.",
            "What do you call a Healer in Goldor armor? A disappointment.",
            "What’s a Healer’s favorite helmet? Bonzo mask because he’s a clown too.",
            "What’s a Healer’s best party finder note? 'plz inv'.",

            "Why did the Mage get kicked from the party? They kept running out of Intelligence in conversation.",
            "What's a Mage's most dangerous enemy in M7? A Fel standing in front of them.",
            "What's the difference between a Mage and a dead Mage? About 500 Intelligence.",
            "Why do Mages and Archers get along? Because they both blame the Debuff for No Damage.",
            "What’s the Mage’s favorite activity? Complaining that their 2-billion-coin setup only does 20 million damage.",

            "What’s an Archer’s ultimate ability? Accidentally over-damaging the Maxor and killing the Tank.",
            "What’s the Archer’s favorite activity? Complaining about Debuff.",
            "Why don’t Archers argue? They just send a friendly reminder that they carry the DPS.",

            "How do you know the Tank is having a bad day? The Archer is the one tanking the hits."
    };
    public static void init(KeyBinding jokeKey){
        if(jokeKey.wasPressed() && System.currentTimeMillis()-Jokes.buttonPressTime>=1000) {
            int randomNum = rand.nextInt((jokes.jokesList.length));
            Objects.requireNonNull(mcClient.getNetworkHandler()).sendChatMessage(jokes.jokesList[randomNum]);
            buttonPressTime = System.currentTimeMillis();
        }
    }
}
