package com.yasharhnd.github_crawler.github_graphql_client.client;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationData {

    private final String cursor;
    private final Integer perPage;
    private final ListRequestSide side;

    public String before() {
        return Objects.equals(side, ListRequestSide.BACKWARD) ? cursor : null;
    }

    public String after() {
        return Objects.equals(side, ListRequestSide.FORWARD) ? cursor : null;
    }

    public Integer last() {
        return Objects.equals(side, ListRequestSide.BACKWARD) ? perPage : null;
    }

    public Integer first() {
        return Objects.equals(side, ListRequestSide.FORWARD) ? perPage : null;
    }

}
