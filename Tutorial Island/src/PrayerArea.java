package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;

public class PrayerArea extends Task {
    public PrayerArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        /*
        TALK TO BROTHER BRACE (brotherBraceID)
        CLICK PRAYER TAB (prayerTab)
        TALK TO BROTHER BRACE
        CLICK FRIENDS TAB (friendsTab)
        TALK TO BROTHER BRACE
        OPEN DOOR (prayerDoorID)
        */
    }
}
