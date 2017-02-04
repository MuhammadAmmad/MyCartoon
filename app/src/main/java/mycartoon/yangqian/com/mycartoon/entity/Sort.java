package mycartoon.yangqian.com.mycartoon.entity;

/**
 * 区域分类
 * Created by Administrator on 2017/1/6.
 */

public class Sort {
    private String area;

    public Sort(String sort) {
        this.area = sort;
    }

    public String getSort() {
        return area;
    }

    public void setSort(String sort) {
        this.area = sort;
    }
}
