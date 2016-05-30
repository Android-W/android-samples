package androidsamples.androidw.com.androidsamples.firebase;

import java.util.Map;

public class Push {

    public String message;
    public String instanceId;

    public Push() {

    }

    public Push(Map<String, String> data) {
        parse(data);
    }

    private void parse(Map<String, String> data) {
        if (data != null) {
            if (data.containsKey("message")) message = data.get("message");
            if (data.containsKey("instanceId")) instanceId = data.get("instanceId");
        }
    }

}
