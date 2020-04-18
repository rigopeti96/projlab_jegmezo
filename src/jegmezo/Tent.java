package jegmezo;

public class Tent implements Item{

    @Override
    public boolean equip(Inventory inventory) {
        return inventory.equipTent();
    }

    @Override
    public boolean unequip(Inventory inventory) {
        return inventory.unequipTent();
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
}
