package br.edcaron.md5check.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eduardo.caron
 */
public class Arquivo {

    private int id;
    private String md5;
    private String diretorio;
    private String tamanho;

    public Arquivo(int id, String md5, String diretorio, String tamanho) {
        this.id = id;
        this.md5 = md5;
        this.diretorio = diretorio;
        this.tamanho = tamanho;
    }

    public Arquivo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getDiretorio() {
        return diretorio;
    }

    public void setDiretorio(String diretorio) {
        this.diretorio = diretorio;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

}
