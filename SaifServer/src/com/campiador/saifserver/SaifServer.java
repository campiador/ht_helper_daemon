package com.campiador.saifserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import com.campiador.saifserver.ServerGraphics.ISG;

public class SaifServer {
	
	private static final int SERVER_PORT = 1212;
	private static File selectedFile;
	private static boolean isFileSelected = false;
	
	private static Socket connectionSocket;
	private static ServerSocket welcomingSocket;
	protected static boolean isConnected = false;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		try {
			ISG controller = new ISG() {
				
				@Override
				public void onSelectClick() {
					selectFile();
				}
				
				@Override
				public void onConnectClick() {
					if (isFileSelected) {
						if (!isConnected) {
							isConnected = !isConnected;
							try {
								startConnection(selectedFile);
								closeConnection();
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							try {
								closeConnection();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						
					}
					
				}

				@Override
				public boolean isConnected() {
					return isConnected;
				}
			};
			ServerGraphics sg = new ServerGraphics(controller);
			sg.setSize(300,100);
			sg.setTitle("Saif Sender");
			sg.setLocationRelativeTo(null);
			sg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			sg.setVisible(true);
//			selectFile();
//			startConnection(selectedFile);
//			closeConnection();
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
	}

	private static File selectFile() {
		isFileSelected = true;
		return selectedFile = new FileAndDirection().getFileAndDirection();
	}

	private static void closeConnection() throws IOException {
		
		welcomingSocket.close();
		connectionSocket.close();
	}

	private static void startConnection(File file) throws IOException,
			FileNotFoundException {
		welcomingSocket = new ServerSocket(SERVER_PORT);
		connectionSocket = welcomingSocket.accept();
		
		Scanner inputFile = new Scanner(file);
		PrintStream ps = new PrintStream(connectionSocket.getOutputStream());
		while (inputFile.hasNextLine()) {
			String line = inputFile.nextLine();
			if(!line.equals("")){
				ps.println(line);
				ps.flush();
			}
			
		}
		inputFile.close();
	}

}
