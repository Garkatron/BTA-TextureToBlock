package deus.ttb.guis.BlockBuilder;

import deus.builib.guimanagement.routing.Page;
import deus.builib.guimanagement.routing.Router;
import deus.builib.interfaces.nodes.INode;
import deus.builib.nodes.Root;
import deus.builib.nodes.stylesystem.TextureManager;
import deus.builib.nodes.types.interaction.Button;
import deus.builib.nodes.types.interaction.TextField;
import deus.builib.nodes.types.semantic.Div;
import deus.ttb.TTB;
import deus.ttb.block.TTBBlocks;
import deus.ttb.tools.TTBBlockData;
import deus.ttb.tools.TTBDataSaver;
import deus.ttb.tools.TTBTextureCollection;

import java.io.File;

import static deus.ttb.util.blockanditems.BlockMaker.genericBlockBuilder;


public class MainPage extends Page {

	private static boolean saveBlocks = true;

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
		try {
			TextField nameTextField = (TextField) getDocument().getNodeById("nameTextField");

			TTBBlockData data = TTBDataSaver.readFile(TTB.TTBBlocksFolder.getAbsolutePath(), nameTextField.getText());

			if (data == null) {
				System.err.println("Error loading the file" + nameTextField.getText());
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

			String blockFolderPath = TTB.TTBTexturesFolder.getPath() + "/" + data.name();
			String FRONT_PATH = blockFolderPath + ".front.png";
			String BACK_PATH = blockFolderPath + ".back.png";
			String RIGHT_PATH = blockFolderPath + ".right.png";
			String LEFT_PATH = blockFolderPath + ".left.png";
			String TOP_PATH = blockFolderPath + ".top.png";
			String BOTTOM_PATH = blockFolderPath + ".bottom.png";

			TTBDataSaver.decodeBase64ToImage(textureCollection.getTexture(TTBTextureCollection.Face.FRONT).orElse(""), FRONT_PATH);
			TTBDataSaver.decodeBase64ToImage(textureCollection.getTexture(TTBTextureCollection.Face.BACK).orElse(""), BACK_PATH);
			TTBDataSaver.decodeBase64ToImage(textureCollection.getTexture(TTBTextureCollection.Face.RIGHT).orElse(""), RIGHT_PATH);
			TTBDataSaver.decodeBase64ToImage(textureCollection.getTexture(TTBTextureCollection.Face.LEFT).orElse(""), LEFT_PATH);
			TTBDataSaver.decodeBase64ToImage(textureCollection.getTexture(TTBTextureCollection.Face.TOP).orElse(""), TOP_PATH);
			TTBDataSaver.decodeBase64ToImage(textureCollection.getTexture(TTBTextureCollection.Face.BOTTOM).orElse(""), BOTTOM_PATH);

			TexturesMenu.addImageToButton(frontNode, new File(FRONT_PATH));
			TexturesMenu.addImageToButton(backNode, new File(BACK_PATH));
			TexturesMenu.addImageToButton(rightNode, new File(RIGHT_PATH));
			TexturesMenu.addImageToButton(leftNode, new File(LEFT_PATH));
			TexturesMenu.addImageToButton(topNode, new File(TOP_PATH));
			TexturesMenu.addImageToButton(bottomNode, new File(BOTTOM_PATH));

			nameTextField.setText(data.name());

			System.out.println("loaded data successfully: " + data.name());
		} catch (Exception e) {
			System.err.println("Error loading files: " + e.getMessage());
			e.printStackTrace();
		}
	}


	private void createBlock() {
		Root doc = getDocument();

		String frontTexturePath = getTextureFromButton((Button) doc.getNodeById("FRONT"));
		String backTexturePath = getTextureFromButton((Button) doc.getNodeById("BACK"));
		String rightTexturePath = getTextureFromButton((Button) doc.getNodeById("RIGHT"));
		String leftTexturePath = getTextureFromButton((Button) doc.getNodeById("LEFT"));
		String topTexturePath = getTextureFromButton((Button) doc.getNodeById("TOP"));
		String bottomTexturePath = getTextureFromButton((Button) doc.getNodeById("BOTTOM"));

		TextField nameTextField = (TextField) doc.getNodeById("nameTextField");

		System.out.println("NAME: " + nameTextField.getText());

		TTBBlockData data = new TTBBlockData(
			TTB.MOD_CONFIG.newBlockID(),
			nameTextField.getText(),
			new TTBTextureCollection(
				frontTexturePath,
				backTexturePath,
				rightTexturePath,
				leftTexturePath,
				topTexturePath,
				bottomTexturePath,
				null,
				null,
				null,
				null,
				null
			)
		);

		if (saveBlocks) {
			TTBDataSaver.createFile(TTB.TTBBlocksFolder.getAbsolutePath(), data.name(), data);
		}

		TTBBlocks.makeBlockDynamic(data, genericBlockBuilder);
	}

	private String getTextureFromButton(Button b) {
		if (b == null) {
			TTB.LOGGER.error("BUTTON IS NULL");
			return null;
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
			return null;


		}

		TTB.LOGGER.error("{} DIV DOESN'T EXIST", b.getId());
		return null;
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
