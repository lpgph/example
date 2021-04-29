### 说明
    认证 authentication
    授权 authorization
    原则上认证与授权是分离的  用户先认证后根据认证服务器获得的sessionId或token取请求授权服务器获取授权token
    
    网关分为API网关和客户端网关 API网关是第三方应用所使用的网关
    客户端网关是自己系统内部应用使用的网关 在该网关通过认证后通过授权码获取授权token存储到该客户端session中返回sessionId 
    前端应用通过sessionId请求网关  网关根据sessionId获取授权token 判断 access_token 是否过期 如果过期则通过刷新token重新获取授权token
    然后通过 access_token 请求资源服务器返回资源
    所有的资源服务器需要授权接口都是通过 access_token 请求的
    
    Q1: 手机端如何登录？  
        方案1 通过API的方式登录 登录后得到授权token 使用 access_token 请求登录 过期后请求刷新
        方案2 认证成功后生成sessionId将 token 存储 login 携带return 
        
    Q2: 浏览器端如何组成SSO
        浏览器端使用cookie+session的方式来处理 
    
    Q3: 不同的应用如何组合成不同的SSO 如 前台项目 如商城 平台服务 其他应用 组合成一个SSO, 后台项目 如CMS,ERP,OA 组合成一个SSO
        不需要 只是用户角色不同 例如 用户可以划分 会员 商家 推广者 开发者 管理员(客服 运营 ....)
        会员是基础权限  其他是附加权限
        还有授权的时候判断用户是否有该权限域即可  如CMS客户端有必须有CMS登录权限  那么授权时判断用户是否存在该权限 如果不存在则授权失败
        
    Q4: 针对Q3问题要不要分为前台用户和后台用户？
        不需要 用户只是角色不同 根据不同的请求接口分离不同的请求角色即可
        
    Q5: Cookie 保存 access_token 则需要修改 WebSessionServerSecurityContextRepository 
    
    ===========================================
     目前支持两种登录方案  
     方案1 在header中添加 device_type 则会直接返回jwt token 由前端控制token刷新 请求 api-gateway 
     方案2 在请求参数中添加 return_url, 例如 http://localhost:8081/oauth2/authorization/c1 (client1 客户端的认证请求) 
     请求进行认证转发，最后跳转到http://localhost:8081/(由client1客户端控制) 携带请求的cookie(认证cookie(s0)和客户端认证授权cookie)
     接口可以通过携带cookie或者在header中添加X-Auth-Token进行请求 值为客户端 sessionId, device_type 存在则返回 header
    ===========================================
    uaa-gateway  单独的网关
    authentication 认证 指的是当前用户的身份，解决 “我是谁？”的问题，当用户登陆过后系统便能追踪到他的身份并做出符合相应业务逻辑的操作 
        认证服务包含 用户的信息以及认证方式 如账号密码登录 微信授权  手机验证码 
    authorization 授权 指的是什么样的身份被允许访问某些资源，解决“我能做什么？”的问题，在获取到用户身份后继续检查用户的权限
        根据用户认证成功后生成的凭证(credentials) 如sessionId,token等进行授权 生成token
        
    认证返回sessionId 或token
        过滤器中需要通过cookie或header中token获取authentication 并存储到上下文中
    然后根据认证服务获取授权token

### 参考
    http://seanthefish.com/2020/07/24/micro-service-authorization/index.html
    https://insights.thoughtworks.cn/api-2/
    https://www.oauth.com/
    https://www.cnblogs.com/linianhui/p/openid-connect-core.html
    https://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html
    https://www.jianshu.com/p/50ade6f2e4fd
    
    https://github.com/Snailclimb/spring-security-jwt-guide/blob/master/docs/SpringSecurity%E4%BB%8B%E7%BB%8D.md
    http://qtdebug.com/spring-security-8-token/
    
    https://www.cnblogs.com/lihaoyang/p/12142861.html
    https://www.cnblogs.com/hzhuxin/p/10849741.html
    https://www.cnblogs.com/hzhuxin/p/10778279.html
    
    微服务结构

    https://docs.microsoft.com/zh-cn/dotnet/architecture/microservices/architect-microservice-container-applications/direct-client-to-microservice-communication-versus-the-api-gateway-pattern
    https://docs.microsoft.com/zh-cn/azure/architecture/microservices/design/gateway
    https://docs.microsoft.com/zh-cn/dotnet/architecture/microservices/multi-container-microservice-net-applications/microservice-application-design
    https://docs.microsoft.com/zh-cn/xamarin/xamarin-forms/enterprise-application-patterns/authentication-and-authorization
    https://microservices.io/patterns/apigateway.html
