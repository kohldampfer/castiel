import junit.framework.Assert; import org.junit.Test; import java.io.*; import org.homeunix.wap.php.parser.*; import org.antlr.runtime.*; import 
org.antlr.runtime.tree.*; import java.util.*; public class TestNotEndingPhpFile1 {
	@Test
	public void testReadFileAndCheckTree() throws IOException,FileNotFoundException,
		org.antlr.runtime.RecognitionException {
		
		String fileName = "filter1.php";
		InputStream is = new FileInputStream(fileName);
		ANTLRInputStream input = new ANTLRInputStream(is);
		PhpLexer lexer = new PhpLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		PhpParser parser = new PhpParser(tokens);
		RuleReturnScope r = parser.compilationUnit();

		CommonTree tree = (CommonTree)r.getTree();
		Assert.assertEquals(2, tree.getChildCount());

		CommonTree child1 = (CommonTree)tree.getChild(0);
		Assert.assertEquals("<?php", child1.toString());
		Assert.assertEquals(0, child1.getChildCount());

		CommonTree child2 = (CommonTree)tree.getChild(1);
		Assert.assertEquals(0, child2.getChildCount());
		Assert.assertEquals("`cat \"$build_dir/filter-all\" \"$build_dir/filter-$edition\" > \"$filter_file\"`;\n", child2.toString());
	}
}
