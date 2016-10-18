import junit.framework.Assert;
import org.junit.Test;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;

public class TestPhpWithEchoCommand {

	@Test
	public void testReadFileAndGetTokens() throws IOException {
		// read file
		String fileName = "echo_php.php";

		try {
			System.out.println("Create input stream ...");
			InputStream is = new FileInputStream(fileName);

			System.out.println("Create antlr input stream ...");
			ANTLRInputStream input = new ANTLRInputStream(is);

			System.out.println("Create a lexer attached to that input stream ...");
			PhpLexer lexer = new PhpLexer(input);

			System.out.println("Testing tokens ... ");
			Token t = lexer.nextToken();

			System.out.println("Token 1");
			Assert.assertEquals(t.getText(), "<?php");
			Assert.assertEquals(t.getType(), PhpLexer.BodyString);

			System.out.println("Token 2");
			t = lexer.nextToken();
			Assert.assertEquals(t.getText(), "\n");
			Assert.assertEquals(t.getType(), PhpLexer.WhiteSpace);

			System.out.println("Token 3");
			t = lexer.nextToken();
			System.out.println(t);
			Assert.assertEquals(t.getText(), "echo");
			Assert.assertEquals(t.getType(), PhpLexer.ECHO);

			System.out.println("Token 4");
			t = lexer.nextToken();
			Assert.assertEquals(t.getText(), " ");
			Assert.assertEquals(t.getType(), PhpLexer.WhiteSpace);

			System.out.println("Token 5");
			t = lexer.nextToken();
			Assert.assertEquals(t.getText(), "\"Hallo\"");
			Assert.assertEquals(t.getType(), PhpLexer.DoubleQuotedString);

			System.out.println("Token 6");
			t = lexer.nextToken();
			Assert.assertEquals(t.getText(), ";");
			Assert.assertEquals(t.getType(), PhpLexer.SEMICOLON);

			System.out.println("Token 7");
			t = lexer.nextToken();
			Assert.assertEquals(t.getText(), "\n");
			Assert.assertEquals(t.getType(), PhpLexer.WhiteSpace);

			System.out.println("Token 8");
			t = lexer.nextToken();
			System.out.println(t);
			Assert.assertEquals(t.getText(), "<EOF>");
			Assert.assertEquals(t.getType(), PhpLexer.EOF);
		} catch (FileNotFoundException e) {
			File file = new File(fileName);
			String fullPath = file.getAbsolutePath();

			System.out.println("Could not find test file at " + fullPath);
		}
	}

	@Test
	public void testReadFileAndTestTree() {

	}
}
