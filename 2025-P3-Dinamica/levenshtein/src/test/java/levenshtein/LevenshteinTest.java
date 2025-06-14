package levenshtein;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author vale
 *
 */
public class LevenshteinTest {
  private static Stream<Arguments> levenshteinArgsProviderDivide() {
    return Stream.of(
        Arguments.of("abc", "abc", 0),
        Arguments.of("abc", "ab", 1),
        Arguments.of("cat", "cut", 1),
        Arguments.of("kitten", "sitting", 3),
        Arguments.of("hola", "bola", 1),
        Arguments.of("aser", "hacer", 2));
  }

  private static Stream<Arguments> levenshteinArgsProvider() {
    return Stream.of(
        Arguments.of("abc", "abc", 0),
        Arguments.of("abc", "ab", 1),
        Arguments.of("cat", "cut", 1),
        Arguments.of("kitten", "sitting", 3),
        Arguments.of("hola", "bola", 1),
        Arguments.of("aser", "hacer", 2),
        Arguments.of("hola como estas, que lindo dia", "chau como andas, que dia feo", 14));
  }

  @ParameterizedTest(name = "{index}: levenshteinDistanceDC({0}, {1}) = {2}")
  @MethodSource("levenshteinArgsProviderDivide")
  void levenshteinDCTest(String str1, String str2, int expected) {
    int result = Levenshtein.computeLevenshteinDistance(str1, str2);
    assertEquals(expected, result);
  }

  @ParameterizedTest(name = "{index}: levenshteinDistancePD({0}, {1}) = {2}")
  @MethodSource("levenshteinArgsProvider")
  void levenshteinPDTest(String str1, String str2, int expected) {
    int result = Levenshtein.computeLevenshteinDistancePD(str1, str2);
    assertEquals(expected, result);
  }

  @ParameterizedTest(name = "{index}: levenshteinDistanceMemo({0}, {1}) = {2}")
  @MethodSource("levenshteinArgsProvider")
  void levenshteinMemoTest(String str1, String str2, int expected) {
    int result = Levenshtein.computeLevenshteinDistanceMemo(str1, str2);
    assertEquals(expected, result);
  }
}
