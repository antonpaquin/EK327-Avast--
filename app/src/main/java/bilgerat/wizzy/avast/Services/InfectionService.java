package bilgerat.wizzy.avast.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class InfectionService extends Service {
    /*
     * Manges change in infection stats over time
     * Should hold all the variables of tracking, and InfectionModel is an interface to these vars
     * Rules:
     *   Any virus with <2% infection is removed
     *   Healthiness is modeled as an unremovable virus with strong aggression and very weak strength
     *   Healthiness gradually develops immunity to new viruses (+strength boost) (timed so 1 medium-strength virus vs health will go extinct in about 2 weeks)
     *   Growth event:
     *      Triggered by aggression stat (%chance per tick)
     *      Successful propotional to strength * heat effectiveness
     *      heat effectiveness: Gaussian distribution, sigma = (|original latitude| - current latitude) * (1-%heat resistance) tuned so equator -> north pole is 5sigma
     *      successful growth transfers 10% of the losing virus's % to the growing virus, min 1% transfer
     *   Viruses are spread via:
     *      infectiousness in transmission method * %infection in original body
     *      Makes a growth check vs the receiving body
     *      Each virus in a transmission event gets a shot at infection
     *   Tick:
     *      Once every 15 minutes
     *      Each virus gets a growth check
     *      Remove low% viruses
     */
    public InfectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
