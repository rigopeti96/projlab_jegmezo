package jegmezo;

public class BreakableShovel extends Shovel implements Item{

    private int used;

    /** Beteszi egy Player Inventory-jába magát
     *  @param inventory - A Player inventoryja, amibe be kell tenni a Shovelt.
     *  @return bool - Sikerült-e betenni.  */
    public boolean equip(Inventory inventory) {
        return inventory.equipBreakableShovel(this);
    }

    /** kiveszi egy Player Inventory-jából magát
     *  @param inventory - A Player inventoryja, amibõl ki kell tenni a Shovelt.
     *  @return boolean - Sikerült-e kivenni */
    public boolean unequip(Inventory inventory) {
        return inventory.unequipBreakableShovel(this);
    }

    /** a Player használja a tárgyat
     * @param player Player, aki használja a tárgyat
     * @return true ha sikeres, false ha nem */
    public boolean use(Player player) {

        return false;
    }

    /** Mindig hamisat ad (érvényes specifikáció szerint)
     * @return false - A Shovel nem tudja megmenteni játékosát*/
    public boolean canSurvive() {
        System.out.println("BreakableShovel canSurvive");
        return false;
    }

    /** Mindig hamisat ad (érvényes specifikáció szerint)
     * @return false - A Shovel nem tud megmenteni más játékosokat*/
    public boolean canSave() {
        System.out.println("BreakableShovel canSave");
        return false;
    }

    /**
     * Visszaadja a tárgy nevét
     * @return A tárgy neve
     */
    public String getName() {
        return "breakable shovel";
    }
}
