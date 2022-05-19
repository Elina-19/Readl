package ru.itis.readl.repositories.specifications;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCriteria {

    public enum SearchOperation {

        LIKE, IN

    }

    private String key;

    private Object value;

    private SearchOperation operation;

}
