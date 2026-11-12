package com.outsystems.batterycheck;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Reads the sticky ACTION_BATTERY_CHANGED broadcast once and returns the level and charging state.
 * No permission is required and no receiver is kept registered.
 */
public class BatteryCheck extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getStatus".equals(action)) {
            getStatus(callbackContext);
            return true;
        }
        return false;
    }

    private void getStatus(CallbackContext callbackContext) {
        Intent status = cordova.getActivity().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (status == null) {
            callbackContext.error("Battery status is not available on this device.");
            return;
        }

        int level = status.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = status.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        int plugged = status.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

        int percent = scale > 0 ? Math.round(level * 100f / scale) : -1;

        try {
            JSONObject result = new JSONObject();
            result.put("level", percent);
            result.put("isPlugged", plugged != 0);
            callbackContext.success(result);
        } catch (JSONException e) {
            callbackContext.error(e.getMessage());
        }
    }
}
