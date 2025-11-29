package io.github.tomtom2211.tomsaddons.modconfig;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.text.Text;
import java.util.List;


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
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Config.config.starredMobESP = newValue)
                    .build();

            var entryBuilder2 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Enable immunity timers"), Config.config.immunityTimers)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Config.config.immunityTimers = newValue)
                    .build();

            var entryBuilder3 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Bonzo's mask"), Config.config.bonzoMask)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> Config.config.bonzoMask = newValue)
                    .build();

            var entryBuilder4 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Spirit mask"), Config.config.spiritMask)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> Config.config.spiritMask = newValue)
                    .build();

            var entryBuilder5 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Phoenix pet"), Config.config.phoenixPet)
                    .setDefaultValue(true)
                    .setSaveConsumer(newValue -> Config.config.phoenixPet = newValue)
                    .build();

            var entryBuilder6 = builder.entryBuilder()
                    .startIntField(Text.of("Catacombs lvl"), Config.config.cataLVL)
                    .setMax(50)
                    .setMin(0)
                    .setDefaultValue(50)
                    .setSaveConsumer(newValue -> Config.config.cataLVL = newValue)
                    .build();

            var entryBuilder7 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Enable instant requeue"), Config.config.instantRequeue)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Config.config.instantRequeue = newValue)
                    .build();

            var entryBuilder8 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Enable command aliases"), Config.config.commandAliases)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Config.config.commandAliases = newValue)
                    .build();

            var entryBuilder9 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Enable mining timers"), Config.config.miningTimers)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Config.config.miningTimers = newValue)
                    .build();

            var entryBuilder10 = builder.entryBuilder()
                    .startIntField(Text.of("Pickubulus level"), Config.config.pickobulusLevel)
                    .setDefaultValue(1)
                    .setSaveConsumer(newValue -> Config.config.pickobulusLevel = newValue)
                    .setMin(1)
                    .setMax(3)
                    .build();
            var entryBuilder11 = builder.entryBuilder()
                    .startBooleanToggle(Text.of("Legendary Bal lvl 100"), Config.config.balLvl100)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Config.config.balLvl100 = newValue)
                    .build();

            var entryBuilder12 = builder.entryBuilder()
                    .startStringDropdownMenu((Text.of("Select Drill Engine")), Config.config.drillEngine)
                    .setDefaultValue("None")
                    .setSelections(
                            List.of(
                            "None",
                            "Mithril-Infused",
                            "Titanium-Infused",
                            "Gemstone",
                            "Perfectly-Cut"
                            )
                    )
                    .setSuggestionMode(false)
                    .setSaveConsumer(newValue -> Config.config.drillEngine = newValue)
                    .build();



            // Add the user config section into the dungeons / mining category
            dungeons.addEntry(entryBuilder1);
            dungeons.addEntry(entryBuilder2);
            dungeons.addEntry(entryBuilder3);
            dungeons.addEntry(entryBuilder4);
            dungeons.addEntry(entryBuilder5);
            dungeons.addEntry(entryBuilder6);
            dungeons.addEntry(entryBuilder7);
            dungeons.addEntry(entryBuilder8);
            mining.addEntry(entryBuilder9);
            mining.addEntry(entryBuilder10);
            mining.addEntry(entryBuilder11);
            mining.addEntry(entryBuilder12);

            // Add the save button
            builder.setSavingRunnable(Config::save);

            // Add the config builder into the modmenu config button
            return builder.build();
        };
    }

}

