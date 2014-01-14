package framewise.page;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author chanwook
 */
public class PagingParameterHandlerMethodArgumentResolverTests {

    @Test
    public void sendTemplateVar() {
        PagingParameterHandlerMethodArgumentResolver r = new PagingParameterHandlerMethodArgumentResolver();
        PagingParam param = new PagingParam();
        r.setTemplateVariable(param, new String[]{"key1:value1", "key2:value2"});
        Map<String, String> var = param.getTemplateVariableMap();


        assertThat(var.get("key1"), is("value1"));
        assertThat(var.get("key2"), is("value2"));
    }
}
