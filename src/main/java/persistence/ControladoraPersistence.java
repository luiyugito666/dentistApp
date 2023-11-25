/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.User;
import persistence.exceptions.NonexistentEntityException;

/**
 *
 * @author jorge
 */
public class ControladoraPersistence {
    DentistJpaController dentistJpa= new DentistJpaController();
    PatientJpaController patientJpa=new PatientJpaController();
    PersonJpaController personJpa=new PersonJpaController();
    ResponsibleJpaController responsibleJpa=new ResponsibleJpaController();
    SchudeleJpaController schudeleJpa=new SchudeleJpaController();
    SecretaryJpaController secretaryJpa=new SecretaryJpaController();
    ShiftJpaController shiftJpa=new ShiftJpaController();
    UserJpaController userJpa=new UserJpaController();

    public void createUser(User user) {
        userJpa.create(user);
    }

    public List<User> getAllUsers() {
        return userJpa.findUserEntities();
    }

    public void deleteUser(int idUser) {
        try {
            userJpa.destroy(idUser);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int idUser) {
        return userJpa.findUser(idUser);
    }

    public void editUser(User usu) {

        try {
            userJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

   
            
            
            
    
    
}
