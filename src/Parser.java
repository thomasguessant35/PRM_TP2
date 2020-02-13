import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.apache.commons.io.FileUtils.writeStringToFile;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Parser {

	public Map<String, Integer> dictionnary = new HashMap<String, Integer>();

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

	public void getWords(File f) throws IOException {
		if(f.getName().contains("pdf")) {
			PDDocument document = PDDocument.load(f);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			String splitChar = "([^a-zA-Z']+)'*\\1*";
			text = text.replaceAll("\\.", "");
			text = text.replaceAll("/", splitChar);
			text = text.replaceAll("\\)", splitChar);
			text = text.replaceAll("\\(", splitChar);
			text = text.replaceAll(",", "");
			String[] words = text.split(splitChar);

			for(String word:  words) {
				if(dictionnary.containsKey(word)) {
					dictionnary.put(word, dictionnary.get(word)+1);
				}else {
					dictionnary.put(word, 1);
				}
			}

			document.close();
		}

		this.writeCsv(dictionnary);
	}

	private void writeCsv(Map<String, Integer> dictionnary) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, Integer> kvp : dictionnary.entrySet()) {
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