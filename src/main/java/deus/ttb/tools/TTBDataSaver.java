package deus.ttb.tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.processing.FilerException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class TTBDataSaver {

	public static boolean createFile(String folderName, String filename, Map<String, Object> data) {
		File folder = new File(folderName);
		if (!folder.exists() && !folder.mkdirs()) {
			System.err.println("Can't create dir: " + folderName);
			return false;
		}

		File file = new File(folderName + File.separator + filename);
		try (FileWriter writer = new FileWriter(file)) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(data);
			writer.write(json);
			return true;
		} catch (IOException e) {
			System.err.println("Error when writing: " + e.getMessage());
			return false;
		}

	}
}
