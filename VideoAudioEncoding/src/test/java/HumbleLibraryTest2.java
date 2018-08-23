import io.humble.video.*;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HumbleLibraryTest2 {

    @Test
    public void shouldPrintHumbleVersion() {
        final String version = io.humble.video_native.Version.getVersionInfo();
        System.out.println("humble version = " + version);
    }

    @Test
    public void shouldPrintHelp() {//NOP
        final Options options = new Options();
        options.addOption("h", "help", false, "displays help");
        options.addOption("v", "version", false, "version of this library");

        final HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(HumbleLibraryTest2.class.getCanonicalName() + " [<filename> ...]", options);
    }

    @Test
    public void getInfoOfMediaFile() throws IOException, InterruptedException {
        Path inputPath = Paths.get("src/test/resources/Видео для проекта по автоматизации оценки.mp4");

        // A Demuxer opens up media containers, parses  and de-multiplexes the streams of media data without those containers.
        final Demuxer demuxer = Demuxer.make();

//        demuxer.open(inputPath.toUri().toString(), null, false, true, null, null);
        demuxer.open(inputPath.toAbsolutePath().toString(), null, false, true, null, null);
        System.out.println(demuxer.getDuration());//248038000
        System.out.println(demuxer.getBitRate());//477721
        System.out.println(demuxer.getFileSize());//14811644
//-----------

        System.out.println("Metadata");
        demuxer.getMetaData().getKeys().forEach(System.out::println);
//-----------

        final String formattedDuration = formatTimeStamp(demuxer.getDuration());
        System.out.printf("Duration: %s, start: %f, bitrate: %d kb/s\n",
                formattedDuration,
                demuxer.getStartTime() == Global.NO_PTS ? 0 : demuxer.getStartTime() / 1000000.0,
                demuxer.getBitRate() / 1000);
//-----------
        // Finally, a container consists of several different independent streams of
        // data called Streams. In Humble there are two objects that represent streams:
        // DemuxerStream (when you are reading) and MuxerStreams (when you are writing).

        // First find the number of streams in this container.
        int ns = demuxer.getNumStreams();
        System.out.println("getNumStreams= " + ns);

        // Now, let's iterate through each of them.
        for (int i = 0; i < ns; i++) {
            DemuxerStream stream = demuxer.getStream(i);

            KeyValueBag metadata = stream.getMetaData();
            // Language is usually embedded as metadata in a stream.
            final String language = metadata.getValue("language");

            // We will only be able to make a decoder for streams we can actually
            // decode, so the caller should check for null.
            Decoder d = stream.getDecoder();

            System.out.printf(" Stream #0.%1$d (%2$s): %3$s\n", i, language, d != null ? d.toString() : "unknown coder");
            System.out.println("  Metadata:");
            for (String key : metadata.getKeys())
                System.out.printf("    %s: %s\n", key, metadata.getValue(key));
        }
//-----------

        /*Size 14464 KB (14 MB)
        Length 00:04:08
        Demuxer lavf

        Video
        Resolution 704 x 576
        Aspect ratio 1.22222
        Format h264
        Bitrate 0 kbps
        Frames per second 10.000000
        Selected codec lavc:h264

        Initial Audio Stream
        Format pcm_mulaw
        Bitrate 0 kbps
        Rate 8000 Hz
        Channels 1
        Selected codec lavc:pcm_mulaw*/
    }

    @Test
    public void name() {
        Path inputPath = Paths.get("src/test/resources/Видео для проекта по автоматизации оценки.mp4");

        Muxer muxer = Muxer.make(inputPath.toAbsolutePath().toString(), null, null);
//        muxer.write(packet);
//        muxer.addNewStream(coder);
        int i = 0;
    }

    @Test
    public void shouldFindAudioStream() throws IOException, InterruptedException {
        Path inputPath = Paths.get("src/test/resources/Видео для проекта по автоматизации оценки.mp4");
        Path outputPath = Paths.get("src/test/resources/outout.flac");
        String filename = inputPath.toAbsolutePath().toString();

        // Start by creating a container object, in this case a demuxer since we are reading, to get audio data from.
        Demuxer demuxer = Demuxer.make();

        // Open the demuxer with the filename passed on.
        demuxer.open(inputPath.toAbsolutePath().toString(), null, false, true, null, null);

        //Query how many streams the call to open found
        int numStreams = demuxer.getNumStreams();

        //Iterate through the streams to find the first audio stream
        int audioStreamId = -1;
        Decoder audioDecoder = null;
        for (int i = 0; i < numStreams; i++) {
            final DemuxerStream stream = demuxer.getStream(i);
            final Decoder decoder = stream.getDecoder();
            if (decoder != null && decoder.getCodecType() == MediaDescriptor.Type.MEDIA_AUDIO) {
                audioStreamId = i;
                audioDecoder = decoder;
                // stop at the first one.
                break;
            }
        }
        if (audioStreamId == -1)
            throw new RuntimeException("could not find audio stream in container: " + filename);

        //---

         //Now we have found the audio stream in this file.  Let's open up our decoder so it can do work.
        audioDecoder.open(null, null);
        System.out.println(audioDecoder);

        System.out.println(audioDecoder.getFrameSize());
        System.out.println(audioDecoder.getSampleRate());
        System.out.println(audioDecoder.getChannels());
        System.out.println(audioDecoder.getChannelLayout());
        System.out.println(audioDecoder.getSampleFormat());
        //---
        DemuxerStream stream = demuxer.getStream(1);

//        demuxer.read(packet);

//        Muxer muxer = Muxer.make(outputPath.toAbsolutePath().toString(), null, null);
//        muxer.open(null,null);
//        muxer.addNewStream(audioDecoder);
//        muxer.close();

        demuxer.close();

    }

    private static String formatTimeStamp(long duration) {
        if (duration == Global.NO_PTS) {
            return "00:00:00.00";
        }
        double d = 1.0 * duration / Global.DEFAULT_PTS_PER_SECOND;
        int hours = (int) (d / (60 * 60));
        int mins = (int) ((d - hours * 60 * 60) / 60);
        int secs = (int) (d - hours * 60 * 60 - mins * 60);
        int subsecs = (int) ((d - (hours * 60 * 60.0 + mins * 60.0 + secs)) * 100.0);
        return String.format("%1$02d:%2$02d:%3$02d.%4$02d", hours, mins, secs, subsecs);
    }
}
