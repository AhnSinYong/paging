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

        if (StringUtils.hasText(itemSize)) {
            param.setPageItemSize(Integer.parseInt(itemSize));
        }

        if(StringUtils.hasText(pageNumber)){
            param.setPageNumber(Integer.parseInt(pageNumber));
        }

        if(StringUtils.hasText(naviSize)){
            param.setNavigationSize(Integer.parseInt(naviSize));
        }

        return param;
    }
}
