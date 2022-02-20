package com.sg.ocbc.repository;

import com.sg.ocbc.domain.Client;
import com.sg.ocbc.utility.RandomUtility;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientRepositoryTests {

    @Mock
    private ClientRepository repo;

    @Test
    public void testCreateUser() {
        Client client = Client.builder().id(RandomUtility.getUUID())
                .loggedIn(false)
                .email("sagar@gmail.com")
                .name("sagar")
                .password(RandomUtility.encodePassword("pass"))
                .build();
        Mockito.when(repo.save(client)).thenReturn(client);
        Client savedUser = repo.save(client);
        assertThat(savedUser.getEmail()).isEqualTo(client.getEmail());
    }

    @Test
    public void testCreateUserErrorNoId() {
        Client client = Client.builder()
                .loggedIn(false)
                .email("sagar@gmail.com")
                .name("sagar")
                .password(RandomUtility.encodePassword("pass"))
                .build();
        Mockito.when(repo.save(client))
                .thenThrow(new NullPointerException("Error occurred"));
    }
}