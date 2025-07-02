package port.in;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import port.out.StartMatchPort;
import org.mockito.junit.jupiter.MockitoExtension;
import service.StartMatchService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StartMatchUseCaseTest {

    @Mock
    private StartMatchPort startMatchPort;

    private StartMatchUseCase useCaseUnderTest;

    @BeforeEach
    void setUp() {
        useCaseUnderTest = new StartMatchService(startMatchPort);
    }
    @Test
    void shouldCallPortWithCorrectMatchData(){
        //given
        String matchData = "Germany-Brazil";
        //when
        useCaseUnderTest.apply(matchData);
        //then
        verify(startMatchPort, times(1)).apply(matchData);
    }

}