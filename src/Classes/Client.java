/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author PC-Etudiante Info
 */
public class Client {
    private String codeCli;
    private String nomCli;
    private String prenomCli;
    private String adresseCli;
    private String telephoneCli;

    public Client() {
    }

    public Client(String codeCli, String nomCli, String prenomCli, String adresseCli, String telephoneCli) {
        this.codeCli = codeCli;
        this.nomCli = nomCli;
        this.prenomCli = prenomCli;
        this.adresseCli = adresseCli;
        this.telephoneCli = telephoneCli;
    }

    public String getPrenomCli() {
        return prenomCli;
    }

    public void setPrenomCli(String prenomCli) {
        this.prenomCli = prenomCli;
    }

    public String getTelephoneCli() {
        return telephoneCli;
    }

    public void setTelephoneCli(String telephoneCli) {
        this.telephoneCli = telephoneCli;
    }

    

    public String getCodeCli() {
        return codeCli;
    }

    public void setCodeCli(String codeCli) {
        this.codeCli = codeCli;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getAdresseCli() {
        return adresseCli;
    }

    public void setAdresseCli(String adresseCli) {
        this.adresseCli = adresseCli;
    }

    @Override
    public String toString() {
        return "Client{" + "codeCli=" + codeCli + ", nomCli=" + nomCli + ", prenomCli=" + prenomCli + ", adresseCli=" + adresseCli + ", telephoneCli=" + telephoneCli + '}';
    }
    
    
    

    
}
