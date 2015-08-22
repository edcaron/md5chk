/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edcaron.md5check.daos;

import br.edcaron.md5check.modelo.Arquivo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author eduardo.caron
 */
public class ArquivoDAO {

    ResultSet resultadoQ;

    
    /**
     * Funcao para gravar lista de arquivos na base de dados
     * @param listaDeArquivos
     * @return String com contagem de registros salvos ou o erro retornado.
     */
    public String salvar(ArrayList<Arquivo> listaDeArquivos) {
        String retorno = "";
        int contagem = 0;
        for (Arquivo arquivo : listaDeArquivos) {
            try {
                Statement st = ConexaoBD.getInstance().getConnection().createStatement();

                String sql = "INSERT INTO files (id, filename, md5, size, insert_date) VALUES"
                        + "(DEFAULT, "
                        + "'" + arquivo.getDiretorio() + "',"
                        + "'" + arquivo.getMd5() + "', "
                        + "'" + arquivo.getTamanho() + "', "
                        + " now() "
                        + "); ";

                System.out.println("SQL = " + sql);
                contagem++;
                st.execute(sql);

            } catch (SQLException e) {
                System.out.println(e.toString());
                retorno = (e.toString());
            }
            
        }
        retorno += contagem;
        return retorno;
    }

    /**
     * Funcao para verificar arquivos repetidos na base de dados
     * @param listaDeArquivos lista de arquivos a serem comparados com o que está na base de dados
     * @return lista com listas de arquivos novos e arquivos duplicados
     */
    public ArrayList<ArrayList> verificarDuplicados(ArrayList<Arquivo> listaDeArquivos) {
        ArrayList<Arquivo> arquivosDuplicados = new ArrayList<>();
        ArrayList<Arquivo> arquivosNovos = new ArrayList<>();
        ArrayList<ArrayList> retorno = new ArrayList<>();

        for (Arquivo arq : listaDeArquivos) {

            String sql = "SELECT count(*) FROM files WHERE md5 ='" + arq.getMd5() + "';";
            System.out.println(sql);
            try {
                resultadoQ = ConexaoBD.getInstance().getConnection().createStatement().executeQuery(sql);

                while (resultadoQ.next()) {
                    int contagem = (resultadoQ.getInt(1));
                    System.out.println("" + contagem);
                    if (contagem > 0) {
                        arquivosDuplicados.add(arq);

                    } else {
                        arquivosNovos.add(arq);
                    }
                }

            } catch (Exception e) {
                System.out.println("problemas consultar arquivos");
                System.out.println(e);
            }

        }

        retorno.add(0, arquivosDuplicados);
        retorno.add(1, arquivosNovos);
        return retorno;
    }
}
