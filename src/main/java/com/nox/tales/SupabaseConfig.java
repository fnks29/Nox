package com.nox.tales;

import io.github.jupitershim.suppabase.SupabaseClient;
import io.github.jupitershim.suppabase.SupabaseClientBuilder;

public class SupabaseConfig {
    private static final String SUPABASE_URL = "https://your-supabase-url.supabase.co";
    private static final String SUPABASE_KEY = "your-supabase-key";
    
    private static SupabaseClient client;
    
    public static SupabaseClient getClient() {
        if (client == null) {
            client = new SupabaseClientBuilder()
                .setUrl(SUPABASE_URL)
                .setKey(SUPABASE_KEY)
                .build();
        }
        return client;
    }
}