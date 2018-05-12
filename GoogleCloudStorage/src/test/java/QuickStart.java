import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QuickStart {

    @Test
    public void getBucketsList() throws IOException {
        String jsonPath = "/home/dmitrid/dev/PMU/pmu-voice-be/doc/key/PMU-Voice-ec7c06c94ac5.json";
        // You can specify a credential file by providing a path to GoogleCredentials.
        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        /*Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
//              .setProjectId("pmu-voice")
                .build()
                .getService();*/

        Storage storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(jsonPath)))
                .setProjectId("pmu-voice")
                .build()
                .getService();

        System.out.println("Buckets:");
        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            System.out.println(bucket.toString());
        }
    }

    @Test
    public void createBucket() throws Exception {
        // Instantiates a client
//        Storage storage = StorageOptions.getDefaultInstance().getService();

        String jsonPath = "/home/dmitrid/dev/PMU/pmu-voice-be/doc/key/PMU-Voice-ec7c06c94ac5.json";
        // You can specify a credential file by providing a path to GoogleCredentials.
        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("pmu-voice")
                .build()
                .getService();

        // The name for the new bucket
        String bucketName = "long-audio";  // "my-new-bucket";

        // Creates the new bucket
        Bucket bucket = storage.create(BucketInfo.of(bucketName));

        System.out.printf("Bucket %s created.%n", bucket.getName());
    }

    @Test
    public void uploadFile() throws IOException {
//        gs://<bucket_name>/<file_path_inside_bucket>
//        gs://bucket_name/object_name
//        gs://long-audio/16kHz_ru
        String jsonPath = "/home/dmitrid/dev/PMU/pmu-voice-be/doc/key/PMU-Voice-ec7c06c94ac5.json";
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("pmu-voice")
                .build()
                .getService();
        String bucketName = "long-audio";  // "my-new-bucket";
        Bucket bucket = storage.get(bucketName);

//        String fileName = "/home/dmitrid/dev/archieve/JavaLibraries/GoogleCloudStorage/src/test/resources/recForge2/16kHz_ru.flac";
        String fileName = "/home/dmitrid/dev/archieve/JavaLibraries/GoogleCloudStorage/src/test/resources/exampleVideoResources/result.flac";

        Path path = Paths.get(fileName);

        InputStream content = Files.newInputStream(path);
//        String blobName = "16kHz_ru";
        String blobName = "result";
//        Blob blob = bucket.create(blobName, content, "text/plain");
        Blob blob = bucket.create(blobName, content, "audio/flac");

    }
}
