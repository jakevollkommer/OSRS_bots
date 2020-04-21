package starterMining;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;
import tutorialIsland.TutorialComponents;

import static starterMining.HelperMethods.*;
import static starterMining.StarterMinerConstants.*;

public class DropInventory extends Task {

	StarterMinerConditions starterMinerConditions = new StarterMinerConditions(ctx);

	public DropInventory(ClientContext ctx) { super(ctx); }

	@Override
	public boolean activate() {
		return LUMBRIDGE_AREA.contains(ctx.players.local().tile()) &&
				Condition.wait(starterMinerConditions.pickaxeEquipped, 100, 1);
	}

	@Override
	public void execute() {
		System.out.println("Starting Starter Miner!");

		System.out.println("Enabling shift click drop!");
		turnOnShiftDrop(ctx);
		System.out.println("Shift click drop enabled!");

		Component inventoryTab = ctx.widgets.component(Constants.RESIZABLE_VIEWPORT_BOTTOM_LINE_WIDGET, 55);
		inventoryTab.click();

		// Drop everything besides bronze pickaxe
		Item[] inventoryItems = ctx.inventory.items();
		System.out.println("Checking inventory items");

		for (int i = 0; i < inventoryItems.length; i++) {
			if (inventoryItems[i].id() != bronzePickaxeId) {
				ctx.inventory.drop(inventoryItems[i], true);
			}
		}

		System.out.println("Equipping bronze pickaxe!");
		Item pickaxe = getItemFromInventory(bronzePickaxeId, ctx);
		pickaxe.interact("Wield");
		System.out.println("Equipped bronze dagger!");

	}

}
