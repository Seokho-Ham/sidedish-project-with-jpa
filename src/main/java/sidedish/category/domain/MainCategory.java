package sidedish.category.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "main_category")
public class MainCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_category_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "mainCategory",fetch = FetchType.LAZY)
    List<SubCategory> subCategories = new ArrayList<>();

    public MainCategory(String title) {
        this.title = title;
    }

    public void addSubCategory(SubCategory subCategory) {
        subCategories.add(subCategory);
    }
    @Override
    public String toString() {
        return "MainCategory{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subCategories=" + subCategories +
                '}';
    }
}
