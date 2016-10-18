import junit.framework.Assert;
import org.junit.Test;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;
import java.util.*;

public class TestPhpCommonTokenStream2 {
	
	@Test
	public void testReadFileAndGetTokenStream() throws IOException {
		// read file
		String fileName = "filter1.php";

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
			Assert.assertEquals(tokens.getTokens().get(1).getText(), "\n\n");
			Assert.assertEquals(tokens.getTokens().get(1).getType(), PhpLexer.WhiteSpace);

			tokens.consume();
			System.out.println(tokens.get(2));
			Assert.assertEquals(tokens.getTokens().get(2).getText(), "`cat \"$build_dir/filter-all\" \"$build_dir/filter-$edition\" > \"$filter_file\"`;\n");
			Assert.assertEquals(tokens.getTokens().get(2).getType(), PhpLexer.PhpStatement);

		} catch (FileNotFoundException f) {
			File file = new File(fileName);
			String fullPath = file.getAbsolutePath();

			System.out.println("Could not find test file at " + fullPath);
		}
	}
}
