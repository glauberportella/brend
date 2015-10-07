package com.biavan.brend.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arquivo {
	
	public static List<String> lerArquivo(String nomeArquivo, boolean ignorarPrimeiraLinha) {

		List<String> linhas = new ArrayList<String>();
		
		try {
			FileReader arquivo = new FileReader(nomeArquivo);

			BufferedReader buffer = new BufferedReader(arquivo);

			String linha = buffer.readLine();
			
			// Ignora a primeira linha
			if (ignorarPrimeiraLinha)
				linha = buffer.readLine();

			while (linha != null) {

				linhas.add(linha);

				linha = buffer.readLine();
			}

			arquivo.close();
		} catch (FileNotFoundException ex) {
			System.out.println(new java.util.Date()
					+ "Arquivo não encontrado: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println(new java.util.Date() + "Erro de IO: "
					+ ex.getMessage());
		}
		
		return linhas;
	}

}
