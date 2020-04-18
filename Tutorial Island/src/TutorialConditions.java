package tutorialIsland;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;

import java.util.concurrent.Callable;

import static tutorialIsland.TutorialConstants.cookedShrimpID;

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

    // Is the chat window gone?
    Callable<Boolean> chatWindowInvalid = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return !tutorialComponents.chatHeader.valid();
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

    // Have we clicked the options tab?
    Callable<Boolean> optionsClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.optionsTab.textureId() != -1;
        }
    };

    // Have we clicked the management tab?
    Callable<Boolean> managementTabClicked = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.accountManagementTab.textureId() != -1;
        }
    };

    // Have we gotten our net?
    Callable<Boolean> startFishing = new Callable<Boolean>() {
        @Override
        public Boolean call() throws Exception {
            return tutorialComponents.instructionsHeader.text().contains("start fishing");
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
