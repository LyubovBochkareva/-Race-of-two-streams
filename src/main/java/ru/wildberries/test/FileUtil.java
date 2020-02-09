package ru.wildberries.test;

import java.io.IOException;
import java.io.FileWriter;

/**
 * @author Lyubov Bochkareva
 * @since 09.02.20.
 */

class FileUtil {

	private FileWriter file;

	FileUtil() {
		try {
			file = new FileWriter("Результат.txt");
		} catch (IOException e) {
			System.exit(1);
		}
	}

	/**
	 * Write string object in to file.
	 *
	 * @param str string object
	 */
	synchronized void writeString(String str) throws IOException {
		file.write(str);
		file.append('\n');
		file.flush();
	}

	void close() throws IOException {
		file.close();
	}
}
