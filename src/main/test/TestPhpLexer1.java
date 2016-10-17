import junit.framework.*;
import org.junit.*;
import java.io.*;
import org.homeunix.wap.php.parser.*;
import org.antlr.runtime.*;

public class TestPhpLexer1 {
	
	@Test
	public void testReadFileAndGetLexerStream() {
		// read file
		String fileName = "filter.php";
		InputStream is = new FileInputStream(fileName);
		
		// create input stream from standard input
		ANTLRInputStream input = new ANTLRInputStream(is);
		// Create a lexer attached to that input stream
		PhpLexer lexer = new PhpLexer(input);
	}
}