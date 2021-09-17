package io.lpgph.ddd.user.representation.dao;

import io.lpgph.ddd.user.representation.params.UserQueryParams;
import io.lpgph.ddd.user.representation.response.UserResult;
import io.lpgph.ddd.user.representation.response.UserInfoResult;
import io.lpgph.ddd.user.representation.response.UserOptionResult;
import io.lpgph.ddd.common.bean.page.Page;
import io.lpgph.ddd.common.bean.page.Sort;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserQueryDao {

  UserInfoResult getInfoById(Long id);

  List<UserOptionResult> findOptionByName(@Param("keyword") String keyword, @Param("limit") int limit);

  Long countByQuery(@Param("query") UserQueryParams query);

  List<UserResult> listPageByQuery(
          @Param("query") UserQueryParams queryParams, @Param("sort") List<Sort> sort, @Param("page") Page page);
}
