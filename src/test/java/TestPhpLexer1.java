import junit.framework.Assert;
import org.junit.Test;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;

public class TestPhpLexer1 {
	
	@Test
	public void testReadFileAndGetLexerStream() throws IOException {
		// read file
		String fileName = "filter.php";

		try {
			InputStream is = new FileInputStream(fileName);

			// create input stream from standard input
			ANTLRInputStream input = new ANTLRInputStream(is);
			// Create a lexer attached to that input stream
			PhpLexer lexer = new PhpLexer(input);
		} catch (FileNotFoundException f) {
			File file = new File(fileName);
			String fullPath = file.getAbsolutePath();

			System.out.println("Could not find test file at " + fullPath);

		}
	}
}
