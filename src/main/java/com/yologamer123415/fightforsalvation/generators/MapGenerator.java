package com.yologamer123415.fightforsalvation.generators;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapGenerator {
	private static final Map<Character, TileType<?>> tileTypes = new LinkedHashMap<>();

	public static void loadTileTypes() {
		Class<?> tileClass;
		try {
			tileClass = Class.forName("nl.han.ica.oopg.tile.TileType");
		} catch (ClassNotFoundException e) {
			return;
		}

		File[] files = new File("src/main/resources/tiletypes").listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

		if (files == null) return;

		for (File file : files) {
			String[] spriteNameSplit = file.getName().split("_");
			char spriteChar = spriteNameSplit[0].charAt(0);
			String className = uppercaseString( spriteNameSplit[1] );

			TileType<?> tileType;
			try {
				Class<?> spriteClass = Class.forName(className);
				Sprite sprite = new Sprite( file.getAbsolutePath() );

				tileType = (TileType<?>) tileClass.getConstructor(Class.class, Sprite.class).newInstance(spriteClass, sprite);
			} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
				e.printStackTrace();
				continue;
			}

			tileTypes.put(spriteChar, tileType);
		}
	}

	/**
	 * Generate the {@link TileMap} from a Level.
	 *
	 * @param level The level to get it from.
	 * @return The TileMap to use.
	 */
	public static TileMap generateTilemapFromFile(int level) {
		FilePersistence file = new FilePersistence("main/resources/levels/" + level + ".txt");
		if (!file.fileExists()) return null;
		String[] data = file.loadDataStringArray("\n");

		TileType<?>[] types = new TileType[ tileTypes.size() ];
		int[][] indexMap = new int[ data.length ][ data.length ];

		for (int i = 0; i < data.length; i++) {
			char[] row = data[i].toCharArray();

			for (int j = 0; j < row.length; j++) {
				types[row[j]] = tileTypes.get( row[j] );
				indexMap[i][j] = new ArrayList<>( tileTypes.keySet() ).indexOf( row[j] );
			}
		}

		return new TileMap(data.length * data.length, types, indexMap);
	}

	/**
	 * Uppercase every word in a {@link String}
	 * SOURCE: https://gist.github.com/Hylke1982/166a792313c5e2df9d31
	 *
	 * @param in The input string
	 * @return The uppercased string
	 */
	private static String uppercaseString(String in) {
		return Stream.of(in.trim().split("-"))
				.map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
				.collect(Collectors.joining(""));
	}
}
