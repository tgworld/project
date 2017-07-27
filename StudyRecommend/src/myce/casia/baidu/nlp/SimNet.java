package myce.casia.baidu.nlp;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import myce.casia.baidu.utils.GsonUtils;
import myce.casia.baidu.utils.HttpUtil;

/**
 * 短文本相似度
 */
public class SimNet {

    /**
     * 代码中所需工具类
     * FileUtil,Base64Util,HttpUtil请从
     * https://ai.baidu.com/file/BA73D199EED14D8AA5FC5A4BF4BDDA34
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/88C6E86FB5D141889391693FC84504B1
     * 下载
     */
	public static void main(String[] args){
		String shortText1 = "16公路上行驶的一辆汽车车牌为偶数的频率约是（　　A．50%  B．100%  C．由各车所在单位或个人定   D．无法确定";
		String shortText2 = "二元一次方程组的求解";
		double d=TextSim(shortText1, shortText2);
		System.out.println(shortText1+"与"+shortText2+"这两个短文本的相似度是："+d);
	}
    public static double  TextSim(String shortText1,String shortText2) {
        // 短文本相似度url
        String simNetUrl = "https://aip.baidubce.com/rpc/2.0/nlp/v1/simnet";
        double d=0.0;
        try {
          //  String shortText1 = "你好百度";
            Map<String, Object> qslots_params = new HashMap<String, Object>();
            qslots_params.put("terms_sequence", shortText1);
            qslots_params.put("type", 0);
            qslots_params.put("items", new ArrayList());
            List<Object> qslots = new ArrayList<Object>();
            qslots.add(qslots_params);

           // String shortText2 = "你好世界";
            Map<String, Object> tslots_params = new HashMap<String, Object>();
            tslots_params.put("terms_sequence", shortText2);
            tslots_params.put("type", 0);
            tslots_params.put("items", new ArrayList());
            List<Object> tslots = new ArrayList<Object>();
            tslots.add(tslots_params);

            Map<String, Object> input = new HashMap<String, Object>();
            input.put("qslots", qslots);
            input.put("tslots", tslots);
            input.put("type", 0);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("input", input);

            String params = GsonUtils.toJson(map);
            params = URLEncoder.encode(params, "GBK");

            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.3b1c18830d9173f045b326ef6141c23d.2592000.1497073914.282335-9632067";
            String result = HttpUtil.post(simNetUrl, accessToken, params);
            //System.out.println(result);
            JSONObject json=new JSONObject(result);
           // System.out.println(json.getJSONObject("output").get("score"));
            d=(double) json.getJSONObject("output").get("score");
          
        } catch (Exception e) {
            e.printStackTrace();
        }
		return d;
    }
}