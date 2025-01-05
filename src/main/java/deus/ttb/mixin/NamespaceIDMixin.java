package deus.ttb.mixin;

import net.minecraft.core.util.collection.NamespaceID;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(NamespaceID.class)
public class NamespaceIDMixin {



	@Inject(method = "<init>(Ljava/lang/String;Ljava/lang/String;)V", at = @At(value = "RETURN"))
	private void test(String namespace, String value, CallbackInfo ci) {
		try {
			Field namespaceField = NamespaceID.class.getDeclaredField("namespace");
			namespaceField.setAccessible(true);
			String originalNamespace = (String) namespaceField.get(this);

			namespaceField.set(this, originalNamespace.startsWith("$") ? originalNamespace.substring(1) : originalNamespace);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Inject(method = "<init>(Ljava/lang/String;)V", at = @At(value = "RETURN"))
	private void test(String formattedString, CallbackInfo ci) {
		try {
			Field namespaceField = NamespaceID.class.getDeclaredField("namespace");
			namespaceField.setAccessible(true);
			String originalNamespace = (String) namespaceField.get(this);

			namespaceField.set(this, originalNamespace.startsWith("$") ? originalNamespace.substring(1) : originalNamespace);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}



}
