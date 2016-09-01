package np.com.gnikesh.studentshub;

import android.app.Application;

import com.parse.Parse;


/**
 * Created by nikesh on 9/2/15.
 */
public class HubApplication extends Application {

    @Override
    public void onCreate() {
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "WzaAjXJ71rIn53jQ8PmFLnuxJbh2tXyuMFVO3gF7", "FrhvNOqjfLWQRvhaQmGD0an4YzWcV73SVPbXvpxH");

        super.onCreate();
    }
}
