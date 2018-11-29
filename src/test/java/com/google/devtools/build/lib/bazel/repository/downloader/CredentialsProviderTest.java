package com.google.devtools.build.lib.bazel.repository.downloader;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class CredentialsProviderTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private final CredentialsProvider credentialsProvider = new CredentialsProvider();

    @Test
    public void netrcFileAbsent_getCredentialsReturnsNull() throws Exception {
        String[] credentials = credentialsProvider.getCredentials("AnExistingHost");
        assertThat(credentials == null);
//        try (InputStreamReader reader =
//                     new InputStreamReader(
//                             new HashInputStream(
//                                     new ByteArrayInputStream("hello".getBytes(UTF_8)),
//                                     Hashing.sha1(),
//                                     HashCode.fromString("aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d")),
//                             UTF_8)) {
//            assertThat(CharStreams.toString(reader)).isEqualTo("hello");
//        }
    }

}