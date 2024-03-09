package com.example.testolder;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import org.json.JSONObject;




//public class fetchWorker  extends Worker {
//    public DataFetcherWorker () {
//
//    }
//}
public class fetchWorker extends Worker {


    public fetchWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            String url = "http://192.168.0.101:8080/check";
            String response = http.get(url); // Assume you have an HttpUtil class for making HTTP requests

            JSONObject jsonObject = new JSONObject(response);

            int checkValue = jsonObject.getInt("check");
            String name = jsonObject.getString("name");
            if (checkValue == 5) {
                NotificationHelper.makeNotification(getApplicationContext(), name ,checkValue);
            }

            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }
    }
}