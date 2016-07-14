/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.mdb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Suzy
 */
@MessageDriven(mappedName = "jms/login", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class LoginMessageBean implements MessageListener {
    @PersistenceContext(unitName = "Lab12_ToanDCSE61278-ejbPU")
    private EntityManager em;
    
    public LoginMessageBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage msg = (TextMessage) message;
            System.out.println("String: " + msg.getText());
        } catch (JMSException ex) {
            Logger.getLogger(LoginMessageBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
