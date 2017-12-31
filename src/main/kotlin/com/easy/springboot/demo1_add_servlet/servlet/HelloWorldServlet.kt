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





