package blog.createblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@Id 기본키, @GeneratedValue 값 생성 IDEMTITY 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void Update(String title, String content){
        this.title = title;
        this.content = content;
    }

    @CreatedDate // 엔티티가 생성될 때 생성시간 저장
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedBy//엔티티가 수정 될 때 시간 서장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
