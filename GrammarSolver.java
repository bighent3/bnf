//Henton Hailey-Marshall
//CSE 143 AA with Ido Avnon
//Homework A6: Grammar Solver
import java.util.*;

import java.util.*;
import java.io.*;

import java.io.*;
import java.util.*;
// Core JavaFX application classes
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

// UI controls
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

// Layout panes
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

// Event handling
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

// FXML related classes
import javafx.fxml.FXMLLoader;

// Graphics and media
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

// Geometry and positioning
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//This class creates a grammar solver, it takes a list from a text file, and user input to
//to randomly create sentences following an organization structure of nonterminals separated
//by a unique symbol from rules that are further segmented. 
public class GrammarSolver {
    private final Map<String, List<String[]>> rulesTerm;
    private final Random elementSelect;

    //This method will be passed a grammar as a list of strings. Your method should store this in
    //a convenient way so that you can later generate parts of thegrammar. It should throw an
    //IllegalArgumentException if the grammar is empty orif there are two or more entries in the 
    //grammar for thesame nonterminal. Your method is not to change thelist of strings.
    //Constructor accepts: grammar, list of grammars
    //Throws illegal argument exceptiion if grammar is empty, not in terminal :: rule format or
    //identical terminals
    //Post: creates Map of non terminals and corresponding rules
    GrammarSolver(List<String> grammar) {
        if (grammar.isEmpty()) {
            throw new IllegalArgumentException("Grammar is empty");
        }
       
        this.rulesTerm = new TreeMap<>();
        this.elementSelect = new Random();

        for (String rule : grammar) {
            String[] segment = rule.split("::=");
            if (segment.length != 2) {
                throw new IllegalArgumentException("Must be in non terminal ::= rule format");
            }
            String nonterminal = segment[0].trim();
            String[] rules = segment[1].trim().split("[|]");

            if (rulesTerm.containsKey(nonterminal)) {
                throw new IllegalArgumentException("Same nonterminal: " + nonterminal);
            }

            List<String[]> ruleValues = new ArrayList<>();
            for (String law : rules) {
                ruleValues.add(law.trim().split("\\s+"));
            }

            rulesTerm.put(nonterminal, ruleValues);
        }
    }

    //Returns true if the given symbol is a nonterminal of the grammar; returns false otherwise.
    //Param: String symbol
    //Return Boolean True if symbol is map, false if not present
    //Post: Checks for nonterminal presence in grammar
    public boolean grammarContains(String symbol) {
        return rulesTerm.containsKey(symbol);
    }

    //In this method you should use the grammar to randomly generate the given number of 
    //occurrences ofthe given symbol and you should return the result as anarray of strings. 
    //For any given nonterminal symbol,each of its rules should be applied with equaprobability. 
    //It should throw anIllegalArgumentException if the grammar does notcontain the given 
    //nonterminal symbol or if the numberof times is less than 0.
    //Param: String Symbol
    //int times: user given value
    //Return String[]: size = number of sentences wanted by user
    //Post generates the number of sentences wanted by user
    public String[] generate(String symbol, int times) {
        if (!grammarContains(symbol)) {
            throw new IllegalArgumentException("Symbol not found: " + symbol);
        }
        if (times < 0) {
            throw new IllegalArgumentException("Times cannot be less than 0");
        }

        String[] symbolArray = new String[times];
        for (int i = 0; i < times; i++) {
            symbolArray[i] = generateSymbol(symbol).trim();
        }
        return symbolArray;
    }

    //Public private pair called by number of sentences wanted by user
    //accept number of sentences wanted by user. 
    //returns string given if not present in grammar. otherwise returns concatenated sentence
    private String generateSymbol(String symbol) {
        if (!grammarContains(symbol)) {
            return symbol;
        }

        List<String[]> ruleList = rulesTerm.get(symbol);
        String[] ruleChosen = ruleList.get(elementSelect.nextInt(ruleList.size()));

        String product = "";
        for (String segment : ruleChosen) {
            if(!product.isEmpty()) {
                product += " ";
            }
            product += generateSymbol(segment);
        }
        return product;
    }

    //This method should return a string representation of the various nonterminal symbols from 
    //the grammar as asorted, comma-separated list enclosed in squarebrackets, as in 
    //“[<np>, <s>, <vp>]”
    //Return String: tree keys in order to form sentence
    public String getSymbols() {
        return rulesTerm.keySet().toString();
    }
}
