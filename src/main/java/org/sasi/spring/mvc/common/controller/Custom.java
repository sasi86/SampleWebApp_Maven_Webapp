package org.sasi.spring.mvc.common.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chooseCity")
@Retention(RetentionPolicy.RUNTIME)
public @interface Custom {

}
