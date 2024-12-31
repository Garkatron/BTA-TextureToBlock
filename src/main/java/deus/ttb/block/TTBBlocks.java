package deus.ttb.block;

import deus.builib.gssl.Signal;
import deus.ttb.TTB;
import deus.ttb.guis.BlockBuilder.ExampleBlock;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.sound.BlockSound;
import net.minecraft.core.sound.BlockSounds;
import turniplabs.halplibe.helper.BlockBuilder;

import static deus.ttb.util.blockanditems.BlockMaker.genericBlockBuilder;
import static deus.ttb.util.blockanditems.BlockMaker.make;

public class TTBBlocks {

	public static ExampleBlock exampleBlock;
	public static ExampleBlock exampleBlock2;
	public static Signal<Boolean> makeBlock = new Signal<>();

	public static void initialize() {

		BlockBuilder stoneBlockBuilder = genericBlockBuilder
			.setBlockSound(BlockSounds.STONE)
			.setBlockSound(new BlockSound("step.stone", "step.stone", 1.0f, 1.0f))
			.setTags(BlockTags.MINEABLE_BY_PICKAXE)
			;

		makeBlock.connect((r,v)->{
			exampleBlock2 = make(
				stoneBlockBuilder
					.setTextures(TTB.MOD_ID + ":block/block_log_pile_side")
					.setTopBottomTextures(TTB.MOD_ID + ":block/block_log_pile"),
				new ExampleBlock("block.log.pile", TTB.MOD_CONFIG.newBlockID(), Material.stone)
			);
		});

		exampleBlock = make(
			stoneBlockBuilder
				.setTextures(TTB.MOD_ID+":block/buildingTable/side")
				// $ttb:block/C¿\Users\masit\IdeaProjects\BTA-TextureToBlock\src\main\resources\assets\ttb\textures\block\buildingTable\top.png
				// ttb:block/&../run/TTB/ttb/bottom.png
				.setTopTexture(TTB.MOD_ID+":block/buildingTable/top")
				.setNorthSouthTextures(TTB.MOD_ID+":block/buildingTable/front")
				.setBottomTexture(TTB.MOD_ID+":block/buildingTable/bottom"),
			new ExampleBlock("block.building.table", TTB.MOD_CONFIG.newBlockID(), Material.stone)
		);

	}


	public void blockAddDetails() {
		//ItemToolPickaxe.miningLevels.put(runeBlock, 1);
		//CreativeHelper.setParent(runeBlock, Block.netherrack);
		//CreativeHelper.setParent(netherRuneBlock, Block.stone);
		//Registries.ITEM_GROUPS.register("rune:rune_ores", Registries.stackListOf(runeBlock));
		//Registries.ITEM_GROUPS.register("rune:rune_ores", Registries.stackListOf(netherRuneBlock));
	}
}
