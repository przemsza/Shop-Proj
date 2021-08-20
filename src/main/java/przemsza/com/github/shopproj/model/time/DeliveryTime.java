package przemsza.com.github.shopproj.model.time;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
public class DeliveryTime {

    private Map<Integer, String> times;

    public DeliveryTime() {
        times  = new TreeMap<>();
        times.put(1, "30 minut");
        times.put(2, "45 minut");
        times.put(3, "60 minut");
        times.put(4, "75 minut");
        times.put(5, "90 minut");
        times.put(6, "105 minut");
        times.put(7, "120 minut");
    }

    public Map<Integer, String> getTimes() {
        return times;
    }
}
