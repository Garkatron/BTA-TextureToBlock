package deus.ttb.mixin;

import net.minecraft.client.render.stitcher.AtlasStitcher;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.texturepack.TexturePackList;
import net.minecraft.core.util.collection.NamespaceID;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

@Mixin(AtlasStitcher.class)
public class AtlasSticherMixin {

	@Redirect(method = "generateAtlas()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/texturepack/TexturePackList;getResourceAsStream(Ljava/lang/String;)Ljava/io/InputStream;"), remap = false)
	private InputStream test(TexturePackList instance, String stream) throws FileNotFoundException {

		if (stream.contains("¿")) {
			System.out.println("STREAM: " + stream);
			int questionMarkIndex = stream.indexOf("¿");

			String cleanedStream = stream.substring(questionMarkIndex - 1).replace("¿", ":");

			if (cleanedStream.endsWith(".png")) {
				cleanedStream = cleanedStream.substring(0, cleanedStream.length() - 4); // Eliminar los últimos 4 caracteres (".png")
			}

			System.out.println("LIMPIO: " + cleanedStream);
			System.out.println("- - - - - - - > ¿ PATH < - - - - - - -");

			return new FileInputStream(cleanedStream);
		} else if (stream.contains("&")) {

			int questionMarkIndex = stream.indexOf("&");

			String cleanedStream = stream.substring(questionMarkIndex - 1).replace("¿", ":");

			String clean = cleanedStream.substring(0, cleanedStream.length() - 4).replace("&", "");

			System.out.println("2LIMPIO: " + clean);
			System.out.println("- - - - - - - > & PATH < - - - - - - -");

			try {
				return new FileInputStream(clean.substring(1));

			} catch (FileNotFoundException e) {
				System.err.println("- - - - - - - > FILE NOT FOUND < - - - - - - -" + clean);
				throw e;
			}
		}



		return instance.getResourceAsStream(stream);
	}

}
