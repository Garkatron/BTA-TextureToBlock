package deus.ttb.block;

import deus.builib.gssl.Signal;
import deus.ttb.TTB;
import deus.ttb.guis.BlockBuilder.ExampleBlock;
import deus.ttb.tools.TTBBlockData;
import deus.ttb.tools.TTBTextureCollection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.RenderEngine;
import net.minecraft.client.render.RenderGlobal;
import net.minecraft.client.render.stitcher.AtlasStitcher;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import net.minecraft.core.world.chunk.Chunk;
import turniplabs.halplibe.helper.BlockBuilder;

import java.util.ArrayList;
import java.util.List;

import static deus.ttb.util.blockanditems.BlockMaker.genericBlockBuilder;
import static deus.ttb.util.blockanditems.BlockMaker.make;

public class TTBBlocks {

	public static ExampleBlock exampleBlock;

	public static List<Block> dynamicBlocks = new ArrayList<>();

	public static void initialize() {

		BlockBuilder stoneBlockBuilder = genericBlockBuilder
			.setBlockSound(BlockSounds.STONE)
			.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			;

		exampleBlock = make(
			stoneBlockBuilder
				.setTextures(TTB.MOD_ID+":block/buildingTable/side")
				// $ttb:block/C¿/Users/masit/IdeaProjects/BTA-TextureToBlock/run/TTB/textures/testblock/top.png
				// $ttb:block/C¿\Users\masit\IdeaProjects\BTA-TextureToBlock\src\main\resources\assets\ttb\textures\block\buildingTable\top.png
				// ttb:block/&../run/TTB/ttb/bottom.png
				.setTopTexture("$ttb:block/C¿/Users/masit/IdeaProjects/BTA-TextureToBlock/run/TTB/textures/testblock/top.png")
				.setNorthSouthTextures(TTB.MOD_ID+":block/buildingTable/front")
				.setBottomTexture(TTB.MOD_ID+":block/buildingTable/bottom"),
			new ExampleBlock("block.building.table", TTB.MOD_CONFIG.newBlockID(), Material.stone)
		);

	}

	public static void makeBlockDynamic(TTBBlockData ttbBlockData, BlockBuilder blockBuilder) {
		// ? Formatting path
		String path = ttbBlockData.textureCollection().getTexture(TTBTextureCollection.Face.TOP).orElse("test");
		path = path.replace(":","¿");
		String path2 = "$ttb:block/"+path;

		// ? Creating the block
		dynamicBlocks.add(make(
			blockBuilder.setTextures(path2),
			new Block(ttbBlockData.name(), ttbBlockData.id(), Material.stone)
		));

		// ? Generate atlas
		TextureRegistry.blockAtlas.generateAtlas();

		// ? Reloading chunks
		Minecraft.getMinecraft(exampleBlock).renderGlobal.loadRenderers();
	}


	public void blockAddDetails() {

		//ItemToolPickaxe.miningLevels.put(runeBlock, 1);
		//CreativeHelper.setParent(runeBlock, Block.netherrack);
		//CreativeHelper.setParent(netherRuneBlock, Block.stone);
		//Registries.ITEM_GROUPS.register("rune:rune_ores", Registries.stackListOf(runeBlock));
		//Registries.ITEM_GROUPS.register("rune:rune_ores", Registries.stackListOf(netherRuneBlock));
	}
}
