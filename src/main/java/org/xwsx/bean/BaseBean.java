package org.xwsx.bean;

public class BaseBean {
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public BaseBean(){
        this.page = new Page();
    }
}
