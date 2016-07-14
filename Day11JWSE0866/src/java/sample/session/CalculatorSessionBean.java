/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import javax.ejb.Stateless;

/**
 *
 * @author Suzy
 */
@Stateless
public class CalculatorSessionBean implements CalculatorSessionBeanLocal, CalculatorSessionBeanRemote {

    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public double add(double num1, double num2) {
        return num1 + num2;
    }
    
    @Override
    public double sub(double num1, double num2) {
        return num1 - num2;
    }
    
    
}
