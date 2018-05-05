import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class QuickstartSample {
    /*https://cloud.google.com/docs/authentication/production
    https://cloud.google.com/billing/docs/how-to/manage-billing-account?hl=ru&visit_id=1-636604299121123873-3853186258&rd=2
    https://cloud.google.com/docs/authentication/getting-started#auth-cloud-implicit-java
    https://cloud.google.com/docs/authentication/production#providing_credentials_to_your_application*/

    /*export GOOGLE_APPLICATION_CREDENTIALS="/home/user/Downloads/[FILE_NAME].json"*/

/*
audio format != audio encoding
    .wav
                   linear PCM
    FLAC           FLAC


 Speech-to-Text automatically determines the encoding and sample rate for WAV or FLAC files based on the file header
 если установить кодировку и частоту (выборки), не соответствующую действительным => ошибка


*/

 /*// Import the Base64 encoding library.
    import org.apache.commons.codec.binary.Base64;

    // Encode the speech.
    byte[] encodedAudio = Base64.encodeBase64(audio.getBytes());*/

    /**
     * Demonstrates using the Speech API to transcribe an audio file.
     */
    public static void main(String... args) throws Exception {
        String jsonPath = "/home/dmitrid/dev/PMU/pmu-voice-be/doc/key/PMU-Voice-ec7c06c94ac5.json";//todo check - working!!!
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        SpeechSettings speechSettings = SpeechSettings.newBuilder().setCredentialsProvider(() -> credentials).build();

        // Instantiates a client
//        try (SpeechClient speechClient = SpeechClient.create()) {//for environment variable GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speechClient = SpeechClient.create(speechSettings)) {

            // The path to the audio file to transcribe
            String fileName = "GoogleSpeechAPI/src/main/resources/" +
//                    "Танк_16kHz.wav";
//                    "Танк2.3gp";
//                    "Перечисление.wav";
//                    "Перечисл2.wav";
//                    "ru_30sec_wav_16khz.wav";
//                    "Slava2.wav";
//                    "mp3_10sec_16KHz.mp3";//don't work
                    "recForge2/16kHz_ru.flac";//!!!
//                    "recForge2/16kHz_ru.wav";
//                    "recForge2/16kHz_ru.ogg";
//                    "recForge2/15s_48kHz_ru.opus";

            // Reads the audio file into memory
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            // Builds the sync recognize request
            RecognitionConfig config = RecognitionConfig.newBuilder()
//                    .setEncoding(RecognitionConfig.AudioEncoding.UNRECOGNIZED)
//                    .setEncoding(RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED)
//                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)//необязательный параметр для записей в этой кодировке, также как и для FLAC
//                    .setEncoding(RecognitionConfig.AudioEncoding.AMR_WB)
//                    .setEncoding(RecognitionConfig.AudioEncoding.AMR)
//                    .setEncoding(RecognitionConfig.AudioEncoding.OGG_OPUS)
//                    .setSampleRateHertz(48000)
//                    .setSampleRateHertz(16000)
//                    .setSampleRateHertz(8000)
//                    .setLanguageCode("en-US")
                    .setLanguageCode("ru-RU")
                    .setMaxAlternatives(30)//максимальное количество альтернатив перевода
//                    .setEnableWordTimeOffsets(true)//дополнительная информация о таймингах переведённых слов в ответе
                    //Phrase hints - слова которые мы ожидаем в записи
                    .addSpeechContexts(SpeechContext.newBuilder().addAllPhrases(Arrays.asList("Добрый день",
                            "Водка Гадость", "Camel", "лимонад", "капучино", "полет без фильтра",
                            "может быть хотите кофе", "l&m")))
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {//result corresponds to a segment of audio (segments of audio are separated by pauses)
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                //alternatives contains a list of possible transcriptions, of type SpeechRecognitionAlternatives
                for (SpeechRecognitionAlternative speechRecognitionAlternative : result.getAlternativesList()) {
                    System.out.printf("------------------------ Transcription: %s%n", speechRecognitionAlternative.getTranscript());
                    System.out.println(speechRecognitionAlternative.getConfidence());
//                    System.out.println(speechRecognitionAlternative.getAllFields().entrySet());
                }
//                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
//                System.out.printf("------------------------ Transcription: %s%n", alternative.getTranscript());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
