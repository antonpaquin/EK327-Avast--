package bilgerat.wizzy.avast.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import bilgerat.wizzy.avast.Utils.HttpApi;
import bilgerat.wizzy.avast.Utils.InfectionModel;

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
     *   New virus:
     *      Virus is set to 100% infected on current host, everything else deleted
     *   Tick:
     *      Once every 15 minutes
     *      Each virus gets a growth check
     *      Remove low% viruses
     */

    public static InfectionModel model;
    private static final int tickInterval = 15;

    public InfectionService() {
        final Handler handler = new Handler();
        Runnable doTicks = new Runnable() {
            @Override
            public void run() {
                try {
                    tick();
                } catch (Exception e) {
                   e.printStackTrace();
                } finally {
                    handler.postDelayed(this, tickInterval*60*1000);
                }
            }
        };
        handler.post(doTicks);
        model = new InfectionModel();
    }

    public void tick() {
        model.purge_low_viruses();
        ArrayList<InfectionModel.Virus> aggressors = model.get_aggressors();
        for (int i=0; i<aggressors.size(); i++) {
            growthEvent(aggressors.get(i));
        }
        model.gain_resistances();
    }

    public static void growthEvent(InfectionModel.Virus aggressor) {
        for (int j=0; j<model.viruses.size(); j++) {
            InfectionModel.Virus victim = model.viruses.get(j);
            double transfer = InfectionModel.attack(aggressor, victim);
            transfer *= 0.05;
            transfer *= (model.infection.get(victim));
            transfer = Math.min(model.infection.get(victim), transfer);
            model.infection.put(aggressor, model.infection.get(aggressor) + transfer);
            model.infection.put(victim, model.infection.get(victim) - transfer);
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static int[] getTransmission() {
        ArrayList<Integer> li = new ArrayList<>();
        Random r = new Random();
        for (int i=0; i<model.viruses.size(); i++) {
            InfectionModel.Virus v = model.viruses.get(i);
            if (r.nextDouble() % 1 < v.infectivity_near * model.infection.get(v)) {
                li.add(v.ID);
            }
        }
        int[] res = new int[li.size()];
        for (int i=0; i<li.size(); i++) {
            res[i] = li.get(i);
        }
        return res;
    }

    public static void transmissionEvent(int virusID) {
        HttpApi.getVirus(Integer.toString(virusID), new HttpApi.ResponseHandler() {
            @Override
            public void onSuccess(String response) {
                InfectionModel.Virus v = InfectionModel.parseJSONVirus(response);
                InfectionService.growthEvent(v);
            }

            @Override
            public void onFail(String response) {
                Log.d("Avast: ", response);
            }
        });
    }
}
