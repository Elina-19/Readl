package ru.itis.readl.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.readl.models.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BookSpecification implements Specification<Book> {

    private List<SearchCriteria> searchCriteriaList;

    public BookSpecification(){
        searchCriteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria searchCriteria){
        searchCriteriaList.add(searchCriteria);
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria: searchCriteriaList){
            switch (criteria.getOperation()){
                case LIKE:
                    predicates.add(builder.like(
                            builder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case IN:
                    predicates.add(builder.in(
                            root.get(criteria.getKey()))
                            .value(criteria.getValue()));
                    break;
                default: break;
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
