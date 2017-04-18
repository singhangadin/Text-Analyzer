package com.github.angads25.textanalyzer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.sql.Statement;

public class Utility {
	public static synchronized void incrementWord(Statement statement, String word5) throws SQLException {
		String query = 
				"UPDATE ALLWORDS "
				+ "SET Frequency = Frequency + 1 "
				+ "WHERE Letter = '"+word5.toString().toUpperCase()+"'";
		statement.executeUpdate(query);
	}
	
	public static synchronized void saveCheckpoint() {
		File checkptDir = new File("checkpoint");
		if(!checkptDir.exists()) {
			checkptDir.mkdir();
		}
		File checkpoint = new File(checkptDir.getPath()+"/checkpoint.txt");
		if(checkpoint.exists()) {
			checkpoint.delete();
		}
		try {
			FileOutputStream fout = new FileOutputStream(checkpoint);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(LogSingleton.getInstance());
			oout.flush();
			oout.close();
			fout.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
