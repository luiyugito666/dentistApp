/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Patient extends Person implements Serializable{
    //private int idPatient;
    private boolean haveOS;
    private String bloodType;
    
    @OneToMany(mappedBy = "patient")
    private List<Shift> listShift;
    
    @OneToOne
    private Responsible unResposible;
    
    public Patient() {
    }

    public Patient(boolean haveOS, String bloodType, List<Shift> listShift, Responsible unResposible, int id, String dni, String name, String lastName, String phone, String address, Date birthday) {
        super(id, dni, name, lastName, phone, address, birthday);
        this.haveOS = haveOS;
        this.bloodType = bloodType;
        this.listShift = listShift;
        this.unResposible = unResposible;
    }

    public boolean isHaveOS() {
        return haveOS;
    }

    public void setHaveOS(boolean haveOS) {
        this.haveOS = haveOS;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public List<Shift> getListShift() {
        return listShift;
    }

    public void setListShift(List<Shift> listShift) {
        this.listShift = listShift;
    }

    public Responsible getUnResposible() {
        return unResposible;
    }

    public void setUnResposible(Responsible unResposible) {
        this.unResposible = unResposible;
    }
    
    
}
