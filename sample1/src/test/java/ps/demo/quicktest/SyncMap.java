package ps.demo.quicktest;

import lombok.SneakyThrows;

import java.util.*;

public class SyncMap {

    static Map<String, Long> map = Collections.synchronizedMap(new HashMap<String, Long>());

    @SneakyThrows
    public static void main(String[] args) {
        Set s = map.keySet();

        String key = "k1";
        for (int i = 0; i < 1000000000; i++) {
            Long lastMs = map.get(key);
            if (lastMs == null) {
                map.put(key, System.currentTimeMillis());
            }
            lastMs = map.get(key);
            if (System.currentTimeMillis() - lastMs < 2000) {
                //System.out.println("continue");
                continue;
            }
            map.put(key, System.currentTimeMillis());
            System.out.println("Ok doing something~ "+map.get(key));
            Thread.sleep(300);
        }

        System.out.println("End.");
//        synchronized(map) {  // Synchronizing on map, not s!
//            Iterator i = s.iterator(); // Must be in synchronized block
//            while (i.hasNext()) {
//                System.out.println("map key"+i.hasNext());
//            }
//        }
    }
}
