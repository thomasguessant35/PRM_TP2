import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Parser {
	
	public HashMap<String, Integer> words = new HashMap<String, Integer>();
	
	public void getFiles() {
		File folder = new File("./Files");
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        getWords(file);
		    }
		}
	}
	
	public void getWords(File f) {
		
		Scanner sc = null;
		try {
	        sc = new Scanner(f);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	    while (sc.hasNextLine()) {
	            Scanner s2 = new Scanner(sc.nextLine());
	        while (s2.hasNext()) {
	            String s = s2.next();
	            if(words.containsKey(s)) {
	            	words.put(s, words.get(s)+1);
	            }else {
	            	words.put(s, 1);
	            }
	        }
	    }
	    
	    //System.out.println(words);
	}
	
	
}
