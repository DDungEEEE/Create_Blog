package blog.createblog.controller;

import blog.createblog.domain.Article;
import blog.createblog.dto.AddArticleRequest;
import blog.createblog.repository.BlogRepository;
import blog.createblog.service.BlogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.WeakHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Rollback(false)
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    BlogService blogService;
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    WebApplicationContext context;

    @BeforeEach
    public void mockMvcSetup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성 성공")
    public void addArticle() throws  Exception{
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest addArticleRequest = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(addArticleRequest);

        ResultActions result = mockMvc.perform(post(url)
                .
                contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("글 내용 검색")
    void testFindById() throws Exception {
        final String url = "/api/Articles/{id}";

        final String title = "title";
        final String content = "content";


        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }
}