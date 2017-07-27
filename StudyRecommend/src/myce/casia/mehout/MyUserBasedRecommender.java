package myce.casia.mehout;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;



import java.io.File;
import java.util.*;

public class MyUserBasedRecommender {
	public  static List<RecommendedItem> userBasedRecommender(long userID,int size) {
		// step:1 构建模型 2 计算相似度 3 查找k紧邻 4 构造推荐引擎
		System.out.println("进入了myuser");
		List<RecommendedItem> recommendations = null;
		try {
			//DataModel model = MyDataModel.myDataModel();//构造数据模型
			System.out.println("加载数据");
			DataModel model=new FileDataModel(new File("./data/movie_preferences.csv"));
			
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);//用PearsonCorrelation 算法计算用户相似度
			UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);//计算用户的“邻居”，这里将与该用户最近距离为 3 的用户设置为该用户的“邻居”。
			System.out.println(neighborhood);
			Recommender recommender = new CachingRecommender(new GenericUserBasedRecommender(model, neighborhood, similarity));//采用 CachingRecommender 为 RecommendationItem 进行缓存
			recommendations = recommender.recommend(userID, size);//得到推荐的结果，size是推荐接过的数目
		//System.out.println(recommendations);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return recommendations;
	}

	public static void main(String args[]) throws Exception {
		
		
		List<RecommendedItem> recommendations =userBasedRecommender(1, 25);

		for(int i=0;i<recommendations.size();i++){
			System.out.println(recommendations.get(i));
		}
		
	}
}