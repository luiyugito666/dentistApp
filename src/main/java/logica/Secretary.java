/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Secretary extends Person {
   // private int idSecretary;
    private String sector;
    @OneToOne
    private User unUser;

    public Secretary() {
    }

    public Secretary(String sector, User unUser, int id, String dni, String name, String lastName, String phone, String address, Date birthday) {
        super(id, dni, name, lastName, phone, address, birthday);
        this.sector = sector;
        this.unUser = unUser;
    }

  

    public User getUnUser() {
        return unUser;
    }

    public void setUnUser(User unUser) {
        this.unUser = unUser;
    }

  

   

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
    
    
}
