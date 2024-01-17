package blog.createblog.dto;

import blog.createblog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity(){ //DTO를 엔티티로 변환
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
