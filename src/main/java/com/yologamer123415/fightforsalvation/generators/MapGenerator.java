package com.yologamer123415.fightforsalvation.generators;

import com.yologamer123415.fightforsalvation.FightForSalvation;
import com.yologamer123415.fightforsalvation.chests.EndChest;
import com.yologamer123415.fightforsalvation.chests.NormalChest;
import com.yologamer123415.fightforsalvation.monsters.BowMonster;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import com.yologamer123415.fightforsalvation.monsters.SwordMonster;
import com.yologamer123415.fightforsalvation.player.Player;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.data.JSONObject;

import java.io.File;
import java.util.*;

public class MapGenerator {
	private static final Map<Character, TileType<?>> tileTypes = new LinkedHashMap<>();
	public static final int TILESIZE = 50;

	/**
	 * Loads the {@link TileType}s into the map.
	 * Should only be used at initialization!
	 */
	public static void loadTileTypes() {
		File[] files = new File("src/main/resources/tiletypes")
				.listFiles((dir, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg"));

		if (files == null) return;

		System.out.println("Loading tiletypes...");

		for (File file : files) {
			String[] spriteNameSplit = getBaseName(file.getName()).split("_");

			boolean isObstacle = false;
			char spriteChar;
			if (spriteNameSplit[0].charAt(0) == '%') {
				isObstacle = true;
				spriteChar = spriteNameSplit[0].charAt(1);
			} else {
				spriteChar = spriteNameSplit[0].charAt(0);
			}

			String packageName = "com.yologamer123415.fightforsalvation." + ( isObstacle ? "obstacles" : "tyles" );

			Class<?> tileTypeClass;
			try {
				tileTypeClass = Class.forName( packageName + "." + spriteNameSplit[1] );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				continue;
			}

			System.out.println("Discovered sprite with char '" + spriteChar + "' and tiletype '" + tileTypeClass.getName() + "'.");

			tileTypes.put( spriteChar, new TileType( tileTypeClass, new Sprite( file.getAbsolutePath() ) ) );
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

		TileType<?>[] types = new TileType[endChestIndex + 1];
		int[][] indexMap = new int[ mapRows.length ][ mapRows[0].toCharArray().length ];

		for (int i = 0; i < mapRows.length; i++) {
			Arrays.fill(indexMap[i], -1); //Fill with default value (-1)

			char[] row = mapRows[i].toCharArray();

			for (int j = 0; j < row.length; j++) {
				char placeChar = row[j];
				switch (placeChar) {
					case '@':
						monsterPositions.add(new TilePosition(i, j));
						break;
					case 'c':
						chestPositions.add(new TilePosition(i, j));
						break;
					case 'C':
						indexMap[i][j] = endChestIndex;
						break;
					case '*':
						Player player = new Player();
						FightForSalvation.getInstance().addGameObject(player, TILESIZE * i, TILESIZE * j);
						break;
					default:
						int typeIndex = new ArrayList<>(tileTypes.keySet()).indexOf(placeChar);
						if (typeIndex == -1) continue; //Incorrect tile, ignore.

						types[typeIndex] = tileTypes.get(row[j]);
						indexMap[i][j] = typeIndex;
						break;
				}
			}
		}

		if (chestPositions.size() < chestCount) throw new IllegalArgumentException("Not enough chest positions available for size.");
		if (monsterPositions.size() < monsterCount) throw new IllegalArgumentException("Not enough chest positions available for size.");

		types[chestIndex] = new TileType(NormalChest.class, new Sprite("src/main/resources/sprites/Chest.png"));
		types[endChestIndex] = new TileType(EndChest.class, new Sprite("src/main/resources/sprites/EndChest.png"));

//		place( monsterPositions, monsterCount, getRandomMonster() );
//		place( chestPositions, chestCount, indexMap, chestIndex );

		System.out.println("TileMap has been generated, and is ready to set.");

		return new TileMap(TILESIZE, types, indexMap);
	}

	/**
	 * Place {@link GameObject}s on random positions.
	 *
	 * @param availablePositions The positions to place to.
	 * @param placeCount The available position count.
	 * @param object The {@link GameObject} to place.
	 */
	private static void place(List<TilePosition> availablePositions, int placeCount, GameObject object) {
		final Random rand = new Random();

		for (int i = 0; i < placeCount; i++) {
			TilePosition randomPos = availablePositions.get(rand.nextInt(availablePositions.size()));
			availablePositions.remove(randomPos);
			FightForSalvation.getInstance().addGameObject(object, TILESIZE * randomPos.getRow(), TILESIZE * randomPos.getLine());
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
			return new BowMonster(new Sprite(""));
		} else {
			return new SwordMonster(new Sprite(""));
		}
	}

	/**
	 * Removes the extension of a filename.
	 *
	 * SOURCE: https://github.com/google/guava/blob/018796b79b314b5b7790c9320c1a7c89140af76d/guava/src/com/google/common/io/Files.java#L820
	 * EDITED, removed unneeded methods.
	 *
	 *
	 * @param fileName The name of the file
	 * @return The name without extension
	 */
	private static String getBaseName(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
	}
}
