package com.github.angads25.textanalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MainMenu{
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		TaskHandler handler = new TaskHandler();
		System.out.println("1. Start from beginning");
		System.out.println("2. Resume old session");
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
							Queue<IterableFile> files = new LinkedList<>();
							for (File book : books) {
								IterableFile iterableFile = new IterableFile(book);
								files.add(iterableFile);
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
							try {
								FileInputStream fin = new FileInputStream(checkpoint);
								ObjectInputStream oin = new ObjectInputStream(fin);
								Object obj = oin.readObject();
									if(obj!=null && obj instanceof HashMap<?,?>) {
										HashMap<String, IterableFile> HM = (HashMap<String, IterableFile>) obj;
										booksDir = new File("books");
										if(!booksDir.exists()) {
											System.err.println("Books directory doesn't exist");
										}
										else {
											File[] books = booksDir.listFiles();
											if(books.length<=0) {
												System.err.println("There are no books to read!");
											}	
											else {
												Queue<IterableFile> files = new LinkedList<>();
												for (File book : books) {
													String key = book.getName()+"|"+book.length();
													if(HM.containsKey(key)) {
														long length = book.length();
														long completed = HM.get(key).getCompleted();
														if(completed < length) {
															System.out.println("Found File: "+book.getName()+" completed: "+completed);
															IterableFile iterableFile = new IterableFile(book);
															iterableFile.setCompleted(completed);
															files.add(iterableFile);
														}
													}
													else {
														IterableFile iterableFile = new IterableFile(book);
														files.add(iterableFile);
													}
												}
//												handler.startFeeding(files);
											}
										}
									}
								oin.close();
								fin.close();
							} 
							catch (IOException e) {
								e.printStackTrace();
							} 
							catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
					break;
					
			case 3: checkptDir = new File("checkpoint");
					if(!checkptDir.exists()) {
						System.err.println("Checkpoint Directory doesn't exist");
					}
					else {
						File[] checkpoints = checkptDir.listFiles();
						if(checkpoints.length<=0) {
							System.err.println("You don't have anything to see!");
						}
						else {
							File checkpoint = new File(checkptDir.getPath()+"/checkpoint.txt");

							try {
								FileInputStream fin = new FileInputStream(checkpoint);
								ObjectInputStream oin = new ObjectInputStream(fin);
								Object obj = oin.readObject();
									if(obj!=null && obj instanceof HashMap<?,?>) {
										HashMap<String, IterableFile> HM = (HashMap<String, IterableFile>) obj;
										for(String key : HM.keySet()) {
											IterableFile file = HM.get(key);
											System.out.println(	file.getFile().getName()+"/"+
																file.getCompleted()+"/"+
																file.getLength());
										}
									}
								oin.close();
								fin.close();
							} 
							catch (IOException e) {
								e.printStackTrace();
							} 
							catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
					break;
					
			default:
					break;
		}
		sc.close();
	}
}
