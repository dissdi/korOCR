package com.example.koreanocr;

import static android.speech.tts.TextToSpeech.ERROR;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class MainFragment extends Fragment {

    private SeekBar seekBar;

    private TextToSpeech tts;

    public static float speed;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main_page, container, false);
        seekBar = (SeekBar) v.findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // SeekBar 값이 변경될 때마다 호출됩니다.
                if (progress == 0){
                    speed = 0.6f;
                    tts.setSpeechRate(speed);
                    MyAnalyzer.tts.setSpeechRate(speed);
                }
                else {
                    speed = (float) (progress) * 1.2f;
                    tts.setSpeechRate(speed);
                }
                tts.speak("이것은 음성 속도 조절 기능 입니다.", TextToSpeech.QUEUE_FLUSH, null);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 사용자가 SeekBar를 터치하기 시작할 때 호출됩니다.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 사용자가 SeekBar를 터치를 끝낼 때 호출됩니다.
            }
        });
        return (ViewGroup) inflater.inflate(R.layout.main_page, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // TTS 객체가 남아있다면 실행을 중지하고 메모리에서 제거한다.
        if(tts != null){
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }

}
