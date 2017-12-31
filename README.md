Spring Boot  Web应用程序中注册 Servlet 的方法实例

本文实例工程源代码：https://github.com/KotlinSpringBoot/demo1_add_servlet

我们对如何创建Controller 来响应JSON 以及如何显示数据到页面中，已经很熟悉了。


Web开发使用 Controller 基本上可以完成大部分需求，但是我们还可能会用到 Servlet、Filter、Listener、Interceptor 等等。

当使用spring-Boot时，嵌入式Servlet容器通过扫描注解的方式注册Servlet、Filter和Servlet规范的所有监听器（如HttpSessionListener监听器）。 
Spring boot 的主 Servlet 为 DispatcherServlet，其默认的url-pattern为“/”。也许我们在应用中还需要定义更多的Servlet，该如何使用SpringBoot来完成呢？

在spring boot中添加自己的Servlet有两种方法，代码注册Servlet和注解自动注册（Filter和Listener也是如此）。 
一、代码注册通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean 获得控制。 
也可以通过实现 ServletContextInitializer 接口直接注册。

二、在 SpringBootApplication 上使用@ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。

通过代码注册Servlet示例



![image.png](http://upload-images.jianshu.io/upload_images/1233356-b771d4dc39c18099.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-d7eccc4f3d964a69.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-93b9bbe6fc26e5a6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-c053d17b3ef4bce5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-8a8cd5f04e76abdc.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-588427a9103f800a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-0d1ac88cb117a6e2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


Demo1AddServletApplication.kt

```kotlin
package com.easy.springboot.demo1_add_servlet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Demo1AddServletApplication

fun main(args: Array<String>) {
    runApplication<Demo1AddServletApplication>(*args)
}

```

build.gradle

```groovy
buildscript {
	ext {
		kotlinVersion = '1.2.0'
		springBootVersion = '2.0.0.M7'
	}
	repositories {
		mavenCentral()
		maven { url "https://repo.spring.io/snapshot" }
		maven { url "https://repo.spring.io/milestone" }
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
		classpath("org.jetbrains.kotlin:kotlin-allopen:${kotlinVersion}")
	}
}

apply plugin: 'kotlin'
apply plugin: 'kotlin-spring'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

group = 'com.easy.springboot'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = 1.8
compileKotlin {
	kotlinOptions.jvmTarget = "1.8"
}
compileTestKotlin {
	kotlinOptions.jvmTarget = "1.8"
}

repositories {
	mavenCentral()
	maven { url "https://repo.spring.io/snapshot" }
	maven { url "https://repo.spring.io/milestone" }
}


dependencies {
	compile('org.springframework.boot:spring-boot-starter-web')
	compile("org.jetbrains.kotlin:kotlin-stdlib-jre8")
	compile("org.jetbrains.kotlin:kotlin-reflect")
	testCompile('org.springframework.boot:spring-boot-starter-test')
}

```


工程目录
```
demo1_add_servlet$ tree
.
├── build
│   ├── kotlin
│   │   ├── compileKotlin
│   │   └── compileTestKotlin
│   └── kotlin-build
│       └── version.txt
├── build.gradle
├── demo1_add_servlet.iml
├── demo1_add_servlet.ipr
├── demo1_add_servlet.iws
├── demo1_add_servlet_main.iml
├── demo1_add_servlet_test.iml
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── out
│   └── production
│       ├── classes
│       │   ├── META-INF
│       │   │   └── demo1_add_servlet_main.kotlin_module
│       │   └── com
│       │       └── easy
│       │           └── springboot
│       │               └── demo1_add_servlet
│       │                   ├── Demo1AddServletApplication.class
│       │                   └── Demo1AddServletApplicationKt.class
│       └── resources
│           └── application.properties
└── src
    ├── main
    │   ├── java
    │   ├── kotlin
    │   │   └── com
    │   │       └── easy
    │   │           └── springboot
    │   │               └── demo1_add_servlet
    │   │                   └── Demo1AddServletApplication.kt
    │   └── resources
    │       ├── application.properties
    │       ├── static
    │       └── templates
    └── test
        ├── java
        ├── kotlin
        │   └── com
        │       └── easy
        │           └── springboot
        │               └── demo1_add_servlet
        │                   └── Demo1AddServletApplicationTests.kt
        └── resources

35 directories, 18 files

```

启动应用

/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/bin/java -XX:TieredStopAtLevel=1 -noverify -Dspring.output.ansi.enabled=always -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=51448 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Djava.rmi.server.hostname=127.0.0.1 -Dspring.liveBeansView.mbeanDomain -Dspring.application.admin.enabled=true "-javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=51449:/Applications/IntelliJ IDEA.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/lib/packager.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/lib/tools.jar:/Users/jack/KotlinSpringBoot/demo1_add_servlet/out/production/classes:/Users/jack/KotlinSpringBoot/demo1_add_servlet/out/production/resources:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-web/2.0.0.M7/eb364c43417c6de883c38ffc9d79b91a63c9c3bd/spring-boot-starter-web-2.0.0.M7.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jre8/1.1.61/7cb0d1b54614623a4b297af1eea7bc3aa0e9e63/kotlin-stdlib-jre8-1.1.61.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.1.61/bb6da1a122c3fba2f7ee5c71946441e4f756fad4/kotlin-reflect-1.1.61.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-json/2.0.0.M7/7f352d7960cbe1a341e0721625dddb1bca6c5313/spring-boot-starter-json-2.0.0.M7.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter/2.0.0.M7/b9affaea8ab21294386d5c2682b66f143852878d/spring-boot-starter-2.0.0.M7.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-tomcat/2.0.0.M7/709f5f42c3cfdc6d5a5d6921b7bcb7714f8d638c/spring-boot-starter-tomcat-2.0.0.M7.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.hibernate.validator/hibernate-validator/6.0.5.Final/c0a53a8178a0487c5f2fe84434b9965a3cd44f22/hibernate-validator-6.0.5.Final.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-webmvc/5.0.2.RELEASE/8dbe4a28cae3ffa222aeff6776a3928a88d66e6f/spring-webmvc-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-web/5.0.2.RELEASE/eea8edef792fb93c1c55cc61078ce14ffb3542df/spring-web-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jre7/1.1.61/59dfce93b1995717338435dd974884007d8e8474/kotlin-stdlib-jre7-1.1.61.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.1.61/fa7813a26c548c9c412dd2d42fb466cfcd8dcf3c/kotlin-stdlib-1.1.61.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/2.0.0.M7/db2360882ec36c119c4378d01b0325a833b07271/spring-boot-autoconfigure-2.0.0.M7.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot/2.0.0.M7/b1e1ad98517ae8988dd578b3ae2e2705c8f5634/spring-boot-2.0.0.M7.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-logging/2.0.0.M7/328a8032b5ed852958d410dec02638c0a93b3cb1/spring-boot-starter-logging-2.0.0.M7.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/javax.annotation/javax.annotation-api/1.3.1/20a2c0583598d68b0835474bbe07792d4f3b219f/javax.annotation-api-1.3.1.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/5.0.2.RELEASE/743a5f6d94a8af5759e6e70c360a7b4a47a704c/spring-context-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-aop/5.0.2.RELEASE/4d49aa2cff33be79da99413e28a905898600ccaa/spring-aop-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-beans/5.0.2.RELEASE/301ee07b390bc8b5691f4206411b49beb06f7ff2/spring-beans-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-expression/5.0.2.RELEASE/246bf50b5b46379041d333b4a46a01a7aea0b788/spring-expression-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/5.0.2.RELEASE/45b2958ab3fb022dd29f8b1c553ebf1c75a144aa/spring-core-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.yaml/snakeyaml/1.19/2d998d3d674b172a588e54ab619854d073f555b5/snakeyaml-1.19.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.datatype/jackson-datatype-jdk8/2.9.2/9b0b7c623262e29c73e8f6a78f6150372d2066f8/jackson-datatype-jdk8-2.9.2.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.datatype/jackson-datatype-jsr310/2.9.2/e1653d338703d8233cc1ac18c6722510bdaceb4f/jackson-datatype-jsr310-2.9.2.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.module/jackson-module-parameter-names/2.9.2/be78ca220839b2c53ebd33fd4d580b5a0cacdd5a/jackson-module-parameter-names-2.9.2.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-databind/2.9.2/1d8d8cb7cf26920ba57fb61fa56da88cc123b21f/jackson-databind-2.9.2.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-websocket/8.5.23/52f07abcae10dc7e1764304b0877def175c2c833/tomcat-embed-websocket-8.5.23.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-core/8.5.23/79261793a47f507890ee08f749b9d81774e4f7f0/tomcat-embed-core-8.5.23.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.apache.tomcat.embed/tomcat-embed-el/8.5.23/98d979cde444dffa6d434c8377d0123b2dfa614c/tomcat-embed-el-8.5.23.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/javax.validation/validation-api/2.0.0.Final/4a7ff48084bd336c4de1f79f027f6f77badf399e/validation-api-2.0.0.Final.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.jboss.logging/jboss-logging/3.3.1.Final/c46217ab74b532568c0ed31dc599db3048bd1b67/jboss-logging-3.3.1.Final.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/com.fasterxml/classmate/1.3.4/3d5f48f10bbe4eb7bd862f10c0583be2e0053c6/classmate-1.3.4.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.2.3/7c4f3c474fb2c041d8028740440937705ebb473a/logback-classic-1.2.3.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-to-slf4j/2.10.0/f7e631ccf49cfc0aefa4a2a728da7d374c05bd3c/log4j-to-slf4j-2.10.0.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.slf4j/jul-to-slf4j/1.7.25/af5364cd6679bfffb114f0dec8a157aaa283b76/jul-to-slf4j-1.7.25.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.springframework/spring-jcl/5.0.2.RELEASE/212a0bdd54e026641cf527aeb8e578f86e402bf1/spring-jcl-5.0.2.RELEASE.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-annotations/2.9.0/7c10d545325e3a6e72e06381afe469fd40eb701/jackson-annotations-2.9.0.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.9.2/aed20e50152a2f19adc1995c8d8f307c7efa414d/jackson-core-2.9.2.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.apache.tomcat/tomcat-annotations-api/8.5.23/aaf17df9fe0240e9e9d5375d24d5f177174b73d9/tomcat-annotations-api-8.5.23.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.2.3/864344400c3d4d92dfeb0a305dc87d953677c03c/logback-core-1.2.3.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/1.7.25/da76ca59f6a57ee3102f8f9bd9cee742973efa8a/slf4j-api-1.7.25.jar:/Users/jack/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-api/2.10.0/fec5797a55b786184a537abd39c3fa1449d752d6/log4j-api-2.10.0.jar com.easy.springboot.demo1_add_servlet.Demo1AddServletApplicationKt
objc[30612]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/bin/java (0x10c75a4c0) and /Library/Java/JavaVirtualMachines/jdk1.8.0_40/Contents/Home/jre/lib/libinstrument.dylib (0x10dfb74e0). One of the two will be used. Which one is undefined.

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::             (v2.0.0.M7)

2017-12-31 13:51:44.939  INFO 30612 --- [           main] c.e.s.d.Demo1AddServletApplicationKt     : Starting Demo1AddServletApplicationKt on jacks-MacBook-Air.local with PID 30612 (/Users/jack/KotlinSpringBoot/demo1_add_servlet/out/production/classes started by jack in /Users/jack/KotlinSpringBoot/demo1_add_servlet)
2017-12-31 13:51:44.944  INFO 30612 --- [           main] c.e.s.d.Demo1AddServletApplicationKt     : No active profile set, falling back to default profiles: default
2017-12-31 13:51:45.100  INFO 30612 --- [           main] ConfigServletWebServerApplicationContext : Refreshing org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@63355449: startup date [Sun Dec 31 13:51:45 CST 2017]; root of context hierarchy
2017-12-31 13:51:45.364  WARN 30612 --- [kground-preinit] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:46.101  WARN 30612 --- [kground-preinit] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:48.480  INFO 30612 --- [           main] o.h.v.i.engine.ValidatorFactoryImpl      : HV000238: Temporal validation tolerance set to 0.
2017-12-31 13:51:49.411  INFO 30612 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2017-12-31 13:51:49.443  INFO 30612 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2017-12-31 13:51:49.446  INFO 30612 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/8.5.23
2017-12-31 13:51:49.478  INFO 30612 --- [ost-startStop-1] o.a.catalina.core.AprLifecycleListener   : The APR based Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: [/Users/jack/Library/Java/Extensions:/Library/Java/Extensions:/Network/Library/Java/Extensions:/System/Library/Java/Extensions:/usr/lib/java:.]
2017-12-31 13:51:49.659  INFO 30612 --- [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2017-12-31 13:51:49.660  INFO 30612 --- [ost-startStop-1] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 4574 ms
2017-12-31 13:51:49.833  WARN 30612 --- [ost-startStop-1] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:49.884  INFO 30612 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-12-31 13:51:49.892  INFO 30612 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-12-31 13:51:49.893  INFO 30612 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
2017-12-31 13:51:49.894  INFO 30612 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'httpPutFormContentFilter' to: [/*]
2017-12-31 13:51:49.894  INFO 30612 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'requestContextFilter' to: [/*]
2017-12-31 13:51:50.063  WARN 30612 --- [           main] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:50.167  INFO 30612 --- [           main] o.h.v.i.engine.ValidatorFactoryImpl      : HV000238: Temporal validation tolerance set to 0.
2017-12-31 13:51:50.296  WARN 30612 --- [           main] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:50.353  WARN 30612 --- [           main] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:50.355  WARN 30612 --- [           main] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:50.436  INFO 30612 --- [           main] s.w.s.m.m.a.RequestMappingHandlerAdapter : Looking for @ControllerAdvice: org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@63355449: startup date [Sun Dec 31 13:51:45 CST 2017]; root of context hierarchy
2017-12-31 13:51:50.607  INFO 30612 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
2017-12-31 13:51:50.608  INFO 30612 --- [           main] s.w.s.m.m.a.RequestMappingHandlerMapping : Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
2017-12-31 13:51:50.651  INFO 30612 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-31 13:51:50.651  INFO 30612 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-31 13:51:50.669  WARN 30612 --- [           main] o.s.h.c.j.Jackson2ObjectMapperBuilder    : For Jackson Kotlin classes support please add "com.fasterxml.jackson.module:jackson-module-kotlin" to the classpath
2017-12-31 13:51:50.711  INFO 30612 --- [           main] o.s.w.s.handler.SimpleUrlHandlerMapping  : Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
2017-12-31 13:51:50.955  INFO 30612 --- [           main] o.s.j.e.a.AnnotationMBeanExporter        : Registering beans for JMX exposure on startup
2017-12-31 13:51:51.083  INFO 30612 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2017-12-31 13:51:51.089  INFO 30612 --- [           main] c.e.s.d.Demo1AddServletApplicationKt     : Started Demo1AddServletApplicationKt in 8.173 seconds (JVM running for 10.559)



![image.png](http://upload-images.jianshu.io/upload_images/1233356-49cfee9eca38b601.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




![image.png](http://upload-images.jianshu.io/upload_images/1233356-9e9140abb760ebc3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)





```ErrorMvcAutoConfiguration
package org.springframework.boot.autoconfigure.web.servlet.error;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProviders;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.MapAccessor;
import org.springframework.core.Ordered;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.PropertyPlaceholderHelper.PlaceholderResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.util.HtmlUtils;

/**
 * {@link EnableAutoConfiguration Auto-configuration} to render errors via an MVC error
 * controller.
 *
 * @author Dave Syer
 * @author Andy Wilkinson
 * @author Stephane Nicoll
 */
@Configuration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class })
// Load before the main WebMvcAutoConfiguration so that the error View is available
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties({ ServerProperties.class, ResourceProperties.class })
public class ErrorMvcAutoConfiguration {

	private final ServerProperties serverProperties;

	private final List<ErrorViewResolver> errorViewResolvers;

	public ErrorMvcAutoConfiguration(ServerProperties serverProperties,
			ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
		this.serverProperties = serverProperties;
		this.errorViewResolvers = errorViewResolversProvider.getIfAvailable();
	}

	@Bean
	@ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
	public DefaultErrorAttributes errorAttributes() {
		return new DefaultErrorAttributes(
				this.serverProperties.getError().isIncludeException());
	}

	@Bean
	@ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
	public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
		return new BasicErrorController(errorAttributes, this.serverProperties.getError(),
				this.errorViewResolvers);
	}

	@Bean
	public ErrorPageCustomizer errorPageCustomizer() {
		return new ErrorPageCustomizer(this.serverProperties);
	}

	@Bean
	public static PreserveErrorControllerTargetClassPostProcessor preserveErrorControllerTargetClassPostProcessor() {
		return new PreserveErrorControllerTargetClassPostProcessor();
	}

	@Configuration
	static class DefaultErrorViewResolverConfiguration {

		private final ApplicationContext applicationContext;

		private final ResourceProperties resourceProperties;

		DefaultErrorViewResolverConfiguration(ApplicationContext applicationContext,
				ResourceProperties resourceProperties) {
			this.applicationContext = applicationContext;
			this.resourceProperties = resourceProperties;
		}

		@Bean
		@ConditionalOnBean(DispatcherServlet.class)
		@ConditionalOnMissingBean
		public DefaultErrorViewResolver conventionErrorViewResolver() {
			return new DefaultErrorViewResolver(this.applicationContext,
					this.resourceProperties);
		}

	}

	@Configuration
	@ConditionalOnProperty(prefix = "server.error.whitelabel", name = "enabled", matchIfMissing = true)
	@Conditional(ErrorTemplateMissingCondition.class)
	protected static class WhitelabelErrorViewConfiguration {

		private final SpelView defaultErrorView = new SpelView(
				"<html><body><h1>Whitelabel Error Page</h1>"
						+ "<p>This application has no explicit mapping for /error, so you are seeing this as a fallback.</p>"
						+ "<div id='created'>${timestamp}</div>"
						+ "<div>There was an unexpected error (type=${error}, status=${status}).</div>"
						+ "<div>${message}</div></body></html>");

		@Bean(name = "error")
		@ConditionalOnMissingBean(name = "error")
		public View defaultErrorView() {
			return this.defaultErrorView;
		}

		// If the user adds @EnableWebMvc then the bean name view resolver from
		// WebMvcAutoConfiguration disappears, so add it back in to avoid disappointment.
		@Bean
		@ConditionalOnMissingBean(BeanNameViewResolver.class)
		public BeanNameViewResolver beanNameViewResolver() {
			BeanNameViewResolver resolver = new BeanNameViewResolver();
			resolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
			return resolver;
		}

	}

	/**
	 * {@link SpringBootCondition} that matches when no error template view is detected.
	 */
	private static class ErrorTemplateMissingCondition extends SpringBootCondition {

		@Override
		public ConditionOutcome getMatchOutcome(ConditionContext context,
				AnnotatedTypeMetadata metadata) {
			ConditionMessage.Builder message = ConditionMessage
					.forCondition("ErrorTemplate Missing");
			TemplateAvailabilityProviders providers = new TemplateAvailabilityProviders(
					context.getClassLoader());
			TemplateAvailabilityProvider provider = providers.getProvider("error",
					context.getEnvironment(), context.getClassLoader(),
					context.getResourceLoader());
			if (provider != null) {
				return ConditionOutcome
						.noMatch(message.foundExactly("template from " + provider));
			}
			return ConditionOutcome
					.match(message.didNotFind("error template view").atAll());
		}

	}

	/**
	 * Simple {@link View} implementation that resolves variables as SpEL expressions.
	 */
	private static class SpelView implements View {

		private final NonRecursivePropertyPlaceholderHelper helper;

		private final String template;

		private volatile Map<String, Expression> expressions;

		SpelView(String template) {
			this.helper = new NonRecursivePropertyPlaceholderHelper("${", "}");
			this.template = template;
		}

		@Override
		public String getContentType() {
			return "text/html";
		}

		@Override
		public void render(Map<String, ?> model, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			if (response.getContentType() == null) {
				response.setContentType(getContentType());
			}
			Map<String, Object> map = new HashMap<>(model);
			map.put("path", request.getContextPath());
			PlaceholderResolver resolver = new ExpressionResolver(getExpressions(), map);
			String result = this.helper.replacePlaceholders(this.template, resolver);
			response.getWriter().append(result);
		}

		private Map<String, Expression> getExpressions() {
			if (this.expressions == null) {
				synchronized (this) {
					ExpressionCollector expressionCollector = new ExpressionCollector();
					this.helper.replacePlaceholders(this.template, expressionCollector);
					this.expressions = expressionCollector.getExpressions();
				}
			}
			return this.expressions;
		}

	}

	/**
	 * {@link PlaceholderResolver} to collect placeholder expressions.
	 */
	private static class ExpressionCollector implements PlaceholderResolver {

		private final SpelExpressionParser parser = new SpelExpressionParser();

		private final Map<String, Expression> expressions = new HashMap<>();

		@Override
		public String resolvePlaceholder(String name) {
			this.expressions.put(name, this.parser.parseExpression(name));
			return null;
		}

		public Map<String, Expression> getExpressions() {
			return Collections.unmodifiableMap(this.expressions);
		}

	}

	/**
	 * SpEL based {@link PlaceholderResolver}.
	 */
	private static class ExpressionResolver implements PlaceholderResolver {

		private final Map<String, Expression> expressions;

		private final EvaluationContext context;

		ExpressionResolver(Map<String, Expression> expressions, Map<String, ?> map) {
			this.expressions = expressions;
			this.context = getContext(map);
		}

		private EvaluationContext getContext(Map<String, ?> map) {
			StandardEvaluationContext context = new StandardEvaluationContext();
			context.addPropertyAccessor(new MapAccessor());
			context.setRootObject(map);
			return context;
		}

		@Override
		public String resolvePlaceholder(String placeholderName) {
			Expression expression = this.expressions.get(placeholderName);
			return escape(expression == null ? null : expression.getValue(this.context));
		}

		private String escape(Object value) {
			return HtmlUtils.htmlEscape(value == null ? null : value.toString());
		}

	}

	/**
	 * {@link WebServerFactoryCustomizer} that configures the server's error pages.
	 */
	private static class ErrorPageCustomizer implements ErrorPageRegistrar, Ordered {

		private final ServerProperties properties;

		protected ErrorPageCustomizer(ServerProperties properties) {
			this.properties = properties;
		}

		@Override
		public void registerErrorPages(ErrorPageRegistry errorPageRegistry) {
			ErrorPage errorPage = new ErrorPage(
					this.properties.getServlet().getServletPrefix()
							+ this.properties.getError().getPath());
			errorPageRegistry.addErrorPages(errorPage);
		}

		@Override
		public int getOrder() {
			return 0;
		}

	}

	/**
	 * {@link BeanFactoryPostProcessor} to ensure that the target class of ErrorController
	 * MVC beans are preserved when using AOP.
	 */
	static class PreserveErrorControllerTargetClassPostProcessor
			implements BeanFactoryPostProcessor {

		@Override
		public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
				throws BeansException {
			String[] errorControllerBeans = beanFactory
					.getBeanNamesForType(ErrorController.class, false, false);
			for (String errorControllerBean : errorControllerBeans) {
				try {
					beanFactory.getBeanDefinition(errorControllerBean).setAttribute(
							AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, Boolean.TRUE);
				}
				catch (Throwable ex) {
					// Ignore
				}
			}
		}

	}

}
```

exclude, 有异常不会找默认error页面了，而是直接输出字符串


```kotlin
package com.easy.springboot.demo1_add_servlet

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableAutoConfiguration(exclude = [ErrorMvcAutoConfiguration::class]) // exclude, 有异常不会找默认error页面了，而是直接输出字符串
class Demo1AddServletApplication

