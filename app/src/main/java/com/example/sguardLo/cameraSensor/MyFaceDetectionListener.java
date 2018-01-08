package com.example.sguardLo.cameraSensor;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Face;

/**
 * Created by Sguard Lo on 01/01/2018.
 */

public class MyFaceDetectionListener extends Activity implements Camera.FaceDetectionListener{

    private boolean detected = false;

    @Override
    public void onFaceDetection(Face[] faces, Camera camera) {

        if (faces.length > 0 && !detected){
            detected = true;
            CameraActivity.getPrompt().setText(this.toString());
        }
    }

    public boolean isDetected() {
        return detected;
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
