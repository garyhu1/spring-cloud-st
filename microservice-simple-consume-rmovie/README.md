# service-consumer
服务消费者


## Feign

## Zuul
禁用一些默认的过滤器（例如DebugFilter、FormBodyWrapperFilter、PreDecorationFilter等）
这些默认的过滤器都存放在org.springframework.cloud.netflix.zuul.filters包中
###禁用配置：
`zuul.<SimpleClassName>.<filterType>.disable=true`
**例如**
把org.springframework.cloud.netflix.zuul.filters.post.SendResponseFilter设置禁用
`zuul.SendResponseFilter.post.disable=true`
或者
PreRequestLogFilter禁用
`zuul.PreRequestLogFilter.pre.disable=true`
