package com.github.angads25.textanalyzer;

import java.io.File;
import java.util.Queue;

public class TaskHandler implements CallBackListener {
	private CallBackThread[] callBackThreads;
	private Queue<File> files;
	private int total = 0;
	
	public void startFeeding(Queue<File> files) {
		this.files = files;
		System.out.println("Queue size: "+files.size());
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("No. of Cores: "+cores);
		callBackThreads = new CallBackThread[cores];
		for (int i = 0; i < callBackThreads.length; i++) {
			CallBackThread thread = callBackThreads[i];
			thread = new CallBackThread();
			thread.setName(i+"");
			thread.setCallBackListener(this);
			thread.setFile(files.remove());
			thread.start();
			total++;
		}
	}
	
	@Override
	public void onTaskCompleted(String threadName) {
		int threadIndex = Integer.parseInt(threadName);
		if(files.size() > 0) {
			CallBackThread thread = callBackThreads[threadIndex];
			thread = new CallBackThread();
			thread.setName(threadIndex+"");
			thread.setCallBackListener(this);
			thread.setFile(files.remove());
			thread.start();
			total++;
		}
		else {
			System.out.println("Computed Files: "+total);
		}
	}
}
