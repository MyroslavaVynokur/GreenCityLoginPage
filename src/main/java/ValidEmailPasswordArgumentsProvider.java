import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

public class ValidEmailPasswordArgumentsProvider implements ArgumentsProvider {


    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
        return Stream.of(
                Arguments.of("mirka.vyno@gmail.com", "Test-User123")

        );

    }
}

 //   String actual = driver.getCurrentUrl();
//            String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/42";
//            Assert.assertEquals(actual, expected);
//ElementClickInterceptedException