package com.coffee.covidtrace;

public class faqs {

    private String faq_name, faq_title, faq_detail;
    private boolean expendable;

    public boolean isExpendable() {
        return expendable;
    }

    public void setExpendable(boolean expendable) {
        this.expendable = expendable;
    }

    public faqs(String faq_name, String faq_detail) {
        this.faq_name = faq_name;
        this.faq_detail = faq_detail;
        this.expendable = false;
    }



    public String getFaq_name() {
        return faq_name;
    }

    public void setFaq_name(String faq_name) {
        this.faq_name = faq_name;
    }

    public String getFaq_detail() {
        return faq_detail;
    }

    public void setFaq_detail(String faq_detail) {
        this.faq_detail = faq_detail;
    }

    @Override
    public String toString() {
        return "faq{" +
                "faq_name='" + faq_name + '\'' +
                ", faq_detail='" + faq_detail + '\'' +
                '}';
    }


}
