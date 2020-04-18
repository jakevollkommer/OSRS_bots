package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;

public class BankArea extends Task {
    public BankArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        /*
        USE BANK (bankBoothID)
        X OUT
        USE POLL BOOTH (pollBoothID)
        continueChat()
        X OUT
        OPEN DOOR (bankDoorInID)
        TALK TO ACCOUNT GUIDE (accountGuideID)
        CLICK ACCOUNT MANAGEMENT TAB (accountManagementTab)
        TALK TO ACCOUNT GUIDE
        OPEN DOOR (bankDoorOutID)
        */
    }
}
