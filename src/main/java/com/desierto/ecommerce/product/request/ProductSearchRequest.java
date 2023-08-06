package com.desierto.ecommerce.product.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearchRequest {
    private String keyword;

    private Integer page;

    private Integer size;

    public Integer getPage(){
        if(page == null || page == 0){
            return 0;
        }

        return page;
    }

    public void setPage(Integer page){
        this.page = page;
    }

    public Integer getSize(){
        if(size == null || size == 0){
            return 0;
        }
        return size;
    }

    public void setSize(Integer size){
        this.size = size;
    }
}
