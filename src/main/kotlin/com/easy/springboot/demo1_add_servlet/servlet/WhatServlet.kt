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





