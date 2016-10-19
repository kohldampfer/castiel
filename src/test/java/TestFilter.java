import junit.framework.Assert; 
import org.junit.Test; 
import java.io.*; 
import org.homeunix.wap.php.parser.*; 
import org.antlr.runtime.*; 
import org.antlr.runtime.tree.*; 
import java.util.*;

 
public class TestFilter {
	@Test
	public void testReadFileAndCheckTree() throws IOException,FileNotFoundException,
		org.antlr.runtime.RecognitionException {

		String fileName = "filter.php";

		InputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);
		PhpLexer lexer = new PhpLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PhpParser parser = new PhpParser(tokens);
		RuleReturnScope r = parser.compilationUnit();

		CommonTree tree = (CommonTree)r.getTree();
		Assert.assertEquals(3, tree.getChildCount());

		CommonTree child1 = (CommonTree)tree.getChild(0);
		Assert.assertEquals(0, child1.getChildCount());
		Assert.assertEquals("<?php", child1.toString());
	}
}