fun main(args: Array<String>) {
    runApplication<Demo1AddServletApplication>(*args)
}

```

![image.png](http://upload-images.jianshu.io/upload_images/1233356-cd1a47ea3527ba62.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-89594714ce5f9eb0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




![image.png](http://upload-images.jianshu.io/upload_images/1233356-9ef9d6ef57f12b96.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-ab8f20a977286ff7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-b962c33ab146184c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](http://upload-images.jianshu.io/upload_images/1233356-18bf5071c0ba380c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![image.png](http://upload-images.jianshu.io/upload_images/1233356-c59f3add99673326.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-3de09260673a70f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-81306c92afdacdaa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![image.png](http://upload-images.jianshu.io/upload_images/1233356-ae96c866b61716d0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


HelloWorldServlet.kt

```
package com.easy.springboot.demo1_add_servlet.servlet

import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(urlPatterns=["/hello"])
class HelloWorldServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        println("Invoke doGet: req = $req, resp = $resp")
        doPost(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        println("Invoke doPost: req = $req, resp = $resp")
        resp.contentType = "application/json"
        val om = ObjectMapper()
        val resultJson = om.writeValueAsString(User("你好，World", "10000000001"))
        val out = resp.writer
        out.println(resultJson)
    }

    data class User(var username: String, var id: String)

}

