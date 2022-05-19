package ru.itis.readl.utils;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.itis.readl.dto.VkAccountDto;
import ru.itis.readl.dto.VkToken;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Component
public class VkUtils {

    @Value("${oauth2.vk.v}")
    private String vkApiVersion;

    @Value("${oauth2.vk.client.id}")
    private String vkClientId;

    @Value("${oauth2.vk.client.secret}")
    private String vkClientSecret;

    @Value("${oauth2.vk.redirect-url}")
    private String vkRedirectUrl;

    @Value("${oauth2.vk.api.user.url}")
    private String vkApiUserGetUrl;

    @Value("${oauth2.vk.api.token.url}")
    private String vkApiTokenUrl;

    @Value("${oauth2.vk.api.authorize.url}")
    private String vkAuthorizeUrl;

    public VkToken getAuthToken(String code){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("client_id", vkClientId);
        uriParameters.put("client_secret", vkClientSecret);
        uriParameters.put("redirect_uri", vkRedirectUrl);
        uriParameters.put("code", code);
        String uri = generateURI(vkApiTokenUrl, uriParameters);

        return restTemplate.getForObject(uri, VkToken.class);
    }

    public VkAccountDto getVkAccountDto(VkToken vkToken){
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("v", vkApiVersion);
        uriParameters.put("user_ids", vkToken.getUserId().toString());
        uriParameters.put("access_token", vkToken.getAccessToken());
        String uri = generateURI(vkApiUserGetUrl, uriParameters);

        return restTemplate.getForObject(uri, VkAccountDto.class);
    }

    public String getAuthorizeUrl(){
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("client_id", vkClientId);
        uriParameters.put("redirect_uri", vkRedirectUrl);
        uriParameters.put("scope", "email");
        uriParameters.put("display", "page");
        uriParameters.put("response_type", "code");
        uriParameters.put("v", vkApiVersion);

        return generateURI(vkAuthorizeUrl, uriParameters);
    }

    private static String generateURI(String host, Map<String, String> params) {
        StringBuilder resultURI = new StringBuilder(host);

        if (!params.isEmpty()) {
            resultURI.append("?");
        } else return resultURI.toString();

        for (Map.Entry<String, String> param: params.entrySet()) {
            resultURI.append(param.getKey())
                    .append("=")
                    .append(param.getValue())
                    .append("&");
        }

        return resultURI
                .deleteCharAt(resultURI.length() - 1)
                .toString();
    }
}
