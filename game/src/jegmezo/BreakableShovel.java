package jegmezo;

public class BreakableShovel extends Shovel{

    private int durability = 3;

    /** Beteszi egy Player Inventory-jába magát
     *  @param inventory - A Player inventoryja, amibe be kell tenni a Shovelt.
     *  @return bool - Sikerült-e betenni.  */
    @Override
    public boolean equip(Inventory inventory) {
        return inventory.equipBreakableShovel(this);
    }

    /** kiveszi egy Player Inventory-jából magát
     *  @param inventory - A Player inventoryja, amibõl ki kell tenni a Shovelt.
     *  @return boolean - Sikerült-e kivenni */
    @Override
    public boolean unequip(Inventory inventory) {
        return inventory.unequipBreakableShovel(this);
    }

    /** a Player használja a tárgyat
     * @param player Player, aki használja a tárgyat
     * @return true ha sikeres, false ha nem */
    @Override
    public boolean use(Player player) {
        if(super.use(player)){
            durability--;
            if(durability == 0) unequip(player.getInventory());
            return true;
        }
        return false;
    }

    /**
     * Visszaadja a tárgy nevét
     * @return A tárgy neve .
     */
    @Override
    public String getName() {
        return "breakable shovel";
    }
}
