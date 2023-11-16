
- sidecar（istio-proxy）是开源项目envoy的扩展版，Envoy是用C＋＋开发的非常有影响力的轻量级高性能开源服务代理。作为服务网格的数据面，是istio架构中唯一的数据面组件， Envoy 提供了动态服务发现、负载均衡、TLS , HTTP/2 及gRPC 代理、熔断器、健康检查、流量拆分、灰度发布、故障注入等功能。在istio-proxy容器中除了有Envoy ，还有一个pilot-agent的守护进程。


- istio-pilot是istio的控制中枢Pilot服务。如果把数据面的envoy 也看作一种agent， 则Pilot 类似传统C /S 架构中的服务端Master，下发指令控制客户端完成业务功能。和传统的微服务架构对比， Pilot 至少涵盖服务注册中心和Config Server等管理组件的功能。
Pilot 更重要的一个功能是向数据面下发规则，包括VirtualService 、DestinationRule 、Gateway 、ServiceEntry 等流量治理规则，也包括认证授权等安全规则。Pilot 负责将各种规则转换成Envoy 可识别的格式，通过标准的XDS 协议发送给Envoy,指导Envoy 完成功作。在通信上， Envoy 通过gRPC 流式订阅Pilot 的配置资源。如下图所示， Pilot 将VirtualService 表达的路由规则分发到Evnoy 上， Envoy 根据该路由规则进行流量转发。


- mixer： Istio 控制面部署了两个Mixer 组件： istio-telemetry 和istio-policy ，分别处理遥测数据的
  收集和策略的执行。
  


