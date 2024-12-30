package deus.ttb.interfaces;

import net.minecraft.client.render.stitcher.AtlasStitcher;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.util.collection.NamespaceID;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(AtlasStitcher.class)
public interface AtlasStitcherAccessor {

	@Invoker("getTexture")
	IconCoordinate invokeGetTexture(@NotNull NamespaceID id);
}
