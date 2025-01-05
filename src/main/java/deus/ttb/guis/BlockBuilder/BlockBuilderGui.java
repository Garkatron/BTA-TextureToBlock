package deus.ttb.guis.BlockBuilder;

import deus.builib.guimanagement.AdvancedGuiContainer;
import deus.builib.guimanagement.routing.Page;
import deus.builib.nodes.types.interaction.Button;
import deus.ttb.tools.TTBBlockData;
import deus.ttb.tools.TTBTextureCollection;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.util.helper.Side;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockBuilderGui extends AdvancedGuiContainer {
	private static final Page page = new MainPage(router);
	private static final Page texturesMenu = new TexturesMenu(router);


	public static Button currentButton = new Button();


	static {
		router.registerRoute("home", page);
		router.registerRoute("texturesMenu", texturesMenu);

		router.navigateTo("home");

	}

	public BlockBuilderGui(IInventory inventory, IInventory playerInventory) {
		super(new ExampleContainer(page, inventory, playerInventory));

	}



}
