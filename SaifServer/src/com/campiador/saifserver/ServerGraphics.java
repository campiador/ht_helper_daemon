package com.campiador.saifserver;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;

public class ServerGraphics extends JFrame{
	
	public interface ISG{
		
		void onConnectClick();
		void onSelectClick();
		boolean isConnected();
		
	}
	public ServerGraphics(final ISG control) {
		setLayout(new FlowLayout());
		try {
			add(new Label("SERVER ADDRESS: " + InetAddress.getLocalHost().toString()));
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Button selectButton = new Button("SELECT FILE");
		selectButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				control.onSelectClick();
			}
		});
		final Button connectButton = new Button("START");
		connectButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				control.onConnectClick();
				if (control.isConnected()) {
					
					connectButton.setLabel("FINISH");
				}
				else{
					connectButton.setLabel("START");
				}
			}
		});
		add(selectButton);
		
		add(connectButton);
	}
	
}
