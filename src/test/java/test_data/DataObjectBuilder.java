package test_data;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class DataObjectBuilder {

	public static <T> T buildDataObject(String filePath, Class<T> dataType) {
		T returnedData;
		String absoluteFilePath = System.getProperty("user.dir").concat(filePath);

		try (Reader reader = Files.newBufferedReader(Paths.get(absoluteFilePath));) {

			Gson gson = new Gson();
			returnedData = gson.fromJson(reader, dataType);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return returnedData;
	}
}
