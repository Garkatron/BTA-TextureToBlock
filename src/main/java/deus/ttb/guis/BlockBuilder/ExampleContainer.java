package deus.ttb.guis.BlockBuilder;


import deus.builib.guimanagement.AdvancedContainer;
import deus.builib.interfaces.IPage;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.player.inventory.IInventory;

public class ExampleContainer extends AdvancedContainer {

	public ExampleContainer(IPage page, IInventory inventory, IInventory playerInventory) {
		super(page, inventory, playerInventory);
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
		return true;
	}
}
