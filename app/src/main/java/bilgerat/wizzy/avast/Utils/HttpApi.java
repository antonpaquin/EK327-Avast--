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
 *
 */
public class HttpApi {

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
