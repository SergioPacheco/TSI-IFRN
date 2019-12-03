package Controller;

import com.google.gson.Gson;

import Modelo.Cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ConsultaController {

    String url = "http://correiosapi.apphb.com/cep/";

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            while ((read = reader.read()) != -1) {
                buffer.append((char) read);
            }
            return buffer.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public Cep consulta(String cep) throws IOException, Exception {
            
            String jsonFile = readUrl(url+cep);
            Gson gson = new Gson();
            Cep Cep = gson.fromJson(jsonFile, Cep.class);
            
    return Cep;
}

}
