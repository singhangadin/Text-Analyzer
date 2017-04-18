package com.github.angads25.textanalyzer;

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
}
