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
public class StringDataResponseMy extends MyBaseResponse {

    private String data;

    public static MyBaseResponse successWithData(Object strableObj) {
        StringDataResponseMy stringDataResponse = new StringDataResponseMy();
        stringDataResponse.setData(new Gson().toJson(strableObj));
        return stringDataResponse;
    }

}
