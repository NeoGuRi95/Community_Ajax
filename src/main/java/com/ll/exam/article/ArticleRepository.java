package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArticleRepository {
    private static List<ArticleDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(id -> {
            String title = "제목%d".formatted(id);
            String body = "내용%d".formatted(id);
            String chat = "채팅";
            write(title, body, chat);
        });
    }

    public static long write(String title, String body) {
        long id = ++lastId;
        ArticleDto newArticleDto = new ArticleDto(id, title, body, new ArrayList<>());

        datum.add(newArticleDto);

        return id;
    }

    public static long write(String title, String body, String chat) {
        long id = ++lastId;
        ArrayList<String> chatList = new ArrayList<>();

        chatList.add(chat);

        ArticleDto newArticleDto = new ArticleDto(id, title, body, chatList);

        datum.add(newArticleDto);

        return id;
    }

    public static List<ArticleDto> findAll() {
        return datum;
    }

    public static ArticleDto findById(long id) {
        for (ArticleDto articleDto : datum) {
            if (articleDto.getId() == id) {
                return articleDto;
            }
        }

        return null;
    }

    public void delete(long id) {
        ArticleDto articleDto = findById(id);

        if (articleDto == null) return;

        datum.remove(articleDto);
    }

    public void modify(long id, String title, String body) {
        ArticleDto articleDto = findById(id);

        if (articleDto == null) return;

        articleDto.setTitle(title);
        articleDto.setBody(body);
    }

    public List<ArticleDto> findAllIdGreaterThan(long fromId) {
        return datum
                .stream()
                .filter(articleDto -> articleDto.getId() > fromId)
                .collect(Collectors.toList());
    }

    public void writeChat(long id, String chat) {
        ArticleDto articleDto = findById(id);

        if (articleDto == null) return;

        articleDto.getChattingList().add(chat);
    }
}
