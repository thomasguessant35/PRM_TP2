import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Parser {
	
	public HashMap<String, Integer> dictionnary = new HashMap<String, Integer>();
	
	public void getFiles() {
		File folder = new File("./Files");
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        try {
					getWords(file);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
	}
	
	public void getWords(File f) throws IOException{
		if(f.getName().contains("pdf")) {
			PDDocument document = PDDocument.load(f);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			text.replace(".", "");
			text.replace(",", "");
			String[] words = text.split(" ");
			
			for(String word:  words) {
				if(dictionnary.containsKey(word)) {
	            	dictionnary.put(word, dictionnary.get(word)+1);
	            }else {
	            	dictionnary.put(word, 1);
	            }
			}
			
		    document.close();
		}
	    
	    System.out.println(dictionnary);
	}
	
	
}
