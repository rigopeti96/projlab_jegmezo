package jegmezo;

public class Tent implements Item{

    @Override
    public boolean equip(Inventory inventory) {
        return inventory.equipTent(this);
    }

    @Override
    public boolean unequip(Inventory inventory) {
        return inventory.unequipTent(this);
    }

    @Override
    public boolean use(Player player) {

        return false;
    }

    @Override
    public boolean canSave() {
        return false;
    }

    @Override
    public boolean canSurvive() {
        return false;
    }

    /**
     * Visszaadja a tárgy nevét
     * @return A tárgy neve
     */
    @Override
    public String getName() {
        return "tent";
    }
}
