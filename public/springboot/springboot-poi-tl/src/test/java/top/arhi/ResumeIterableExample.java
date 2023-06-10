package top.arhi;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.Numberings;
import com.deepoove.poi.data.Pictures;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@DisplayName("Foreach resume example")
public class ResumeIterableExample {

    ResumeDataV2 datas = new ResumeDataV2();

    @BeforeEach
    public void init() {
        datas.setPortrait(Pictures.ofLocal("src/test/resources/sayi.png").size(100, 100).create());
        datas.setName("卅一");
        datas.setJob("BUG工程师");
        datas.setPhone("18080809090");
        datas.setSex("男");
        datas.setProvince("杭州");
        datas.setBirthday("2000.08.19");
        datas.setEmail("adasai90@gmail.com");
        datas.setAddress("浙江省杭州市西湖区古荡1号");
        datas.setEnglish("CET6 620");
        datas.setUniversity("美国斯坦福大学");
        datas.setProfession("生命工程");
        datas.setEducation("学士");
        datas.setRank("班级排名 1/36");
        datas.setHobbies("音乐、画画、乒乓球、旅游、读书\nhttps://github.com/Sayi");

        // 技术栈部分
        TextRenderData textRenderData = new TextRenderData("SpringBoot、SprigCloud、Mybatis");
        Style style = Style.builder().buildFontSize(10).buildColor("7F7F7F").buildFontFamily("微软雅黑").build();
        textRenderData.setStyle(style);
        datas.setStack(Numberings.of(textRenderData, textRenderData, textRenderData).create());

        /*
         * {{?experiences}} {{company}} {{department}} {{time}} {{position}}
         * {{*responsibility}} {{/experiences}}
         */
        List<ExperienceData> experiences = new ArrayList<ExperienceData>();
        ExperienceData data0 = new ExperienceData();
        data0.setCompany("香港微软");
        data0.setDepartment("Office 事业部");
        data0.setTime("2001.07-2020.06");
        data0.setPosition("BUG工程师");
        textRenderData = new TextRenderData("负责生产BUG，然后修复BUG，同时有效实施招聘行为");
        textRenderData.setStyle(style);
        data0.setResponsibility(Numberings.of(textRenderData, textRenderData).create());
        ExperienceData data1 = new ExperienceData();
        data1.setCompany("自由职业");
        data1.setDepartment("OpenSource 项目组");
        data1.setTime("2015.07-2020.06");
        data1.setPosition("研发工程师");
        textRenderData = new TextRenderData("开源项目的迭代和维护工作");
        textRenderData.setStyle(style);
        TextRenderData textRenderData1 = new TextRenderData("持续集成、Swagger文档等工具调研");
        textRenderData1.setStyle(style);
        data1.setResponsibility(Numberings.of(textRenderData, textRenderData1, textRenderData).create());
        experiences.add(data0);
        experiences.add(data1);
        experiences.add(data0);
        experiences.add(data1);
        datas.setExperiences(experiences);
    }

    @Test
    public void testResumeExample() throws Exception {
        XWPFTemplate template = XWPFTemplate.compile("src/test/resources/template/iterable_resume.docx").render(datas);

        FileOutputStream out = new FileOutputStream("target/out_example_resume_iterable.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }


    @Test
    public void testOne() {
        //模板地址
        String templateFilePath = "src/test/resources/template/example2.docx";
        //生成文件的保存地址
        String destFilePath = "target/example2_instance.docx";
        Map<String, Object> datas = new HashMap<String, Object>();

        Student student1 = new Student();
        student1.setAge("12");
        student1.setIndex("1");
        student1.setName("张三");
        student1.setSex("男");
        Student student2 = new Student();
        student2.setAge("13");
        student2.setIndex("2");
        student2.setName("李四");
        student2.setSex("男1");
        List<Student> studentList = Arrays.asList(student1, student2);
        datas.put("list1", studentList);

        // 行循环实例
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();

        //这里可以指定一个config类，用来指定一些规则，
        Configure config = Configure.builder()
                .bind("list1", policy).build();

        XWPFTemplate compile = XWPFTemplate.compile(templateFilePath, config);
        compile.render(datas);

        try {
            compile.writeToFile(destFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Test
    public void test3() {


        //模板地址
        String templateFilePath = "src/test/resources/template/document.docx";
        //生成文件的保存地址
        String destFilePath = "target/document_instance.docx";


        Document document = new Document();
        document.setCompanyCount("2");
        document.setProjectCount("2");
        OrderDetail detail1 = new OrderDetail();
        detail1.setOrderDeptId("1001");
        detail1.setPrice("20");
        detail1.setOrderId("detail1");
        OrderDetail detail2 = new OrderDetail();
        detail2.setOrderDeptId("1002");
        detail2.setPrice("201");
        detail2.setOrderId("detail2");
        List<OrderDetail> orderDetailList1 = new ArrayList<>();
        orderDetailList1.add(detail1);
        orderDetailList1.add(detail2);
        Order order1 = new Order();
        order1.setOrderDetailList(orderDetailList1);
        order1.setTotalPrice("221");
        order1.setOrderDetailList(orderDetailList1);
        OrderDetail detail3 = new OrderDetail();
        detail3.setOrderDeptId("10012");
        detail3.setPrice("10");
        detail3.setOrderId("detail3");
        OrderDetail detail4 = new OrderDetail();
        detail4.setOrderDeptId("1002");
        detail4.setPrice("200");
        detail4.setOrderId("detail4");

        List<OrderDetail> orderDetailList2 = new ArrayList<>();
        orderDetailList2.add(detail3);
        orderDetailList2.add(detail4);


        Order order2 = new Order();
        order2.setOrderDetailList(orderDetailList2);
        order2.setTotalPrice("210");


        List<Order> orderList = new ArrayList<>();

        orderList.add(order1);
        orderList.add(order2);

        document.setOrderList(orderList);


        // 行循环实例
        LoopRowTableRenderPolicy policy = new LoopRowTableRenderPolicy();
        //这里可以指定一个config类，用来指定一些规则，
        Configure config = Configure.builder()
                .bind("orderDetailList", policy)
                .bind("orderList",policy).build();


        XWPFTemplate compile = XWPFTemplate.compile(templateFilePath, config);
        compile.render(datas);

        try {
            compile.writeToFile(destFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

//    @Test
//    public void test4() throws IOException {
//        //模板地址
//        String templateFilePath = "src/test/resources/template/document.docx";
//        //生成文件的保存地址
//        String destFilePath = "target/document_instance.docx";
//
//        Map<String, Object> map = new HashMap<>();
//
//        Map<String, Object> map1 = new HashMap<>() {
//            {
//                put("tex1", "hhhhh11");
//            }
//        };
//
//        Map<String, Object> map2 = new HashMap<>() {
//            {
//                put("tex1", "hhhhh22");
//            }
//        };
//
//
//        List<Map<String, Object>> maps = Arrays.asList(map1, map2);
//        map.put("tests", maps);
//        map.put("tex2", "哈哈");
//
//        ConfigureBuilder builder = Configure.builder();
//        XWPFTemplate compile = XWPFTemplate.compile(templateFilePath + "test4.docx", builder.build());
//        compile.render(map);
//        compile.writeToFile(destFilePath + "out_test4.docx");
//
//    }


    @Test
    public void test5() throws IOException {
        //模板地址
        String templateFilePath = "src/test/resources/template/document1.docx";
        //生成文件的保存地址
        String destFilePath = "target/document1_instance.docx";
        XWPFTemplate template = XWPFTemplate.compile(templateFilePath).render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});

        template.writeAndClose(new FileOutputStream(destFilePath));
    }

}

