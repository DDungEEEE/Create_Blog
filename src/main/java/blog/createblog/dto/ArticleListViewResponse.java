package blog.createblog.dto;

import blog.createblog.domain.Article;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
public class ArticleListViewResponse {

    private final Long id;
    private final  String title;
    private final String content;

    public ArticleListViewResponse(Article article){
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
