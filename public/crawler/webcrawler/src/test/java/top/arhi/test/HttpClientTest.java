package top.arhi.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import top.arhi.mapper.ImgMapper;
import top.arhi.utils.MyBatisUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class HttpClientTest {

    private static Set<String> hashSet = new HashSet<String>(10000000);

    @Test
    public static void main(String[] args) throws Exception {
//        parseHtml("https://pic.netbian.com");
//        String prefix="https://pic.netbian.com/index_";
//        String suffix=".html";
//        for(int i=1101;i<=1277;i++){
//            String full=prefix+i+suffix;
//            parseHtml(full);
//        }
//        saveDataToMysql(hashSet);

        parselocalHtml("e:\\home\\html");
        saveDataToMysql(hashSet);
    }




    private static void parseHtml(String uri) throws URISyntaxException, IOException, InterruptedException {

        //1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig.Builder custom = RequestConfig.custom();
        RequestConfig config = custom.setConnectTimeout(5000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
        //2. 创建HttpGet请求，并进行相关设置
        HttpGet httpGet = new HttpGet(uri);
        httpGet.setConfig(config);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36");
        //3.发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.判断响应状态码并获取响应数据
        if (response.getStatusLine().getStatusCode() == 200) { //200表示响应成功
            String html = EntityUtils.toString(response.getEntity(), "gbk");
            System.out.println(html);
            Document document = Jsoup.parse(html);
            Elements img = document.getElementsByTag("img");
            String prefix = "https://pic.netbian.com";
            for (Element element : img) {
                String src = element.attr("src");
                if("".equals(src)){
                    continue;
                }
                src = prefix + src;
                hashSet.add(src);
            }
        }

        //5.关闭资源
        httpClient.close();
        response.close();
        Thread.sleep(20000);
    }


    private static void saveDataToMysql(Set<String> hashSet){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        //根据Mapper接口,利用sqlSession的getMapper方法实现Mapper接口
        ImgMapper mapper = sqlSession.getMapper(ImgMapper.class);//多态
        for (String resource : hashSet) {
            System.out.println(resource);
            mapper.add("netbian","https://pic.netbian.com",resource);
        }
        sqlSession.close();
    }


    private static void downloadImg(String fileUrl, String target) throws IOException {
        URL url = new URL(fileUrl);/*将网络资源地址传给,即赋值给url*/
        /*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        DataInputStream in = new DataInputStream(connection.getInputStream());
        /*此处也可用BufferedInputStream与BufferedOutputStream*/
        DataOutputStream out = new DataOutputStream(new FileOutputStream(target));
        /*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
        byte[] buffer = new byte[4096];
        int count = 0;
        /*将输入流以字节的形式读取并写入buffer中*/
        while ((count = in.read(buffer)) > 0) {
            out.write(buffer, 0, count);
        }
        out.close();/*后面三行为关闭输入输出流以及网络资源的固定格式*/
        in.close();
        connection.disconnect();
    }

    private static void parselocalHtml(String path) throws IOException {

        File parent=new File(path);
        File[] children = parent.listFiles();
        for (File child : children) {
            Document document = Jsoup.parse(child, "UTF-8");
            Elements img = document.getElementsByTag("img");
            for (Element element : img) {
                String src = element.attr("src");
                if("".equals(src)){
                    continue;
                }
                hashSet.add(src);
            }
        }
    }
}
