package com.pairone.library.dto.book;

import java.util.List;

public class BookCreateReq {

    private String name;
    private int pageCount;
    private int editionNo;
    private Integer bookinfoId;
    private Integer publisherId;
    private Integer categoryId;
    private List<Integer> authorId;

    public Integer getBookinfoId() {
        return bookinfoId;
    }

    public void setBookinfoId(Integer bookinfoId) {
        this.bookinfoId = bookinfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getEditionNo() {
        return editionNo;
    }

    public void setEditionNo(int editionNo) {
        this.editionNo = editionNo;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Integer> getAuthorId() {
        return authorId;
    }

    public void setAuthorId(List<Integer> authorId) {
        this.authorId = authorId;
    }
}
