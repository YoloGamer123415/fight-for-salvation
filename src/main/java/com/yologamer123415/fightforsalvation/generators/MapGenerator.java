package com.yologamer123415.fightforsalvation.generators;

import nl.han.ica.oopg.persistence.FilePersistence;

import java.util.HashMap;
import java.util.Map;

public class MapGenerator {
	private static final Map<Character, Integer> tiles = new HashMap<>();

	static {
		tiles.put('#', 0);
		tiles.put('c', 1);
		tiles.put('C', 2);
		tiles.put('B', 3);
		tiles.put('b', 4);
		tiles.put('S', 5);
		tiles.put('s', 6);
	}

	public static int[][] generateTilemapFromFile(FilePersistence file) {
		int[][] array = new int[6][6];

		if (!file.fileExists()) return array;

		String[] data = file.loadDataStringArray("\n");
		for (int i = 0; i < data.length; i++) {
			char[] row = data[i].toCharArray();
			for (int j = 0; j < row.length; j++) {
				array[i][j] = tiles.get(row[j]);
			}
		}

		return array;
	}
}
