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
        assertThat(credentials).isNull();
    }

}