package deus.ttb.tools;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class PathUtils {
	public static String relPathTransform(String modid, String cat, String path) {
		String transformedPath = modid + ":" + cat + "/&" + path;
		return transformedPath;
	}

	public static List<String> getFirstLevelSubdirectories(String parentDirPath) {
		File parentDir = new File(parentDirPath);
		List<String> subdirectories = new ArrayList<>();

		if (parentDir.exists() && parentDir.isDirectory()) {
			File[] files = parentDir.listFiles();

			if (files != null) {
				for (File file : files) {
					if (file.isDirectory()) {
						subdirectories.add(file.getName());
					}
				}
			}
		} else {
			System.out.println("La carpeta especificada no es válida.");
		}

		return subdirectories;
	}

	public static List<String> getPngFiles(String parentDirPath) {
		File parentDir = new File(parentDirPath);
		List<String> pngFiles = new ArrayList<>();

		if (parentDir.exists() && parentDir.isDirectory()) {
			File[] files = parentDir.listFiles();

			if (files != null) {
				for (File file : files) {
					if (file.isFile() && file.getName().endsWith(".png")) {
						pngFiles.add(file.getName());
					}
				}
			}
		} else {
			System.out.println("La carpeta especificada no es válida.");
		}

		return pngFiles;
	}

}
