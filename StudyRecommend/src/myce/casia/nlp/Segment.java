package myce.casia.nlp;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.dictionary.CustomDictionary;

public class Segment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		String s="都没有老婆温柔，都没有老婆有宝宝";
		CustomDictionary.add("宝宝", "a 3");
		System.out.println(s+"的分词结果为："+"\r\n"+HanLP.segment(s));
		
	}

}
