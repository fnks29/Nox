package com.nox.tales.services;

import com.nox.tales.models.Audiobook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfflineService {
    private static OfflineService instance;
    private final String OFFLINE_DIR = System.getProperty("user.home") + File.separator + "NoxTales" + File.separator + "offline";
    private Map<String, Boolean> downloadedAudiobooks;
    
    // Singleton
    public static OfflineService getInstance() {
        if (instance == null) {
            instance = new OfflineService();
        }
        return instance;
    }
    
    private OfflineService() {
        // Inicializar o diretório offline
        initOfflineDirectory();
        
        // Carregar informações sobre audiobooks baixados
        downloadedAudiobooks = new HashMap<>();
        loadDownloadedAudiobooks();
    }
    
    private void initOfflineDirectory() {
        File offlineDir = new File(OFFLINE_DIR);
        if (!offlineDir.exists()) {
            offlineDir.mkdirs();
        }
    }
    
    private void loadDownloadedAudiobooks() {
        // Em uma implementação real, verificaríamos quais audiobooks estão disponíveis offline
        File offlineDir = new File(OFFLINE_DIR);
        File[] files = offlineDir.listFiles((dir, name) -> name.endsWith(".mp3"));
        
        if (files != null) {
            for (File file : files) {
                String audiobookId = file.getName().replace(".mp3", "");
                downloadedAudiobooks.put(audiobookId, true);
            }
        }
    }
    
    /**
     * Verifica se um audiobook está disponível offline
     * @param audiobookId ID do audiobook
     * @return true se o audiobook estiver disponível offline
     */
    public boolean isAudiobookAvailableOffline(String audiobookId) {
        return downloadedAudiobooks.containsKey(audiobookId) && downloadedAudiobooks.get(audiobookId);
    }
    
    /**
     * Baixa um audiobook para uso offline
     * @param audiobook O audiobook a ser baixado
     * @return true se o download for bem-sucedido
     */
    public boolean downloadAudiobook(Audiobook audiobook) {
        try {
            // Em uma implementação real, baixaríamos o arquivo de áudio da URL
            // e o salvaríamos localmente
            
            // Simular o download salvando um arquivo vazio
            File audioFile = new File(OFFLINE_DIR + File.separator + audiobook.getId() + ".mp3");
            if (!audioFile.exists()) {
                audioFile.createNewFile();
            }
            
            // Marcar como baixado
            downloadedAudiobooks.put(audiobook.getId(), true);
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Remove um audiobook baixado
     * @param audiobookId ID do audiobook a ser removido
     * @return true se a remoção for bem-sucedida
     */
    public boolean removeOfflineAudiobook(String audiobookId) {
        File audioFile = new File(OFFLINE_DIR + File.separator + audiobookId + ".mp3");
        boolean deleted = audioFile.delete();
        
        if (deleted) {
            downloadedAudiobooks.remove(audiobookId);
        }
        
        return deleted;
    }
    
    /**
     * Obtém o caminho do arquivo de áudio local
     * @param audiobookId ID do audiobook
     * @return Caminho do arquivo local ou null se não estiver disponível
     */
    public String getLocalAudioPath(String audiobookId) {
        if (isAudiobookAvailableOffline(audiobookId)) {
            return OFFLINE_DIR + File.separator + audiobookId + ".mp3";
        }
        return null;
    }
    
    /**
     * Obtém a lista de audiobooks disponíveis offline
     * @return Lista de IDs dos audiobooks disponíveis offline
     */
    public List<String> getOfflineAudiobooks() {
        return new ArrayList<>(downloadedAudiobooks.keySet());
    }
    
    /**
     * Salva o progresso do usuário localmente
     * @param userId ID do usuário
     * @param audiobookId ID do audiobook
     * @param position Posição em milissegundos
     * @param percentage Porcentagem concluída
     */
    public void saveLocalProgress(String userId, String audiobookId, long position, double percentage) {
        try {
            // Em uma implementação real, salvaríamos o progresso em um arquivo local
            String progressData = String.format("%s,%d,%.2f,%d\n", audiobookId, position, percentage, System.currentTimeMillis());
            
            Path progressFile = Paths.get(OFFLINE_DIR, userId + "_progress.txt");
            Files.write(progressFile, progressData.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Sincroniza o progresso local com o servidor quando a conexão é restaurada
     * @param userId ID do usuário
     */
    public void syncLocalProgress(String userId) {
        try {
            Path progressFile = Paths.get(OFFLINE_DIR, userId + "_progress.txt");
            if (Files.exists(progressFile)) {
                // Em uma implementação real, leríamos o arquivo de progresso
                // e enviaríamos os dados para o servidor
                
                // Após sincronizar, podemos limpar o arquivo
                Files.delete(progressFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}