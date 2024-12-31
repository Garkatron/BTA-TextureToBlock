package deus.ttb.guis.BlockBuilder;

import deus.builib.guimanagement.routing.Page;
import deus.builib.guimanagement.routing.Router;
import deus.builib.nodes.Root;
import deus.builib.nodes.types.interaction.Button;
import deus.ttb.TTB;
import net.minecraft.core.util.helper.Side;

public class TexturesMenu extends Page {

	public TexturesMenu(Router router) {
		super(TTB.class, router);

		// Your xml path here
		xmlPath = "C:\\Users\\masit\\IdeaProjects\\BTA-TextureToBlock\\run\\GuiLibrary\\TestFolder\\TexturesMenu\\test.xml";

		setup(()->{
			Root doc = getDocument();

			Button backButton = (Button) doc.getNodeById("backButton");
			backButton.setOnReleaseAction((r)->{
				router.backPreviousPage();
			});
		});



	}

	private void openTexturesMenu(Side side){
		BlockBuilderGui.blockSide = side;

		router.navigateTo("texturesMenu");
	}

}
