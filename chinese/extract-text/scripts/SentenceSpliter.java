import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class SentenceSpliter {
	
	private boolean inEngSingleQuoteMark = false;
	private boolean inEngDoubleQuotationMarks = false;
	
	private boolean allow(char c, char last) {
		if (c == '\'' && inEngSingleQuoteMark)
			return false;
		else if (c == '"' && inEngDoubleQuotationMarks)
			return false;
		else if (isChineseChar(c) || isLetterOrDigit(c))
			return false;
		else if (c == '“' || c == '‘' || c == '(' || c == '[' || c == '{'
			|| c == '（' || c == '｛'|| c == '［')
			return false;
		else
			return true;
	}
	
	/**
	 * Extract sentences from the given string.
	 * 
	 * @param paragraph
	 *            A string that to be splitted. It shouldn't be null.
	 * @return An ArrayList contains the extracted sentences.
	 */
	public List<String> extractSentences(String paragraph) {
		ArrayList<String> sentences = new ArrayList<String>();
		inEngSingleQuoteMark = false;
		inEngDoubleQuotationMarks = false;
		boolean blanket = false;
		int pos = 0;
		int prepos = 0;
		while (pos < paragraph.length()) {
			char curChar = paragraph.charAt(pos);
			if (curChar == '\'' || curChar == '`')
				inEngSingleQuoteMark = !inEngSingleQuoteMark;
			if (curChar == '"')
				inEngDoubleQuotationMarks = !inEngDoubleQuotationMarks;
			
			if (curChar == '(' || curChar == '（') {
				blanket = true;
				pos++;
				continue;
			}

			if (curChar == ')' || curChar == '）') {
				blanket = false;
				pos++;
				continue;
			}

			if (blanket == true) {
				if (curChar == '。'
						|| curChar == '！'
						|| curChar == '？'
						|| curChar == '…'
						|| curChar == '.'
						|| curChar == '!'
						|| curChar == '?'){
					blanket = false;
				} else {
					pos++;
					continue;
				}
			}

			if (pos < paragraph.length() - 2) {
				char nextChar = paragraph.charAt(pos + 1);
				char afterNextChar = paragraph.charAt(pos + 2);
				char curChar_1 = 0;
				char curChar_2 = 0;
				char curChar_3 = 0;
				if (pos > 0) {
					curChar_1 = paragraph.charAt(pos - 1);
				}
				if (pos > 1) {
					curChar_2 = paragraph.charAt(pos - 2);
				}
				if (pos > 2) {
					curChar_3 = paragraph.charAt(pos - 3);
				}

				if (curChar == '。' || curChar == '！' || curChar == '？'
						|| curChar == '…' && nextChar == '…') {
					prepos = pos = addSentences(sentences, prepos, pos, paragraph);
				} else if (isChineseChar(paragraph.charAt(pos))) {
					pos++;
				} else {
					if (curChar == '.' || curChar == '!' || curChar == '?') {
						if ((curChar == '.' && (isDigit(nextChar) || isLower(nextChar)))
								|| (curChar == '.' && nextChar == ' ' &&  isDigit(afterNextChar))
								|| (curChar == '.' && curChar_3 == ';'
										&& curChar_2 == ' ' && isDigit(curChar_1))
								|| (curChar == '.' && curChar_2 == 'N' && curChar_1 == 'o')
								|| (curChar == '.' && curChar_2 == 'M' && curChar_1 == 'r')
								|| (curChar == '.' && curChar_2 == ' ' && (curChar_1 != 'I' && isUpper(curChar_1)))
								|| (curChar_2 == '.' && isAlpha(curChar_1))
								|| ((curChar == ':') && nextChar != ' ')) {
							pos++;
						} else {
							prepos = pos = addSentences(sentences, prepos, pos, paragraph);
						}

					} else {
						pos++;
					}
				}
			} else if (paragraph.charAt(pos) == '.'
					|| paragraph.charAt(pos) == '!'
					|| paragraph.charAt(pos) == '?') {
				prepos = pos = addSentences(sentences, prepos, pos, paragraph);
			} else {
				pos++;
			}
		}

		if (pos > 0) {
			prepos = pos = addSentences(sentences, prepos, pos, paragraph);
		}
		return sentences;
	}
	
	private int addSentences(List<String> list, int prepos, int pos, String paragraph) {
		char last = '\0';
		if (pos != 0)
			last = paragraph.charAt(pos-1);
		while(pos < paragraph.length() && allow(paragraph.charAt(pos), last)) {
			last = paragraph.charAt(pos);
			pos++;
			if (pos < paragraph.length()) {
				char curChar = paragraph.charAt(pos);
				if (curChar == '\'' || curChar == '`')
					inEngSingleQuoteMark = !inEngSingleQuoteMark;
				if (curChar == '"')
					inEngDoubleQuotationMarks = !inEngDoubleQuotationMarks;
			}
		}
		if (pos < paragraph.length()) {
			char curChar = paragraph.charAt(pos);
			if (curChar == '\'' || curChar == '`')
				inEngSingleQuoteMark = !inEngSingleQuoteMark;
			if (curChar == '"')
				inEngDoubleQuotationMarks = !inEngDoubleQuotationMarks;			
		}
		
		String line = paragraph.substring(prepos, pos);
		line = removeExtraSpaces(line).trim();
		if (line.length() != 0)
			list.add(line);
		return pos;
	}
	
	private boolean isLetterOrDigit(char c) {
		return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
				|| (c >= '0' && c <= '9') || (c >= '０' && c <= '９'));
	}
	
	private boolean isChineseChar(char c) {
		return (c >= '\u4E00' && c <= '\u9FA5');
	}

	private boolean isDigit( char c ) {
		return (c >= '0' && c <= '9')
					|| (c >= '０' && c <= '９');
	}
	
	private boolean isLower(char c) {
		if (c >= 'a' && c <= 'z')
			return true;
		else
			return false;
	}
	
	private boolean isAlpha(char c) {
		if (isLower(c) || isUpper(c))
			return true;
		else
			return false;
	}
	
	private boolean isUpper(char c) {
		if (c >= 'A' && c <= 'Z')
			return true;
		else
			return false;
	}

    private String removeExtraSpaces( String text ) {
        text = text.replaceAll("　", " ");
        text = text.replaceAll("[ \t\u000B\u000C\u00A0\uE5F1]+", " ");
        text = text.replaceAll("(^ +)|( +$)", "");
        return text;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            System.err.println("Usage: java org.thunlp.text.SentenceSpliter");
            System.err.println("       The program reader data from stdin");
            System.exit(-1);
        }

        SentenceSpliter spliter = new SentenceSpliter();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String paragraph;
        while((paragraph = br.readLine()) != null) {
			for (String line : spliter.extractSentences(paragraph))
				System.out.println(line);
		}
		br.close();
	}
}
