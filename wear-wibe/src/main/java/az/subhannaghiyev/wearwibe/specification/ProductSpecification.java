package az.subhannaghiyev.wearwibe.specification;

import az.subhannaghiyev.wearwibe.entity.Product;
import az.subhannaghiyev.wearwibe.entity.enums.Category;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class ProductSpecification implements Specification<Product> {

    private final String keyword;
    private final Category category;
    private final String color;
    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;


    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        Predicate predicate =  cb.conjunction();

        if (keyword != null && !keyword.isEmpty()) {
            String pattern = "%" + keyword.toLowerCase() + "%";
            Predicate namePredicate = cb.like(cb.lower(root.get("name")), pattern);
            Predicate descPredicate = cb.like(cb.lower(root.get("description")), pattern);
            predicate = cb.and(predicate, cb.or(namePredicate, descPredicate));
        }

        if (category != null) {
            predicate = cb.and(predicate, cb.equal(root.get("category"), category));
        }

        if (color != null && !color.isEmpty()) {
            predicate = cb.and(predicate, cb.equal(cb.lower(root.get("color")), color.toLowerCase()));
        }

        if (minPrice != null) {
            predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }

        if (maxPrice != null) {
            predicate = cb.and(predicate, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        return predicate;
    }
}
