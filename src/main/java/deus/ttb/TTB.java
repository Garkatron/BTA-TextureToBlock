package deus.ttb;

import deus.ttb.block.TTBBlocks;
import deus.ttb.item.TTBItems;
import deus.ttb.util.configuration.ConfigHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;

import java.io.File;


public class TTB implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "ttb";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ConfigHandler MOD_CONFIG = new deus.ttb.util.configuration.ConfigHandler();
	public static File TTBTexturesFolder = null;
	public static File TTBBlocksFolder = null;

	@Override
    public void onInitialize() {
        LOGGER.info("{} initialized.", TTB.MOD_ID);

		File mcdir = Minecraft.getMinecraft(this).getMinecraftDir();

		TTBTexturesFolder = new File(mcdir, "TTB/textures/");
		TTBBlocksFolder = new File(mcdir, "TTB/blocks/");

		try {
			if (TTBTexturesFolder.exists()) {
				System.out.println("The 'Textures To Blocks' folder already exists at: " + TTBTexturesFolder.getAbsolutePath());
			} else {
				if (TTBTexturesFolder.mkdirs()) {
					System.out.println("The 'Textures To Blocks' folder was successfully created at: " + TTBTexturesFolder.getAbsolutePath());
				} else {
					System.err.println("Unable to create the 'Textures To Blocks' folder. Please check permissions or the path.");
				}
			}
		} catch (Exception e) {
			System.err.println("An error occurred while creating the 'Textures To Blocks' folder: " + e.getMessage());
		}

		try {
			if (TTBBlocksFolder.exists()) {
				System.out.println("The 'Textures To Blocks' folder already exists at: " + TTBBlocksFolder.getAbsolutePath());
			} else {
				if (TTBBlocksFolder.mkdirs()) {
					System.out.println("The 'Textures To Blocks' folder was successfully created at: " + TTBBlocksFolder.getAbsolutePath());
				} else {
					System.err.println("Unable to create the 'Textures To Blocks' folder. Please check permissions or the path.");
				}
			}
		} catch (Exception e) {
			System.err.println("An error occurred while creating the 'Textures To Blocks' folder: " + e.getMessage());
		}



		TTBItems.initialize();
		TTBBlocks.initialize();
    }

	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
