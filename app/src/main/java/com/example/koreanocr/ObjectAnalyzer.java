package com.example.koreanocr;

import static android.net.wifi.p2p.WifiP2pManager.ERROR;

import android.content.Context;
import android.media.Image;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import androidx.annotation.OptIn;
import androidx.camera.core.ExperimentalGetImage;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;
import androidx.camera.view.PreviewView;

import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.objects.ObjectDetection;
import com.google.mlkit.vision.objects.ObjectDetector;
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions;

import java.util.Locale;

public class ObjectAnalyzer implements ImageAnalysis.Analyzer {
    private ObjectDetector detector;
    private PreviewView screen;
    private Context context;
    private TextView textView;
    private TextToSpeech tts;
    // Live detection and tracking
    private ObjectDetectorOptions options =
            new ObjectDetectorOptions.Builder()
                    .setDetectorMode(ObjectDetectorOptions.STREAM_MODE)
                    .enableClassification()  // Optional
                    .build();
    public ObjectAnalyzer(Context context) {
        this.context = context;
        this.screen = WordDetectFragment.previewView;
        this.textView = WordDetectFragment.textView;
        this.detector = ObjectDetection.getClient(options);
        this.tts = new TextToSpeech( context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.KOREAN);
                    tts.setSpeechRate(2);
                }
            }
        });
    }

    @Override
    @OptIn(markerClass = ExperimentalGetImage.class)
    public void analyze(ImageProxy imageProxy) {
        Image mediaImage = imageProxy.getImage();
        if (mediaImage != null) {
            InputImage image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.getImageInfo().getRotationDegrees());

        }
    }

}
