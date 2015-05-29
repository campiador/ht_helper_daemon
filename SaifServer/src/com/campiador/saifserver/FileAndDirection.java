package com.campiador.saifserver;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileAndDirection extends JFrame {

	public File getFileAndDirection() {
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(jfc.FILES_AND_DIRECTORIES);
		int result = jfc.showOpenDialog(this);

		if (result == jfc.CANCEL_OPTION) {
			System.exit(1);
		}
		File f = jfc.getSelectedFile();
		return f;
	}
}