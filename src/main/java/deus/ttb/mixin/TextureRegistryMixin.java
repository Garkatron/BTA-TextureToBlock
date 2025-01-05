package deus.ttb.mixin;

import deus.ttb.TTB;
import deus.ttb.interfaces.AtlasStitcherAccessor;
import net.minecraft.client.render.stitcher.AtlasStitcher;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.util.collection.NamespaceID;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;

@Mixin(TextureRegistry.class)
public class TextureRegistryMixin  {

	@Shadow
	public static HashMap<String, AtlasStitcher> stitcherMap;


	@Inject(method = "getTexture(Lnet/minecraft/core/util/collection/NamespaceID;)Lnet/minecraft/client/render/stitcher/IconCoordinate;", at = @At("RETURN"), remap = false, cancellable = true)
	private static void test(NamespaceID id, CallbackInfoReturnable<IconCoordinate> cir) {

		//System.out.println("DADADA: "+id.value);


		String str = id.value.substring(1);
		String[] split2 = str.split("/");
		AtlasStitcher atlas = stitcherMap.get(split2[0]);

		if (id.value.startsWith("$")) {

			System.out.println("[GET TEXTURE]: $");
			IconCoordinate result = ((AtlasStitcherAccessor) atlas).invokeGetTexture(new NamespaceID(TTB.MOD_ID, str.replace("¿",":")));

			cir.setReturnValue(result);
		} else if (id.value.startsWith("&")) {

			System.out.println("[GET TEXTURE]: &");

			IconCoordinate result = ((AtlasStitcherAccessor) atlas).invokeGetTexture(new NamespaceID(TTB.MOD_ID, str));
			cir.setReturnValue(result);

		}


	}

	/*
	@Inject(method = "hasTexture(Lnet/minecraft/core/util/collection/NamespaceID;)Z", at=@At("HEAD"), remap = false, cancellable = true)
	private static void test2(NamespaceID id, CallbackInfoReturnable<Boolean> cir) {

	}
	*/


}
