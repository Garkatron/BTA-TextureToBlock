package deus.ttb.guis.BlockBuilder;

import deus.builib.nodes.Root;
import deus.builib.nodes.types.interaction.Button;
import deus.ttb.TTB;
import deus.builib.guimanagement.routing.Page;
import deus.builib.guimanagement.routing.Router;
import deus.ttb.tools.PathUtils;
import net.minecraft.core.util.helper.Side;

import java.util.List;

public class MainPage extends Page {

	public MainPage(Router router) {
		super(TTB.class, router);

		// Your xml path here
		xmlPath = "C:\\Users\\masit\\IdeaProjects\\BTA-TextureToBlock\\run\\GuiLibrary\\TestFolder\\ExampleBlockGui\\test.xml";

		setup(()->{
			Root doc = getDocument();

			Button FRONT = (Button) doc.getNodeById("FRONT");
			Button BACK = (Button) doc.getNodeById("BACK");
			Button TOP = (Button) doc.getNodeById("TOP");
			Button BOTTOM = (Button) doc.getNodeById("BOTTOM");
			Button LEFT = (Button) doc.getNodeById("LEFT");
			Button RIGHT = (Button) doc.getNodeById("RIGHT");

			if (FRONT != null) {
				FRONT.setOnReleaseAction((n)->{
					openTexturesMenu(Side.NORTH);
				});
			}

			if (BACK != null) {
				BACK.setOnReleaseAction((n)->{
					openTexturesMenu(Side.SOUTH);
				});
			}

			if (TOP != null) {
				TOP.setOnReleaseAction((n)->{
					openTexturesMenu(Side.TOP);
				});
			}

			if (BOTTOM != null) {
				BOTTOM.setOnReleaseAction((n)->{
					openTexturesMenu(Side.BOTTOM);
				});
			}

			if (LEFT != null) {
				LEFT.setOnReleaseAction((n)->{
					openTexturesMenu(Side.WEST);
				});
			}

			if (RIGHT != null) {
				RIGHT.setOnReleaseAction((n)->{
					openTexturesMenu(Side.NORTH);
				});
			}

		});



	}

	private void openTexturesMenu(Side side){
		BlockBuilderGui.blockSide = side;

		router.navigateTo("texturesMenu");
	}

}
