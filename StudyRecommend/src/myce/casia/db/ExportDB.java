package myce.casia.db;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class ExportDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		 //默认读取hibernate.cfg.xml文件  
        Configuration cfg=new Configuration().configure();//读配置文件  
        SchemaExport export=new SchemaExport(cfg);//通过此生成ddl  
        export.create(true, true);//打印到控制台，输出脚本到数据库 
	}

}
