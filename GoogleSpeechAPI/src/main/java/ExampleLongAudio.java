import com.google.api.gax.longrunning.OperationFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExampleLongAudio {
    //todo google speechapi with video model

    /**
     * Performs transcription of the given audio file synchronously with
     * the selected model.
     *
     * @param fileName the path to a audio file to transcribe
     */
    public static void transcribeModelSelection(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        byte[] content = Files.readAllBytes(path);

        try (SpeechClient speech = SpeechClient.create()) {
            // Configure request with video media type
            RecognitionConfig recConfig = RecognitionConfig.newBuilder()
                    // encoding may either be omitted or must match the value in the file header
                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                    .setLanguageCode("en-US")
                    // sample rate hertz may be either be omitted or must match the value in the file header
                    .setSampleRateHertz(16000)
                    .setModel("video")
                    .build();

            RecognitionAudio recognitionAudio = RecognitionAudio.newBuilder()
                    .setContent(ByteString.copyFrom(content))
                    .build();


            RecognizeResponse recognizeResponse = speech.recognize(recConfig, recognitionAudio);
            // Just print the sourceDest result here.
            SpeechRecognitionResult result = recognizeResponse.getResultsList().get(0);
            // There can be several alternative transcripts for a given chunk of speech. Just use the
            // sourceDest (most likely) one here.
            SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
            System.out.printf("Transcript : %s\n", alternative.getTranscript());
        }
    }

//-----------------------------

    public static void asyncRecognizeGcs(String gcsUri) throws Exception {
        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create()) {

            // Configure remote file request for Linear16
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(RecognitionConfig.AudioEncoding.FLAC)
                    .setLanguageCode("en-US")
                    .setSampleRateHertz(16000)
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setUri(gcsUri)
                    .build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            List<SpeechRecognitionResult> results = response.get().getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // sourceDest (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s\n", alternative.getTranscript());
            }
        }
    }

    //--------------------------------------
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String jsonPath = "/home/dmitrid/dev/PMU/pmu-voice-be/doc/key/PMU-Voice-ec7c06c94ac5.json";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        SpeechSettings speechSettings = SpeechSettings.newBuilder().setCredentialsProvider(() -> credentials).build();

        try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {

            // Configure remote file request for Linear16
            RecognitionConfig config = RecognitionConfig.newBuilder()
//                    .setEncoding(RecognitionConfig.AudioEncoding.FLAC)
                    .setLanguageCode("ru-RU")
                    .setMaxAlternatives(30)
//                    .setSampleRateHertz(16000)
                    .build();
//            String gcsUri = "gs://long-audio/16kHz_ru";//uri to the file(blob) into GoogleCloudStorage
            String gcsUri = "gs://long-audio/result";
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setUri(gcsUri)
                    .build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speechClient.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            List<SpeechRecognitionResult> results = response.get().getResultsList();

            for (SpeechRecognitionResult result : results) {//result corresponds to a segment of audio (segments of audio are separated by pauses)
                for (SpeechRecognitionAlternative speechRecognitionAlternative : result.getAlternativesList()) {
//                    System.out.printf("------------------------ Transcription: %s%n", speechRecognitionAlternative.getTranscript());
                    System.out.printf("%s%n", speechRecognitionAlternative.getTranscript());
//                    System.out.println(speechRecognitionAlternative.getConfidence());
                }
            }

        }
    }
}
