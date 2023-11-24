/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import persistence.ControladoraPersistence;

/**
 *
 * @author jorge
 */
public class ControladoraLogica {
    ControladoraPersistence controlPer=new ControladoraPersistence();
    public void createUser(String usuario, String password, String rol){
        User user=new User();
        
        user.setUserName(usuario);
        user.setPassword(password);
        user.setRol(rol);
        controlPer.createUser(user);
    }
    
}
