package com.ueliton.materialdesign.modelos;

/**
 * Created by Ueliton on 04/08/2015.
 */
public class Cidade {

    private int idIcone;
    private String nome;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdIcone() {
        return idIcone;
    }

    public void setIdIcone(int idIcone) {
        this.idIcone = idIcone;
    }
    public Cidade(String nome, int idIcone) {
        this.idIcone = idIcone;
        this.nome = nome;
    }
}
