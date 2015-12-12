package bilgerat.wizzy.avast.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import bilgerat.wizzy.avast.Activities.DesignAppearanceActivity;
import bilgerat.wizzy.avast.Services.ConnectionService;
import bilgerat.wizzy.avast.Support.VirusView;

/**
 * Created by Anton on 12/6/2015.
 *
 */

public class InfectionModel {
    /*
     * Set of functions that deals with adding new information to and getting information from InfectionService
     */
    public ArrayList<Virus> viruses = new ArrayList<>();
    public Map<Virus,Double> infection = new HashMap<>();
    public Virus health = new Virus();
    public static Map<Integer, Double> resistance;
    public static int hostId;

    private final double resistance_growth = 12.0/(14*24*4);

    public InfectionModel() {
        health = new Virus();
        health.ID = -1;
        Arrays.fill(health.colors, 0x93ff72);
        Arrays.fill(health.shapes, VirusView.GONE);
        health.name = "Health";
        health.originalLatitude = 0;
        health.aggression = 1;
        health.strength = 1;
        health.heatResist = 1;
        health.infectivity_far = 0;
        health.infectivity_near = 0;
        infection.put(health, 0.);
    }

    public void purge_low_viruses() {
        double growthFactor = 0;
        for (int i=0; i<viruses.size(); i++) {
            Virus v = viruses.get(i);
            if (infection.get(v) < 0.02) {
                viruses.remove(i);
                growthFactor += infection.get(v);
                infection.remove(v);
            }
        }
        infection.put(health, infection.get(health) + growthFactor);
    }

    public void gain_resistances() {
        for (int i=0; i<viruses.size(); i++) {
            Virus v = viruses.get(i);
            resistance.put(v.ID, resistance.get(v.ID) + resistance_growth);
        }
    }

    public static double attack(Virus source, Virus target) {
        double s1 = source.strength;
        if (source.ID == -1) {
            s1 += resistance.get(target.ID);
        }
        s1 *= getHeatEffectiveness(source);
        double s2 = target.strength * getHeatEffectiveness(target);
        return Math.max(0, s1-s2);
    }

    public static double getHeatEffectiveness(Virus v) {
        double lat = ConnectionService.getLatitude();
        double delta = Math.abs(lat - v.originalLatitude);
        delta *= 1-v.heatResist;
        return Math.exp(-(delta*delta/2));
    }

    public ArrayList<Virus> get_aggressors() {
        Random r = new Random();
        ArrayList<Virus> res = new ArrayList<>();
        for (int i=0; i<viruses.size(); i++) {
            Virus v = viruses.get(i);
            if (v.aggression > r.nextDouble() % 1) {
                res.add(v);
            }
        }
        res.add(health);
        return res;
    }

    public static class Virus {
        public int ID;
        public int[] colors;
        public int[] shapes;
        public String name;
        public double originalLatitude;

        public double aggression;
        public double strength;
        public double heatResist;
        public double infectivity_near;
        public double infectivity_far;

        public Virus() {
            colors = new int[13];
            shapes = new int[12];
        }
    }

    public static Virus parseJSONVirus(String json) {
        try {
            Virus v = new Virus();
            JSONObject j = new JSONObject(json);
            return v;
        }catch (JSONException e) {
            return new Virus();
        }
    }

    public void new_virus(Virus v) {
        viruses = new ArrayList<>();
        viruses.add(v);
        infection = new HashMap<>();
        infection.put(health, 0.);
        infection.put(v, 1.);
    }
}
