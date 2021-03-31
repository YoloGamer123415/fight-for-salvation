package com.yologamer123415.fightforsalvation.generators;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.chests.EndChest;
import com.yologamer123415.fightforsalvation.chests.NormalChest;
import com.yologamer123415.fightforsalvation.helpers.FileHelper;
import com.yologamer123415.fightforsalvation.monsters.BowMonster;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.monsters.KnifeMonster;
import nl.han.ica.oopg.exceptions.GameEngineRuntimeException;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.data.JSONObject;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MapGenerator {
	private static final Map<Character, TileType<?>> tileTypes = new LinkedHashMap<>();
	private static final Map<Character, GameObject> gameObjects = new LinkedHashMap<>();
	public static final int TILESIZE = 50;

	/**
	 * Loads the {@link TileType}s and {@link GameObject}s into the map.
	 * Should only be used at initialization!
	 */
	public static void load() {
		for (File file : FileHelper.listFiles("tiletypes")) {
			String[] spriteNameSplit = FileHelper.getBaseName(file.getName()).split("_");

			char spriteChar = spriteNameSplit[0].charAt(0);

			Class<?> tileTypeClass;
			try {
				tileTypeClass = Class.forName("com.yologamer123415.fightforsalvation." + spriteNameSplit[1]);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				continue;
			}

			Sprite sprite = new Sprite("src/main/resources/" + file.getParentFile().getName() + "/" + file.getName());

			tileTypes.put(spriteChar, new TileType(tileTypeClass, sprite));
		}

		for (File file : FileHelper.listFiles("gameobjects")) {
			String[] spriteNameSplit = FileHelper.getBaseName(file.getName()).split("_");

			char spriteChar = spriteNameSplit[0].charAt(0);
			Class<?> gameObjectClass;
			try {
				gameObjectClass = Class.forName("com.yologamer123415.fightforsalvation." + spriteNameSplit[1]);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				continue;
			}

			Sprite sprite = new Sprite("src/main/resources/" + file.getParentFile().getName() + "/" + file.getName());

			GameObject object;
			try {
				object = (GameObject) gameObjectClass.getDeclaredConstructor(Sprite.class).newInstance(sprite);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				e.printStackTrace();
				return;
			}

			gameObjects.put(spriteChar, object);
		}
	}

	/**
	 * Generate the {@link TileMap} from a Level.
	 *
	 * @param level The level to get it from.
	 * @return The TileMap to use.
	 */
	public static TileMap generateTilemapFromFile(int level) {
		FilePersistence file = new FilePersistence("main/resources/levels/" + level + ".json");
		if (!file.fileExists()) throw new IllegalArgumentException("Level " + level + " does not exists!");
		JSONObject data = JSONObject.parse(file.loadDataString());

		int monsterCount = data.getInt("totalMonsters");
		int chestCount = data.getInt("totalChests");
		String[] mapRows = data.getJSONArray("map").getStringArray();

		System.out.println("Loading tilemap for level " + level + "...");

		List<TilePosition> monsterPositions = new ArrayList<>();
		List<TilePosition> chestPositions = new ArrayList<>();

		final int chestIndex = tileTypes.size();
		final int endChestIndex = tileTypes.size() + 1;

		int[][] indexMap = new int[ mapRows.length ][ mapRows[0].toCharArray().length ];

		for (int x = 0; x < mapRows.length; x++) {
			Arrays.fill(indexMap[x], -1); //Fill with default value (-1)

			char[] row = mapRows[x].toCharArray();

			for (int y = 0; y < row.length; y++) {
				char placeChar = row[y];

				if (gameObjects.containsKey(placeChar)) {
					GameObject object = gameObjects.get(placeChar);

					if (FightForSalvation.getInstance().getGameObjectItems().contains(object)) continue;

					FightForSalvation.getInstance().addGameObject(object, (float) (y * TILESIZE), (float) (x * TILESIZE));
				} else if (tileTypes.containsKey(placeChar)) {
					indexMap[x][y] = new ArrayList<>(tileTypes.keySet()).indexOf(placeChar);
				} else {
					switch (placeChar) {
						case '@':
							monsterPositions.add(new TilePosition(x, y));
							break;
						case 'c':
							chestPositions.add(new TilePosition(x, y));
							break;
						case 'C':
							indexMap[x][y] = endChestIndex;
							break;
						case ' ':
							break;
						default:
							throw new IllegalArgumentException("Char " + placeChar + " not registered in resources.");
					}
				}
			}
		}

		if (chestPositions.size() < chestCount) throw new IllegalArgumentException("Not enough chest positions available for size.");
		if (monsterPositions.size() < monsterCount) throw new IllegalArgumentException("Not enough monster positions available for size.");

		List<TileType<?>> types = new LinkedList<>(tileTypes.values());
		types.add(new TileType(NormalChest.class, new Sprite("src/main/resources/tiletypes/special/NormalChest.png")));
		types.add(new TileType(EndChest.class, new Sprite("src/main/resources/tiletypes/special/EndChest.png")));

		place(monsterPositions, monsterCount);
		place(chestPositions, chestCount, indexMap, chestIndex);

		System.out.println("TileMap has been generated, and is ready to set.");

		return new TileMap(TILESIZE, types.toArray(new TileType[0]), indexMap);
	}

	/**
	 * Place {@link GameObject}s on random positions.
	 *
	 * @param availablePositions The positions to place to.
	 * @param placeCount The available position count.
	 */
	private static void place(List<TilePosition> availablePositions, int placeCount) {
		final Random rand = new Random();

		for (int i = 0; i < placeCount; i++) {
			TilePosition randomPos = availablePositions.get(rand.nextInt(availablePositions.size()));
			availablePositions.remove(randomPos);

			GameObject toPlace = getRandomMonster();

			System.out.println(toPlace.getClass().getName());
			System.out.println(randomPos.toString());

			//Add without the addGameObject() method, we force it to add the gameobject multiple times...
			FightForSalvation.getInstance().getGameObjectItems().add(toPlace);
			toPlace.setX(TILESIZE * randomPos.getLine());
			toPlace.setY(TILESIZE * randomPos.getRow());
		}
	}

	/**
	 * Place {@link nl.han.ica.oopg.tile.Tile}s on random positions.
	 *
	 * @param availablePositions The positions to place to.
	 * @param placeCount The available position count.
	 * @param indexMap The tile index map, used for placement.
	 * @param index The index of the tiletype.
	 */
	private static void place(List<TilePosition> availablePositions, int placeCount, int[][] indexMap, int index) {
		final Random rand = new Random();

		for (int i = 0; i < placeCount; i++) {
			TilePosition randomPos = availablePositions.get(rand.nextInt(availablePositions.size()));
			availablePositions.remove(randomPos);
			indexMap[randomPos.getRow()][randomPos.getLine()] = index;
		}
	}

	/**
	 * Get a random monster.
	 *
	 * @return The random monster.
	 */
	private static Monster getRandomMonster() {
		final Random rand = new Random();

		if (rand.nextBoolean()) {
			return new BowMonster();
		} else {
			return new KnifeMonster();
		}
	}
}
