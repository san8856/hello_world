package com.yedam.reserve;

public class Review {
    private int reviewNo;
    private String title;
    private String content;
    private String customerId;
    private String writeDate;
    private int viewCnt;

    public Review(int reviewNo, String title, String customerId, String writeDate, int viewCnt) {
        this.reviewNo = reviewNo;
        this.title = title;
        this.customerId = customerId;
        this.writeDate = writeDate;
        this.viewCnt = viewCnt;
    }
    public Review(int reviewNo, String title, String content, String customerId, String writeDate, int viewCnt) {
        this.reviewNo = reviewNo;
        this.title = title;
        this.content = content;
        this.customerId = customerId;
        this.writeDate = writeDate;
        this.viewCnt = viewCnt;
    }

    public int getReviewNo() { return reviewNo; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getCustomerId() { return customerId; }
    public String getWriteDate() { return writeDate; }
    public int getViewCnt() { return viewCnt; }
}
