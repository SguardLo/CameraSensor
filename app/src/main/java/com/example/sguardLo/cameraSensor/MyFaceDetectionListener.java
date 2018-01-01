package com.example.sguardLo.cameraSensor;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Face;
import android.util.Log;

/**
 * Created by Sguard Lo on 01/01/2018.
 */

public class MyFaceDetectionListener extends Activity implements Camera.FaceDetectionListener{

    private boolean detected = false;

    @Override
    public void onFaceDetection(Face[] faces, Camera camera) {

        if (faces.length > 0 && !detected){
            Log.d("FaceDetection", "face detected: "+ faces.length +
                    " Face 1 Location X: " + faces[0].rect.centerX() +
                    "Y: " + faces[0].rect.centerY() );
            detected = true;
            String string = this.toString();
            sendText(string);
        }
    }

    public boolean isDetected() {
        return detected;
    }

    public void sendText(String string){
        CameraActivity.getPrompt().setText(string);
    }

    public void setDetected() {
        this.detected = false;
    }

    @Override
    public String toString() {
        String output = "No Detected";

        if(isDetected()){
            output = "Detected";
            return output;
        }
        return output;
    }

}
