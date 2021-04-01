package com.yologamer123415.fightforsalvation.helpers;

import com.yologamer123415.fightforsalvation.generators.MapGenerator;

public class LocationHelper {
	public static float screenPixelToTilePixel(float pixelLocation) {
		return pixelLocation / MapGenerator.TILESIZE;
	}

	public static float tilePixelToScreenPixel(float pixelLocation) {
		return pixelLocation * MapGenerator.TILESIZE;
	}
}
