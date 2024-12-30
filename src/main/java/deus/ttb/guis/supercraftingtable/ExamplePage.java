package deus.ttb.guis.supercraftingtable;

import deus.builib.nodes.Root;
import deus.builib.nodes.types.interaction.Button;
import deus.ttb.TTB;
import deus.builib.guimanagement.routing.Page;
import deus.builib.guimanagement.routing.Router;
import deus.ttb.block.TTBBlocks;

public class ExamplePage extends Page {

	public ExamplePage(Router router, String... text) {
		super(TTB.class, router);

		// Your xml path here
		xmlPath = "/assets/ttb/guis/ExampleBlockGui/test.xml";

		setup(()->{
			Root doc = getDocument();
			Button b = (Button) doc.getNodeById("test");
			if (b!=null) {
				b.setOnReleaseAction((n)->{
					TTBBlocks.makeBlock.emit(true);
				});
			}
		});



	}
}
