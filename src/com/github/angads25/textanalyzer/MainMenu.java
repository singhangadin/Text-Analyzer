package com.github.angads25.textanalyzer;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MainMenu{
	
	public static void main(String[] args) {
		TaskHandler handler = new TaskHandler();
		System.out.println("1. Start from beginning");
		System.out.println("2. Resume previous session");
		System.out.println("3. Show completed files");
		System.out.println("0. Exit");
		System.out.print("Enter your choice:");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		switch(option) {
			case 1:	File booksDir = new File("books");
					if(!booksDir.exists()) {
						System.err.println("Books directory doesn't exist");
					}
					else {
						File[] books = booksDir.listFiles();
						if(books.length<=0) {
							System.err.println("There are no books to read!");
						}	
						else {
							Queue<File> files = new LinkedList<>();
							for (File book : books) {
								files.add(book);
							}
							handler.startFeeding(files);
						}
					}
					break;
					
			case 2:	File checkptDir = new File("checkpoint");
					if(!checkptDir.exists()) {
						System.err.println("Checkpoint Directory doesn't exist");
					}
					else {
						File[] checkpoints = checkptDir.listFiles();
						if(checkpoints.length<=0) {
							System.err.println("You don't have anything to restore!");
						}
						else {
							File checkpoint = new File(checkptDir.getPath()+"/checkpoint.txt");
							
						}
					}
					break;
					
			case 3: 
					break;
					
			default:
					break;
		}
		sc.close();
	}
}
