package io.danpalmer101.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "filter.addheaders")
public class AddHeadersFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private Map<String, String> values;

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * only filter requests to a certain path.
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        for (Map.Entry<String, String> entry : this.values.entrySet()) {
            LOGGER.debug("Adding header: '{}: {}'", entry.getKey(), entry.getValue());

            RequestContext.getCurrentContext().addZuulRequestHeader(entry.getKey(), entry.getValue());
        }

        return null;
    }

}
