import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MyLogger implements Logger {

    private static HashMap<String,Long> startMap = new HashMap<>();
    private static HashMap<String,Long> endMap = new HashMap<>();

    @Override
    public void start(String processId, long startTime) {
        startMap.put(processId,startTime);
    }

    @Override
    public void end(String processId, long endTime) {
        endMap.put(processId,endTime);
    }

    @Override
    public void print() {
        LogEntity [] logs = new LogEntity[startMap.size()];
        int index = 0;
        for (Map.Entry<String,Long> m : startMap.entrySet()) {
            logs[index++] = new LogEntity(m.getKey(),m.getValue());
        }
        Arrays.sort(logs,(x,y)->(int)(x.getStartTime()-y.getStartTime()));
        for (int i = 0; i < logs.length; i++) {
            LogEntity current = logs[i];
            if(endMap.containsKey(current.getProcessId())) {
                System.out.println(current.getProcessId() +" started at " + current.getStartTime() + " and ended at " + endMap.get(current.getProcessId()));
            }
        }
    }
}
class LogEntity {

    private String processId;
    private long startTime;

    public long getStartTime() {
        return startTime;
    }

    public String getProcessId() {
        return processId;
    }

    public LogEntity(String processId, long startTime) {
        this.processId = processId;
        this.startTime = startTime;
    }
}
