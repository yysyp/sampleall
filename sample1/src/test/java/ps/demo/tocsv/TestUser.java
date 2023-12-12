package ps.demo.tocsv;

import ps.demo.common.MyCsvUtil;
import ps.demo.common.MyFileUtil;

import java.util.ArrayList;
import java.util.List;

public class TestUser {

    public static void main(String[] args) {

        User user1 = new User();
        user1.setName("Tom");
        user1.setAge(22);
        user1.setSex("男");

        User user2 = new User();
        user2.setName("John");
        user2.setAge(21);
        user2.setSex("女");

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        //MyFileUtil.getFileTsInHomeDir("mycsv.csv").getAbsolutePath()
        String path = "C:\\Users\\Dell\\2023-12-12_194012-mycsv.csv";
        MyCsvUtil.writeCSVFile(path,
                users, true, true);
        System.out.println("Complete");
    }
}
