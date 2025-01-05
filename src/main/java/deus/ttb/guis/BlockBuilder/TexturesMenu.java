package deus.ttb.guis.BlockBuilder;

import deus.builib.guimanagement.routing.Page;
import deus.builib.guimanagement.routing.Router;
import deus.builib.nodes.Root;
import deus.builib.nodes.stylesystem.TextureManager;
import deus.builib.nodes.types.containers.Layout;
import deus.builib.nodes.types.containers.ScrollableLayout;
import deus.builib.nodes.types.interaction.Button;
import deus.builib.nodes.types.interaction.TextField;
import deus.builib.nodes.types.representation.Label;
import deus.builib.nodes.types.semantic.Div;
import deus.builib.util.rendering.TextureMode;
import deus.builib.util.rendering.TextureProperties;
import deus.ttb.TTB;
import deus.ttb.tools.PathUtils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;


public class TexturesMenu extends Page {

	private List<String> folders = new ArrayList<>();

	public TexturesMenu(Router router) {
		super(TTB.class, router);

		// Path del XML
		xmlPath = "/assets/ttb/guis/TexturesMenu/test.xml";

		setup(() -> {
			Root doc = getDocument();

			// Cargar carpetas iniciales
			find("");

			// Configurar botón de regreso
			Button backButton = (Button) doc.getNodeById("backButton");
			if (backButton != null) {
				backButton.setOnReleaseAction((n) -> router.backPreviousPage());
			}

			// Configurar campo de texto principal
			TextField mainTextField = (TextField) doc.getNodeById("mainTextField");
			if (mainTextField != null) {
				mainTextField.setOnEnter((t) -> find(mainTextField.getText()));
			}
		});
	}

	private void find(String currentFilter) {
		ScrollableLayout content = (ScrollableLayout) getDocument().getNodeById("content");

		// Limpiar contenido previo
		if (content != null) {
			content.setChildren(new ArrayList<>());
		}

		// Obtener las carpetas
		folders = PathUtils.getFirstLevelSubdirectories(TTB.TTBTexturesFolder.getPath());

		for (String folder : folders) {
			String folderName = extractFolderName(folder);

			// Crear layout de carpeta
			Layout layout = (Layout) new Layout().addChildren(
				new Label().setText(List.of(folderName))
			);
			layout.setAttributes(Map.of("group", "folder"));

			// Obtener archivos PNG
			List<File> files = PathUtils.getPngFiles(folder);
			for (File file : files) {
				layout.addChildren(createFileButton(file));
			}

			// Filtrar y agregar al contenido
			if (shouldAddFolderToContent(currentFilter, folder) && content != null) {
				content.addChildren(layout);
			}
		}
	}

	private String extractFolderName(String folderPath) {
		String[] parts = folderPath.replace("\\", "/").split("/");
		return parts[parts.length - 1];
	}

	private Button createFileButton(File file) {
		return (Button) new Button()
			.setOnReleaseAction((b) -> {
				Map<String, Object> styles = new HashMap<>();

				String str = "TTB:TEXTURE:BLOCK_BUILDER:DYN:"+ file.getName();

				TextureManager.getInstance().addTexture(
					str, new TextureProperties(file.getAbsolutePath(),16,16, new TextureProperties.Border(0,0,0,0), false, TextureMode.STRETCH)
				);

				styles.put("backgroundImage", str);
				styles.put("width", "100%");
				styles.put("height", "100%");

				Div div = new Div();
				div.applyStyle(styles);
				BlockBuilderGui.currentButton.setChildren(new ArrayList<>());
				BlockBuilderGui.currentButton.addChildren(div);


				router.backPreviousPage();
			})
			.addChildren(
				new Label().setText(List.of(file.getName()))
					.setAttributes(Map.of("group", "fileButton"))
			);
	}

	private boolean shouldAddFolderToContent(String currentFilter, String folder) {
		return currentFilter.isEmpty() || currentFilter.isBlank() || currentFilter.equals(folder);
	}
}
