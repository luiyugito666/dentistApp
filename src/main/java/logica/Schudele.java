/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Schudele implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSchudele;
    private String endSchudele;
    private String startScredule;

    public Schudele() {
    }

    public Schudele(int idSchudele, String endSchudele, String startScredule) {
        this.idSchudele = idSchudele;
        this.endSchudele = endSchudele;
        this.startScredule = startScredule;
    }

    public int getIdSchudele() {
        return idSchudele;
    }

    public void setIdSchudele(int idSchudele) {
        this.idSchudele = idSchudele;
    }

    public String getEndSchudele() {
        return endSchudele;
    }

    public void setEndSchudele(String endSchudele) {
        this.endSchudele = endSchudele;
    }

    public String getStartScredule() {
        return startScredule;
    }

    public void setStartScredule(String startScredule) {
        this.startScredule = startScredule;
    }
    
    
}
