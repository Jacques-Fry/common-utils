package com.jacques.utils.style;

import java.io.Serializable;
import java.util.List;

/**
 * 分页返回格式
 *
 * @info 分页返回格式
 * @version 1.0.0
 * @author Jacques·Fry
 * @since 2021/1/4 17:56
 */
public class PageResult<T> implements Serializable {

  private Long total;
  private List<T> rows;

  public PageResult(Long total, List<T> rows) {
    super();
    this.total = total;
    this.rows = rows;
  }

  public Long getTotal() {
    return total;
  }

  public void setTotal(Long total) {
    this.total = total;
  }

  public List<T> getRows() {
    return rows;
  }

  public void setRows(List<T> rows) {
    this.rows = rows;
  }


}
