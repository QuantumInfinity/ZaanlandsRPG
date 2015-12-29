package zrpg.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NativeUtils {

	public static void loadLibraryFromJar(String nativePath) {
		try {
			File tmp = File.createTempFile("lwjgl", "");
			File natives = new File(tmp.getParent() + File.separator + new File(nativePath).getName());
			tmp.delete();
			natives.deleteOnExit();

			copyFromJar(nativePath, natives);
			System.setProperty("org.lwjgl.librarypath", natives.getParent() + File.separator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void copyFromJar(String from, File to) {
		System.out.println(to);
		try {
			if (!from.startsWith("/"))
				throw new IllegalArgumentException("The path has to be absolute (start with '/').");

			if (!to.exists())
				to.createNewFile();

			byte[] buffer = new byte[1024];
			int readBytes;

			InputStream is = NativeUtils.class.getResourceAsStream(from);
			if (is == null)
				throw new FileNotFoundException("File " + to + " was not found inside JAR.");

			OutputStream os = new FileOutputStream(to);
			try {
				while ((readBytes = is.read(buffer)) != -1)
					os.write(buffer, 0, readBytes);
			} finally {
				os.close();
				is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}