```


@WebServlet(urlPatterns=["/hello"])
@ServletComponentScan

```kotlin
package com.easy.springboot.demo1_add_servlet

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@EnableAutoConfiguration(exclude = [ErrorMvcAutoConfiguration::class]) // exclude, 有异常不会找默认error页面了，而是直接输出字符串
@ServletComponentScan
class Demo1AddServletApplication

fun main(args: Array<String>) {
    runApplication<Demo1AddServletApplication>(*args)
}

```


![image.png](http://upload-images.jianshu.io/upload_images/1233356-f9d50470968e26a4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


中文乱码：

![image.png](http://upload-images.jianshu.io/upload_images/1233356-062ce2dbe7e0758f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

ISO-8859-1是单字节编码，自身不能显示中文。

ISO-8859-1编码是单字节编码，向下兼容ASCII，其编码范围是0x00-0xFF，0x00-0x7F之间完全和ASCII一致，0x80-0x9F之间是控制字符，0xA0-0xFF之间是文字符号。

此字符集支持部分于欧洲使用的语言，包括阿尔巴尼亚语、巴斯克语、布列塔尼语、加泰罗尼亚语、丹麦语、荷兰语、法罗语、弗里西语、加利西亚语、德语、格陵兰语、冰岛语、爱尔兰盖尔语、意大利语、拉丁语、卢森堡语、挪威语、葡萄牙语、里托罗曼斯语、苏格兰盖尔语、西班牙语及瑞典语。


```kotlin
package com.easy.springboot.demo1_add_servlet.servlet

