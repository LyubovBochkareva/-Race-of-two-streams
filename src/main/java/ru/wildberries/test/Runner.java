package ru.wildberries.test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lyubov Bochkareva
 * @since 09.02.20.
 */

public class Runner {

	private static Logger log = Logger.getLogger(Runner.class.getName());

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch downLatch = new CountDownLatch(2);
		FileUtil fileUtil = new FileUtil();
		MyThread thread1 = new MyThread("Поток A", downLatch, fileUtil);
		MyThread thread2 = new MyThread("Поток Б", downLatch, fileUtil);
		thread1.start();
		thread2.start();
		downLatch.await();
		try {
			fileUtil.close();
		} catch (IOException e) {
			log.log(Level.WARNING, "Failed to close the file", e);
		}
	}
}
