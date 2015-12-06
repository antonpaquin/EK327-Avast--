package bilgerat.wizzy.avast.Utils;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by Anton on 12/6/2015.
 * The HTTP interface which should be the only thing talking with the server
 * httpGet is private for a reason, don't touch it
 */
public class HttpApi {

    //"returns" actually means calling handler.onSuccess(String) which is automatically done by properly calling httpGet
    //For all intents and purposes the end of the function is httpGet(...);

    public static void getVirusHistoryGraph(String virusId, ResponseHandler handler) {
        //gets the URL of an image that is the history graph of virus infections over time
        //String
    }

    public static void getHostInfected(String hostId, ResponseHandler handler) {
        //Returns what has infected the host at hostId
        //JSON
    }

    public static void getVirusInfected(String virusId, ResponseHandler handler) {
        //Gets stats of what the virus has infected -- number, mean % per person
        //JSON
    }

    public static void getVirusMap(String virusId, ResponseHandler handler) {
        //Gets the URL of an image which is a map of everyone that has some infection with the virus
        //String
    }

    public static void createVirus(String hostId, Map<String,String> params, ResponseHandler handler) {
        //Returns a virus ID
        //String
        //Also creates the virus in the DB with the given params -- virus design
    }

    public static void getLocalVirus(String hostId, Map<String,String> params, ResponseHandler handler) {
        //Returns a small random sample (10? 100?) of viruses in the local area as defined by GPS (params)
        //Should be unique responses, but not returning enough is OK
        //JSON
    }

    public static void tellInfected(String hostId, Map<String,String> params, ResponseHandler handler) {
        //Tells the server what has infected the host and how much
        //Response should just be a basic Okay
        //String or JSON
    }

    private static void httpGet(final String url, final Map<String,String> requestParams, final ResponseHandler handler) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(@Nullable Void[] params) {
                URL target;
                URLConnection connection;
                BufferedReader reader;
                String response = "";
                try {
                    Uri.Builder builder = Uri.parse(url).buildUpon();
                    if (requestParams != null) {
                        for (String k: requestParams.keySet()) {
                            builder.appendQueryParameter(k,requestParams.get(k));
                        }
                    }
                    target = new URL(builder.build().toString());

                    connection = target.openConnection();
                    connection.setDoInput(true);

                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while((line = reader.readLine()) != null) {
                        response = response + line;
                    }

                    handler.onSuccess(response);
                    reader.close();

                }catch (IOException e) {
                    e.printStackTrace();
                    handler.onFail(e.getMessage());
                }
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                handler.postExecute();
            }
        }.execute((Void)null);
    }
    public static abstract class ResponseHandler {
        public abstract void onSuccess(String response);
        public abstract void onFail(String response);
        public void postExecute() {}
    }
}
