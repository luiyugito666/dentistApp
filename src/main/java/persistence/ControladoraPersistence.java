/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistence;

import logica.User;

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

   
            
            
            
    
    
}
