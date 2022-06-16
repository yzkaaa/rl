package com.yzk.sys.vo.params;

import lombok.Data;

@Data
public class PageParams {
    /**
     共<span class="info-number">${total}</span>条结果，
     分成<span class="info-number">${pagenum}</span>页显示，
     当前第<span class="info-number">${page}</span>页
     */
    //多少条结果
    Long total;
    //分为多少页显示
    Long pagenum;
    //第几页
    Integer page;
}
