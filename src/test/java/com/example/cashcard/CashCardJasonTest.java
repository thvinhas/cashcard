package com.example.cashcard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class CashCardJasonTest {

@Autowired
private JacksonTester<CashCard> json;

@Test
    void casCardSerializationTest() throws IOException {
    CashCard cashCard = new CashCard (99L, 123.45);
    assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
    assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
    assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id").isEqualTo(99);
    assertThat(json.write(cashCard)).hasJsonPathValue("@.amount");
    assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount").isEqualTo(123.45);
}

@Test
    void cashCardDeserializationTest() throws IOException {
    String expected = """
              {
                           "id":99,
                           "amount":123.45
                       }
            """;
    assertThat(json.parse(expected)).isEqualTo(new CashCard(99L, 123.45));
    assertThat(json.parseObject(expected).id()).isEqualTo(99);
    assertThat(json.parseObject(expected).amount()).isEqualTo(123.45);
}


}
