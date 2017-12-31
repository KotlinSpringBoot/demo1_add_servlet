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
