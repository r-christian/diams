package com.christian.diams.controller.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IAuthorize {
    boolean isAuthorized(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
