package az.subhannaghiyev.wearwibe.entity;

import az.subhannaghiyev.wearwibe.entity.enums.Category;
import az.subhannaghiyev.wearwibe.entity.enums.Size;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @ElementCollection(targetClass = Size.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_sizes", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    private List<Size> sizes;

    @Column(name = "color")
    private String color;

    @Column(length = 2048)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Category category;
}
