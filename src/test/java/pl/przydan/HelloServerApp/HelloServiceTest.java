package pl.przydan.HelloServerApp;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class HelloServiceTest {

    private static String WELCOME = "Hello";
    private static String FALLBACK_LANG = "Hola";

    @Test
    public void test_null_prepareGreeting_nullName_returnsGreetingWithFallbackName() throws Exception {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        String expected = WELCOME + " " + HelloService.FALLBACK_NAME + "!";
        //when
        String name = null;
        var result = SUT.prepareGreeting(name, "-1");
        //then
        assertEquals(expected, result);
    }

    @Test
    public void test_null_prepareGreeting_nullLang_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        LangRepository mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        String expected = FALLBACK_LANG + " " + HelloService.FALLBACK_NAME + "!";
        //when
        String name = null;
        var result = SUT.prepareGreeting(name, null);
        //then
        assertEquals(expected, result);
    }

    @Test
    public void test_null_prepareGreeting_textLang_returnsGreetingWithFallbackIdLang() throws Exception {
        //given
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        String expected = FALLBACK_LANG + " " + HelloService.FALLBACK_NAME + "!";
        //when
        String name = null;
        var result = SUT.prepareGreeting(name, "abc");
        //then
        assertEquals(expected, result);
    }

    @Test
    public void test_name_prepareGreeting_name_returnsGreetingWithName() throws Exception {
        //given
        var mockRepository = alwaysReturningHelloRepository();
        var SUT = new HelloService(mockRepository);
        String name = "test";
        String expected = WELCOME + " " + name + "!";
        //when
        var result = SUT.prepareGreeting(name, "-1");
        //then
        assertEquals(expected, result);
    }

    @Test
    public void test_prepareGreeting_nonExistingLang_returnsGreetingWithFallbackLang() {
        // given
        LangRepository mockRepository = AlwaysEmptyLangRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "-1");
        // then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    private LangRepository AlwaysEmptyLangRepository() {
        return new LangRepository() {
                @Override
                Optional<Lang> findById(Integer id) {
                    return Optional.empty();
                }
            };
    }


    private LangRepository alwaysReturningHelloRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

    private LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_LANG, null));
                }
                return Optional.empty();
            }
        };
    }
}
