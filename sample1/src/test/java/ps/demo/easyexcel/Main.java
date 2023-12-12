package ps.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        String filename = "C:\\Users\\Dell\\2023-12-12_214012-myexcel.xlsx";

        EasyExcel.write(filename, DemoData.class).sheet("Data list").doWrite(Main.getData());

        List<DemoData> list = new ArrayList<>();
        EasyExcel.read(filename, DemoData.class, new ReadListener<DemoData>() {
            @Override
            public void invoke(DemoData o, AnalysisContext analysisContext) {
                list.add(o);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                //done!
            }
        }).sheet().doRead();

        System.out.println("-->Read excel result=" + list);
        FileUtils.delete(new File(filename));
    }

    public static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("Test" + i);
            list.add(demoData);
        }
        return list;
    }


}
