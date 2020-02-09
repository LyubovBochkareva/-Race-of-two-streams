package ru.wildberries.test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Lyubov Bochkareva
 * @since 09.02.20.
 */

public class MyThread extends Thread {

	private CountDownLatch latch;
	private final FileUtil fileUtil;
	private static Logger log = Logger.getLogger(MyThread.class.getName());

	MyThread(String name, CountDownLatch downLatch, FileUtil fileUtil) {
		super(name);
		this.latch = downLatch;
		this.fileUtil = fileUtil;
	}

	@Override
	public void run() {
		for (int i = 1; i < 100; i++) {
			try {
				fileUtil.writeString(this.getName() + ' ' + i);
			} catch (IOException e) {
				log.log(Level.SEVERE, this.getName() + "Failed to write to file ", e);
			}
		}
		synchronized (fileUtil) {
			try {
				if (latch.getCount() == 2) {

					fileUtil.writeString(this.getName() + " Выиграл");
				} else {
					fileUtil.writeString(this.getName() + " Проиграл");
				}
			} catch (IOException e) {
				log.log(Level.SEVERE, this.getName() + "Failed to write to file ", e);
			}
			latch.countDown();
		}
	}
}
