package com.nox.tales;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class TTSCloudService {
    private static final String VITS_API_URL = "https://api.meuservicotts.com/vits";
    private static final OkHttpClient client = new OkHttpClient();

    public static String convertTextToSpeech(String text) throws IOException {
        String url = VITS_API_URL + "?text=" + text;
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            return response.body().string();
        }
    }
}