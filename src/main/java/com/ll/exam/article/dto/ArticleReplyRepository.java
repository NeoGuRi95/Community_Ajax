package com.ll.exam.article.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleReplyRepository {
    private static List<ArticleReplyDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(roomId -> {
            String body = "test댓글";
            write(roomId, body);
        });
    }

    public static long write(long roomId, String body) {
        long id = ++lastId;
        ArticleReplyDto newArticleReplyDto = new ArticleReplyDto(id, roomId, body);

        datum.add(newArticleReplyDto);

        return id;
    }

    public List<ArticleReplyDto> findReplyList(long roomId, long replyFromId) {
        List<ArticleReplyDto> replyDtoList = new ArrayList<>();

        for (ArticleReplyDto articleReplyDto : datum) {
            if (articleReplyDto.getRoomId() == roomId && articleReplyDto.getId() > replyFromId) {
                replyDtoList.add(articleReplyDto);
            }
        }

        return replyDtoList;
    }
}
