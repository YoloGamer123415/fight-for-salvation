package com.yologamer123415.fightforsalvation.helpers;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;

public class LocationHelper {
	public static float screenToTilePixel(float pixelLocation) {
		return pixelLocation / MapGenerator.TILESIZE;
	}

	public static float tileToScreenPixel(float pixelLocation) {
		return pixelLocation * MapGenerator.TILESIZE;
	}
}
