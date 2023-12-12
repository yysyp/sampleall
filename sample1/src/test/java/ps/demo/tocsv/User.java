package ps.demo.tocsv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class User {
    @CsvBindByName(column = "name")
    @CsvBindByPosition(position = 0)
    private String name;
    @CsvBindByName(column = "age")
    @CsvBindByPosition(position = 1)
    private Integer age;
    @CsvBindByName(column = "sex")
    @CsvBindByPosition(position = 2)
    private String sex;
}
