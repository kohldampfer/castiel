import junit.framework.Assert;
import org.junit.Test;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;

public class TestFilter2 {

	@Test
	public void testReadFileAndCheckTree() throws IOException,FileNotFoundException,
		org.antlr.runtime.RecognitionException {

		String fileName = "filter2.php";

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

		// Check 3. child of tree
		CommonTree child3 = (CommonTree)tree.getChild(2);
		Assert.assertEquals(2, child3.getChildCount());
		Assert.assertEquals("=", child3.toString());

		// Check first child of 3. child
		CommonTree child1fromchild3 = (CommonTree)child3.getChild(0);
		Assert.assertEquals(1, child1fromchild3.getChildCount());
		Assert.assertEquals("$", child1fromchild3.toString());

		CommonTree child1fromchild1fromchild3 = (CommonTree)child1fromchild3.getChild(0);
		Assert.assertEquals(0, child1fromchild1fromchild3.getChildCount());
		Assert.assertEquals("filter_file", child1fromchild1fromchild3.toString());

		// Check second child for 3. child
		CommonTree child2fromchild3 = (CommonTree)child3.getChild(1);
		Assert.assertEquals(0, child2fromchild3.getChildCount());
		Assert.assertEquals("\"$tmp_dir/filter\"", child2fromchild3.toString());

	}

}
