package starterMining;

import org.powerbot.script.Area;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.ClientAccessor;

import static starterMining.HelperMethods.createAreaFromCorners;

public class StarterMinerConstants extends ClientAccessor {
	// Item ids
	public static final int bronzePickaxeId 		= 1265;
	public final Component weaponSlot               = ctx.widgets.component(Constants.EQUIPMENT_WIDGET, 17).component(1);
	public static final Area LUMBRIDGE_AREA   = createAreaFromCorners(
			new Tile(3231, 3215, 0),
			new Tile(3236, 3221, 0)
	);

	public StarterMinerConstants(ClientContext ctx) {
		super(ctx);
	}
}
