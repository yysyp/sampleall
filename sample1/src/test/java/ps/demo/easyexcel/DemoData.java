package ps.demo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {

    @ExcelProperty(value = "StudentNo",index = 0)
    private Integer sno;

    @ExcelProperty(value = "StudentName",index = 1)
    private String sname;
}
