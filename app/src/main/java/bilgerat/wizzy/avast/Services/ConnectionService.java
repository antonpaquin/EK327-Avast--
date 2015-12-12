package bilgerat.wizzy.avast.Services;

import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.UUID;

public class ConnectionService extends Service {
    //Runs in the background, and propagates viruses via bluetooth / gps
    //Should:
        //Make the device discoverable
        //Occasionally (30s? 1m? interval) ping for discoverable devices
        //If a device is discovered, establish a connection and send it virus data (an ID number -- more info can be downloaded from the server)
        //On a long (1d? 12h?) interval, ask for city-wide virus data from the server (send GPS location, get a few new potential infections)
        //If a virus connection is requested, send data to InfectionModel

    private static final int bluetoothInterval = 60;
    private static final UUID uuid = UUID.fromString("5917204b-a55c-454b-99ee-9b72b1dedc67");

    private static BluetoothAdapter adapter;

    public ConnectionService() {
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(acceptBluetooth, filter);
        final Handler handler = new Handler();
        Runnable bluetoothCheck = new Runnable() {
            @Override
            public void run() {
                try {
                    doSearchBluetooth();
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    handler.postDelayed(this, bluetoothInterval*1000);
                }
            }
        };
        handler.post(bluetoothCheck);
    }

    public void doSearchBluetooth() {
        try {
            BluetoothServerSocket server = adapter.listenUsingRfcommWithServiceRecord("Avast", uuid);
            BluetoothSocket socket = server.accept();
            sendViruses(socket.getOutputStream());
            receiveViruses(socket.getInputStream());
            socket.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final BroadcastReceiver acceptBluetooth = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                try {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuid);
                    socket.connect();
                    receiveViruses(socket.getInputStream());
                    sendViruses(socket.getOutputStream());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void sendViruses(OutputStream out) throws IOException {
        int[] toTransmit = InfectionService.getTransmission();
        byte[] data = new byte[4];
        for (int i=0; i<toTransmit.length; i++) {
            data = ByteBuffer.allocate(4).putInt(toTransmit[i]).array();
            out.write(data);
        }
        byte[] EOF = ByteBuffer.allocate(4).putInt(Integer.MAX_VALUE).array();
        out.write(EOF);
    }

    public void receiveViruses(InputStream in) throws IOException {
        byte[] data = new byte[4];
        byte[] EOF = ByteBuffer.allocate(4).putInt(Integer.MAX_VALUE).array();
        in.read(data);
        while(data != EOF) {
            InfectionService.transmissionEvent(bytesToInt(data));
            in.read(data);
        }
    }

    public static void startBluetooth(Activity source) {
        adapter = BluetoothAdapter.getDefaultAdapter();
        if (!adapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            source.startActivityForResult(enableBtIntent, 1);
        }
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 0);
        source.startActivity(discoverableIntent);
    }

    public static double getLatitude() {
        return 0;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static int bytesToInt(byte[] bytes) {
        int value = 0;
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;
        }
        return value;
    }
}
