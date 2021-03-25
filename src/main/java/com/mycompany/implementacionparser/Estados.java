/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.implementacionparser;

/**
 *
 * @author phily
 */
public class Estados {
    private int NUM = 6;
    private int error = 1;
    private int PAR_C = 3;
    private int POR = 5;
    private int ID = 7;    
    private int SUMA = 4;
    private int PAR_A = 2;        
    private int EOF = 0;
    private Lexer lexer;
    
    
    public Estados(Lexer elLexer){
        lexer = elLexer;    
    }
    
    int token = lexer.yystate();

    public void avanzar(){
	token = lexer.yystate();
    }

    public void consumir(int tok) {
	if (tok == token) {
		avanzar();
	} else {
		//manejo errors
            System.out.println("se esperaba: "+tok+" pero se recibió :"+token);
	}
}


    public void E() {
        boolean huboError =false;
        
	switch(token) {
		case ID: case NUM: case PAR_A:
			T(); Ep();
			break;
		default:
                    huboError = true;
                    System.out.println(token);
	}
	
        if(!huboError){
            System.out.println("cadena aceptada");
        }
		
	
    }

    public void Ep() {
	switch(token) {
		case PAR_C: case FINCADENA:
			/*lambda, no hago nada*/
			break;
		case SUMA:
			consumir(SUMA); T(); Ep();
			break;
		default:
			error(token);
	}
}

T() {
	switch(token) {
		case ID, NUM, PAR_A:
			F(); Tp();
			break;
		default:
			error(token);
	}
}

Tp() {
	switch(token) {
		case PAR_C, SUMA, FINCADENA:
			/*lambda, no hago nada*/
			break;
		case POR:
			consumir(POR); F(); Tp();
			break;
		default:
			error(token);
	}
}

F() {
	switch(token) {
		case ID:
			consumir(ID);
			break;
		case NUM:
			consumir(NUM);
			break;
		case PAR_A:
			consumir(PAR_A); E(); consumir(PAR_C);
			break;
		default:
			error(token);
	}
}
    
    
    
    
}
