package deus.ttb.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import javax.annotation.processing.FilerException;
import java.io.*;
import java.util.Base64;
import java.util.Map;

public class TTBDataSaver {

	public static boolean createFile(String folderName, String filename, TTBBlockData ttbBlockData) {
		File folder = new File(folderName);
		if (!folder.exists() && !folder.mkdirs()) {
			System.err.println("Can't create dir: " + folderName);
			return false;
		}

		File file = new File(folderName + File.separator + filename);
		try (FileWriter writer = new FileWriter(file)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(ttbBlockData);
			writer.write(json);
			return true;
		} catch (IOException e) {
			System.err.println("Error when writing: " + e.getMessage());
			return false;
		}

	}


	public static TTBBlockData readFile(String folderName, String filename) {
		File file = new File(folderName + File.separator + filename);

		if (!file.exists() || !file.isFile()) {
			System.err.println("File not found: " + file.getPath());
			return null;
		}

		try (FileReader reader = new FileReader(file)) {
			Gson gson = new Gson();
			return gson.fromJson(reader, TTBBlockData.class);
		} catch (IOException e) {
			System.err.println("Error when reading file: " + e.getMessage());
			return null;
		} catch (JsonSyntaxException e) {
			System.err.println("Error parsing JSON: " + e.getMessage());
			return null;
		}
	}

	public static String encodeImageToBase64(String imagePath) throws IOException {
		File file = new File(imagePath);
		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] imageBytes = fis.readAllBytes();
			return Base64.getEncoder().encodeToString(imageBytes);
		}
	}

	public static void decodeBase64ToImage(String base64Image, String outputPath) throws IOException {
		byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
		try (FileOutputStream fos = new FileOutputStream(outputPath)) {
			fos.write(decodedBytes);
		}
	}
}
