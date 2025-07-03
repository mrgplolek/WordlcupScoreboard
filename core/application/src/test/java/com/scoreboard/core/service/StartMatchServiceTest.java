package com.scoreboard.core.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.scoreboard.core.port.out.StartMatchPort;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartMatchServiceTest {
    @Mock
    private StartMatchPort startMatchPort;

    @InjectMocks
    private StartMatchService serviceUnderTest;

    @Test
    void shouldCallPortWithCorrectMatchData(){
        //given
        String matchData = "Germany-Brazil";
        //when
        serviceUnderTest.apply(matchData);
        //then
        verify(startMatchPort, times(1)).apply(matchData);
    }
}