import java.util.*;

public class GrammarSolver {

    //This method will be passed a grammar as a list of strings. Your method should store this in
    //a convenient way so that you can later generate parts of thegrammar. It should throw an
    //IllegalArgumentException if the grammar is empty orif there are two or more entries in the 
    //grammar for thesame nonterminal. Your method is not to change thelist of strings.
    GrammarSolver(List<String> grammar) {

    }

    //Returns true if the given symbol is a nonterminal of the grammar; returns false otherwise.
    //Param: String symbol:
    //Return Boolean:
    public boolean grammarContains(String symbol) {

    }

    //In this method you should use the grammar to randomly generate the given number of 
    //occurrences ofthe given symbol and you should return the result as anarray of strings. 
    //For any given nonterminal symbol,each of its rules should be applied with equaprobability. 
    //It should throw anIllegalArgumentException if the grammar does notcontain the given 
    //nonterminal symbol or if the numberof times is less than 0.
    //Param: String Symbol
    //int times
    //Return String[]:
    public String[] generate(String symbol, int times) {

    }

    //This method should return a string representation of the various nonterminal symbols from 
    //the grammar as asorted, comma-separated list enclosed in squarebrackets, as in 
    //“[<np>, <s>, <vp>]”
    //Return String:
    public String getSymbols() {

    }
}
