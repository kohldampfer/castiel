import junit.framework.Assert;
import org.junit.Test;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;
import java.util.*;

public class TestPhpCommonTokenStream1 {
	
	@Test
	public void testReadFileAndGetTokenStream() throws IOException {
		// read file
		String fileName = "filter.php";

		try {
			System.out.println("Create input stream ... ");
			InputStream is = new FileInputStream(fileName);

			// create input stream from standard input
			System.out.println("Create antlr input stream ...");
			ANTLRInputStream input = new ANTLRInputStream(is);

			// create a lexer attachted to that input stream
			System.out.println("Create a lexer attached to that input stream ...");
			PhpLexer lexer = new PhpLexer(input);

			// create a stream of tokens pulled from the lexer
			System.out.println("create a stream of tokens pulled from the lexer ...");
			CommonTokenStream tokens = new CommonTokenStream(lexer);


			tokens.consume();

			// System.out.println(tokens.size());
			// System.out.println(tokens.index());
			System.out.println(tokens.get(0));
			Assert.assertEquals(tokens.getTokens().get(0).getText(), "<?php");
			Assert.assertEquals(tokens.getTokens().get(0).getType(), PhpLexer.BodyString);

			tokens.consume();
			System.out.println(tokens.get(1));
			Assert.assertEquals(tokens.getTokens().get(1).getText(), "\n");
			Assert.assertEquals(tokens.getTokens().get(1).getType(), PhpLexer.WhiteSpace);

			tokens.consume();
			System.out.println(tokens.get(2));
			Assert.assertEquals(tokens.getTokens().get(2).getText(), "if");
			Assert.assertEquals(tokens.getTokens().get(2).getType(), PhpLexer.IF);

			tokens.consume();
			System.out.println(tokens.get(3));
			Assert.assertEquals(tokens.getTokens().get(3).getText(), " ");
			Assert.assertEquals(tokens.getTokens().get(3).getType(), PhpLexer.WhiteSpace);

			tokens.consume();
			System.out.println(tokens.get(4));
			Assert.assertEquals(tokens.getTokens().get(4).getText(), "(");
			Assert.assertEquals(tokens.getTokens().get(4).getType(), PhpLexer.OPEN_BRACE);

			tokens.consume();
			System.out.println(tokens.get(5));
			Assert.assertEquals(tokens.getTokens().get(5).getText(), " ");
			Assert.assertEquals(tokens.getTokens().get(5).getType(), PhpLexer.WhiteSpace);

			tokens.consume();
			System.out.println(tokens.get(6));
			Assert.assertEquals(tokens.getTokens().get(6).getText(), "$");
			Assert.assertEquals(tokens.getTokens().get(6).getType(), PhpLexer.DOLLAR);

			tokens.consume();
			System.out.println(tokens.get(7));
			Assert.assertEquals(tokens.getTokens().get(7).getText(), "is_pro");
			Assert.assertEquals(tokens.getTokens().get(7).getType(), PhpLexer.UnquotedString);

			tokens.consume();
			System.out.println(tokens.get(8));
			Assert.assertEquals(tokens.getTokens().get(8).getText(), " ");
			Assert.assertEquals(tokens.getTokens().get(8).getType(), PhpLexer.WhiteSpace);

			tokens.consume();
			System.out.println(tokens.get(9));
			Assert.assertEquals(tokens.getTokens().get(9).getText(), ")");
			Assert.assertEquals(tokens.getTokens().get(9).getType(), PhpLexer.CLOSE_BRACE);

			tokens.consume();
			System.out.println(tokens.get(10));
			Assert.assertEquals(tokens.getTokens().get(10).getText(), " ");
			Assert.assertEquals(tokens.getTokens().get(10).getType(), PhpLexer.WhiteSpace);

			tokens.consume();
			System.out.println(tokens.get(11));

			tokens.consume();
			System.out.println(tokens.get(12));

			tokens.consume();
			System.out.println(tokens.get(13));

			tokens.consume();
			System.out.println(tokens.get(14));

		} catch (FileNotFoundException f) {
			File file = new File(fileName);
			String fullPath = file.getAbsolutePath();

			System.out.println("Could not find test file at " + fullPath);
		}
	}
}
