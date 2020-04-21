package starterMining;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;

public class StarterMinerComponents extends ClientAccessor {

	public final Component bronzeDagger          = ctx.widgets.component(312, 9).component(2);
	public final Component wornEquipment         = ctx.widgets.component(Constants.EQUIPMENT_WIDGET, 1);
	public final Component weaponSlot            = ctx.widgets.component(Constants.EQUIPMENT_WIDGET, 17).component(1);

	public StarterMinerComponents(ClientContext ctx) {
		super(ctx);
	}

}
