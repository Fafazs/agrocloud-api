package com.agrocloud.api.config;

import com.agrocloud.api.model.Article;
import com.agrocloud.api.repository.ArticleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSeeder implements CommandLineRunner {

private final ArticleRepository articleRepo;

public DataSeeder(ArticleRepository articleRepo) {
    this.articleRepo = articleRepo;
}

@Override
public void run(String... args) {

    articleRepo.deleteAll();

    articleRepo.save(new Article(
        "Alface Crespa",
        "/images/alface.jpg",
        "Folhosa de crescimento rápido, ideal para iniciantes e cultivo em vasos ou canteiros.",
        List.of("Rúcula", "Cebolinha"),
        "18°C a 24°C",
        "70%",
        "Solo rico em nitrogênio e bem drenado"
    ));

    articleRepo.save(new Article(
        "Tomate Cereja",
        "/images/tomate.jpg",
        "Produz frutos pequenos e saborosos, adaptando-se bem a vasos grandes.",
        List.of("Manjericão", "Calêndula"),
        "21°C a 28°C",
        "60% a 70%",
        "Solo rico em matéria orgânica"
    ));

    articleRepo.save(new Article(
        "Cebolinha",
        "/images/cebolinha.jpg",
        "Planta resistente e fácil de cultivar durante todo o ano.",
        List.of("Alface", "Coentro"),
        "15°C a 25°C",
        "60%",
        "Solo leve e fértil"
    ));

    articleRepo.save(new Article(
        "Coentro",
        "/images/coentro.jpg",
        "Erva aromática muito utilizada na culinária brasileira.",
        List.of("Cebolinha", "Alface"),
        "18°C a 30°C",
        "60%",
        "Solo fértil e bem drenado"
    ));

    articleRepo.save(new Article(
        "Manjericão",
        "/images/manjericao.jpg",
        "Erva aromática perfeita para cultivo doméstico e acompanhamento de tomates.",
        List.of("Tomate Cereja", "Pimenta"),
        "20°C a 30°C",
        "60%",
        "Solo fértil e úmido"
    ));

    articleRepo.save(new Article(
        "Morango",
        "/images/morango.jpg",
        "Fruta muito popular para cultivo em vasos suspensos e jardineiras.",
        List.of("Alface", "Espinafre"),
        "15°C a 25°C",
        "70%",
        "Solo rico em matéria orgânica"
    ));

    articleRepo.save(new Article(
        "Cenoura",
        "/images/cenoura.jpg",
        "Raiz nutritiva que exige solo profundo e bem solto.",
        List.of("Alface", "Cebolinha"),
        "16°C a 24°C",
        "60%",
        "Solo arenoso e profundo"
    ));

    articleRepo.save(new Article(
        "Rúcula",
        "/images/rucula.jpg",
        "Folhosa de sabor marcante e rápido desenvolvimento.",
        List.of("Alface", "Cenoura"),
        "15°C a 22°C",
        "65%",
        "Solo fértil e drenado"
    ));

    articleRepo.save(new Article(
        "Hortelã",
        "/images/hortela.jpg",
        "Planta aromática vigorosa ideal para vasos individuais.",
        List.of(),
        "18°C a 28°C",
        "70%",
        "Solo úmido e rico em matéria orgânica"
    ));

    articleRepo.save(new Article(
        "Pimenta Dedo-de-Moça",
        "/images/pimenta.jpg",
        "Planta produtiva que se adapta muito bem ao cultivo doméstico.",
        List.of("Manjericão", "Coentro"),
        "22°C a 30°C",
        "60%",
        "Solo fértil e bem drenado"
    ));

    System.out.println("Biblioteca carregada.");
}

}
