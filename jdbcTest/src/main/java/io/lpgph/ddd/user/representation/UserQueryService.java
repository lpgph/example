package io.lpgph.ddd.user.representation;

import io.lpgph.ddd.common.bean.page.Page;
import io.lpgph.ddd.common.bean.page.PageResult;
import io.lpgph.ddd.common.bean.page.Sort;
import io.lpgph.ddd.user.representation.params.UserQueryParams;
import io.lpgph.ddd.user.representation.dao.UserQueryDao;
import io.lpgph.ddd.user.representation.response.UserInfoResult;
import io.lpgph.ddd.user.representation.response.UserResult;
import io.lpgph.ddd.user.representation.response.UserOptionResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserQueryService {

  private final UserQueryDao userQueryDao;

  public UserInfoResult getInfoById(long id) {
    return userQueryDao.getInfoById(id);
  }

  public PageResult<UserResult> listPageByQuery(
          UserQueryParams queryParams, Sort sort, Page page) {
    Long total = userQueryDao.countByQuery(queryParams);
    if (total == null || total == 0) return PageResult.<UserResult>withDefault().build();
    return PageResult.<UserResult>builder()
        .total(total)
        .data(userQueryDao.listPageByQuery(queryParams, List.of(sort), page))
        .build();
  }

  public List<UserOptionResult> findOptionByName(String keyword, Integer limit) {
    return userQueryDao.findOptionByName(keyword, (limit==null||limit==0)?20:limit);
  }
}
