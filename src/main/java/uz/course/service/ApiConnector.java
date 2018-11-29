package uz.course.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.reflect.misc.FieldUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApiConnector {

    public static Builder newBuilder(Class<?> responseType) {
        return new ApiConnector().new Builder(responseType);
    }

    public class Builder<T> {
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        private String url;
        private Map params = new ConcurrentHashMap();
        private ResponseEntity<T> result;
        private Class<T> responseType;

        public Builder(Class<T> responseType) {
            this.responseType = responseType;
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//            MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//            mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM,MediaType.ALL));
//            restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);


            /*List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
            //Add the Jackson Message converter
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            // Note: here we are making this converter to process any kind of response,
            // not only application/*json, which is the default behaviour
            converter.setSupportedMediaTypes(Arrays.asList(new MediaType[]{MediaType.ALL}));
            messageConverters.add(converter);
            restTemplate.setMessageConverters(messageConverters);*/
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder clearParams() {
            params.clear();
            return this;
        }

        public Builder addParam(String key, Object value) {
            this.params.put(key, value);
            return this;
        }

        public Builder addHeader(String key, String value) {
            headers.add(key, value);
            return this;
        }

        public Builder post() {
            result = restTemplate.postForEntity(url, params, responseType);
            return this;
        }

        public Builder post(Object data) {
//            result = restTemplate.postForEntity(url, data, responseType);
            Map<String, Object> params = new HashMap<>();
            if (data != null)
                for (Field field : FieldUtil.getDeclaredFields(data.getClass())) {
                    field.setAccessible(true);
                    try {
                        params.put(field.getName(), field.get(data));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            HttpEntity<Map> request = new HttpEntity<Map>(params, headers);
            result = restTemplate.exchange(url, HttpMethod.POST, request, responseType);
            return this;
        }

        public Builder exchange(HttpMethod httpMethod) {
            HttpEntity<Map> request = new HttpEntity<Map>(params, headers);
            result = restTemplate.exchange(url, httpMethod, request, responseType);
            return this;
        }

        public Builder get() {
            result = restTemplate.getForEntity(url, responseType, params);
            return this;
        }

        public T build() {
            return result.getBody();
        }
    }
}