package jegmezo.model;

import jegmezo.controller.GameController;

/**
 * Player megépítheti egy mezőn és védelmet nyújt a kör végéig
 * Implementálja az Item interfészt. */
public class Tent extends Shovel {

    public Tent(GameController gameController) {
        super(gameController);
    }

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
        if(player.buildTent()){
            unequip(player.getInventory());
            return true;
        }
        return false;
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
        return "Tent";
    }

    /**
     * Meghívja a getName függvényt, hogy rendes nevet adjon vissza
     * @return - a tárgy neve
     */
    @Override
    public String toString(){return getName();}


    /**
     * Visszaadja a tárgy leírását
     * @return A tárgyhoz tatrozó leírás
     */
    @Override
    public String getDescription(){
        return "Sátor:\nHa felépíted, megvéd\na hóvihar ellen.\nA kör végén eltűnik.";
    }

    /**
     * Visszaadja, hogy a tárgy használható-e
     * @return Ennek értéke
     */
    @Override
    public boolean isUseable(){
        return true;
    }
}
