package io.github.tomtom2211.tomsaddons;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class Tomsaddons implements ModInitializer {

    @Override
    public void onInitialize() {
        Random rand = new Random();
        String[] jokes = {
                "Why did the Healer bring a pickaxe to dungeons? Because it was the only way they'd ever get carried.",
                "A Healer walks into Floor 7. The party says: 'Wait, this isn’t Bedwars—why are you here?'",
                "What’s a Healer’s favorite weapon? The Party Finder 'kick' button.",
                "How do you make a Healer feel useful? Pretend you didn’t bring a Wand of Atonement.",
                "Why don’t Healers ever die? Because they’re already dead inside after 500 runs of Floor 5.",
                "What’s a Healer’s ultimate ability? Convincing the party that they actually matter.",
                "Why do Berserkers love Healers? Because someone has to revive them after they jump into a miniboss alone.",
                "How do you spot a Healer in a dungeon run? They’re the one with the lowest damage and the highest hope.",
                "What’s the hardest part about being a Healer? Explaining to your party why you’re not actually AFK.",
                "Why did the Healer quit SkyBlock? They realized their pet rock does more DPS.",
                "Why do parties only bring Healers once? To remember why they don’t bring them again.",
                "What’s a Healer’s main stat? Patience… for waiting to be replaced by a Tank.",
                "How many Healers does it take to clear Floor 7? Nobody knows, they never make it that far.",
                "Why did the Healer fail the puzzle room? They were too busy reviving themselves emotionally.",
                "What do you call a Healer with high DPS? A liar.",
                "Why do Healers love Fairy Rooms? Because finally, someone else is healing them.",
                "What’s the difference between a Healer and a Spirit Leprechaun? One drops loot, the other just drops performance.",
                "Why don’t Healers argue? They don’t have the DPS to back it up.",
                "What’s a Healer’s favorite song? 'Stayin’ Alive.'",
                "Why did the Healer refuse to fight Necron? They didn’t want to break their zero damage record.",
                "What’s the similarity between a Healer and a Treasure Talisman? Both are rare… and still useless.",
                "Why don’t Healers play Archer? Because bows do too much damage and it scares them.",
                "What’s a Healer’s secret weapon? Guilt-tripping the party into keeping them around.",
                "Why do Tanks and Healers get along? Because misery loves company.",
                "What’s a Healer’s favorite dungeon role? Spectator.",
                "Why did the Healer cross the dungeon? To rez the Berserker who thought he was immortal.",
                "What’s a Healer’s favorite miniboss? None. They’re all just more work.",
                "Why don’t Healers get lost in dungeons? Because everyone leaves them behind anyway.",
                "What’s the difference between a Healer and a potion? At least the potion is useful.",
                "Why did the Healer bring a Flower of Truth? To convince themselves they’re helpful.",
                "What do Healers call 1 million damage? A group effort.",
                "Why are Healers like puzzle rooms? Everyone groans when they see one.",
                "Why did the Healer stop attacking? They didn’t want to risk stealing aggro.",
                "What’s the Healer’s motto? 'I revive, therefore I am… tolerated.'",
                "Why do parties only invite Healers at low levels? To teach them disappointment early.",
                "What’s the scariest word for a Healer? 'Solo run.'",
                "Why did the Healer sit in the corner? Because their damage was already grounded.",
                "What’s a Healer’s favorite boss phase? The one where everyone else does the work.",
                "Why don’t Healers ever get blamed? Because nobody remembers they exist.",
                "What do you call a Healer with a Terminator? A very confused Archer.",
                "Why do Healers love spirit pets? Because they’re used to being ghosts.",
                "What’s a Healer’s best dungeon strategy? Hiding behind the Tank.",
                "Why did the Healer bring 10 stacks of healing pots? Habit.",
                "What’s the Healer’s biggest fear? Someone finding out they’re replaceable.",
                "Why do Healers like revive stones? They finally get to feel needed.",
                "Why don’t Healers flex their gear? Because no one’s impressed by mender crowns.",
                "What’s a Healer’s spirit animal? A fairy—it dies constantly, but keeps coming back.",
                "Why do Healers make good friends? Because they’re used to supporting people better than them.",
                "What’s the Healer’s dungeon carry method? Emotional support.",
                "Why do Healers love Catacombs? Because it’s the only place they feel like they belong—barely."
        };

        KeyBinding joke = KeyBindingHelper.registerKeyBinding(new KeyBinding("Funny healer joke", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_J, "Tom's Addons"));
        MinecraftClient mcClient = MinecraftClient.getInstance();
        AtomicLong buttonPressTime = new AtomicLong(System.currentTimeMillis());
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(joke.wasPressed()&&client != null&&System.currentTimeMillis()- buttonPressTime.get() >=1000){
                int randomNum = rand.nextInt(50);
                mcClient.getNetworkHandler().sendChatMessage(jokes[randomNum]);
                buttonPressTime.set(System.currentTimeMillis());
            }
        });
    }
}
