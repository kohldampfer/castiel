import junit.framework.Assert;
import org.junit.Test;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.*;

public class TestSimpleIfElsePhp {

	@Test
	public void testReadFileAndCheckTree() throws IOException,FileNotFoundException,
		org.antlr.runtime.RecognitionException {

		String fileName = "simple_if_else_php.php";

		InputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);
		PhpLexer lexer = new PhpLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		PhpParser parser = new PhpParser(tokens);

		RuleReturnScope r = parser.compilationUnit();

		CommonTree tree = (CommonTree)r.getTree();
		Assert.assertEquals(2, tree.getChildCount());

		CommonTree child1 = (CommonTree)tree.getChild(0);
		Assert.assertEquals(0, child1.getChildCount());
		Assert.assertEquals("<?php", child1.toString());

		CommonTree child2 = (CommonTree)tree.getChild(1);
		Assert.assertEquals(3, child2.getChildCount());
		Assert.assertEquals("if", child2.toString());

		CommonTree child1fromchild2 = (CommonTree)child2.getChild(0);
		Assert.assertEquals(1, child1fromchild2.getChildCount());
		Assert.assertEquals("$", child1fromchild2.toString());

		CommonTree child2fromchild2 = (CommonTree)child2.getChild(1);
		Assert.assertEquals(1, child2fromchild2.getChildCount());
		Assert.assertEquals("echo", child2fromchild2.toString());

		CommonTree child3fromchild2 = (CommonTree)child2.getChild(2);
		Assert.assertEquals(1, child3fromchild2.getChildCount());
		Assert.assertEquals("else", child3fromchild2.toString());

		CommonTree child1fromchild3 = (CommonTree)child3fromchild2.getChild(0);
		Assert.assertEquals(1, child1fromchild3.getChildCount());
		Assert.assertEquals("echo", child1fromchild3.toString());

		CommonTree child2fromchild3 = (CommonTree)child1fromchild3.getChild(0);
		Assert.assertEquals(0, child2fromchild3.getChildCount());
		Assert.assertEquals("\"no pro\"", child2fromchild3.toString());

	}

}
