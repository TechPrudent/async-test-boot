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
	private AsyncHelper asyncHelper;
	
	@PostConstruct
	public void testAsync() throws NumberFormatException, InterruptedException {
		
		ArrayList<CompletableFuture<String>> arrayList = new ArrayList<>(); 
		for (int i = 1; i <= 20; i++) {
			CompletableFuture<String> data = this.asyncHelper.getData(String.valueOf(i));
			arrayList.add(data);
		}
		
		String result = arrayList.stream().map(CompletableFuture::join).collect(Collectors.joining(","));
		System.out.println(result);
		
		
	}

}