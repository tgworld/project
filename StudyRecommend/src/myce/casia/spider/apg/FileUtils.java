package myce.casia.spider.apg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	
	public static FileWriter fw;
	public static BufferedWriter writer;
	public  static ArrayList<String> filelist = new ArrayList<String>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path="./testlog/";
		
		List<String>  list=getFiles(path);
		
		for (int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i).split("\\.")[0].split("\\：")[1].trim());
			
		}
		//getFiles(path);
	}
	
	
	public static BufferedWriter FileWriterToPath(String path) throws IOException{
		
		
		 fw=new FileWriter(new File(path));
			
		 return writer=new BufferedWriter(fw);
		
		
		
	}
	
	
	
	public static  ArrayList<String> getFiles(String filePath){
		  File root = new File(filePath);
		    File[] files = root.listFiles();
		    for(File file:files){     
		     if(file.isDirectory()){
		      /*
		       * 递归调用
		       */
		      getFiles(file.getAbsolutePath());
		     // System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
		     }else{
		    	 filelist.add(file.getName());
		  //    System.out.println("显示"+filePath+"下所有文件name: "+file.getName());
		     }     
		    }
			return filelist;
	
}
}
