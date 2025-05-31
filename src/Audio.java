import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Audio {
private Clip backgroundmusic;

    public Clip getBackgroundmusic() {
        return backgroundmusic;
    }

    public void setBackgroundmusic(Clip backgroundmusic) {
        this.backgroundmusic = backgroundmusic;
    }

    /**
     * Plays background music
     */
    public void playMusic(){
    try{
File musicpath = new File("src/resources/music2.wav");

if(musicpath.exists()){

    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicpath);

    backgroundmusic = AudioSystem.getClip();
    backgroundmusic.open(audioInput);
    backgroundmusic.loop(Clip.LOOP_CONTINUOUSLY);
    backgroundmusic.start();
    FloatControl volume = (FloatControl) backgroundmusic.getControl(FloatControl.Type.MASTER_GAIN);
    volume.setValue(-10.0f); // volume in decibels (dB)





}else{
    System.out.println("music not found");
}

    }catch (Exception e){







    }




}


}
