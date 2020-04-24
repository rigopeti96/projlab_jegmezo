package jegmezo;

/**
 * Player megépítheti egy mezőn és védelmet nyújt a kör végéig
 * Implementálja az Item interfészt. */
public class Tent implements Item{

    /** Beteszi egy Player Inventory-jába magát
     * @param inventory - A Player inventoryja, amibe be kell tenni a Tent-et.
     * @return bool - Sikerült-e betenni. */
    public boolean equip(Inventory inventory) { return inventory.equipTent(this); }

    /** Kiveszi egy Player Inventory-jából magát
     * @param inventory - A Player inventoryja, amiből ki kell venni a Tent-et.
     * @return bool - Sikerült-e kivenni. */
    public boolean unequip(Inventory inventory) { return inventory.unequipTent(this); }

    /** A Player használja a Tent-et, akkor megépíti a mezőre, amin áll.
     * @param player Player, aki használja a tárgyat
     * @return bool - Sikerült-e használni a tárgyat. */
    public boolean use(Player player) {
        return player.tile.build(Building.TENT);
    }

    /** Mindig hamisat ad (érvényes specifikáció szerint)
     @return false - A Tent nem tud megmenteni más játékosokat*/
    public boolean canSave() {
        return false;
    }

    /** Mindig hamisat ad (érvényes specifikáció szerint)
     * @return false - A Tent nem tudja megmenteni játékosát*/
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
