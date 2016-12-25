
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * This is a very simple demo of calling the Chinese Word Segmenter
 * programmatically. It assumes an input file in UTF8.
 * <p/>
 * <code>
 *  Usage: java -mx1g -cp seg.jar SegDemo fileName
 *  </code> This will run correctly in the distribution home directory. To run
 * in general, the properties for where to find dictionaries or normalizations
 * have to be set.
 *
 * @author Christopher Manning
 */

public class SegDemo {

	private static final String basedir = System.getProperty("SegDemo", "data");

	private List<String> getDirectory(File file) {
		File flist[] = file.listFiles();
		if (flist == null || flist.length == 0) {
			return null;
		}
		List<String> file_names = new ArrayList<String>();
		for (File f : flist) {
			file_names.add(f.getName());
		}
		return file_names;
	}

	public static void main(String[] args) throws Exception {
		System.setOut(new PrintStream(System.out, true, "utf-8"));

		Properties props = new Properties();
		props.setProperty("sighanCorporaDict", basedir);
		// props.setProperty("NormalizationTable", "data/norm.simp.utf8");
		// props.setProperty("normTableEncoding", "UTF-8");
		// below is needed because CTBSegDocumentIteratorFactory accesses it
		props.setProperty("serDictionary", basedir + "/dict-chris6.ser.gz");
		props.setProperty("inputEncoding", "UTF-8");
		props.setProperty("sighanPostProcessing", "true");

		CRFClassifier<CoreLabel> segmenter = new CRFClassifier<>(props);
		segmenter.loadClassifierNoExceptions(basedir + "/ctb.gz", props);
		File inputDir = new File(args[0]);
		File outputDir = new File(args[1]);
		SegDemo s = new SegDemo();
		List<String> file_list = s.getDirectory(inputDir);

		for (String file_name : file_list) {
			String input_file_name = inputDir.getAbsolutePath() + "/" + file_name;
            int pos = file_name.indexOf(".");
            String output_file_name = outputDir.getAbsolutePath() + "/" + file_name.substring(0,pos) + ".seg";
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(input_file_name)));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output_file_name)));
            String sentence = null;
            System.out.println("seg file :" + input_file_name);
            System.out.println("write to :" + output_file_name);
			List<List<String>> result = new ArrayList<List<String>>();
            while ((sentence = reader.readLine()) != null) {  
            	List<String> words = segmenter.segmentString(sentence);
				result.add(words);
				/*
				for(String word: words)
					writer.write(word + " ");
				writer.newLine();
				*/
            }  
			for(List<String> sent: result)
			{
				for(String word: sent)
					writer.write(word + " ");
				writer.newLine();
			}
			reader.close();
			writer.close();
		}
	}
}
