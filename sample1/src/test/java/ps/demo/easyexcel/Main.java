package ps.demo.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {

        String filename = "C:\\Users\\Dell\\myexcel002.xlsx";

//        EasyExcel.write(filename, DemoData.class).sheet("Data list").needHead(true).doWrite(Main.getData());
//
//        List<DemoData> list = new ArrayList<>();
//        EasyExcel.read(filename, DemoData.class, new ReadListener<DemoData>() {
//            @Override
//            public void invoke(DemoData o, AnalysisContext analysisContext) {
//                list.add(o);
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
//                //done!
//            }
//        }).sheet().headRowNumber(1).doRead();
//
//        System.out.println("-->(1)Read excel result=" + list);

        List<LinkedHashMap<String, Object>> dataList = new ArrayList<>();
        EasyExcel.read(filename, new AnalysisEventListener<Map<String, Object>>() {
            private Map<Integer, String> headMap;

            @Override
            public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
                this.headMap = headMap;
            }

            @Override
            public void invoke(Map<String, Object> rowData, AnalysisContext analysisContext) {
                LinkedHashMap<String, Object> dataMap = new LinkedHashMap<>();
                for (int i = 0, n = rowData.size(); i < n; i++) {
                    String key = (headMap.get(i)+i).trim();
                    Object value = rowData.get(i);
//                    int keyPrefix = 0;
//                    String key1 = key;
//                    while (dataMap.containsKey(key1)) {
//                        keyPrefix++;
//                        key1 = key+keyPrefix;
//                    }
                    dataMap.put(key, value);
                }
                dataList.add(dataMap);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().headRowNumber(1).doRead();

        System.out.println("------------------------------------");
        System.out.println("-->(2)Read excel dataList.0=" + dataList.get(0));
        System.out.println("-->(2)Read excel dataList.size=" + dataList.size());
        System.out.println("-->(2)Read excel dataList=" + dataList);
        //Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Gson gson = new Gson();
        String json1 = gson.toJson(dataList);
        System.out.println("-->(2)Read excel json1=" + json1);

        //FileUtils.delete(new File(filename));
    }

    public static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("Test" + i);
            demoData.setAge(RandomUtils.nextInt(10, 90));
            list.add(demoData);
        }
        return list;
    }


}
