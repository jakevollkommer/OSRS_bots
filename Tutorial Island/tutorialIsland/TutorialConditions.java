package tutorialIsland;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;

import java.util.concurrent.Callable;

import static tutorialIsland.TutorialConstants.*;

public class TutorialConditions extends ClientAccessor {
    TutorialComponents tutorialComponents = new TutorialComponents(ctx);

    // Are you doing an animation?
    Callable<Boolean> animation = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            // -1 means no animation, i.e. player is not fishing
            return ctx.players.local().animation() != -1;
        }
    };

    // Did the player stop moving?
    Callable<Boolean> notInMotion = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            System.out.println("inmotion: " + ctx.players.local().inMotion());
            return !ctx.players.local().inMotion();
        }
    };

    // Are we out of combat?
    Callable<Boolean> outOfCombat = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            System.out.println("inmotion: " + ctx.players.local().inMotion());
            return !ctx.players.local().interacting().valid();
        }
    };

    // Are we in combat?
    Callable<Boolean> inCombat = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            System.out.println("inmotion: " + ctx.players.local().inMotion());
            return ctx.players.local().interacting().valid();
        }
    };


    // Is the chat window gone?
    Callable<Boolean> chatWindowInvalid = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return !tutorialComponents.chatHeader.valid();
        }
    };

    // Is the equipment interface gone?
    Callable<Boolean> equipmentInterfaceInvalid = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return !tutorialComponents.equipmentX.valid();
        }
    };

    // Is the bank open?
    Callable<Boolean> bankOpen = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.bankX.valid();
        }
    };

    // Is the bank closed?
    Callable<Boolean> bankClosed = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return !tutorialComponents.bankX.valid();
        }
    };

    // Is the poll booth open?
    Callable<Boolean> pollBoothOpen = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.pollX.valid();
        }
    };

    // Is the poll booth closed?
    Callable<Boolean> pollBoothClosed = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return !tutorialComponents.pollX.valid();
        }
    };

    // Do we have chat options?
    Callable<Boolean> chatOptions = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            Component chatOption = ctx.widgets.component(Constants.CHAT_WIDGET, Constants.CHAT_OPTIONS[0]);
            return chatOption.valid();
        }
    };

    // Can we continue the chat?
    Callable<Boolean> canContinue = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return ctx.chat.canContinue();
        }
    };

    // Have we casted a spell?
    Callable<Boolean> spellCasted = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("mainland");
        }
    };

    // Are we ready to smith?
    Callable<Boolean> readyToSmith = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("smith");
        }
    };

    // Can we click a flashing tab?
    Callable<Boolean> tabReady = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.chatHeader.text().contains("flashing") ||
                    tutorialComponents.instructionsHeader.text().contains("flashing");
        }
    };

    // Can we start skilling?
    Callable<Boolean> skillReady = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("skills");
        }
    };

    // Can we start woodcutting?
    Callable<Boolean> woodcutReady = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("Woodcutting");
        }
    };

    // Did we cook a shrimp?
    Callable<Boolean> shrimpCooked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return (HelperMethods.getItemFromInventory(cookedShrimpID, ctx) != null);
        }
    };

    // Did we make bread?
    Callable<Boolean> breadMade = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return (HelperMethods.getItemFromInventory(breadID, ctx) != null);
        }
    };

    // Did we get ingredients?
    Callable<Boolean> gotIngredients = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return (HelperMethods.getItemFromInventory(flourID, ctx) != null);
        }
    };

    // Did we make dough?
    Callable<Boolean> doughMade = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return (HelperMethods.getItemFromInventory(doughID, ctx) != null);
        }
    };

    // Did we smelt a bar?
    Callable<Boolean> barSmelted = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return (HelperMethods.getItemFromInventory(bronzeBarID, ctx) != null);
        }
    };

    // Did we smith a dagger?
    Callable<Boolean> daggerSmithed = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return (HelperMethods.getItemFromInventory(bronzeDaggerID, ctx) != null);
        }
    };

    // Ready to smith?
    Callable<Boolean> smithReady = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.bronzeDagger.valid();
        }
    };

    // Have we clicked the options tab?
    Callable<Boolean> optionsClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.optionsTab.textureId() != -1;
        }
    };

    // Have we clicked the magic tab?
    Callable<Boolean> mageTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.mageTab.textureId() != -1;
        }
    };

    // Have we clicked the management tab?
    Callable<Boolean> managementTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.accountManagementTab.textureId() != -1;
        }
    };

    // Have we clicked the equipment tab?
    Callable<Boolean> equipmentTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.equipmentTab.textureId() != -1;
        }
    };

    // Have we clicked the worn equipment?
    Callable<Boolean> wornEquipmentClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return  tutorialComponents.instructionsHeader.text().contains("worn inventory");
        }
    };

    // Have we clicked the combat tab?
    Callable<Boolean> combatTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.combatTab.textureId() != -1;
        }
    };

    // Have we clicked the quest tab?
    Callable<Boolean> questTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.questTab.textureId() != -1;
        }
    };

    // Have we clicked the friends tab?
    Callable<Boolean> friendsTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.friendsTab.textureId() != -1;
        }
    };

    // Have we clicked the prayer tab?
    Callable<Boolean> prayerTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.prayerTab.textureId() != -1;
        }
    };

    // Have we gotten our net?
    Callable<Boolean> startFishing = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("start fishing");
        }
    };

    // Can we go down the ladder?
    Callable<Boolean> ladderReady = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("ladder");
        }
    };

    // Can we use ranged?
    Callable<Boolean> rangeReady = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("bow");
        }
    };

    // Are we moving?
    Callable<Boolean> inMotion = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return ctx.players.local().inMotion();
        }
    };

    public TutorialConditions(ClientContext ctx) {
        super(ctx);
    }
}
