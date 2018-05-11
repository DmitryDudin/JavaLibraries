import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BrampFfmpegLibraryTest {

    @Test
    public void test() throws IOException {
//        /usr/bin/ffmpeg
// /usr/share/ffmpeg
// /usr/share/man/man1/ffmpeg.1.gz

        FFmpeg ffmpeg = new FFmpeg("/usr/bin/ffmpeg");
        Path outputPath = Paths.get("src/test/resources/result.flac");

        Path inputPath = Paths.get("src/test/resources/Видео для проекта по автоматизации оценки.mp4");
        ffmpeg.run(new FFmpegBuilder()
//                .setInput("/home/dmitrid/dev/archieve/JavaLibraries/VideoAudioEncoding/src/test/resources/Видео для проекта по автоматизации оценки.mp4")
//                .addOutput("/home/dmitrid/dev/archieve/JavaLibraries/VideoAudioEncoding/src/test/resources/result.flac")
                        .setInput(inputPath.toAbsolutePath().toString())
                        .addOutput(outputPath.toAbsolutePath().toString())
                        .setFormat("flac")
                        .setAudioChannels(1)
                        .setAudioCodec("flac")
                        .setAudioSampleRate(16_000)
                        .done()

        );

        /*FFmpeg ffmpeg = new FFmpeg("/path/to/ffmpeg");
        FFprobe ffprobe = new FFprobe("/path/to/ffprobe");

        FFmpegBuilder builder = new FFmpegBuilder()

                .setInput("input.mp4")     // Filename, or a FFmpegProbeResult
                .overrideOutputFiles(true) // Override the output if it exists

                .addOutput("output.mp4")   // Filename for the destination
                .setFormat("mp4")        // Format is inferred from filename, or can be set
                .setTargetSize(250_000)  // Aim for a 250KB file

                .disableSubtitle()       // No subtiles

                .setAudioChannels(1)         // Mono audio
                .setAudioCodec("aac")        // using the aac codec
                .setAudioSampleRate(48_000)  // at 48KHz
                .setAudioBitRate(32768)      // at 32 kbit/s

                .setVideoCodec("libx264")     // Video using x264
                .setVideoFrameRate(24, 1)     // at 24 frames per second
                .setVideoResolution(640, 480) // at 640x480 resolution

                .setStrict(FFmpegBuilder.Strict.EXPERIMENTAL) // Allow FFmpeg to use experimental specs
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

// Run a one-pass encode
        executor.createJob(builder).run();

// Or run a two-pass encode (which is better quality at the cost of being slower)
        executor.createTwoPassJob(builder).run();*/
    }
}
