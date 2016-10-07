/*
 * Class that build the AST and make walker tree to SQLI
 */

package org.homeunix.wap.utils;

import org.homeunix.wap.php.parser.PhpParser;
import org.homeunix.wap.php.parser.PhpLexer;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;

/**
 *
 * @author Iberia Medeiros
 */
public class buildAST {
    private CommonTreeNodeStream nodes = null;
    private Boolean validFile = false;
    private String finalFile;
    
    
    // *** CONSTRUCTORS
//    public buildAST(String filename, SymbolTable st, List files, int fileVerified) throws IOException{
    public buildAST(String filename, int fileVerified) throws IOException {
        String final_file;
        Boolean encontrei = false;
        Boolean valid_file = false;

        if (fileVerified == 0){ // somente para ficheiros de include/require
            ManageFiles ff = new ManageFiles(filename);
            final_file = ff.composePathFile(filename, ff.getBaseDir());
            ManageFiles fff = new ManageFiles(final_file);
            if (fff.existFile(final_file)){
                encontrei = true;
                valid_file = true;
            }           
        }
        else {// if (verified == 1)
            final_file = filename;
            valid_file = true;
            this.validFile = true;
            this.finalFile = final_file;
        }

        if (valid_file == true){
        try {
            InputStream is = new FileInputStream(final_file);
       
            // Create input stream from standard input
            ANTLRInputStream input = new ANTLRInputStream(is);
            System.out.println(" --> Create input stream from standard input");
            // Create a lexer attached to that input stream
            PhpLexer lexer = new PhpLexer(input);
            System.out.println(" --> Create a lexer attached to that input stream.");
            // Create a stream of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            System.out.println(" --> Create a stream of tokens pulled from the lexer.");

            // Create a parser attached to the token stream
            PhpParser parser = new PhpParser(tokens);
            RuleReturnScope r = parser.compilationUnit();   // launch parser
            System.out.println(" --> Create a parser attached to the token  stream.");

            // Get AST from result of parser
            CommonTree t = (CommonTree)r.getTree();
            System.out.println(" --> Get AST from result of parser.");
            //CommonTreeNodeStream nodes = new CommonTreeNodeStream(t);
            this.nodes = new CommonTreeNodeStream(t);
            nodes.setTokenStream(tokens);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + final_file);
        } catch (Exception e) {
            System.err.println("Error parsing " + final_file);
            System.err.println(e.getMessage());
        }
        }

    }
    
    public CommonTreeNodeStream getNodes(){
        return this.nodes;
    }
    
    public Boolean getValidFile(){
        return this.validFile;
    }
    
    public String getFinalFile(){
        return this.finalFile;
    }

}
