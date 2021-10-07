# Java Agent 实现测试代码

当前实现为未使用上下文类加载器的版本，会在 `ServiceLoader` 中的 `nextService` 方法中触发 `ServiceConfigurationError`:

```java
if (!service.isAssignableFrom(c)) {
    fail(service, "Provider " + cn  + " not a subtype");
}

private static void fail(Class<?> service, String msg) throws ServiceConfigurationError {
    throw new ServiceConfigurationError(service.getName() + ": " + msg);
}
```

在应用侧配置启动参数进行测试：
```commandline
-javaagent:/Users/tianshuang/IdeaProjects/agent-test/target/agent-test-1.0-SNAPSHOT.jar
```