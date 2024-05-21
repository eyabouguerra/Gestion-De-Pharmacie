/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author PC-Etudiante Info
 */
public class Medicament {
    
    public enum TypeMedicament {NORMAL,SPECIALE}
    
    private StringProperty codeMed;
    private StringProperty libelleMed;
    private StringProperty categorieMed;
    private float prixMed;
    private int qteStock;
    private TypeMedicament typeMed;


    public Medicament(String codeMed, String libelleMed, String categorieMed, float prixMed, int qteStock, TypeMedicament typeMed) {
        this.codeMed = new SimpleStringProperty(codeMed);
        this.libelleMed = new SimpleStringProperty (libelleMed);
        this.categorieMed = new SimpleStringProperty (categorieMed);
        this.prixMed = prixMed;
        this.qteStock = qteStock ;
        this.typeMed = typeMed;

    }


    public Medicament() {
    }
     

    public String getCodeMed() {
        return codeMed.get();
    }

    public void setCodeMed(String codeMed) {
        this.codeMed.set(codeMed);
    }

    public String getLibelleMed() {
        return libelleMed.get();
    }

    public void setLibelleMed(String libelleMed) {
        this.libelleMed.set(libelleMed);
    }

    public String getCategorieMed() {
        return categorieMed.get();
    }

    public void setCategorieMed(String categorieMed) {
        this.categorieMed.set(categorieMed);
    }

    public float getPrixMed() {
        return prixMed;
    }

    public void setPrixMed(float prixMed) {
        this.prixMed = prixMed;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    
    public TypeMedicament getTypeMed() {
        return typeMed;
    }

    public void setTypeMed(TypeMedicament typeMed) {
        this.typeMed = typeMed;
    }



    @Override
    public String toString() {
        return "Medicament{" + "codeMed=" + codeMed.get() + ", libelleMed=" + libelleMed.get() + ", categorieMed=" + categorieMed.get() + ", prixMed=" + prixMed + ", qteStock=" + qteStock + ", typeMed=" + typeMed + '}';
    }
    
    
    
}
