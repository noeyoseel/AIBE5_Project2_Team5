package com.example.pixel_project2.common.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PostDiversityOrderingTest {

    @Test
    void diversifyInterleavesItemsByKeyWhileKeepingBucketOrder() {
        List<PostStub> items = List.of(
                new PostStub(10L, 1L),
                new PostStub(9L, 1L),
                new PostStub(8L, 1L),
                new PostStub(7L, 2L),
                new PostStub(6L, 3L),
                new PostStub(5L, 2L)
        );

        List<PostStub> diversified = PostDiversityOrdering.diversify(items, PostStub::authorId);

        assertThat(diversified)
                .extracting(PostStub::id)
                .containsExactly(10L, 7L, 6L, 9L, 5L, 8L);
    }

    @Test
    void diversifyReturnsCopyForSmallLists() {
        List<PostStub> items = List.of(
                new PostStub(2L, 1L),
                new PostStub(1L, 1L)
        );

        List<PostStub> diversified = PostDiversityOrdering.diversify(items, PostStub::authorId);

        assertThat(diversified)
                .extracting(PostStub::id)
                .containsExactly(2L, 1L);
        assertThat(diversified).isNotSameAs(items);
    }

    private record PostStub(Long id, Long authorId) {
    }
}
