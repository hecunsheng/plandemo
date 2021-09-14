package run.star.plan.ISP.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hecs
 * @date 2021/7/20 14:38
 */
public class SimpleHttpServer {

    private String host;
    private int port;
    private Map<String, List<Viewer>> viewers = new HashMap<>();

    public SimpleHttpServer(String host, int port) {
    }

    public void addViewers(String urlDirectory, Viewer viewer) {
        if (!viewers.containsKey(urlDirectory)) {
            viewers.put(urlDirectory, new ArrayList<Viewer>());
        }
        this.viewers.get(urlDirectory).add(viewer);
    }

    public void run() {
        viewers.values().forEach(list ->
                list.forEach(viewer ->
                        System.out.println(viewer.outputInPlainText())
                ));
    }
}
