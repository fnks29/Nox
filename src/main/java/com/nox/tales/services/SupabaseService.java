package com.nox.tales.services;

import com.nox.tales.SupabaseConfig;
import com.nox.tales.models.Audiobook;
import com.nox.tales.models.User;
import com.nox.tales.models.UserPreferences;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SupabaseService {
    private static SupabaseService instance;
    
    // Singleton
    public static SupabaseService getInstance() {
        if (instance == null) {
            instance = new SupabaseService();
        }
        return instance;
    }
    
    private SupabaseService() {
        // Inicializar o cliente Supabase
        // SupabaseConfig.getClient();
    }
    
    // Métodos para gerenciar usuários
    public User getCurrentUser() {
        // Em uma implementação real, buscaríamos o usuário atual do Supabase
        // Por enquanto, retornamos um usuário de exemplo
        User user = new User("1", "João Silva", "joao.silva@email.com");
        user.setPhotoUrl("/images/profile.png");
        
        // Adicionar alguns audiobooks ao progresso do usuário
        user.updateProgress("1", 1800000, 75.0); // 30 minutos, 75%
        user.updateProgress("2", 900000, 30.0);  // 15 minutos, 30%
        user.updateProgress("3", 300000, 10.0);  // 5 minutos, 10%
        
        // Adicionar alguns favoritos
        user.addFavorite("1");
        user.addFavorite("4");
        user.addFavorite("5");
        
        return user;
    }
    
    public void saveUserPreferences(String userId, UserPreferences preferences) {
        // Em uma implementação real, salvaríamos as preferências no Supabase
        System.out.println("Salvando preferências do usuário " + userId);
    }
    
    public void updateUserProgress(String userId, String audiobookId, long position, double percentage) {
        // Em uma implementação real, atualizaríamos o progresso no Supabase
        System.out.println("Atualizando progresso do audiobook " + audiobookId + " para o usuário " + userId);
    }
    
    // Métodos para gerenciar audiobooks
    public List<Audiobook> getFeaturedAudiobooks() {
        // Em uma implementação real, buscaríamos os audiobooks em destaque do Supabase
        List<Audiobook> audiobooks = new ArrayList<>();
        
        audiobooks.add(new Audiobook("1", "O Senhor dos Anéis", "J.R.R. Tolkien", "Narrador 1", 
                                   "/images/book1.png", "Uma jornada épica...", "Fantasia", 
                                   Duration.ofHours(20), "/audio/lotr.mp3"));
        
        audiobooks.add(new Audiobook("2", "Harry Potter", "J.K. Rowling", "Narrador 2", 
                                   "/images/book2.png", "O menino que sobreviveu...", "Fantasia", 
                                   Duration.ofHours(15), "/audio/hp.mp3"));
        
        audiobooks.add(new Audiobook("3", "Game of Thrones", "George R.R. Martin", "Narrador 3", 
                                   "/images/book3.png", "Intrigas e batalhas...", "Fantasia", 
                                   Duration.ofHours(25), "/audio/got.mp3"));
        
        audiobooks.add(new Audiobook("4", "O Hobbit", "J.R.R. Tolkien", "Narrador 1", 
                                   "/images/book4.png", "Uma aventura inesperada...", "Fantasia", 
                                   Duration.ofHours(10), "/audio/hobbit.mp3"));
        
        return audiobooks;
    }
    
    public List<Audiobook> getRecommendedAudiobooks() {
        // Em uma implementação real, buscaríamos recomendações personalizadas do Supabase
        List<Audiobook> audiobooks = new ArrayList<>();
        
        audiobooks.add(new Audiobook("5", "Duna", "Frank Herbert", "Narrador 4", 
                                   "/images/rec1.png", "Um planeta desértico...", "Ficção Científica", 
                                   Duration.ofHours(18), "/audio/dune.mp3"));
        
        audiobooks.add(new Audiobook("6", "Neuromancer", "William Gibson", "Narrador 5", 
                                   "/images/rec2.png", "O futuro do ciberespaço...", "Ficção Científica", 
                                   Duration.ofHours(12), "/audio/neuromancer.mp3"));
        
        audiobooks.add(new Audiobook("7", "Fundação", "Isaac Asimov", "Narrador 3", 
                                   "/images/rec3.png", "O futuro da humanidade...", "Ficção Científica", 
                                   Duration.ofHours(14), "/audio/foundation.mp3"));
        
        return audiobooks;
    }
    
    public List<Audiobook> getPopularAudiobooks() {
        // Em uma implementação real, buscaríamos os audiobooks mais populares do Supabase
        List<Audiobook> audiobooks = new ArrayList<>();
        
        audiobooks.add(new Audiobook("8", "O Código Da Vinci", "Dan Brown", "Narrador 2", 
                                   "/images/pop1.png", "Um mistério religioso...", "Suspense", 
                                   Duration.ofHours(13), "/audio/davinci.mp3"));
        
        audiobooks.add(new Audiobook("9", "A Menina que Roubava Livros", "Markus Zusak", "Narrador 4", 
                                   "/images/pop2.png", "Durante a Segunda Guerra...", "Drama", 
                                   Duration.ofHours(11), "/audio/bookthief.mp3"));
        
        audiobooks.add(new Audiobook("10", "As Crônicas de Nárnia", "C.S. Lewis", "Narrador 1", 
                                    "/images/pop3.png", "Um mundo mágico...", "Fantasia", 
                                    Duration.ofHours(16), "/audio/narnia.mp3"));
        
        return audiobooks;
    }
    
    public Audiobook getAudiobookById(String id) {
        // Em uma implementação real, buscaríamos o audiobook específico do Supabase
        List<Audiobook> allBooks = new ArrayList<>();
        allBooks.addAll(getFeaturedAudiobooks());
        allBooks.addAll(getRecommendedAudiobooks());
        allBooks.addAll(getPopularAudiobooks());
        
        for (Audiobook book : allBooks) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        
        return null;
    }
    
    public List<Audiobook> searchAudiobooks(String query, String category, String author, String narrator) {
        // Em uma implementação real, faríamos uma busca filtrada no Supabase
        // Por enquanto, vamos filtrar nossa lista local
        List<Audiobook> allBooks = new ArrayList<>();
        allBooks.addAll(getFeaturedAudiobooks());
        allBooks.addAll(getRecommendedAudiobooks());
        allBooks.addAll(getPopularAudiobooks());
        
        List<Audiobook> results = new ArrayList<>();
        
        for (Audiobook book : allBooks) {
            boolean matches = true;
            
            // Filtrar por termo de busca
            if (query != null && !query.isEmpty()) {
                if (!book.getTitle().toLowerCase().contains(query.toLowerCase()) && 
                    !book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                    matches = false;
                }
            }
            
            // Filtrar por categoria
            if (category != null && !category.isEmpty() && !category.equals(book.getCategory())) {
                matches = false;
            }
            
            // Filtrar por autor
            if (author != null && !author.isEmpty() && !author.equals(book.getAuthor())) {
                matches = false;
            }
            
            // Filtrar por narrador
            if (narrator != null && !narrator.isEmpty() && !narrator.equals(book.getNarrator())) {
                matches = false;
            }
            
            if (matches) {
                results.add(book);
            }
        }
        
        return results;
    }
}