package myce.casia.spider.apg;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;



public class TextProcess {
	
	public static FileWriter fw;
	public static BufferedWriter writer;
	
	
		
		
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String filePath="./newTask/";

		List<String>  nameList=FileUtils.getFiles(filePath);
		
		for (int i = 0; i < nameList.size(); i++) {
			
			BufferedReader br=new BufferedReader(new InputStreamReader(
					new FileInputStream(filePath+nameList.get(i)), "utf-8"));
			StringBuffer sb=new StringBuffer();
			String s=br.readLine();
			while(s!=null){
			//	System.out.println(s);
				sb.append(s);
				s=br.readLine();
			}
		
			String[] sequence=sb.toString().split("查看解析下载试题篮");
			fw=new FileWriter(new File(filePath+"IM"+nameList.get(i)));
			
			writer=new BufferedWriter(fw);
			
			for (int j = 0; j < sequence.length; j++) {
				
				System.out.println(sequence[j]);
				
				writer.write(sequence[j]+"\r\n");
				
				writer.flush();
				
			}
			writer.close();
			
			
			
			
		}
		
		
	
	
		
		
	}

}
