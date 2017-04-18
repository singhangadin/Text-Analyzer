package com.github.angads25.textanalyzer;

import java.io.File;
import java.io.Serializable;

public class IterableFile implements Serializable {
	/**
	 * 	Version 1
	 */
	private static final long serialVersionUID = -3746018467684820613L;
	
	private File file;
	private long completed;
	private long length;
	
	public IterableFile(File file) {
		this.file = file;
		completed = 0;
		length = file.length();
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public long getCompleted() {
		return completed;
	}
	
	public void setCompleted(long completed) {
		this.completed = completed;
	}
	
	public long getLength() {
		return length;
	}
	
	public void setLength(long length) {
		this.length = length;
	}
}