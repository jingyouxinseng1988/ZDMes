//package com.plc.platform.filter;
//
//import com.alibaba.fastjson.JSON;
//import Constants;
//import com.plc.platform.compo.RedisCompo;
//import AjaxResult;
//import AjaxResultEnum;
//import SpringUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.nio.charset.StandardCharsets;
//import java.util.LinkedList;
//import java.util.List;
//
//public class TokenFilter implements Filter {
//
//    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class);
//
//    protected static List<String> skipUrls = new LinkedList<>();
//
//    private RedisCompo redisCompo;
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse rep = (HttpServletResponse) response;
//
//        // 设置允许跨域的配置
//        // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
//        rep.setHeader("Access-Control-Allow-Origin", "*");
//        // 允许的访问方法
//        rep.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
//        // Access-Control-Max-Age 用于 CORS 相关配置的缓存
//        rep.setHeader("Access-Control-Max-Age", "3600");
//        rep.setHeader("Access-Control-Allow-Headers", "token,Origin, X-Requested-With, Content-Type, Accept");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json; charset=utf-8");
//        String token = req.getParameter("accessToken");
//        AjaxResult result = null;
//        String url = req.getRequestURI().substring(req.getContextPath().length());
//        if (isInclude(url)) {
//            chain.doFilter(req, rep);
//            return;
//        }
//        if (null == token || token.isEmpty()) {
//            result = new AjaxResult(AjaxResultEnum.PARAMETERS_ERROR_NO_TOKEN, null);
//        } else {
//            Object value = redisCompo.getKey(token);
//            if (value == null) {
//                result = new AjaxResult(AjaxResultEnum.PARAMETERS_ERROR_NO_TOKEN.getCode(), "accessToken失效", null);
//            } else {
//                User user = JSON.parseObject(value.toString(), User.class);
//                req.setAttribute(Constants.USER, user);
//                chain.doFilter(req, rep);
//                return;
//            }
//        }
//
//        PrintWriter writer = null;
//        OutputStreamWriter osw = null;
//        try {
//            osw = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8);
//            writer = new PrintWriter(osw, true);
//            String jsonStr = JSON.toJSONString(result);
//            writer.write(jsonStr);
//            writer.flush();
//            writer.close();
//            osw.close();
//        } catch (UnsupportedEncodingException e) {
//            logger.error("过滤器返回信息失败:" + e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error("过滤器返回信息失败:" + e.getMessage(), e);
//        } finally {
//            if (null != writer) {
//                writer.close();
//            }
//            if (null != osw) {
//                osw.close();
//            }
//        }
//        return;
//    }
//
//    private boolean isInclude(String url) {
//        for (String str : skipUrls) {
//            if (url.startsWith(str)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // TODO Auto-generated method stub
//        skipUrls.add("/api/v1/user/login");
//        skipUrls.add("/api/v1/user/register");
//
//        ServletContext servletContext = filterConfig.getServletContext();
//        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
//        SpringUtil.setApplicationContext(ctx);
//        redisCompo = SpringUtil.getBean(RedisCompo.class);
//    }
//
//}
