package com.pairone.library.dto.book;

public class BookUpdateReq {
    private int id;
    private int editionNo;
    private Integer bookinfoId;
    private Integer publisherId;
    private Integer categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBookinfoId() {
        return bookinfoId;
    }

    public void setBookinfoId(Integer bookinfoId) {
        this.bookinfoId = bookinfoId;
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

}
