package bilgerat.wizzy.avast.Activities;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ConnnectionService extends Service {
    //Runs in the background, and propagates viruses via bluetooth / gps
    //Should:
        //Make the device discoverable
        //Occasionally (30s? 1m? interval) ping for discoverable devices
        //If a device is discovered, establish a connection and send it virus data (an ID number -- more info can be downloaded from the server)
        //On a long (1d? 12h?) interval, ask for city-wide virus data from the server (send GPS location, get a few new potential infections)
        //If a virus connection is requested, send data to InfectionModel
    public ConnnectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
