/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edcaron.md5check.controle;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 *
 * @author eduardo.caron
 */
public class ProcessoWindows {
    
     public static String executarComando(String comando, String parametro) {
        try {

            // executar comando no cmd
            String cmd = comando + parametro;
//            System.out.println("comando: " + cmd);
            Process process = Runtime.getRuntime().exec(cmd);

            StreamReader reader = new StreamReader(process.getInputStream());
            reader.start();
            process.waitFor();
            reader.join();
            String output = reader.getResult();

            return output;
        } catch (IOException | InterruptedException e) {
            System.err.println("erro em executarComando" + e);
            return null;
        }
    }

    protected static class StreamReader extends Thread {

        private InputStream is;
        private StringWriter sw = new StringWriter();

        public StreamReader(InputStream is) {
            this.is = is;
        }

        public void run() {
            try {
                int c;
                while ((c = is.read()) != -1) {
                    sw.write(c);
                }
            } catch (IOException e) {
                System.err.println("erro em static class StreamReader extends Thread \n" + e);
            }
        }

        public String getResult() {
            return sw.toString();
        }
    }
    
}
