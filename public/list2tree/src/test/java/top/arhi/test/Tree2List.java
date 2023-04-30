package top.arhi.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.arhi.Application;
import top.arhi.pojo.Place;
import top.arhi.util.TreeUtil;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Tree2List {

    @Autowired
    private JSONObject jsonObject;

    @Test
    public void test1(){
        String result="{\"data\": [\n" +
                "        {\n" +
                "            \"id\": 1,\n" +
                "            \"pid\": null,\n" +
                "            \"name\": \"中华人民共和国\",\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": 2,\n" +
                "                    \"pid\": 1,\n" +
                "                    \"name\": \"湖南省\",\n" +
                "                    \"children\": [\n" +
                "                        {\n" +
                "                            \"id\": 3,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"长沙市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 4,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"株洲市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 5,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"湘潭市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 6,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"衡阳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 7,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"邵阳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 8,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"岳阳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 9,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"常德市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 10,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"张家界市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 11,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"益阳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 12,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"郴州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 13,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"永州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 14,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"怀化市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 15,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"娄底市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 16,\n" +
                "                            \"pid\": 2,\n" +
                "                            \"name\": \"湘西土家族苗族自治州\",\n" +
                "                            \"children\": []\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 17,\n" +
                "                    \"pid\": 1,\n" +
                "                    \"name\": \"广东省\",\n" +
                "                    \"children\": [\n" +
                "                        {\n" +
                "                            \"id\": 18,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"广州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 19,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"深圳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 20,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"佛山市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 21,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"东莞市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 22,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"中山市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 23,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"珠海市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 24,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"江门市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 25,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"肇庆市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 26,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"惠州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 27,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"汕头市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 28,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"潮州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 29,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"揭阳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 30,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"汕尾市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 31,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"湛江市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 32,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"茂名市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 33,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"阳江市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 34,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"云浮市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 35,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"韶关市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 36,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"清远市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 37,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"梅州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 38,\n" +
                "                            \"pid\": 17,\n" +
                "                            \"name\": \"河源市\",\n" +
                "                            \"children\": []\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 39,\n" +
                "                    \"pid\": 1,\n" +
                "                    \"name\": \"江苏省\",\n" +
                "                    \"children\": [\n" +
                "                        {\n" +
                "                            \"id\": 40,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"南京市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 41,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"无锡市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 42,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"徐州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 43,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"常州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 44,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"苏州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 45,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"南通市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 46,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"连云港市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 47,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"扬州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 48,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"盐城市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 49,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"镇江市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 50,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"泰州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 51,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"宿迁市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 52,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"江阴市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 53,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"宜兴市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 54,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"新沂市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 55,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"邳州市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 56,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"溧阳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 57,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"常熟市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 58,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"张家港市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 59,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"昆山市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 60,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"太仓市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 62,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"启东市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 63,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"如皋市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 64,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"海安市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 65,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"东台市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 66,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"仪征市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 67,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"高邮市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 68,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"丹阳市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 69,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"扬中市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 70,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"句容市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 71,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"兴化市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 72,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"靖江市\",\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 73,\n" +
                "                            \"pid\": 39,\n" +
                "                            \"name\": \"泰兴市\",\n" +
                "                            \"children\": []\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]}";
        //用来存储所有的Place信息
        List<Place> list = new ArrayList<>();

        jsonObject = this.jsonObject.parseObject(result);

        //特殊化处理第一个元素
        JSONArray data = jsonObject.getJSONArray("data");
        jsonObject = (JSONObject)data.get(0);
        Place place = JSONObject.parseObject(jsonObject.toJSONString(), Place.class);
        place.setChildren(null);
        list.add(place);
        List<Place> children = TreeUtil.recursive(jsonObject.getJSONArray("children"), jsonObject, list, Place.class);
        System.out.println(children);
    }
}
