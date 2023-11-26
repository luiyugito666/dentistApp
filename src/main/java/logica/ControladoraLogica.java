/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.List;
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

    public List<User> getAllUsers() {
            return controlPer.getAllUsers();
        }

    public void deleteUser(int idUser) {
    controlPer.deleteUser(idUser);
    }

    public User getUserById(int idUser) {
        return controlPer.getUserById(idUser);
    }

    public void editUser(User usu) {
        controlPer.editUser(usu);
    }

    public boolean verifyUser(String user, String password) {
        boolean verify=this.getUser(user,password);
        
        return verify;
    }

    private boolean getUser(String user, String password) {
        
       List<User> listUser=getAllUsers();
       
       for(User use:listUser){
           if(use.getUserName().equalsIgnoreCase(user)){
               if(use.getPassword().equals(password)){
                   return true;
               }
               return false;
           
           }
       
       }
       return false;
        

    }
    
}
