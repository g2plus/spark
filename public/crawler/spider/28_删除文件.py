import os

def delete_files(file_list):
    for file_path in file_list:
        try:
            os.remove(file_path)
            print("File removed:", file_path)
        except FileNotFoundError:
            print("File not found:", file_path)
        except PermissionError:
            print("Permission denied:", file_path)

# Example usage: delete files "example1.txt" and "example2.txt" in the current directory
file_list = []
file_list.append('E:\\develop\\gitee.com\\source\spark\\public\\dubbo\\dubbo-ajax\\dubbo-web\\src\\main\\webapp\\audio\\GEM.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\program\\ruoyi-vue\\ruoyi\\ruoyi-admin\\target\\ruoyi-admin.jar')
file_list.append('E:\\develop\\gitee.com\\source\spark\\public\\dubbo\\dubbo-jsp\\dubbo-web\\src\\main\\webapp\\audio\\GEM.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\heima_mm\\heima_mm\\src\\main\\webapp\\upload\\test.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\maven\\maven\\ssm\\src\\main\\webapp\\audio\\平行世界-G_E_M_邓紫棋-165923410.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\maven\\maven\\ssm\\src\\main\\webapp\\WEB-INF\\audio\\平行世界-G_E_M_邓紫棋-165923410.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\maven\\maven\\ssm_controller\\src\\main\\webapp\\audio\\平行世界-G_E_M_邓紫棋-165923410.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\maven\\maven\\ssm_controller\\src\main\\webapp\\WEB-INF\\audio\\平行世界-G_E_M_邓紫棋-165923410.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\report\\javaweb\\backend.docx')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\springboot\\springboot-dubbo-parent\\springboot-dubbo-consumer\\src\\main\\resources\\static\\audio\\GEM.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\springboot\\springboot-springsecurity\\src\\main\\resources\\static\\css\\study\\study.psd')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\springmvc\\springmvc\\ssm_test\\src\\main\\webapp\\WEB-INF\\audio\\平行世界-G_E_M_邓紫棋-165923410.flac')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\web\\css\\shopping\\shopping.psd')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\web\\css\\study\\study.psd')
file_list.append('E:\\develop\\gitee.com\\source\\spark\\public\\servlet\\web\\WEB-INF\\lib\\spire.doc.free-2.0.0.jar')

print(len(file_list))
delete_files(file_list)

