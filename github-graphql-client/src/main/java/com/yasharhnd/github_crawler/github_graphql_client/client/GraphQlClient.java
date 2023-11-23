package com.yasharhnd.github_crawler.github_graphql_client.client;

import com.apollographql.apollo3.ApolloClient;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Error;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.exception.ApolloException;
import com.apollographql.apollo3.exception.ApolloHttpException;
import com.apollographql.apollo3.rx3.Rx3Apollo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GraphQlClient {

    private static final String BASE_API_URL = "https://api.github.com/graphql";

    private final ApolloClient client;

    public GraphQlClient(@Value("${github.token}") final String githubToken) {
        client = new ApolloClient.Builder()
            .serverUrl(BASE_API_URL)
            .addHttpHeader("Authorization", String.format("Bearer %s", githubToken)).build();
    }

    public <D extends Query.Data> D query(final Query<D> query) throws GitHubException {
        try {
            final var response = Rx3Apollo.single(client.query(query)).blockingGet();
            if (response.hasErrors()) {
                throw throwableBasedOnErrors(response);
            }
            return response.dataOrThrow();
        } catch (final ApolloException e) {
            throw throwableBasedOnException(e);
        }
    }

    public PaginationData pageInfo(final Integer perPage, final ListRequestSide side, final String cursor) {
        return PaginationData.builder().cursor(cursor).perPage(perPage).side(side).build();
    }

    private GitHubException throwableBasedOnErrors(final ApolloResponse<?> response) {
        if (CollectionUtils.isEmpty(response.errors)) {
            return GitHubException.build500("unknown GitHub GraphQL response error");
        }
        final var is403 = response.errors.stream().anyMatch(error ->
            Objects.nonNull(error.getPath()) && Objects.equals(error.getPath().size(), 1) && error.getPath().contains("repository")
                && MapUtils.isNotEmpty(error.getNonStandardFields()) && error.getNonStandardFields().containsKey("type")
                && Objects.equals(error.getNonStandardFields().get("type"), "NOT_FOUND"));
        return is403 ? GitHubException.throwable403() : GitHubException.build500(response.errors.stream().map(Error::getMessage).collect(Collectors.joining("\n")));
    }

    private GitHubException throwableBasedOnException(final ApolloException e) {
        if (e instanceof final ApolloHttpException httpException) {
            if (Objects.equals(httpException.getStatusCode(), 401)) {
                return GitHubException.throwable401();
            }
            if (Objects.equals(httpException.getStatusCode(), 403)) {
                return GitHubException.throwable403();
            }
        }
        return GitHubException.build500(e.getMessage());
    }

}
