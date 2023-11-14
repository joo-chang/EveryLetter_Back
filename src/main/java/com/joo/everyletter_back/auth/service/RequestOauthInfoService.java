package com.joo.everyletter_back.auth.service;

import com.joo.everyletter_back.auth.oauth.OauthClient;
import com.joo.everyletter_back.auth.oauth.OauthMember;
import com.joo.everyletter_back.auth.oauth.OauthParams;
import com.joo.everyletter_back.auth.oauth.OauthProvider;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RequestOauthInfoService {
    //Enum = 키, Client = 값으로 저장하는 Map 생성
    private final Map<OauthProvider, OauthClient> clients;

    //생성과 동시에 클라이언트 주입
    public RequestOauthInfoService(List<OauthClient> clients) {
        this.clients = clients.stream().collect(
                Collectors.toUnmodifiableMap(OauthClient::oauthProvider, Function.identity()));
    }

    //넘겨받은 params의 enum 클래스와 동일한 객체를 주입
    public OauthMember request(OauthParams oauthParams) {
        OauthClient client = clients.get(oauthParams.oauthProvider());
        String accessToken = client.getOauthLoginToken(oauthParams);

        return client.getMemberInfo(accessToken);
    }
}
