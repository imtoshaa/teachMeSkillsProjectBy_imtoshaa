package by.tms.partshop.util;

import static by.tms.partshop.util.constants.ShopConstants.IS_FIRST_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.IS_LAST_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.NUMBER_OF_PAGES;
import static by.tms.partshop.util.constants.ShopConstants.PAGE_NUMBER;
import static by.tms.partshop.util.constants.ShopConstants.PAGE_SIZE;

import by.tms.partshop.entities.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class PageAttributes {

  public void setPageAttributes(ModelMap modelMap, Page<? extends BaseEntity> entityPage,
      int pageNumber, int pageSize) {
    modelMap.addAttribute(NUMBER_OF_PAGES, entityPage.getTotalPages());
    modelMap.addAttribute(IS_FIRST_PAGE, entityPage.isFirst());
    modelMap.addAttribute(IS_LAST_PAGE, entityPage.isLast());
    modelMap.addAttribute(PAGE_NUMBER, pageNumber);
    modelMap.addAttribute(PAGE_SIZE, pageSize);
  }
}
