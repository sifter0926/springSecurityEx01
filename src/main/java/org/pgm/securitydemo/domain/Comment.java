package org.pgm.securitydemo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity(name="tbl_comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Comment> children;

    public void addChild(Comment child) {
        child.setParent(this);
        this.children.add(child);
    }
}
