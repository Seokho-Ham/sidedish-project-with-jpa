package sidedish.category.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "sub_category")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_category_id")
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_id")
    private MainCategory mainCategory;

    public SubCategory(String title) {
        this.title = title;
    }

    public void addMainCategory(MainCategory mainCategory) {
        this.mainCategory = mainCategory;
        mainCategory.setSubCategory(this);
    }
}
