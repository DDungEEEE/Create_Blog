package blog.createblog.repository;

import blog.createblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
//기본적인 CRUD 제공
}
