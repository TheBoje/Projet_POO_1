package Serialisation;


import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;


public abstract class Serializer
{
	public static void toJSON(Object input, String fileName)
	{
		System.out.format("Serialising %s to %s\n", input.getClass(), fileName + ".json");

		Gson gson = GsonUtils.getGson();

		String Json = gson.toJson(input);
		try (PrintStream out = new PrintStream(new FileOutputStream("Data\\" + fileName + ".json")))
		{
			out.print(Json);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		System.out.format("Serialising done\n");
	}

	public static Object fromJSON(String fileName)
	{
		Object out;
		Gson gson = GsonUtils.getGson();

		Path filePath = Path.of("Data\\" + fileName + ".json");
		try
		{
			String readRes = Files.readString(filePath);
			out = "";
			//out = gson.fromJson(readRes, World.class); // FIXME
			return out;
		} catch (IOException e)
		{
			e.printStackTrace();
			return null; // FIXME This is not fancy
		}
	}
}
