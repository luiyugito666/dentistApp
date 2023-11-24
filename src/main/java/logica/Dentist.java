/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Dentist extends Person implements Serializable{
   // private int idDentist;
    
    private String specialty;
    
    @OneToMany(mappedBy = "dentist")
    private List<Shift> listShift;
    
    @OneToOne
    private User unUser;
    @OneToOne
    private Schudele unschudele;

    public Dentist() {
    }

    public Dentist(String specialty, List<Shift> listShift, User unUser, Schudele unschudele, int id, String dni, String name, String lastName, String phone, String address, Date birthday) {
        super(id, dni, name, lastName, phone, address, birthday);
        this.specialty = specialty;
        this.listShift = listShift;
        this.unUser = unUser;
        this.unschudele = unschudele;
    }



    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Shift> getListShift() {
        return listShift;
    }

    public void setListShift(List<Shift> listShift) {
        this.listShift = listShift;
    }

    public User getUnUser() {
        return unUser;
    }

    public void setUnUser(User unUser) {
        this.unUser = unUser;
    }

    public Schudele getUnschudele() {
        return unschudele;
    }

    public void setUnschudele(Schudele unschudele) {
        this.unschudele = unschudele;
    }
    

 
}
