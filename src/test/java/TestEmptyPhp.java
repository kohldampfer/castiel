import junit.framework.Assert;
import org.junit.Test;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;

public class TestEmptyPhp {
	@Test
	public void testReadFileAndGetTokens() throws IOException {
		// read file
		String fileName = "empty_php.php";

		try {
			System.out.println("Create input stream ...");
			InputStream is = new FileInputStream(fileName);

			System.out.println("Create antlr input stream ...");
			ANTLRInputStream input = new ANTLRInputStream(is);

			System.out.println("Create a lexer attached to that input stream ...");
			PhpLexer lexer = new PhpLexer(input);

			System.out.println("Testing tokens ...");
			Token t = lexer.nextToken();

			System.out.println(t);
			Assert.assertEquals(t.getText(), "<?php");
			Assert.assertEquals(t.getType(), PhpLexer.BodyString);

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
	public void testReadFileAndTestTree() throws IOException, org.antlr.runtime.RecognitionException {
		String fileName = "empty_php.php";

		try {
			System.out.println(" --- testReadFileAndTestTree ---");
			InputStream is = new FileInputStream(fileName);
			ANTLRInputStream input = new ANTLRInputStream(is);
			PhpLexer lexer = new PhpLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			PhpParser parser = new PhpParser(tokens);
			RuleReturnScope r = parser.compilationUnit();

			System.out.println("Test root of tree.");
			CommonTree tree = (CommonTree)r.getTree();
			System.out.println(tree.toString());
			Assert.assertEquals("<?php", tree.toString());
			Assert.assertEquals(0, tree.getChildCount());

		} catch (FileNotFoundException e) {
			File file = new File(fileName);
			String fullPath = file.getAbsolutePath();

			System.out.println("Could not find test file at " + fullPath);
		}
	}
}
