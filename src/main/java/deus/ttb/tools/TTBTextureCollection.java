package deus.ttb.tools;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

public class TTBTextureCollection {
	private final Map<Face, String> resolvedTextures;

	public TTBTextureCollection(@Nullable String front,
								@Nullable String back,
								@Nullable String right,
								@Nullable String left,
								@Nullable String top,
								@Nullable String bottom,
								@Nullable String all,
								@Nullable String topBottom,
								@Nullable String sides,
								@Nullable String frontBack,
								@Nullable String leftRight) {
		resolvedTextures = new EnumMap<>(Face.class);
		resolvedTextures.put(Face.FRONT, resolveTexture(front, frontBack, all));
		resolvedTextures.put(Face.BACK, resolveTexture(back, frontBack, all));
		resolvedTextures.put(Face.RIGHT, resolveTexture(right, leftRight, sides, all));
		resolvedTextures.put(Face.LEFT, resolveTexture(left, leftRight, sides, all));
		resolvedTextures.put(Face.TOP, resolveTexture(top, topBottom, all));
		resolvedTextures.put(Face.BOTTOM, resolveTexture(bottom, topBottom, all));
	}

	public TTBTextureCollection(@Nullable TTBTextureCollection ttbTextureCollection) {
		resolvedTextures = new EnumMap<>(Face.class);
		if (ttbTextureCollection != null) {
			for (Face face : Face.values()) {
				resolvedTextures.put(face, ttbTextureCollection.getTexture(face).orElse(null));
			}
		}
	}


	public TTBTextureCollection()
	{
		this(null, null, null, null, null, null, null, null, null, null, null);
	}


	private static String resolveTexture(String... textures) {
		for (String texture : textures) {
			if (texture != null) {
				return TTBDataSaver.encodeImageToBase64(texture);
			}
		}
		return null;
	}

	public Optional<String> getTexture(Face face) {
		return Optional.ofNullable(resolvedTextures.get(face));
	}

	@Override
	public String toString() {
		return "TTBTextureCollection{" +
			"resolvedTextures=" + resolvedTextures +
			'}';
	}

	/**
	 * Enum que define las caras posibles.
	 */
	public enum Face {
		FRONT, BACK, RIGHT, LEFT, TOP, BOTTOM
	}
}
