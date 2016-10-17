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
			System.out.println("Create input stream ...");
			InputStream is = new FileInputStream(fileName);

			System.out.println("Create antlr input stream ...");
			// create input stream from standard input
			ANTLRInputStream input = new ANTLRInputStream(is);

			System.out.println("Create a lexer attached to that input stream ...");
			// Create a lexer attached to that input stream
			PhpLexer lexer = new PhpLexer(input);

			System.out.println("Testing tokens ...");

			Token t = lexer.nextToken();
			System.out.println(t);
			Assert.assertEquals(t.getText(), "<?php");
			Assert.assertEquals(t.getType(), 16);

			t = lexer.nextToken();
			System.out.println(t);

			t = lexer.nextToken();
			System.out.println(t);

			t = lexer.nextToken();
			System.out.println(t);

			t = lexer.nextToken();
			System.out.println(t);

			t = lexer.nextToken();
			System.out.println(t);

			t = lexer.nextToken();
			System.out.println(t);

		} catch (FileNotFoundException f) {
			File file = new File(fileName);
			String fullPath = file.getAbsolutePath();

			System.out.println("Could not find test file at " + fullPath);

		}
	}
}
