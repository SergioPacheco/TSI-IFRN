package br.edu.ifrn.Atividade02;

import br.edu.ifrn.Atividade02.Q01.Calculo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "--Calculos--" );
        Calculo c = new Calculo(); 
        Double r = c.Desconto(80.0D, 13.0D); 
        System.out.println(r);  
        
        
        
    }
}
