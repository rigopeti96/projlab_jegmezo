package jegmezo;

/** */
public class Food implements Item {
	/** */
	private Player food;

	@Override
	public boolean equip(Inventory inventory) {
		return false;
	}

	@Override
	public boolean unequip(Inventory inventory) {
		return false;
	}

	/** */
	public boolean use(Player p) {
	}

	@Override
	public boolean canSave() {
		return false;
	}

	@Override
	public boolean canSurvive() {
		return false;
	}
}
