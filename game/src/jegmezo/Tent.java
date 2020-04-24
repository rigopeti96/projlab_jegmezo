package jegmezo;

public class Tent implements Item{

    public boolean equip(Inventory inventory) {
        return inventory.equipTent(this);
    }

    public boolean unequip(Inventory inventory) {
        return inventory.unequipTent(this);
    }

    public boolean use(Player player) {

        return false;
    }

    public boolean canSave() {
        return false;
    }

    public boolean canSurvive() {
        return false;
    }

    /**
     * Visszaadja a tárgy nevét
     * @return A tárgy neve
     */
    public String getName() {
        return "tent";
    }
}
