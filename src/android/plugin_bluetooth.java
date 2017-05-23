package plugin_bluetooth;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mequonic.viamobile.ViaMobileHelper;
/*import com.mequonic.viamobile.service.ViaMobileServiceBindingActivity;*/

/**
 * This class echoes a string called from JavaScript.
 */
public class plugin_bluetooth extends CordovaPlugin {

    ViaMobileHelper a = new ViaMobileHelper();

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if(action.equals("miMetodo")) {
            this.miMetodo(callbackContext);
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void miMetodo(CallbackContext callbackContext) {
        String message = "";
        if (a.TRANSACTIONRESULT_FAILED == 1){
            message = "1";
        } else {
            message = "mal";
        }
        callbackContext.success(message);
    }
}
