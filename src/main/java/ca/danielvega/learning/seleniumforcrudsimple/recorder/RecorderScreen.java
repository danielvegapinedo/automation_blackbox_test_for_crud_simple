package ca.danielvega.learning.seleniumforcrudsimple.recorder;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.VideoFormatKeys.*;

public class RecorderScreen {

    private ScreenRecorder screenRecorder;
    private StringBuffer verificationErrors = new StringBuffer();
    private File file;

    public RecorderScreen() {
        file = new File("C:\\Users\\daniel\\Documents\\projects\\seleniumForSimpleCrud\\target\\videos");

    }

    public void startRecording() throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null, file, "MyVideo");
        this.screenRecorder.start();
    }

    public File stopRecording() throws Exception {

        this.screenRecorder.stop();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            throw new Exception(verificationErrorString);
        }
        return screenRecorder.getCreatedMovieFiles().get(0);
    }
}
