package jegmezo;




/** J�t�kos, lehet eszkim� �s kutat�. Birtokolhat t�rgyat, haszn�lhatja azt a t�rgyat �s �tadhatja m�sik j�t�kosnak. �t tud l�pni szomsz�dos mez?re. */
public abstract class Player {
	private int bodyHeat;

	private int actions;

	private Tile tile;

	private Inventory inventory;

	private GameController gameController;

	public Player(GameController gameController) {
		this.gameController = gameController;
	}

	public Inventory getInventory(){
		System.out.println("\nPlayer getInventory\n");
		return inventory;
	}

	/** A j�t�kos k�r�t kezeli le */
	public void takeTurn() {
		System.out.println("\nPlayer takeTurn\n");
	}
	
	/** A j�t�kos egyik n�la l�v? t�rgyat �tadhatja egyik j�t�kos t�rs�nak
	 * @return true ha sikeres, false ha nem */
	public boolean trade() {
		System.out.println("\nPlayer trade\n");
		return false;
	}
	
	/** A j�t�kos felvesz egy t�rgyat
	 * @return true, ha a j�t�kos elt�rolta az adott t�rgyat, false ha nem vette fel */
	public boolean takeItem(Item item) {
		System.out.println("\nPlayer takeItem\n");
		return false;
	}
	
	/** A j�t�kos haszn�lja a gy?zelmi t�rgyat
	 * @return ha minden alkatr�sz megvan �s az �sszes j�t�kos ugyanazon a mez?n van, akkor megnyerik a j�t�kot �s true-t ad vissza, am�gy false*/
	public boolean useWinItems() {
		System.out.println("\nPlayer useWinItems\n");
		if(tile.hasAllPlayers() && inventory.hasAllWinItem()){
			gameController.win();
			System.out.println("\nPlayers Win\n");
			return true;
		}
		System.out.println("\nPlayers don't win\n");
		return false;
	}
	
	/** A j�t�kos mozog (tile-t v�laszt �s odamozog)
	 * @return true has sikeres, false ha nem */
	public boolean move() {
		System.out.println("\nPlayer move\n");
		Tile hova=selectTile();
		hova.stepOnto(this,tile);
		return true;
	}
	
	/** Megn�veli a j�t�kos testh?j�t 1-gyel */
	public void increaseBodyHeat() {
		System.out.println("\nPlayer increaseBodyHeat\n");
		bodyHeat++;
	}
	
	/** Lecs�kkenti a j�t�kos testh?j�t 1-gyel */
	public void decreaseBodyHeat() {
		System.out.println("\nPlayer decreaseBodyHeat\n");
		bodyHeat--;
	}
	
	/** A j�t�kos megfullad */
	public void drown() {
		System.out.println("\nPlayer drown\n");
		gamecontroller.gameOver();
	}
	
	/** A j�t�kos elhaszn�l egy akci�pontot */
	public void loseAP() {
		System.out.println("\nPlayer loseAP\n");
		actions--;
	}
	
	/** A Player k�zzel �s �s 1 egys�g havat takar�t el a mez?j�r?l,
	 * @return true ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithHands() {
		System.out.println("\nPlayer digWithHands\n");
		return false;
	}

	/** A Player �s�val �s �s 2 egys�g havat takar�t el a mez?j�r?l,
	 * @return True ha sikeres, false ha a nem tud egyet sem akkor false-t ad vissza */
	public boolean digWithShovel() {
		System.out.println("\nPlayer digWithShovel\n");
		return false;
	}
	
	/** A j�t�kos felveszi a t�rgyat a mez?r?l, amin �ll
	 * @return true ha feltudta venni, false ha nem*/
	public boolean pickup() {
		System.out.println("\nPlayer pickup\n");
		return false;
	}

	/** A j�t�kos kiv�laszthat egy mez?t, amire l�pni fog, vagy megn�zi sarkkutat�val (men�t dob fel)
	 * @return kiv�lasztott tile (tile.getNeighbours eleme) */
	public Tile selectTile() {
		System.out.println("\nPlayer selectTile\n");
		Tile ret=tile.getNeighbours();
		return null;
	}
	
	/** A j�t�kos kiv�laszt egy t�rgyat, amelyet haszn�l (men�t dob fel)
	 * @return true ha sikeres, false nem */
	public boolean useItem() {
		System.out.println("\nPlayer useItem\n");
		return false;
	}
	
	/** A j�t�kos v�laszt egy akci�t, true-val t�r vissza
	 * @return true ha az akci� sikeres, false ha nem vagy a j�t�kos nem v�lasztott akci�t */
	public abstract boolean selectAction();

	//Hozzáírtam mert nem került bele, illetve még ki lehet bővíteni az inventory munkájával is egyenlőre így hagytam hátha valami változik  -T
	public boolean canSave(){
		System.out.println("\nPlayer canSave\n");
		System.out.println("\nTud menekíteni a játékos?\n1:Igen\n2:Nem");
		String choice=System.console().readLine();
		if(choice=="1")
			return true;
		return false;
	}
}

