package com.example.sguardLo.cameraSensor;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.hardware.Camera.*;

public class CameraActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;
    static TextView prompt;
    Button button;
    MyFaceDetectionListener faceDetectionListener = new MyFaceDetectionListener();
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("locations");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // Create an instance of Camera
        mCamera = getCameraInstance();
        mCamera.setFaceDetectionListener(faceDetectionListener);
        button = findViewById(R.id.button_capture);
        prompt = findViewById(R.id.prompt);

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        prompt.setText(faceDetectionListener.toString());
        myRef.child("A4").setValue("free");
    }

    public static TextView getPrompt() {
        return prompt;
    }

    public void onClick(View view) {
        faceDetectionListener.setDetected();
        prompt.setText(faceDetectionListener.toString());
        myRef.child("A4").setValue("free");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null){
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

}
