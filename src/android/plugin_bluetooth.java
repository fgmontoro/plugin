package plugin_bluetooth;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.mequonic.viamobile.ViaMobileHelper;
import com.mequonic.viamobile.service.ViaMobileServiceBindingActivity;

public class plugin_bluetooth extends CordovaPlugin implements ViaMobileHelper.Listener {

    private ViaMobileServiceBindingActivity activity = null;
    private ViaMobileHelper.Listener listener;
    private ViaMobileHelper viaMobileHelper;
    private Context contexto;
    private boolean isStarted = false;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if(action.equals("getBTStatus")) {
            Integer BTStatus =this.getBluetoothState(contexto);
            Log.d("INFO","El estado del BTR es: " + BTStatus);
            String message = "ON";
            callbackContext.success(message);
        } else if(action.equals("bindBT")) {
            this.bind(contexto);
            Log.d("INFO","BIND");
            String message = "BIND";
            callbackContext.success(message);
        } else if(action.equals("unBindBT")) {
            this.unbind(contexto);
            Log.d("INFO","UNBIND");
            String message = "UNBIND";
            callbackContext.success(message);
        } else if(action.equals("start")) {
            this.init(listener);
            Log.d("INFO","STARTED");
            String message = "STARTED";
            callbackContext.success(message);
        }
        return false;
    }

    public void init(ViaMobileHelper.Listener listener){
        if (isStarted){
            isStarted = true;
        }

        listener = this;
        contexto = this.cordova.getActivity().getApplicationContext();
        viaMobileHelper = new ViaMobileHelper();
        viaMobileHelper.setListener(listener);
    }


    public void bind(Context context){
        if (viaMobileHelper != null) {
            viaMobileHelper.setTransactionManagerEnabled("token");
            viaMobileHelper.bindService(context);
        }
    }

    public void unbind(Context context){
        if (viaMobileHelper != null) {
            Log.d("INFO","UNBIND -1-");
            viaMobileHelper.unbindService(context);
        }
    }

    public int getBluetoothState(Context context){
        if (viaMobileHelper != null) {
            Log.d("INFO","Estado gestor de transacciones: " + viaMobileHelper.isTransactionManagerEnabled());
            return viaMobileHelper.getBluetoothState(context);
        } return 88;
    }

    @Override
    public void onViaMobileServiceConnected() {
        Log.d("INFO","onViaMobileServiceConnected");
    }

    @Override
    public void onViaMobileServiceDisconnected() {
        Log.d("INFO","onViaMobileServiceDisconnected");
    }

    @Override
    public void onViaMobileBluetoothStateChanged() {
        Integer BTStatus =this.getBluetoothState(contexto);
        Log.d("INFO","onViaMobileBluetoothStateChanged al estado :" + BTStatus);
    }

    @Override
    public void onViaMobileTransactionStateChanged(boolean b) {
        Log.d("INFO","onViaMobileTransactionStateChanged");
    }

    @Override
    public void onViaMobileTransactionFinished(int i, byte[] bytes) {
        Log.d("INFO","onViaMobileTransactionFinished");
    }
}
