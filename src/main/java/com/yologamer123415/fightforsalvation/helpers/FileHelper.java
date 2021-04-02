package com.yologamer123415.fightforsalvation.helpers;

import java.io.File;

public class FileHelper {
	/**
	 * Removes the extension of a filename.
	 * @see <a href="https://github.com/google/guava/blob/018796b79b314b5b7790c9320c1a7c89140af76d/guava/src/com/google/common/io/Files.java#L820">Guava</a> for the source of this code
	 *
	 * @param fileName The name of the file
	 * @return The name without extension
	 */
	public static String getBaseName(String fileName) {
		int dotIndex = fileName.lastIndexOf('.');
		return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
	}

	/**
	 * List the files (png and jpg) in the resource folder.
	 *
	 * @param folder The name of the folder inside the resources folder.
	 * @return The list of files.
	 *
	 * @throws IllegalArgumentException If folder does not exists.
	 */
	public static File[] listFiles(String folder) {
		File[] content = new File("src/main/resources/" + folder)
				.listFiles((dir, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg"));

		if (content == null) throw new IllegalArgumentException("Folder " + folder + " does not exists.");

		return content;
	}
}
