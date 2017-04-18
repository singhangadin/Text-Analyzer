package com.github.angads25.textanalyzer;

import java.util.Queue;

public class TaskHandler implements CallBackListener {
	private CallBackThread[] callBackThreads;
	private Queue<IterableFile> files;
	
	public void startFeeding(Queue<IterableFile> files) {
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
			thread.setIterableFile(files.remove());
			thread.start();
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
			thread.setIterableFile(files.remove());
			thread.start();
		}
	}
}
