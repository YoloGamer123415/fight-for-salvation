package com.yologamer123415.fightforsalvation.generators;

import com.yologamer123415.fightforsalvation.chests.Chest;
import com.yologamer123415.fightforsalvation.monsters.Monster;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import processing.data.JSONObject;

import java.io.File;
import java.util.*;

public class MapGenerator {
	private static final Map<Character, TileType<?>> tileTypes = new LinkedHashMap<>();

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

		TileType<?>[] types = new TileType[ tileTypes.size() + 2 ]; //Append to for monster and chests
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
						//TODO Place end chest
						break;
					case '*':
						//TODO Place player
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

		//TODO Implement rarity
		types[ tileTypes.size() ] = new TileType(Chest.class, new Sprite(""));
		types[ tileTypes.size() + 1 ] = new TileType(Monster.class, new Sprite(""));

		System.out.println("TileMap has been generated, and is ready to set.");

		return new TileMap(50, types, indexMap);
	}

	private static void placeRandomly(int toPlaceIndex, List<TilePosition> availablePositions, int placeCount, int[][] indexMap) {
		final Random rand = new Random();

		for (int i = 0; i < placeCount; i++) {
			TilePosition randomPos = availablePositions.get(rand.nextInt(availablePositions.size()));

			indexMap[ randomPos.getRow() ][  randomPos.getLine() ] = toPlaceIndex;
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
