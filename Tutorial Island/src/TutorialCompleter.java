package tutorial;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class TutorialCompleter extends Task {
    Component instructionsHeader = ctx.widgets.component(263, 1).component(0);
    Component chatHeader = ctx.widgets.component(231, 2);
    Component optionsTab = ctx.widgets.component(164, 46);
    Component statsTab = ctx.widgets.component(164, 53);
    Component inventoryTab = ctx.widgets.component(164, 55);

    int[] fishingSpots = {505,506,507};
    int shrimpID = 2514;
    int cookedShrimpID = 315;
    int treeID = 1570;
    int tinderboxID = 590;
    int fireID = 2260;

    Item shrimp;
    Item logs;
    Item tinderbox;

    public TutorialCompleter(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return instructionsHeader.text().contains("Getting started"); //TODO .valid()?
    }

    @Override
    public void execute() {
        System.out.println("Starting tutorial");
        Condition.sleep(2000);
        // Talk to the Gielinor Guide
        Npc gielinorGuide = ctx.npcs.select().id(3308).select().poll();
        talkTo(gielinorGuide);
        System.out.println("talked");
        ChatOption chat = ctx.chat.select().text("I am an experienced player.").poll();
        chat.select();
        continueChat();

        optionsTab.click();

        talkTo(gielinorGuide);

        // Continue to First Area
        GameObject firstDoor = ctx.objects.select().id(9398).select().poll();
        firstDoor.click();
        Npc survivalExpert = ctx.npcs.select().id(8503).select().poll();
        talkTo(survivalExpert);
        inventoryTab.click();

        // Fish a shrimp
        GameObject fishingSpot = ctx.objects.select().id(fishingSpots).nearest().select().poll();
        fishingSpot.click();
        // Have you caught a shrimp yet?
        Callable<Boolean> caughtShrimp = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                for(Item item : ctx.inventory.items()) {
                    if(item.id() == shrimpID) {
                        shrimp = item;
                        return true;
                    }
                }
                return false;
            }
        };
        Condition.wait(caughtShrimp, 1000, 10);
        statsTab.click();

        // Woodcut
        talkTo(survivalExpert);
        GameObject tree = ctx.objects.select().id(treeID).nearest().select().poll();
        tree.click();
        // Have you cut a tree yet?
        Callable<Boolean> cutTree = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                Condition.sleep(5000);
                for(Item item : ctx.inventory.items()) {
                    if(item.id() == tinderboxID) {
                        tinderbox = item;
                    } else if(item.id() == treeID) {
                        logs = item;
                        return true;
                    }
                }
                return false;
            }
        };
        Condition.wait(cutTree, 1000, 10);
        tinderbox.click(); //TODO handle null
        logs.click();
        // Have you built a fire yet?
        Callable<Boolean> builtFire = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return instructionsHeader.text().contains("Cooking");
            }
        };
        Condition.wait(builtFire, 1000, 10);
        shrimp.click();
    }

    public void talkTo(Npc npc) {
        System.out.println("Talking to ID " + npc.id() + " AKA " + npc.name());
        npc.interact("Talk-to");
        Callable<Boolean> talking = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return chatHeader.text().contains(npc.name());
            }
        };
        Condition.wait(talking, 500, 10);
        continueChat();
    }

    public void continueChat() {
        while(ctx.chat.canContinue()) {
            ctx.chat.continueChat();
        }
    }
}
