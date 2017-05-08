package com.fineline.service;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.fineline.domain.Sakko;

public class SakkoService {
	
	
	
	public Sakko readSakko() throws Exception{
		
		Sakko sakko = new Sakko();
		Path path;
		try {
			path = Paths.get(this.getClass().getClassLoader()
					.getResource("sakko.txt").toURI());
			StringBuilder data = new StringBuilder();
			Stream<String> lines = Files.lines(path);
			lines.forEach(line -> data.append(line).append(""));
			lines.close();

			sakko.setSakko_count(data.toString());
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		
		
		return sakko;
	}

	public Sakko writeSakko() throws Exception {
		
		Sakko sakko = new Sakko();
		Path path;
		try {

			path = Paths.get(this.getClass().getClassLoader()
					.getResource("sakko.txt").toURI());
			StringBuilder data = new StringBuilder();
			Stream<String> lines = Files.lines(path);
			lines.forEach(line -> data.append(line));
			lines.close();

			String myString = data.toString();

			int new_sakko_count = Integer.parseInt(myString);
			new_sakko_count++;

			try {
				FileWriter writer = new FileWriter(path.toString());
				writer.write("" + new_sakko_count);
				writer.close();
				sakko.setSakko_count("" + new_sakko_count);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
		
		return sakko;
	}
	
	public Sakko resetSakko() throws Exception{
		
		Path path;
		Sakko sakko = new Sakko();
		try {
						
			path = Paths.get(getClass().getClassLoader().getResource("sakko.txt").toURI());
			StringBuilder data = new StringBuilder();
			Stream<String> lines = Files.lines(path);
			lines.forEach(line -> data.append(line));
			lines.close();

			try {
				FileWriter writer = new FileWriter(path.toString());
				writer.write("" + 0);
				writer.close();
				sakko.setSakko_count("" + 0);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
		return sakko;
	}
	
}
