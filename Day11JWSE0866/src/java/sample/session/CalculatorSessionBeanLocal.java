/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.session;

import javax.ejb.Local;

/**
 *
 * @author Suzy
 */
@Local
public interface CalculatorSessionBeanLocal {

    double add(double num1, double num2);

    double sub(double num1, double num2);
    
}
