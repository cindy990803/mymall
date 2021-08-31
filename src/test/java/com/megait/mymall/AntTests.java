package com.megait.mymall;

import org.junit.jupiter.api.Test;
import org.springframework.util.AntPathMatcher;
import static org.assertj.core.api.Assertions.assertThat;  //static 메서드만 import해올 수 있음

public class AntTests {

    @Test
    public void antStylePatternTest() {

        //0818 수업
        //assertThat(checkAntPattern("/static/**", "/static/a.jpg")).isTrue();  //이걸로 바꿔서 테스트해보기
        //assertThat(true, is(checkAntPattern("/static/**", "/static/a.jpg")));


        assertThat(checkAntPattern("/static/*", "/static/a/test.jpg")).isFalse();

//        // double asterisks
//        assertThat(true, is(checkAntPattern("/static/**", "/static/css/a.css")));
//        assertThat(true, is(checkAntPattern("/static/**", "/static/js/a.js")));
//        assertThat(true, is(checkAntPattern("/static/**", "/static/img/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static/**", "/static/a/b/c/d/e/f/g/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static/**", "/static")));
//        assertThat(true, is(checkAntPattern("/static/**", "/static/")));
//
//        // single asterisks
//        assertThat(true, is(checkAntPattern("/static/*", "/static/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static/*", "/static/namkyuProfilePicture.jpg")));
//
//        assertThat(false, is(checkAntPattern("/static/*", "/static/a/test.jpg")));
//        assertThat(false, is(checkAntPattern("/static/*", "/static/a/b/c/d/test.jpg")));
//
//        assertThat(true, is(checkAntPattern("/static*/*", "/static/test.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/*", "/static1/test.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/*", "/static123/test.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/*", "/static-123/test.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/*", "/static~!@#$%^&*()_+}{|/test.jpg")));
//
//        assertThat(false, is(checkAntPattern("/static*/*", "/static12/a/test.jpg")));
//        assertThat(false, is(checkAntPattern("/static*/*", "/static12/a/b/test.jpg")));
//
//        // double and single combine
//        assertThat(true, is(checkAntPattern("/static*/**", "/static/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/**", "/static1/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/**", "/static/a/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/**", "/static/a/b/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static*/**", "/static/a/b/c/a.jpg")));
//
//        assertThat(true, is(checkAntPattern("**/static/**", "a/static/a/b/c/a.jpg")));
//        assertThat(true, is(checkAntPattern("**/static/**", "a/b/static/a/b/c/a.jpg")));
//
//
//        // question-mark
//        assertThat(true, is(checkAntPattern("/static-?/**", "/static-a/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static-?/**", "/static-a/b/c/a.jpg")));
//        assertThat(true, is(checkAntPattern("/static-?/*", "/static-a/abcd.jpg")));
//        assertThat(true, is(checkAntPattern("/static-?/???.jpg", "/static-a/abc.jpg")));


    }


    private boolean checkAntPattern(String pattern, String inputStr) {
        return matches(pattern, inputStr);
    }

    public static boolean matches(String pattern, String inputStr) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        return antPathMatcher.match(pattern, inputStr);
    }
}
