package com.async.processor;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncHelper {
	
	@Async
	public CompletableFuture<String> getData(String input) throws NumberFormatException, InterruptedException {
		Thread.sleep(1 * 1000);
		CompletableFuture<String> c = new CompletableFuture<>();
		System.out.println(Thread.currentThread().getName());
		long count = IntStream.range(0, 10000000).filter(i -> i%5 ==0).count();
		c.complete(String.valueOf(count));
		return c;
	}

}
