package przemsza.com.github.shopproj.model.time;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class DeliveryTime {

    private Map<Integer, String> times;

    public DeliveryTime() {
        times  = new TreeMap<>();
        times.put(1, "30");
        times.put(2, "45");
        times.put(3, "60");
        times.put(4, "75");
        times.put(5, "90");
        times.put(6, "105");
        times.put(7, "120");
    }

    public Map<Integer, String> getTimes() {
        return times;
    }
}
