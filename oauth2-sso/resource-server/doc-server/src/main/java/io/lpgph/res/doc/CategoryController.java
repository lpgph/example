package io.lpgph.res.doc;

import io.lpgph.res.doc.command.CreateCommand;
import io.lpgph.res.doc.common.bean.page.PageResult;
import io.lpgph.res.doc.common.bean.result.Result;
import io.lpgph.res.doc.response.QueryResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("categories")
@Tag(name = "categories", description = "类目 API文档")
public class CategoryController {

  @Operation(
      summary = "创建类目",
      description = "创建类目",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PostMapping
  public void create(@RequestBody CreateCommand command) {}

  @Operation(
      summary = "修改类目",
      description = "修改类目",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "类目id", example = "12"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PutMapping("{id}")
  public void change(@PathVariable("id") long id, @RequestBody CreateCommand command) {}

  @Operation(
      summary = "移动类目节点",
      description = "将类目节点移动到另一个节点下 并更改移动节点所有子节点的依赖",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "移动节点ID", example = "12"),
        @Parameter(name = "toId", description = "模板节点ID", example = "13"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PutMapping("{id}/move/{toId}")
  public void move(@PathVariable("id") long id, @PathVariable("toId") long toId) {}

  @Operation(
      summary = "废弃类目",
      description = "将废弃该类目以及其所有子类目",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "类目ID", example = "12"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PatchMapping("{id}/discard")
  public void discard(@PathVariable("id") long id) {}

  @Operation(
      summary = "批量废弃类目",
      description = "将废弃该类目以及其所有子类目",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PostMapping("discard")
  public void discard(@RequestBody Set<Long> ids) {}

  @Operation(
      summary = "还原废弃类目",
      description = "将废弃的类目以及其所有子类目还原",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "类目ID", example = "12"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PatchMapping("{id}/restore")
  public void restore(@PathVariable("id") long id) {}

  @Operation(
      summary = "批量还原废弃类目",
      description = "将废弃的类目以及其所有子类目还原",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PostMapping("restore")
  public void restore(@RequestBody Set<Long> ids) {}

  @Operation(
      summary = "启用已禁用类目",
      description = "将已禁用的类目以及其所有子类目启用",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "类目ID", example = "12"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PatchMapping("{id}/enable")
  public void enable(@PathVariable("id") long id) {}

  @Operation(
      summary = "批量启用已禁用类目",
      description = "将已禁用的类目以及其所有子类目启用",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PostMapping("enable")
  public void enable(@RequestBody Set<Long> ids) {}

  @Operation(
      summary = "禁用已启用类目",
      description = "将已启用的类目以及其所有子类目禁用",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "类目ID", example = "12"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PatchMapping("{id}/disable")
  public void disable(@PathVariable("id") long id) {}

  @Operation(
      summary = "批量禁用已启用类目",
      description = "将已启用的类目以及其所有子类目禁用",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PostMapping("disable")
  public void disable(@RequestBody Set<Long> ids) {}

  @Operation(
      summary = "删除已废弃类目",
      description = "将已废弃的类目以及其所有子类目删除",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "类目ID", example = "12"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @DeleteMapping("{id}")
  public void remove(@PathVariable("id") long id) {}

  @Operation(
      summary = "批量删除已废弃类目",
      description = "将已废弃的类目以及其所有子类目删除",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @PostMapping("remove")
  public void remove(@RequestBody Set<Long> ids) {}

  @Operation(
      summary = "查询",
      description = "返回：所有用户数据",
      parameters = {
        @Parameter(name = "page", description = "页码", required = true, example = "1"),
        @Parameter(name = "pageSize", description = "页大小", example = "20"),
        @Parameter(name = "sort", description = "排序字段 前缀有'-'为倒序", example = "-name"),
        @Parameter(name = "id", description = "属性id", example = "7"),
        @Parameter(name = "name", description = "属性搜索关键字", example = "尺寸"),
        @Parameter(name = "discard", description = "是否丢弃", example = "false"),
        @Parameter(name = "enable", description = "是否启用", example = "true"),
        @Parameter(name = "afterDate", description = "创建开始时间", example = "2020-08-10 12:00:00"),
        @Parameter(name = "beforeDate", description = "创建结束时间", example = "2020-08-10 12:00:00"),
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "管理员权限"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:read"},
            name = "属性或属性读取权限")
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "返回成功"),
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @GetMapping
  public PageResult<QueryResult> listPageByQuery(
      @RequestParam int page,
      @RequestParam int pageSize,
      String sort,
      Long id,
      String name,
      Boolean discard,
      Boolean enable,
      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime afterDate,
      @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime beforeDate) {
    return null;
  }

  @Operation(
      summary = "获取有效的类目树",
      description = "返回：类目树",
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "返回成功"),
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @GetMapping("tree")
  public List<QueryResult> list() {
    return null;
  }

  @Operation(
      summary = "根据id查询详情",
      description = "返回：数据",
      parameters = {
        @Parameter(
            name = "access_token",
            description = "授权码",
            required = true,
            schema = @Schema(implementation = String.class),
            in = ParameterIn.HEADER,
            example = "abc"),
        @Parameter(name = "id", description = "类目id", example = "1")
      },
      security = {
        @SecurityRequirement(
            scopes = {"SCOPE_admin"},
            name = "必须"),
        @SecurityRequirement(
            scopes = {"attributes", "attributes:create"},
            name = "任意一个")
      },
      responses = {
        @ApiResponse(responseCode = "200", description = "返回成功"),
        @ApiResponse(
            responseCode = "401",
            description = "参数错误",
            content = @Content(schema = @Schema(implementation = Result.class))),
        @ApiResponse(
            responseCode = "500",
            description = "系统内部异常",
            content = @Content(schema = @Schema(implementation = Result.class)))
      })
  @GetMapping("{id}")
  public QueryResult getInfo(@PathVariable("id") long id) {
    return null;
  }

  //  @GetMapping("/option")
  //  public List<CategoryOptionResult> findOptionByName() {
  //    return categoryQueryService.findOptionByName();
  //  }
}
