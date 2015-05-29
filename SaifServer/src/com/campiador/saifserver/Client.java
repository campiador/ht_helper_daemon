package com.campiador.saifserver;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket connection = new Socket("127.0.0.1", 1212);
			Scanner incomingText = new Scanner(connection.getInputStream());
			while (incomingText.hasNextLine()) {
				System.out.println(incomingText.nextLine());
			}connection.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
