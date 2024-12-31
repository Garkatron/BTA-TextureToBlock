package deus.ttb.guis.BlockBuilder;

import deus.builib.guimanagement.AdvancedGuiContainer;
import deus.builib.guimanagement.routing.Page;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.util.helper.Side;

import java.util.ArrayList;
import java.util.List;

public class BlockBuilderGui extends AdvancedGuiContainer {
	private static final Page page = new MainPage(router);
	private static final Page texturesMenu = new TexturesMenu(router);


	public static Side blockSide = Side.NORTH;


	static {
		router.registerRoute("home", page);
		router.registerRoute("texturesMenu", texturesMenu);

		router.navigateTo("home");

	}

	public BlockBuilderGui(IInventory inventory, IInventory playerInventory) {
		super(new ExampleContainer(page, inventory, playerInventory));
	}



}
