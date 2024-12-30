package deus.ttb.item;

import deus.ttb.TTB;
import deus.ttb.util.blockanditems.ItemMaker;
import net.minecraft.core.item.Item;
import turniplabs.halplibe.helper.ItemBuilder;


public class TTBItems {

	public static Item exampleItem;

	public static void initialize() {

		ItemBuilder genericItemBuilder = new ItemBuilder(TTB.MOD_ID);

		exampleItem = genericItemBuilder.build(
			new Item("exampleItem", TTB.MOD_CONFIG.newItemID())
		);

		ItemMaker.assignPriorities(TTBItems.class);
	}
}
