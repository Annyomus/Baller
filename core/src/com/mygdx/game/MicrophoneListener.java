package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioRecorder;

public class MicrophoneListener {
    private final AudioRecorder audioRecorder;
    private final short[] samples;

    public MicrophoneListener() {
        audioRecorder = Gdx.audio.newAudioRecorder(44100, true);
        samples = new short[1024];
    }

    public float getVolume() {
        audioRecorder.read(samples, 0, samples.length);
        float sum = 0;
        for (short sample : samples) {
            sum += sample * sample;
        }
        return (float) Math.sqrt(sum / samples.length);
    }

    public void dispose() {
        audioRecorder.dispose();
    }
}
