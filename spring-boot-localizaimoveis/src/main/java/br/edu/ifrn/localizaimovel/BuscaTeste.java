package br.edu.ifrn.localizaimovel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BuscaTeste {

	public static void main(String[] args) throws IOException, JSONException {
		File input = new File("src/main/resources/zip/lista_imoveis_RN.htm");

		Document doc = Jsoup.parse(input,"windows-1252");
		JSONArray list = new JSONArray();
		for (Element table : doc.select("table")) {
			for (Element row : table.select("tr")) {
				JSONObject jsonObject = new JSONObject();
				Elements tds = row.select("td");
				String link = tds.get(0).html();
				String endereco = tds.get(1).text();
				String bairro = tds.get(2).text();
				String descricao = tds.get(3).text();
				Double preco =  Double.parseDouble(tds.get(4).text().equals("Preço (R$)")?"0":tds.get(4).text().replaceAll("\\.","").replace(",",".")); 
				Double valorAvaliacao =  Double.parseDouble(tds.get(5).text().equals("Valor de avaliação (R$)")?"0":tds.get(5).text().replaceAll("\\.","").replace(",",".")); 
				Double desconto =  Double.parseDouble(tds.get(6).text().equals("Desconto (%)")?"0":tds.get(6).text().replaceAll("\\.","").replace(",",".")); 
				String modalidadeVenda = tds.get(7).text();
				String foto = tds.get(8).html();
				String cidade = tds.get(9).text();
				String estado = tds.get(10).text();

				jsonObject.put("link", link);
				jsonObject.put("endereco", endereco);
				jsonObject.put("bairro", bairro);
				jsonObject.put("descricao", descricao);
				
				jsonObject.put("tipo", descricao.substring(0, descricao.indexOf(",")));
				jsonObject.put("preco", preco);
				jsonObject.put("valorAvaliacao", valorAvaliacao);
				jsonObject.put("desconto", desconto);
				jsonObject.put("modalidadeVenda", modalidadeVenda);
				jsonObject.put("foto", foto);
				jsonObject.put("cidade", cidade);
				jsonObject.put("estado", estado);

				list.put(jsonObject);

			}
		}
		try {

	        FileWriter file = new FileWriter("src/main/resources/json/lista_imoveis_RN.json");
	        file.write(list.toString());
	        file.flush();
	        file.close();
			System.out.println("Arquivo Gravado com sucesso....");


	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}

}
