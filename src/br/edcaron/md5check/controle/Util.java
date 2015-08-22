package br.edcaron.md5check.controle;


import java.io.File;
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author eduardo.caron
 */
public class Util {

    
    /**
     * funcao para conveter byte em kb ou mb
     * @param arquivo File (java)
     * @return String com o tamanho do arquivo e a unidade de medida
     */
    public static String converterByteParaKBouMB(File arquivo) {
        String size = "0";

        DecimalFormat df = new DecimalFormat("#,###.00");

        if (arquivo.length() < 1000000) {
            size = "" + df.format(arquivo.length() / 1024.0) + " KB";
        } else {
            size = "" + df.format(arquivo.length() / 1048576.0) + " MB";
        }
        
        return size;
    }
    
        /**
     * Funcao para apagar arquivos do disco
     *
     * @param arquivo Localizacao completa do arquivo no hd. Exemplo:
     * c:\pasta\arquivo.txt
     */
    public static void apagarArquivo(String arquivo) {
        try {
            File f = new File(arquivo);
            f.delete();
        } catch (Exception e) {
            System.err.println("Erro ao apagar arquivo: " + e);
        }
    }
}
