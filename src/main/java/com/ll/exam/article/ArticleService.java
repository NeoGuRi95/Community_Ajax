package com.ll.exam.article;

import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.dto.ArticleReplyDto;
import com.ll.exam.article.dto.ArticleReplyRepository;

import java.util.List;

public class ArticleService {
    private ArticleRepository articleRepository;
    private ArticleReplyRepository articleReplyRepository;

    public ArticleService() {
        articleRepository = new ArticleRepository();
        articleReplyRepository = new ArticleReplyRepository();
    }

    public long write(String title, String body) {
        return articleRepository.write(title, body);
    }

    public List<ArticleDto> findAll() {
        return articleRepository.findAll();
    }

    public ArticleDto findById(long id) {
        return articleRepository.findById(id);
    }

    public void delete(long id) {
        articleRepository.delete(id);
    }

    public void modify(long id, String title, String body) {
        articleRepository.modify(id, title, body);
    }

    public List<ArticleDto> findIdGreaterThan(long fromId) {
        return articleRepository.findAllIdGreaterThan(fromId);
    }

    public void writeReply(long id, String reply) {
        articleReplyRepository.write(id, reply);
    }

    public List<ArticleReplyDto> findReplyList(long roomId, long replyFromId) {
        return articleReplyRepository.findReplyList(roomId, replyFromId);
    }
}
