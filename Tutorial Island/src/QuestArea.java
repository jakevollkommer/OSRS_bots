package tutorialIsland;

import org.powerbot.script.rt4.ClientContext;

public class QuestArea extends Task {
    public QuestArea(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return false;
    }

    @Override
    public void execute() {
        /*
        RUN TO QUEST DOOR (dont have coords yet)
        OPEN QUEST DOOR (questDoorID)
        TALK TO QUEST GUIDE (questGuideID)
        CLICK QUEST JOURNAL (questTab)
        TALK TO QUEST GUIDE
        CLIMB LADDER (ladderDownID)
        */
    }
}
