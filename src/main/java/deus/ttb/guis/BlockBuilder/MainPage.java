package deus.ttb.guis.BlockBuilder;

import deus.builib.interfaces.nodes.INode;
import deus.builib.nodes.Root;
import deus.builib.nodes.stylesystem.TextureManager;
import deus.builib.nodes.types.interaction.Button;
import deus.builib.nodes.types.interaction.TextField;
import deus.builib.nodes.types.representation.Label;
import deus.builib.nodes.types.semantic.Div;
import deus.ttb.TTB;
import deus.builib.guimanagement.routing.Page;
import deus.builib.guimanagement.routing.Router;
import deus.ttb.block.TTBBlocks;
import deus.ttb.tools.PathUtils;
import deus.ttb.tools.TTBBlockData;
import deus.ttb.tools.TTBDataSaver;
import deus.ttb.tools.TTBTextureCollection;
import java.io.File;
import java.util.List;

import static deus.ttb.util.blockanditems.BlockMaker.genericBlockBuilder;


public class MainPage extends Page {

	private static boolean saveBlocks = false;

	public MainPage(Router router) {
		super(TTB.class, router);

		// Ruta XML
		xmlPath = "C:\\Users\\masit\\IdeaProjects\\BTA-TextureToBlock\\run\\GuiLibrary\\TestFolder\\ExampleBlockGui\\test.xml";

		setup(() -> {
			Root doc = getDocument();

			setupFaceButton(doc, "FRONT");
			setupFaceButton(doc, "BACK");

			setupFaceButton(doc, "TOP");
			setupFaceButton(doc, "BOTTOM");

			setupFaceButton(doc, "LEFT");
			setupFaceButton(doc, "RIGHT");

			Button button = (Button) doc.getNodeById("createButton");
			if (button != null) {
				button.setOnReleaseAction(
					(n) -> createBlock()
				);
			}

			Button button3 = (Button) doc.getNodeById("importButton");
			if (button3 != null) {
				button3.setOnReleaseAction(
					(n) -> loadData()

				);
			}

			Button button2 = (Button) doc.getNodeById("permanentButton");
			if (button2 != null) {
				button2.setToggleMode(true);
				button2.setOnReleaseAction(
					(n) -> saveBlocks = button2.isOn()
				);
			}

			Button button4 = (Button) doc.getNodeById("clearButton");
			if (button4 != null) {
				button4.setToggleMode(true);
				button4.setOnReleaseAction(
					(n) -> reload()
				);
			}


		});
	}

	private void loadData() {
		TextField nameTextField = (TextField) getDocument().getNodeById("nameTextField");

		TTBBlockData data = TTBDataSaver.readFile(TTB.TTBBlocksFolder.getAbsolutePath(), nameTextField.getText());


		if (data == null) {
			System.err.println("Error: No se pudo cargar el archivo " + nameTextField.getText());
			return;
		}

		Root doc = getDocument();
		Button frontNode = (Button) doc.getNodeById("FRONT");
		Button backNode = (Button) doc.getNodeById("BACK");
		Button rightNode = (Button) doc.getNodeById("RIGHT");
		Button leftNode = (Button) doc.getNodeById("LEFT");
		Button topNode = (Button) doc.getNodeById("TOP");
		Button bottomNode = (Button) doc.getNodeById("BOTTOM");

		TTBTextureCollection textureCollection = data.textureCollection();

		TexturesMenu.addImageToButton(frontNode, new File(textureCollection.getTexture(TTBTextureCollection.Face.FRONT).orElse("")));
		TexturesMenu.addImageToButton(backNode, new File(textureCollection.getTexture(TTBTextureCollection.Face.BACK).orElse("")));
		TexturesMenu.addImageToButton(rightNode, new File(textureCollection.getTexture(TTBTextureCollection.Face.RIGHT).orElse("")));
		TexturesMenu.addImageToButton(leftNode, new File(textureCollection.getTexture(TTBTextureCollection.Face.LEFT).orElse("")));
		TexturesMenu.addImageToButton(topNode, new File(textureCollection.getTexture(TTBTextureCollection.Face.TOP).orElse("")));
		TexturesMenu.addImageToButton(bottomNode, new File(textureCollection.getTexture(TTBTextureCollection.Face.BOTTOM).orElse("")));

		nameTextField.setText(data.name());


	}


	private void createBlock() {
		Root doc = getDocument();

		String frontTexture = getTextureFromButton((Button) doc.getNodeById("FRONT"));
		String backTexture = getTextureFromButton((Button) doc.getNodeById("BACK"));
		String rightTexture = getTextureFromButton((Button) doc.getNodeById("RIGHT"));
		String leftTexture = getTextureFromButton((Button) doc.getNodeById("LEFT"));
		String topTexture = getTextureFromButton((Button) doc.getNodeById("TOP"));
		String bottomTexture = getTextureFromButton((Button) doc.getNodeById("BOTTOM"));

		TextField nameTextField = (TextField) doc.getNodeById("nameTextField");

		System.out.println("NAME: " + nameTextField.getText());

		TTBBlockData data = new TTBBlockData(
			TTB.MOD_CONFIG.newBlockID(),
			nameTextField.getText(),
			new TTBTextureCollection(
				frontTexture,
				backTexture,
				rightTexture,
				leftTexture,
				topTexture,
				bottomTexture,
				null, // "all"
				null, // "topBottom"
				null, // "sides"
				null, // "frontBack"
				null  // "leftRight"
			)
		);

		if (saveBlocks) {
			saveBlock(data);
		}

		TTBBlocks.makeBlockDynamic(data, genericBlockBuilder);
	}

	private void saveBlock(TTBBlockData data) {
		TTBDataSaver.createFile(TTB.TTBBlocksFolder.getAbsolutePath(), data.name(), data);

	}


	private String getTextureFromButton(Button b) {
		if (b == null) {
			TTB.LOGGER.error("BUTTON IS NULL");
			return "button null";
		}

		if (!b.getChildren().isEmpty()) {
			INode c = b.getChildren().get(0);
			if (c instanceof Div div) {
				String backgroundImage = (String) div.getStyle().getOrDefault("backgroundImage", "");
				if (!backgroundImage.isEmpty()) {
					TTB.LOGGER.info("{} Background image found: {}", b.getId(), backgroundImage);
					return TextureManager.getInstance().getTexture(backgroundImage).path();
				}

			}
			TTB.LOGGER.error("{} No background image defined in DIV.", b.getId());
			return "no background image";


		}

		TTB.LOGGER.error("{} DIV DOESN'T EXIST", b.getId());
		return "div doesn't exist";
	}


	private void setupFaceButton(Root doc, String buttonId) {
		Button button = (Button) doc.getNodeById(buttonId);
		if (button != null) {
			button.setOnReleaseAction(
				(n) -> {
					BlockBuilderGui.currentButton = (Button) n;
					router.navigateTo("texturesMenu");
				}
			);


		}
	}


}
