package framewise.page;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author chanwook
 */
public class PagingParameterHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return PagingParam.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        PagingParam param = new PagingParam();
        String itemSize = webRequest.getParameter("_pageItemSize");
        String pageNumber = webRequest.getParameter("_pageNumber");
        String naviSize = webRequest.getParameter("_navigationSize");
        String type = webRequest.getParameter("_pagingType");
        String[] vars = webRequest.getParameterValues("_var");

        setPageItemSize(param, itemSize);

        setPageNumber(param, pageNumber);

        setNavigationSize(param, naviSize);

        setPageType(param, type);

        setTemplateVariable(param, vars);

        return param;
    }

    protected void setTemplateVariable(PagingParam param, String[] vars) {
        if (vars != null && vars.length > 0) {
            for (String var : vars) {
                // key:value
                String[] v = var.split(":");
                param.addTemplateVariable(v[0], v[1]);
            }
        }
    }

    protected void setPageType(PagingParam param, String type) {
        if (StringUtils.hasText(type)) { // N=NUMBER, S=SCROLLING, FB=FRONT&BACK
            param.setType(PagingType.get(type));
        }
    }

    protected void setNavigationSize(PagingParam param, String naviSize) {
        if (StringUtils.hasText(naviSize)) {
            param.setNavigationSize(Integer.parseInt(naviSize));
        }
    }

    protected void setPageNumber(PagingParam param, String pageNumber) {
        if (StringUtils.hasText(pageNumber)) {
            param.setPageNumber(Integer.parseInt(pageNumber));
        }
    }

    protected void setPageItemSize(PagingParam param, String itemSize) {
        if (StringUtils.hasText(itemSize)) {
            param.setPageItemSize(Integer.parseInt(itemSize));
        }
    }
}
