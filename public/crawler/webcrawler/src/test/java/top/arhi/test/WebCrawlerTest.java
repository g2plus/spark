package top.arhi.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;


public class WebCrawlerTest {
    @Test
    public void testJsoup() throws InterruptedException, IOException {

        Set<String> hashSet=new HashSet<String>(100000);



        //1.创建httpclient对象
        CloseableHttpClient httpclient= HttpClients.createDefault();

        int i=2;


        //获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();


        while(i<=200){
            String path="";
            if(i==1){
              path = "index"+".html";
            }else{
                path="index_"+i+".html";
            }
            i++;

            //2.选择get请求
            //创建一个URI

            URI uri=null;
            try {
                uri=new URIBuilder()
                        .setScheme("https")//协议
                        .setHost("pic.netbian.com")//地址
                        .setPath(path)//文件
                        //.setParameter("ip", "0")//参数，可以为若干个
                        .build();//创建
            } catch (Exception e) {
                e.printStackTrace();
            }
            HttpGet httpget=null;
            if(uri!=null){
                //get请求
                httpget=new HttpGet(uri);
                //post请求
                //HttpPost httppost=new HttpPost(uri);

            }
            // 3.确定是否有参数
            // 设置为人工访问
            httpget.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
            // 设置超时
            RequestConfig request = RequestConfig.custom()
                    .setConnectTimeout(5000)//连接超时
                    .setConnectionRequestTimeout(5000)//请求超时
                    .setSocketTimeout(5000).build();//Socket协议超时
            // 设置连接
            httpget.setConfig(request);


            //4.执行请求
            CloseableHttpResponse respose=null;//为了关闭respose提前声明
            try {
                //执行请求
                respose=httpclient.execute(httpget);
                System.out.println("版本信息"+respose.getProtocolVersion());
                //状态码：200正常，402禁止，404未找到，500服务器错误，更多状态码可以通过百度百科查看
                System.out.println("状态码："+respose.getStatusLine().toString());
                //获得httpentity对象
                HttpEntity entity=respose.getEntity();
                System.out.println("================================");
                if(entity!=null){//可能为空
                    String html=EntityUtils.toString(entity,"UTF-8");
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
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                //关闭链接
                respose.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //根据StudentMapper接口,利用sqlSession的getMapper方法实现StudentMapper接口
            ImgMapper mapper = sqlSession.getMapper(ImgMapper.class);//多态
            for (String resource : hashSet) {
                System.out.println(resource);
                mapper.add("netbian","https://pic.netbian.com",resource);
                String suffix = resource.substring(resource.lastIndexOf(".") + 1);
                downloadImg(resource,"E:\\home\\bytedance\\netbian\\" + (System.currentTimeMillis()) + "." + suffix);
                Thread.sleep(1000);
            }

        }
    }

    //https://blog.csdn.net/dunegao/article/details/78393358
    private static void downloadPicture(String urlList, String path) {
        URL url = null;
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //https://www.jianshu.com/p/1a5dcfcf0137
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



}
