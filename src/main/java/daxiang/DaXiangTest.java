package daxiang;

import net.sf.json.JSONObject;
import org.junit.Test;
import utils.HttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 测试大象云消息交互
 * Created by wqp on 2018/1/15.
 */
public class DaXiangTest {

    private static final Logger logger = Logger.getLogger(DaXiangTest.class.getName());


    /**
     *  获取token
     *  @return token  应用的token用来调用api的凭证  scope　token的访问权限 expiredTime   token过期时间
     */
    public static Map<String,String> getToken() {
        String postUrl = "https://open.daxiangyun.com/dx/api/auth/token";
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("app_id", "0e4d2250acba4d648f90404cfbf31db9");
        mapData.put("security_key", "b45f5d754989476eab7201f25250905b");
        JSONObject jsonData  = HttpUtils.doPost(postUrl, mapData, "utf-8");

        Map<String,String> map = JSONObject.fromObject(jsonData.get("data"));
        return map;
    }

    /**
     * 上传model file
     * @param token
     * @param file
     * @return
     */
    public static JSONObject upFile(String token, String file){
        String postUrl = "https://open.daxiangyun.com/dx/api/f/v1/file?token=" + token;
        Map<String, String> mapData = new HashMap<String, String>();
        mapData.put("file",file);
        JSONObject jsonData = HttpUtils.doPost(postUrl, mapData,"utf-8");
        return jsonData;

    }


    /**
     * 查看model 状态
     * @return url:模型浏览地址  path:model路径  size:大小    date:日期     status：状态
     */
    public static JSONObject getStatus(String token,String path) {
        String getUrl = "https://open.daxiangyun.com/dx/api/f/v1/file/status?token=" + token + "&path="+path;
        JSONObject jsonData = HttpUtils.doGet(getUrl);
        return jsonData;
    }

    /**
     * base64 编码
     * @param bstr
     * @return
     */
    public static String encode(byte[] bstr){
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * base64 解码
     * @param str
     * @return
     */
    public static byte[] decode(String str){
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer( str );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt;
    }

    public static void main(String args[]){
        //System.out.println(getStatus("1d1b6d1b820b4d88bdee690e80100fc9","opendx/0e4d2250acba4d648f90404cfbf31db9/96333ecf302a40ca8ea9940d83348724/96333ecf302a40ca8ea9940d83348724.rvt"));
        System.out.println(encode("243274".getBytes()));
        System.out.println(decode("ef4ad1cb-015c-4bb6-9030-e7bb82f79a08"));
    }
}
