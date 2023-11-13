package ps.demo.common;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyStringDataResponse extends MyBaseResponse {

    private String data;

    public static MyBaseResponse successWithData(Object strableObj) {
        MyStringDataResponse stringDataResponse = new MyStringDataResponse();
        stringDataResponse.setData(new Gson().toJson(strableObj));
        return stringDataResponse;
    }

}
