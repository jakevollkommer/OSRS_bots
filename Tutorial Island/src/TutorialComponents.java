package tutorialIsland;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;

public class TutorialComponents extends ClientAccessor {
    // Components
    public final Component instructionsHeader    = ctx.widgets.component(263, 1).component(0);
    public final Component chatHeader            = ctx.widgets.component(Constants.CHAT_NPC, 2);

    public final Component accountManagementTab  = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 44);
    public final Component friendsTab            = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 45);
    public final Component optionsTab            = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 40); // TODO check texture type
    public final Component combatTab             = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 52);
    public final Component statsTab              = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 53);
    public final Component questTab              = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 54);
    public final Component inventoryTab          = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 55);
    public final Component equipmentTab          = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 56);
    public final Component prayerTab             = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 57);
    public final Component mageTab               = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 58);

    public final Component bronzeDagger          = ctx.widgets.component(312, 9).component(2);
    public final Component wornEquipment         = ctx.widgets.component(Constants.EQUIPMENT_WIDGET, 1);
    // TODO Not sure about this wind strike one
    // Constants.SPELLBOOK_WIDGET ? 218
    public final Component windStrike            = ctx.widgets.component(261, 1).component(3);

    public TutorialComponents(ClientContext ctx) {
        super(ctx);
    }
}
