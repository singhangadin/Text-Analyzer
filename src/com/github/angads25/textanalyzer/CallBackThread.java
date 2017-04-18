package com.github.angads25.textanalyzer;

import java.io.File;

public class CallBackThread extends Thread {
	private CallBackListener callBackListener;
	private File file;

	@Override
	public void run() {
		super.run();
//		Connection connection = null;
//		StringBuffer word5 = new StringBuffer();
//		try {
//			System.out.println("Started File:"+ file.getName());
//			Class.forName("org.sqlite.JDBC");
//			connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/Angad/Desktop/5LETTERS.db");
//			Statement statement = connection.createStatement();
//			statement.setQueryTimeout(30);
//			FileReader reader = new FileReader(file);
//			long length = file.length();
//			long done=0;
//			int i;
//			while((i = reader.read()) !=- 1) {
//				done++;
//				if(Character.isLetter((char)i)) {
//					word5.append((char)i);
//				}
//				else {
//					if(word5.length()==5) {
//						Utility.incrementWord(statement, word5.toString());
//						System.out.println(file.getName()+": "+((done*100)/length)+"%");
//					}
//					word5.delete(0, word5.length());
//				}
//			}
//			statement.close();
//			connection.close();
//			reader.close();
//		} 
//		catch (Exception e) {
//			System.err.println("File:"+ file.getName());
//			e.printStackTrace();
//		}
		System.out.println("Thread "+getName()+", Completed File:"+ file.getName());
		callBackListener.onTaskCompleted(getName());
	}

	public void setCallBackListener(CallBackListener callBackListener) {
		this.callBackListener = callBackListener;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
