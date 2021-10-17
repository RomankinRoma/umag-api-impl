package kz.reself.impl.constant;

import org.springframework.data.domain.Sort;

public class Constants {
  public static final int DEFAULT_PAGE_SIZE = 5;
  public static final int DEFAULT_PAGE_NUMBER = 0;
  public static final int SUCESS_STATUS_CODE = 200;
  public static final String PAGE_NAME = "page";
  public static final String SIZE_NAME = "size";
  public static final String SORT_NAME = "sort";
  public static final String DECOMISSION_EXCHANGE = "decomission";
  public static final String DECOMISSION_ROUTING_KEY = "general";
  public static final Sort.Direction DEFAULT_SORT_DIRECTION = Sort.Direction.ASC;
}
