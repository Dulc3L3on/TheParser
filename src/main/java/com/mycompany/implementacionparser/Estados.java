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
public class Estados {//lo que podrías haber hecho en lugar de crear este arch.java es haber reempleado el archCup.java con este código, puesto que ya lo habí as compilado, entonces no habría problema con los símbolos xD y listo, te ahorrabas una clase xD
    private final int NUM = 6;
    private final int error = 1;
    private final int PAR_C = 3;
    private final int POR = 5;
    private final int ID = 7;    
    private final int SUMA = 4;
    private final int PAR_A = 2;        
    private final int EOF = 0;
    
    boolean huboError =false; 
    
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
        huboError = false;
        
	switch(token) {
            case ID: case NUM: case PAR_A:
                T(); Ep();
            break;
            default:
                huboError = true;
                System.out.println("No se esperaba el token: "+token);
	}
	
        if(!huboError){
            System.out.println("cadena aceptada");
            huboError= false;
        }		
	
    }

    public void Ep() {
        huboError = false;
        
	switch(token) {                        
            case PAR_C: case EOF:
		/*lambda, no hago nada*/
	    break;
	    case SUMA:
		consumir(SUMA); T(); Ep();
	    break;
	    default:
                huboError = true;
                System.out.println("No se esperaba el token: "+token);
	}
    }

    public void T() {        
        huboError = false;
        
	switch(token) {
            case ID: case NUM: case PAR_A:
		F(); Tp();
	    break;
            default:
                huboError = true;
                System.out.println("No se esperaba el token: " + token);
	}
}

    public void Tp() {
	switch(token) {
            case PAR_C: case SUMA: case EOF:
		/*lambda, no hago nada*/
            break;
            case POR:
                consumir(POR); F(); Tp();
            break;
            default:
                huboError = true;
                System.out.println("No se esperaba el token: " + token);
	}
    }

    public void F() {
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
                huboError = true;
                System.out.println("No se esperaba el token: " + token);
	}
    }             
}
