import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.apache.commons.io.FileUtils.writeStringToFile;

public class Parser {
	
	public Map<String, Integer> words = new HashMap<String, Integer>();
	
	public void getFiles() throws IOException {
		File folder = new File("./Files");
		File[] listOfFiles = folder.listFiles();
		
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        getWords(file);
		    }
		}
	}
	
	public void getWords(File f) throws IOException {
		
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

	    this.writeCsv(words);

	}

	private void writeCsv(Map<String, Integer> words) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, Integer> kvp : words.entrySet()) {
			builder.append(kvp.getKey());
			builder.append(",");
			builder.append(kvp.getValue());
			builder.append("\r\n");
		}

		String content = builder.toString().trim();
		File results = new File("./results.csv");
		writeStringToFile(results, content, "UTF-8");
	}


}
