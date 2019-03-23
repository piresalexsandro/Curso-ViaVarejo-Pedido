package br.com.vvcurso.application;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileCopy {

	public static void main(String[] args) {

		File doArquivo = new File("C:\\eclipse-workspace\\temp\\file.txt");
		File paraArquivo = new File("C:\\eclipse-workspace\\temp\\copied.txt");
			
		try {
			FileUtils.copyFile(doArquivo, paraArquivo);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
