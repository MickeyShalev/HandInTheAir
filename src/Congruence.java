/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Congruence {
    public static void main(String args[]){
        
        //Definition of Triangle A
        Integer a1=5, b1=5, c1=10;
        //Definition of Triangle B
        Integer a2=8, b2=10, c2=5;
        
        //Checks if the trianalges are not congruence
        Boolean failure = false;
        
        //If atleast one side not equals to one of the sides on triangle B
        if((a1!=a2&&a1!=b2&&a1!=c2)||(b1!=a2&&b1!=b2&&b1!=c2) || (c1!=a2&&c1!=b2&&c1!=c2))
            failure = true;
        
        //If pair of sides equals and not equals on triangle B
        if((a1==b1 && a2!=b2) || (a1==c1 && a2!=c2) || (b1==c1 && b2!=c2))
            failure = true;
            
            if(failure)
                System.out.println("Triangles are not congruenced");
            else System.out.println("Triangles are congruenced");
        
        
        
        
    }
}
