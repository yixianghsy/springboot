package hsy.com.mybatis.api;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    //显示的记录
    private List<T> listRecords;
    //总页数
    private int totalPage;
    //总记录数
    private int totalRecords;
    //当前页码
    private int currentPageNum;
    //每页开始的记录
    private int startIndex;
    //每页显示多少条数据
    private int pageSize;
    // 判断上一页
    private int upPage;
    // 判断下一页
    private int downPage;
    //判断是否有首页
    private boolean isFistPage;
    //判断是否有尾页
    private boolean isLastPage;
    //计算显示的页码
    private int startPage;
    private int endPage;

    /**
     *
     * @param currentPageNum 当前页码
     * @param totalRecords 总记录数
     */
    public Page(int currentPageNum,int totalRecords){
        //当前页码，页面传过来
        this.currentPageNum=currentPageNum;
        //总记录，数据库查出来的
        this.totalRecords=totalRecords;
        //总页数
        this.totalPage=totalRecords%pageSize==0?totalRecords/pageSize:(totalRecords/pageSize)+1;
        //每页开始记录的索引
        this.startIndex=(currentPageNum-1)*pageSize;
        //计算上一页
        this.upPage = currentPageNum<=1?1:currentPageNum-1;
        //计算下一页
        this.downPage = currentPageNum>=totalPage?totalPage:currentPageNum+1;
        //判断是否有首页
        this.isFistPage = (currentPageNum>1)?true:false;
        //判断是否有尾页
        this.isLastPage = (currentPageNum<totalPage)?true:false;

        if(totalPage<5){
            startPage=1;
            endPage=totalPage;
        }else{
            startPage=currentPageNum-2;
            endPage=currentPageNum+2;
            if(startPage<1){
                startPage=1;
                endPage=startPage+4;
            }
            if(endPage>totalPage){
                endPage=totalPage;
                startPage=endPage-4;
            }
        }

    }

    /**
     *
     * @param currentPageNum 当前页码
     * @param totalRecords 总记录数
     * @param pageSize 每页显示记录数
     */
    public Page(int currentPageNum,int totalRecords,int pageSize){
        //当前页码，页面传过来
        this.currentPageNum=currentPageNum;
        //每页显示多少条
        this.pageSize = pageSize;
        //总记录，数据库查出来的
        this.totalRecords=totalRecords;
        //总页数
        this.totalPage=totalRecords%pageSize==0?totalRecords/pageSize:(totalRecords/pageSize)+1;
        //每页开始记录的索引
        this.startIndex=(currentPageNum-1)*pageSize;
        //计算上一页
        this.upPage = currentPageNum<=1?1:currentPageNum-1;
        //计算下一页
        this.downPage = currentPageNum>=totalPage?totalPage:currentPageNum+1;
        //判断是否有首页
        this.isFistPage = (currentPageNum>1)?true:false;
        //判断是否有尾页
        this.isLastPage = (currentPageNum<totalPage)?true:false;

        if(totalPage<5){
            startPage=1;
            endPage=totalPage;
        }else{
            startPage=currentPageNum-2;
            endPage=currentPageNum+2;
            if(startPage<1){
                startPage=1;
                endPage=startPage+4;
            }
            if(endPage>totalPage){
                endPage=totalPage;
                startPage=endPage-4;
            }
        }

    }
    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public List<T> getListRecords() {
        return listRecords;
    }
    public void setListRecords(List<T> listRecords) {
        this.listRecords = listRecords;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }
    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }
    public int getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getUpPage() {
        return upPage;
    }
    public void setUpPage(int upPage) {
        this.upPage = upPage;
    }

    public int getDownPage() {
        return downPage;
    }
    public void setDownPage(int downPage) {
        this.downPage = downPage;
    }

    public boolean getIsFistPage() {
        return isFistPage;
    }

    public void setIsFistPage(boolean isFistPage) {
        this.isFistPage = isFistPage;
    }

    public boolean getIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public Page() {
        super();
    }

}