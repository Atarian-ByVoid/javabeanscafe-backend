package com.javabeanscafe.domain.helper;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonView;
import com.javabeanscafe.infrastructure.adapter.dto.others.AbstractViews;

import lombok.Data;

@Data
public class PageWrapper<T> {

    @JsonView({ PaginateViews.Paginate.class })
    private List<T> content;

    @JsonView({ PaginateViews.Paginate.class })
    private int pageNumber;

    @JsonView({ PaginateViews.Paginate.class })
    private int numberOfElements;

    @JsonView({ PaginateViews.Paginate.class })
    private String pageable;

    @JsonView({ PaginateViews.Paginate.class })
    private int pageSize;

    @JsonView({ PaginateViews.Paginate.class })
    private String sort;

    @JsonView({ PaginateViews.Paginate.class })
    private long totalElements;

    @JsonView({ PaginateViews.Paginate.class })
    private int totalPages;

    @JsonView({ PaginateViews.Paginate.class })
    private boolean hasNext;

    @JsonView({ PaginateViews.Paginate.class })
    private boolean hasPrevious;

    @JsonView({ PaginateViews.Paginate.class })
    private boolean isFirst;

    @JsonView({ PaginateViews.Paginate.class })
    private boolean isLast;

    public PageWrapper<T> build(Page<T> page) {
        this.content = page.getContent();
        this.pageNumber = page.getNumber();
        this.numberOfElements = page.getNumberOfElements();
        this.pageable = page.getPageable().toString();
        this.pageSize = page.getSize();
        this.sort = page.getSort().toString();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.hasNext = page.hasNext();
        this.hasPrevious = page.hasPrevious();
        this.isFirst = page.isFirst();
        this.isLast = page.isLast();

        return this;
    }

    public class PaginateViews extends AbstractViews {
    }
}
