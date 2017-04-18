package com.github.angads25.textanalyzer;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CallBackThread extends Thread {
	private CallBackListener callBackListener;
	private IterableFile file;

	@Override
	public void run() {
		super.run();
		Connection connection = null;
		StringBuffer word5 = new StringBuffer();
		try {
			System.out.println("Thread "+getName()+", Started File:"+ file.getFile().getName());
//			Class.forName("org.sqlite.JDBC");
//			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Angad/Desktop/5LETTERS.db");
//			Statement statement = connection.createStatement();
//			statement.setQueryTimeout(30);
			FileReader reader = new FileReader(file.getFile());
			long length = file.getFile().length();
			long done=0;
			int i;
			loop: while((i = reader.read()) !=- 1) {
				done++;
				if(done < file.getCompleted() - 1) {
					System.out.println("Skipping length: "+done);
					continue loop;
				}
				if(Character.isLetter((char)i)) {
					word5.append((char)i);
					continue loop;
				}
				else {
					if(word5.length()==5) {
//						Utility.incrementWord(statement, word5.toString());
//						System.out.println(file.getName()+": "+((done*100)/length)+"%");
//						System.out.println(word5);
						file.setCompleted(done);
						LogSingleton.updateStatus(file);
						Utility.saveCheckpoint();
					}
					word5.delete(0, word5.length());
				}
			}
			file.setCompleted(done);
			LogSingleton.updateStatus(file);
//			statement.close();
//			connection.close();
			reader.close();
		} 
		catch (Exception e) {
			System.err.println("File:"+ file.getFile().getName());
			e.printStackTrace();
		}
		System.out.println("Thread "+getName()+", Completed File:"+ file.getFile().getName());
		callBackListener.onTaskCompleted(getName());
		Utility.saveCheckpoint();
	}

	public void setCallBackListener(CallBackListener callBackListener) {
		this.callBackListener = callBackListener;
	}

	public void setIterableFile (IterableFile file) {
		this.file = file;
	}
}
