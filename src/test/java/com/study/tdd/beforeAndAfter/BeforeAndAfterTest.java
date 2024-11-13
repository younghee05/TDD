package com.study.tdd.beforeAndAfter;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@Slf4j
public class BeforeAndAfterTest {

    @Mock // 임시로 사용할 가짜 객체? / 살제 객체를 생성하는게 아님
    private AddTest addTest;

    @BeforeEach
    void setAddTest() {
        log.info("Mock객체 생성");
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Each : 메소드가 실행될 때마다 무조건 한번 실행
     * All : 모든 테스트가 실행될 때 한번만
     * @BeforeEach
     * @AfterEach
     * @BeforeAll
     * @AfterAll
     */


    @BeforeEach
    void beforeEachTest() {
        log.info("beforeEachTest");
    }

    @AfterEach
    void afterEachTest() {
        log.info("afterEachTest");
    }

    @BeforeAll
    static void beforeAllEachTest() {
        log.info("beforeAllEachTest");
    }

    @AfterAll
    static void afterAllEachTest() {
        log.info("afterAllEachTest");
    }

    @Test
    void test1() {
        /*
        given
        when
        then

         * before() : setUp()
         * test1() : 테스트할 로직
         * after() : tearDown()
         *
         * before() : setUp() 메소드가 호출되고, test1() 메소드가 호출된다.
         * test1() : assertEquals() 메소드가 호출된다.
         * after() : tearDown() 메소드가 호출된다.
         *
         * setUp() : setUp() 메소드는 각 테스트 메소드 실행 전에 한 번만 호출된다.
         * tearDown() : tearDown() 메소드는 각 테스트 메소드 실행 후에 한 번만 호출된다.
         *
         * assertEquals() : assertEquals() 메소드는 assertEquals() 로직을 수행한다.
         *
         * log.info() : log4j 라이브러리를 이용한 로그 출력
         *
         * assertEquals() : assertEquals() 로직은 assertEquals
         */
        // given : 가정
        given(addTest.add()).willReturn(false); // 이 객체를 추가할건데, 이걸 하면 true를 리턴할거야! 무조건 true를 리턴하게 설계되더라도 여기서 false를 설정하면 이게 나옴
        //가능한 이유 - Mock객체기 때문에(가짜 객체)
        Boolean result = addTest.add();
        log.info("test1 메소드 실행!!!!");
        assertEquals(false, result);
    }

    @Test
    void test2() {
        log.info("테스트 2 실행!");
    }
}
