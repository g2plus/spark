package top.arhi.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;



import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.*;
import com.drew.metadata.Tag;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import top.arhi.util.AmpUtil;

public class ExifTest {
    public static void main(String[] args) throws Exception {
        //智能手机拍摄的图片一般不会带有gps信息，
        //此处使用magicExif软件添加gps信息
        //采用高德地图的第三方接口获取地址信息。
        String path = "E:\\develop\\gitee.com\\repo\\funplus\\public\\servlet\\web\\upload\\img\\beauty.jpg";
        HashMap<String, Object> map = readPicInfo(path);
        String latitude = map.get("latitude").toString();
        String longitude = map.get("longitude").toString();
        Double[] point = convert(latitude, longitude);
        System.out.println(point[1] + "," + point[0]);
        String result = AmpUtil.getAMapByLngAndLat(point[1].toString(), point[0].toString());
        Document document = Jsoup.parse(result);
        Elements elementsByTag = document.getElementsByTag("formatted_address");
        String address = elementsByTag.get(0).text();
        System.out.println(address);

    }

    public static HashMap<String, Object> readPicInfo(String file_path) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Tag tag = null;
        File jpegFile = new File(file_path);
        Metadata metadata;
        try {
            metadata = JpegMetadataReader.readMetadata(jpegFile);
            Iterator<Directory> it = metadata.getDirectories().iterator();
            while (it.hasNext()) {
                Directory exif = it.next();
                Iterator<Tag> tags = exif.getTags().iterator();
                while (tags.hasNext()) {
                    tag = (Tag) tags.next();
                    if (tag.getTagName().equals("GPS Latitude")) {
                        map.put("latitude", tag.getDescription());
                    }
                    if (tag.getTagName().equals("GPS Longitude")) {
                        map.put("longitude", tag.getDescription());
                    }
                }
            }
        } catch (JpegProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 经纬度转换
     *
     * @param latitude
     * @param longitude
     */
    public static Double[] convert(String latitude, String longitude) {


        double a = Double.parseDouble(latitude.split(" ")[0].replace("°", ""));
        double b = Double.parseDouble(latitude.split(" ")[1].replace("'", "")) / 60;
        double c = Double.parseDouble(latitude.split(" ")[2].replace("\"", "")) / 60 / 60;
        Double[] address = new Double[2];
        address[0] = a + b + c;
        double d = Double.parseDouble(longitude.split(" ")[0].replace("°", ""));
        double e = Double.parseDouble(longitude.split(" ")[1].replace("'", "")) / 60;
        double f = Double.parseDouble(longitude.split(" ")[2].replace("\"", "")) / 60 / 60;
        address[1] = d + e + f;
        return address;
    }

}
