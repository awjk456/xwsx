package org.xwsx.bean;

public class Page {
    /*
    总条数
     */
    private int totalNumber;
    /*
    当前第几页
     */
    private int currentPage;
    /*
    总页数
     */
    private int totalPage;


    /*
        每页显示几条
         */
    private int pageNumber =5;
    /*
    数据库中limit的参数,从第几条开始取
     */
    private int dbIndex;
    /*
    数据库中limit的参数，一共取多少条
     */
    private int dbNumber;
    /*
    根据当前对象中属性计算设置相关属性址
     */
    private void count(){
        //计算总页数
        int totalPageTemp = this.totalNumber/this.pageNumber;//总页数
        int plus = (this.totalNumber % this.pageNumber) == 0?0:1;//计算多出来不满一页的
        totalPageTemp = totalPageTemp+plus;
        if(totalPageTemp<=0){
            totalPageTemp = 1;
        }
        this.totalPage = totalPageTemp;

        //设置当前页数
        //总页数小雨当前页数，应将当前页数设置为总页数
        if(this.totalPage<this.currentPage){
            this.currentPage = this.totalPage;
        }
        //当前页数小雨1设置为1
        if(this.currentPage<1){
            this.currentPage = 1;
        }
        //设置limit参数
        this.dbIndex=(this.currentPage-1)*this.pageNumber;
        this.dbNumber=this.pageNumber;
        System.out.println("123213========>234243242"+this.totalPage);
    }

    public void setTotalNumbers(int totalNumbers){
        this.totalNumber = totalNumbers;
        this.count();
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        System.out.println("set方法"+pageNumber);
        this.pageNumber = pageNumber;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber) {
        this.dbNumber = dbNumber;
    }
    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