import com.fasterxml.jackson.databind.ObjectMapper
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@WebServlet(urlPatterns = ["/hello"])
class HelloWorldServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        println("Invoke doGet: req.requestURI = ${req.requestURI}, resp.contentType = ${resp.contentType}")
        doPost(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        println("Invoke doPost ===> ")
        resp.contentType = "application/json;charset=UTF-8"
        val om = ObjectMapper()
        val resultJson = om.writeValueAsString(User("你好，World", "10000000001"))
        val out = resp.writer
        out.println(resultJson)
    }

    data class User(var username: String, var id: String)

}

```


![image.png](http://upload-images.jianshu.io/upload_images/1233356-76862b9e80d1f5b3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


后台日志：

2017-12-31 15:14:46.592  INFO 31698 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2017-12-31 15:14:46.598  INFO 31698 --- [           main] c.e.s.d.Demo1AddServletApplicationKt     : Started Demo1AddServletApplicationKt in 6.82 seconds (JVM running for 7.848)
Invoke doGet: req.requestURI = /hello, resp.contentType = null
Invoke doPost ===> 



如果不加注解 @ServletComponentScan

![image.png](http://upload-images.jianshu.io/upload_images/1233356-5ae15b591ccc7089.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


这个 Servlet 将不会被注册：

![image.png](http://upload-images.jianshu.io/upload_images/1233356-6954cbb357d3c9bb.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


 /** 采用 @Configuration + @Bean 注解的方式 */

```kotlin
package com.easy.springboot.demo1_add_servlet.servlet

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// 不使用 @WebServlet(urlPatterns = ["/what"])
class WhatServlet : HttpServlet() {

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        println("Invoke doGet: req.requestURI = ${req.requestURI}, resp.contentType = ${resp.contentType}")
        doPost(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        println("Invoke doPost ===> ")
        resp.contentType = "application/json;charset=UTF-8"
        val om = ObjectMapper()
        val resultJson = om.writeValueAsString(What("What Servlet?", "10000000002"))
        val out = resp.writer
        out.println(resultJson)
    }

    data class What(var name: String, var id: String)

}

/** 采用 @Configuration + @Bean 注解的方式 */
@Configuration
class ServletConfig {
    @Bean
    fun servletRegistrationBean(): ServletRegistrationBean<*> {
        return ServletRegistrationBean(WhatServlet(), "/what")
    }

}

```

![image.png](http://upload-images.jianshu.io/upload_images/1233356-d0ddbcf88d3ac9d9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)




