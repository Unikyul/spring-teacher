package org.example.springv3.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.springv3.reply.Reply;
import org.example.springv3.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;




@NoArgsConstructor // 빈 생성자 (하이버네이트가 om 할때 필요)
@Setter
@Getter
@Table(name = "board_tb")
@Entity // DB에서 조회하면 자동 매핑이됨
public class Board {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_incremnt 설정, 시퀀스 설정
    @Id // PK 설정
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @CreationTimestamp
    private Timestamp createdAt;

    // fk
   // @JsonIgnore
//@JsonIgnoreProperties({"password"})
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //양방향 매핑
  //  @JsonIgnore
  //  @JsonIgnoreProperties({"board","createdAt"})
    @OneToMany(mappedBy = "board")
    private List<Reply> replies;

    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + user +
                ", replies=" + replies +
                '}';
    }
}