package io.github.tomtom2211.tomsaddons.modconfig;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.text.Text;


public class ModMenuImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            // Create builder instance
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.of("Tom's addons"));

            // Create general category
            var dungeons = builder.getOrCreateCategory(Text.of("Dungeons"));
            var mining = builder.getOrCreateCategory(Text.of("Mining"));

            // Creating the user input section
            var entryBuilder1 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Enable starred mob ESP"), Config.config.starredMobESP)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> Config.config.starredMobESP = newValue)
                    .build();

            var entryBuilder2 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Enable Immunity Timers"), Config.config.immunityTimers)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> Config.config.immunityTimers = newValue)
                    .build();

            var entryBuilder3 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Enable Mining Timers"), Config.config.miningTimers)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Config.config.miningTimers = newValue)
                    .build();

            var entryBuilder4 = builder.entryBuilder()
                    .startIntField(Text.of("Pickubulus level"), Config.config.pickobulusLevel)
                    .setDefaultValue(1)
                    .setSaveConsumer(newValue -> Config.config.pickobulusLevel = newValue)
                    .setMin(1)
                    .setMax(3)
                    .build();

            // Add the user config section into the dungeons / mining category
            dungeons.addEntry(entryBuilder1);
            dungeons.addEntry(entryBuilder2);
            mining.addEntry(entryBuilder3);
            mining.addEntry(entryBuilder4);

            // Add the save button
            builder.setSavingRunnable(Config::save);

            // Add the config builder into the modmenu config button
            return builder.build();
        };
    }

}

