package myce.casia.mehout;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;

public class CompsiteRecommend {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      System.out.println(itemid(13).get(0));
	
		
	}
	
	
	public static List<Integer> itemid(int uid){
		// TODO Auto-generated method stub

		List<Integer> itemList=new ArrayList<>();
		
	//	List<RecommendedItem>  recommendedItems=MyUserBasedRecommender.userBasedRecommender(uid, 10);
		//List<RecommendedItem> recommendedItems2=MyItemBasedRecommender.myItemBasedRecommender(1, 3);
		List<RecommendedItem>  recommendedItems3=MySlopeOneRecommender.mySlopeOneRecommender(uid, 10);
		//System.out.println(recommendedItems3);
		for(int i=0;i<recommendedItems3.size();i++){
			System.out.println("推荐的试题编号与得分率为："+recommendedItems3.get(i).getItemID()+" "+recommendedItems3.get(i).getValue());
			if(recommendedItems3.get(i).getValue()<0.8){
				itemList.add((int) recommendedItems3.get(i).getItemID());
				System.out.println("得分率低于0.8的易错题编号为："+(int)recommendedItems3.get(i).getItemID());
			}
		}
		return itemList;
		
		
	}

}
