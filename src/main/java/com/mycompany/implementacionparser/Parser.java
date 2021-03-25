/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.implementacionparser;

import java.io.StringReader;

/**
 *
 * @author phily
 */
public class Parser {               
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String entrada = "3+1*3";
        StringReader lector = new StringReader(entrada);
        
        Lexer lexer = new Lexer(lector);
        Estados estados = new Estados(lexer);   
        
        estados.E();
    }           
}

