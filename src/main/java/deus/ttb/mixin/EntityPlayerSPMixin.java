package deus.ttb.mixin;


import deus.ttb.guis.interfaces.mixin.IEntityPlayer;
import deus.ttb.guis.supercraftingtable.ExampleBlockTileEntity;
import deus.ttb.guis.supercraftingtable.ExampleGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityPlayerSP.class)
public class EntityPlayerSPMixin implements IEntityPlayer {


	@Shadow
	protected Minecraft mc;

	@Override
	public void guiLib$openExampleGui(ExampleBlockTileEntity exampleBlockTileEntity) {
		mc.displayGuiScreen(new ExampleGui(exampleBlockTileEntity, mc.thePlayer.inventory));

	}
}
