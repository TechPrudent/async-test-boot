package com.async.processor;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsyncCaller {

	@Autowired
	private AsyncConfig asyncConfig;

	@PostConstruct
	public void testAsync() throws NumberFormatException, InterruptedException {
		System.out.println("testAsync called");
		ArrayList<CompletableFuture<String>> arrayList = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			final int input = i;
			CompletableFuture<String> data = CompletableFuture.supplyAsync(() -> {
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName());
				return String.valueOf(input);
			}, asyncConfig.taskExecutor());
			arrayList.add(data);
		}

		String result = arrayList.stream().map(CompletableFuture::join).collect(Collectors.joining(","));
		System.out.println(result);

	}

}