package blog.createblog.controller;

import blog.createblog.domain.Article;
import blog.createblog.dto.AddArticleRequest;
import blog.createblog.repository.BlogRepository;
import blog.createblog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.WeakHashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(false)
class BlogApiControllerTest {

    @Autowired
    BlogService blogService;

    @Test
    void 회원가입() {

        //given
        final String name = "제목2";
        final String content = "헤헤";

        //when
        AddArticleRequest addArticleRequest = new AddArticleRequest(name, content);

        //then
        blogService.save(addArticleRequest);
    }
}