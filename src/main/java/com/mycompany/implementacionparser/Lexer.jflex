/*Primer sección: imports*/
package com.mycompany.implementacionparser;
import java_cup.runtime.*;

%%
/*Segunda sección: configuracion*/
%class Lexer
%cup
%unicode
%line
%column
%public

numero = [0-9]
letra = [a-zA-Z]

%%
/*Tercer sección: reglas léxicas*/
<YYINITIAL> "("     {System.out.println("Parentesis_abierto\n");}//tendré que retornar el número para que pueda ser el que el inge asignó, o cb los valores a los que jflex da? xD
<YYINITIAL> ")"     {System.out.println("Parentesis_cerrado\n");}
<YYINITIAL> "+"     {System.out.println("Suma\n");}
<YYINITIAL> "*"     {System.out.println("Por\n");}

<YYINITIAL>{
    {numero}                               {System.out.println("Numero\n");}
    (\$|_|-)({letra}|{numero}|_|-|\$)*     {System.out.priprintlnntl("Identificador\n");}
}

